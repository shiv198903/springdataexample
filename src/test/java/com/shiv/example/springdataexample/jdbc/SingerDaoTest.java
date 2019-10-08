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

@SpringBootTest(classes = DBConfiguration.class)
@RunWith(SpringRunner.class)
public class SingerDaoTest {

	@Autowired
	private SingerDao singerDao;

	@Before
	public void setUp() throws Exception {
		assertNotNull(singerDao);
	}

	@Test
	public void testSetDataSource() {
		assertNotNull("DataSource should be set in SingerDaoImpl", singerDao.getDataSource());
	}

	@Test
	public void testFindAll() {
		List<Singer> singerList = singerDao.findAll();
		assertTrue("FindAll should return a list of Singers", singerList != null && singerList.size() > 0);
	}

	@Test
	public void testFindById() {
		assertNotNull("FindById should return a valid Singer", singerDao.findById(1));
	}

	@Test
	public void testFindByFirstName() {
		List<Singer> singerList = singerDao.findByFirstName("John");
		assertTrue("FindByFirstName should return a list of Singers", singerList != null && singerList.size() == 2);
	}

	@Test
	public void testFindByLastName() {
		List<Singer> singerList = singerDao.findByLastName("Mayer");
		assertTrue("FindByLastName should return a list of Singers", singerList != null && singerList.size() == 1);
	}

	@Test
	public void testInsert() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 1950);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DATE, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		java.sql.Date jsqlD = new java.sql.Date(cal.getTime().getTime());
		Singer singer = new Singer();
		singer.setFirstName("Micheal");
		singer.setLastName("Jackson");
		singer.setBirthDate(jsqlD);
		assertTrue(singerDao.insert(singer) == 1);
	}

	@Test
	public void testUpdate() {
		List<Singer> singerList = singerDao.findByFirstName("Micheal");
		Singer singer = singerList.get(0);
		singer.setFirstName("Michael");
		assertTrue(singerDao.update(singer) == 1);
	}

	@Test
	public void testDelete() {
		List<Singer> singerList = singerDao.findByFirstName("Eric");
		Singer singer = singerList.get(0);
		assertTrue(singerDao.delete(singer) == 1);
	}
	
	@Test
	public void testFindAllWithAlbums() {
		Singer selectedSinger = null;
		
		List<Singer> singerList = singerDao.findAllWithAlbums();
		for(Singer singer : singerList) {
			if(singer.getLastName().equalsIgnoreCase("Mayer")) {
				selectedSinger = singer;
			}
		}
		assertTrue(selectedSinger != null && selectedSinger.getAlbums().get(0).getTitle().equalsIgnoreCase("the search for everything"));
	}

}
