package com.shiv.example.springdataexample.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/*
 * @Repository makes the bean auto-discoverable by spring
 * and also enables Exception override for database 
 * specific errors
 */
@Repository
public class AlbumDaoImpl implements AlbumDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	
	@Override
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
		jdbc = jdbcTemplate;
	}
	
	@Override
	public List<Album> findAll() {
		return jdbc.query("SELECT ID, TITLE, RELEASE_DATE,SINGER_ID FROM ALBUM", new AlbumRowMapper());
	}

	@SuppressWarnings("serial")
	@Override
	public Album findById(long id) {
		return jdbc.queryForObject("SELECT ID, TITLE, RELEASE_DATE,SINGER_ID FROM ALBUM WHERE ID = :albumId", new HashMap<String,Object>() {{put("albumId",id);}}, new AlbumRowMapper());
	}

	@SuppressWarnings("serial")
	@Override
	public Album findByTitle(String title) {
		return jdbc.queryForObject("SELECT ID, TITLE, RELEASE_DATE,SINGER_ID FROM ALBUM WHERE TITLE = :title", new HashMap<String,Object>() {{put("title",title);}}, new AlbumRowMapper());
	}

	@SuppressWarnings("serial")
	@Override
	public int insert(Album album) {
		return jdbc.update("INSERT INTO ALBUM(TITLE,RELEASE_DATE,SINGER_ID) VALUES(:title,:releaseDate,:singerId)", new HashMap<String,Object>() {{put("title",album.getTitle());put("releaseDate",album.getReleaseDate());put("singerId",album.getSingerId());}});
	}

	@SuppressWarnings("serial")
	@Override
	public int update(Album album) {
		return jdbc.update("UPDATE ALBUM SET title=:titleP, release_date = :releaseDateP, singer_id=:singerIdP WHERE id=:idP", new HashMap<String,Object>() {{put("titleP",album.getTitle());put("releaseDateP",album.getReleaseDate());put("singerIdP",album.getSingerId());put("idP",album.getId());}});
	}

	@SuppressWarnings("serial")
	@Override
	public int delete(Album album) {
		return jdbc.update("DELETE FROM ALBUM WHERE id=:idP", new HashMap<String,Object>() {{put("idP",album.getId());}});
	}

	@Override
	public List<Album> findAllWithSingers() {
		String sql = "select s.id as singer_id, s.first_name as first_name, s.last_name as last_name, s.birth_date as birth_date," +
				"a.id as album_id, a.title as title, a.release_date as release_date from album a " +
				"left join singer s on a.singer_id = s.id";
		return jdbc.query(sql, new AlbumResultSetExtractor());
		
	}
	
	private static final class AlbumRowMapper implements RowMapper<Album>{

		@Override
		public Album mapRow(ResultSet rs, int rowNum) throws SQLException {
			Album album = new Album();
			album.setId(rs.getLong("id"));
			album.setTitle(rs.getString("title"));
			album.setReleaseDate(rs.getDate("release_date"));
			album.setSingerId(rs.getLong("singer_id"));
			return album;
		}
		
	}
	
	private static final class AlbumResultSetExtractor implements ResultSetExtractor<List<Album>>{

		@Override
		public List<Album> extractData(ResultSet rs) throws SQLException, DataAccessException {
			Map<Long, Album> map = new HashMap<>();
			Album album;
			while(rs.next()) {
				Long id = rs.getLong("album_id");
				album = map.get(id);
				if(album == null) {
					album = new Album();
					album.setId(id);
					album.setTitle(rs.getString("title"));
					album.setReleaseDate(rs.getDate("release_date"));
					album.setSingerId(rs.getLong("singer_id"));
					map.put(id, album);
				}
				Long singer_id = rs.getLong("singer_id");
				if(singer_id>0) {
					Singer singer = new Singer();
					singer.setFirstName(rs.getString("first_name"));
					singer.setLastName(rs.getString("last_name"));
					singer.setBirthDate(rs.getDate("birth_date"));
					album.setSinger(singer);
				}
			}
			return new ArrayList<>(map.values());
		}
		
	}

}
