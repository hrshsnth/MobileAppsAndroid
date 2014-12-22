package com.example.hw7;

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
	
	
	private static final Class<?>[] classes=new Class[]{Search.class};
	public static void main(String[] args) throws SQLException, IOException {
		// TODO Auto-generated method stub

		writeConfigFile("ormlite_config.txt",classes);
	}

}
