package com.shiv.example.springdataexample.jdbc;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AlbumDaoImplTest {

	@Autowired
	private AlbumDaoImpl albumDao;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testFindAll() {
		List<Album> albums = albumDao.findAll();
		assertTrue(albums != null && albums.size() > 0);
	}

	@Test
	public void testFindById() {
		assertNotNull(albumDao.findById(1));
	}

	@Test
	public void testFindByTitle() {
		assertNotNull(albumDao.findByTitle("The Search For Everything"));
	}

	@Test
	public void testInsert() {
		Album album = new Album();
		album.setTitle("Dangerous");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 1950);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DATE, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		java.sql.Date jsqlD = new java.sql.Date(cal.getTime().getTime());
		album.setReleaseDate(jsqlD);
		album.setSingerId(1);
		assertTrue(albumDao.insert(album) > 0);
	}

	@Test
	public void testUpdate() {
		Album album = albumDao.findByTitle("The Search For Everything");
		album.setTitle("The Search For Everything Good");
		assertTrue(albumDao.update(album) > 0);
	}

	@Test
	public void testDelete() {
		Album album = albumDao.findByTitle("Battle Studies");
		assertTrue(albumDao.delete(album) > 0);
	}

	@Test
	public void testFindAllWithSingers() {
		List<Album> albums = albumDao.findAllWithSingers();
		Album selectedAlbum = null;
		for (Album album : albums) {
			if (album.getTitle().equalsIgnoreCase("From The Cradle")) {
				selectedAlbum = album;
			}
		}
		assertTrue(selectedAlbum != null && selectedAlbum.getSinger().getFirstName().equalsIgnoreCase("Eric"));
	}

}
