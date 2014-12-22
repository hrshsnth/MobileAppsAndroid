package com.example.hw8;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
/**
 * HW8
 * DatabaseHelper.java
 * @author HARISHSAINATH GANAPATHY(800833319) & Dayabaran Gangatharan(800823490) 
 *
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	private static final String DATABASE_NAME="Places.db";
	private static final int DB_VERSION=1;
	
	private Dao<Places, String> newsdao =null;
	private RuntimeExceptionDao<Places, String> runtimedao=null;
	
	public DatabaseHelper(Context context) {
		
		super(context,DATABASE_NAME, null, DB_VERSION, R.raw.ormlite_config);
	}

	@Override
	public void onCreate(SQLiteDatabase arg0, ConnectionSource arg1) {
		// TODO Auto-generated method stub
		try {
			TableUtils.createTable(connectionSource, Places.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase arg0, ConnectionSource arg1, int arg2,
			int arg3) {
		try {
			TableUtils.dropTable(connectionSource, Places.class, true);
			onCreate(arg0,arg1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Dao<Places, String> getDao() throws SQLException
	{
		if(newsdao==null)
		{
			newsdao=getDao(Places.class);
		}
		return newsdao;
	}
	
	public RuntimeExceptionDao<Places, String> getRuntimeDao() throws SQLException
	{
		if(runtimedao==null)
		{
			runtimedao=getRuntimeExceptionDao(Places.class);			
		}
		return runtimedao;
	}
	
}
