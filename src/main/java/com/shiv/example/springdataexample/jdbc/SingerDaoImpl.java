package com.shiv.example.springdataexample.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public class SingerDaoImpl implements SingerDao {

	private DataSource ds;
	private JdbcTemplate jdbc;

	@Override
	public void setDataSource(DataSource ds) {
		this.ds = ds;
		jdbc = new JdbcTemplate(ds);
	}
	
	@Override
	public DataSource getDataSource() {
		return ds;
	}

	@Override
	public List<Singer> findAll() {
		return jdbc.query("SELECT ID, FIRST_NAME, LAST_NAME,BIRTH_DATE FROM SINGER", new SingerMapper());
	}
	
	@Override
	public Singer findById(long id) {
		return jdbc.queryForObject("SELECT ID, FIRST_NAME, LAST_NAME,BIRTH_DATE FROM SINGER WHERE ID=?",new Object[] {id}, new SingerMapper());
	}

	@Override
	public List<Singer> findByFirstName(String firstName) {
		return jdbc.query("SELECT ID, FIRST_NAME, LAST_NAME,BIRTH_DATE FROM SINGER WHERE FIRST_NAME=?",new Object[] {firstName}, new SingerMapper());
	}

	@Override
	public List<Singer> findByLastName(String lastName) {
		return jdbc.query("SELECT ID, FIRST_NAME, LAST_NAME,BIRTH_DATE FROM SINGER WHERE LAST_NAME=?",new Object[] {lastName}, new SingerMapper());
	}

	@Override
	public int insert(Singer singer) {
		return jdbc.update("insert into singer (first_name, last_name, birth_date) values (?, ?, ?)", new Object[] {singer.getFirstName(),singer.getLastName(),singer.getBirthDate()});
	}

	@Override
	public int update(Singer singer) {
		return jdbc.update("update singer set first_name = ?, last_name=?, birth_date=? where id=?", new Object[] {singer.getFirstName(),singer.getLastName(),singer.getBirthDate(), singer.getId()});
	}

	@Override
	public int delete(Singer singer) {
		return jdbc.update("delete from  singer where id=?", new Object[] {singer.getId()});
	}
	
	@Override
	public List<Singer> findAllWithAlbums(){
		String sql = "select s.id as id, s.first_name as first_name, s.last_name as last_name, s.birth_date as birth_date," +
				"a.id as album_id, a.title as title, a.release_date as release_date from singer s " +
				"left join album a on s.id = a.singer_id";
				return jdbc.query(sql, new SingerWithDetailExtractor());
	}
	
	private static final class SingerWithDetailExtractor implements ResultSetExtractor<List<Singer>>{
		@Override
		public List<Singer> extractData(ResultSet rs) throws SQLException, DataAccessException {
			Map<Long, Singer> map = new HashMap<>();
			Singer singer;
			while(rs.next()) {
				Long id = rs.getLong("id");
				singer = map.get(id);
				if(singer == null) {
					singer = new Singer();
					singer.setId(id);
					singer.setFirstName(rs.getString("first_name"));
					singer.setLastName(rs.getString("last_name"));
					singer.setBirthDate(rs.getDate("birth_date"));
					map.put(id, singer);
				}
				Long album_id = rs.getLong("album_id");
				if(album_id>0) {
					Album album = new Album();
					album.setId(album_id);
					album.setTitle(rs.getString("title"));
					album.setReleaseDate(rs.getDate("release_date"));
					album.setSingerId(singer.getId());
					singer.addAlbum(album);
				}
			}
			return new ArrayList<>(map.values());
		}
		
	}

	private static final class SingerMapper implements RowMapper<Singer> {
		@Override
		public Singer mapRow(ResultSet rs, int rowNum) throws SQLException {
			Singer singer = new Singer();
			singer.setId(rs.getLong("id"));
			singer.setFirstName(rs.getString("first_name"));
			singer.setLastName(rs.getString("last_name"));
			singer.setBirthDate(rs.getDate("birth_date"));
			return singer;
		}
	}

	

}
