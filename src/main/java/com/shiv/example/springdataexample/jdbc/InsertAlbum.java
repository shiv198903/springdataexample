package com.shiv.example.springdataexample.jdbc;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class InsertAlbum extends SqlUpdate {
	private static final String SQL_INSERT_ALBUM = "INSERT INTO ALBUM(TITLE,RELEASE_DATE,SINGER_ID) VALUES(:title,:releaseDate,:singerId)";

	public InsertAlbum(DataSource dataSource) {
		super(dataSource, SQL_INSERT_ALBUM);
		super.declareParameter(new SqlParameter("title", Types.VARCHAR));
		super.declareParameter(new SqlParameter("releaseDate", Types.DATE));
		super.declareParameter(new SqlParameter("singerId", Types.LONGVARCHAR));
		super.setGeneratedKeysColumnNames("id");
		super.setReturnGeneratedKeys(true);
	}

}
