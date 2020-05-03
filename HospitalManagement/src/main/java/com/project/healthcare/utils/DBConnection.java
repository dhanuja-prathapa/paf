package com.project.healthcare.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.project.healthcare.utils.Constants;

public class DBConnection {

    //If there is any issue you can change it accordingly
    //Implemented with Singleton Pattern

    private static Connection con;

    private DBConnection() {

    }

    public static Connection getDBConnection() {

            try {
                Class.forName(Constants.DB_DRIVER_NAME);
                con = DriverManager.getConnection(Constants.DBLOCATION_STRING, Constants.DB_USERNAME, Constants.DB_PASSWORD);
            }catch (ClassNotFoundException e){
                System.out.println("Driver Class Not Found");
            }catch (SQLException e){
                createDatabase();
                createTable();
            }

            System.out.println("Connected to DB");


        return con;
    }

    private static void createDatabase() {
        try {
            Class.forName(Constants.DB_DRIVER_NAME);
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306",Constants.DB_USERNAME,Constants.DB_PASSWORD);
            Statement s = con.createStatement();
            s.executeUpdate("CREATE DATABASE IF NOT EXISTS paf");
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTable() {
        String myTableName = "create table hospital(id int, name varchar(32), type varchar(32), description varchar(64), address varchar(64), phone varchar(16))";
        try {
            Class.forName(Constants.DB_DRIVER_NAME);
            con = DriverManager.getConnection(Constants.DBLOCATION_STRING,Constants.DB_USERNAME,Constants.DB_PASSWORD);
            Statement s = con.createStatement();
            //The next line has the issue
            s.executeUpdate(myTableName);
            System.out.println("Table Created");
        }
        catch (SQLException e ) {
            System.out.println("An error has occurred on Table Creation");
        }
        catch (ClassNotFoundException e) {
            System.out.println("An Mysql drivers were not found");
        }
    }
}