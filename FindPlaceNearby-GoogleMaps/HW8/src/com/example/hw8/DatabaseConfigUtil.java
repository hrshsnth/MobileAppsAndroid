package com.example.hw8;

import java.io.IOException;
import java.sql.SQLException;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;
/**
 * HW7
 * DatabaseConfigUtil.java
 * @author HARISHSAINATH GANAPATHY(800833319) & Dayabaran Gangatharan(800823490) 
 *
 */
public class DatabaseConfigUtil extends OrmLiteConfigUtil {
	/** InClassAssign8
	 * FileName : DatabaseConfigUtil.java
	 * @author Dayabaran Gangatharan(800823490) & Harish Sainath Ganapathy Subramaniam(800833319)
	 *  
	 */
	private static final Class<?>[] classes=new Class[]{Places.class};
	public static void main(String[] args) throws SQLException, IOException {
		// TODO Auto-generated method stub

		writeConfigFile("ormlite_config.txt",classes);
	}

}
