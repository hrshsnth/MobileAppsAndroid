package com.example.hw7;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
/**
 * HW7
 * DatabaseHelper.java
 * @author HARISHSAINATH GANAPATHY(800833319) & Dayabaran Gangatharan(800823490) 
 *
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	private static final String DATABASE_NAME="Search.db";
	private static final int DB_VERSION=1;
	
	private Dao<Search, Integer> newsdao =null;
	private RuntimeExceptionDao<Search, Integer> runtimedao=null;
	
	public DatabaseHelper(Context context) {
		
		super(context,DATABASE_NAME, null, DB_VERSION, R.raw.ormlite_config);
	}

	@Override
	public void onCreate(SQLiteDatabase arg0, ConnectionSource arg1) {
		// TODO Auto-generated method stub
		try {
			TableUtils.createTable(connectionSource, Search.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase arg0, ConnectionSource arg1, int arg2,
			int arg3) {
		try {
			TableUtils.dropTable(connectionSource, Search.class, true);
			onCreate(arg0,arg1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Dao<Search, Integer> getDao() throws SQLException
	{
		if(newsdao==null)
		{
			newsdao=getDao(Search.class);
		}
		return newsdao;
	}
	
	public RuntimeExceptionDao<Search, Integer> getRuntimeDao() throws SQLException
	{
		if(runtimedao==null)
		{
			runtimedao=getRuntimeExceptionDao(Search.class);			
		}
		return runtimedao;
	}
	
}
