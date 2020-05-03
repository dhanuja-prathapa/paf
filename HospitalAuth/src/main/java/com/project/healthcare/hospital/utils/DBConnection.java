package com.project.healthcare.hospital.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {

    //If there is any issue you can change it accordingly
    //Implemented with Singleton Pattern

    private static Connection con;

    private DBConnection() {

    }

    public static Connection getDBConnection() throws ClassNotFoundException, SQLException {

        if(con==null ||con.isClosed()) {

            String url = "jdbc:mysql://127.0.0.1:3306/healthcare";
            String username = "root";
            String password = "";
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(url, username, password);
            }catch (Exception e){
                System.out.println(e);
            }

            System.out.println("Connected to DB");
        }

        return con;
    }


//	public static Connection connect() {
//
//		Connection con = null;
//
//		try {
//
//			Class.forName("com.mysql.cj.jdbc.Driver");
//
//			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcare", "root", "root");
//
//		} catch (Exception e) {
//
//			e.printStackTrace();
//		}
//
//		return con;
//
//	}

}