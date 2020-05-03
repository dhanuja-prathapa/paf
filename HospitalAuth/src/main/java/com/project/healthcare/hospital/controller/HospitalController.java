package com.project.healthcare.hospital.controller;

import com.project.healthcare.hospital.model.HospitalAuth;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;


public class HospitalController implements IHospitalController{

    List<HospitalAuth> hospitals;

    Connection con = null;

    public HospitalController(){

        String url = "jdbc:mysql://127.0.0.1:3306/healthcare";
        String username = "root";
        String password = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @Override
    public List<HospitalAuth> getHospitals() {
        List<HospitalAuth> hospitals = new ArrayList<>();
        String sql = "select * from regHospital";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                HospitalAuth h = new HospitalAuth();
                setobject(rs, h);
                hospitals.add(h);
            }
        } catch (SQLException e){
            System.out.println(e);
        }
        return hospitals;
    }

    private void setobject(ResultSet rs, HospitalAuth h) throws SQLException {
        h.setId(rs.getInt(1));
        h.setName(rs.getString(2));
        h.setLicence(rs.getString(3));
    }

    @Override
    public void createHospital(HospitalAuth h){
        String sql = "insert into regHospital values (?,?,?)";

        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, h.getId());
            st.setString(2, h.getName());
            st.setString(3, h.getLicence());
            st.executeUpdate();
        } catch (SQLException e){
            System.out.println(e);
        }
    }

    @Override
    public HospitalAuth getHospital(int id) {
        String sql = "select * from regHospital where id="+id;
        HospitalAuth h = new HospitalAuth();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                setobject(rs, h);
            }
        } catch (SQLException e){
            System.out.println(e);
        }
        return h;
    }

    @Override
    public void updateHospital(HospitalAuth h) {
        String sql = "update regHospital set name=?, licence=? where id=?";

        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, h.getName());
            st.setString(2, h.getLicence());
            st.setInt(3, h.getId());
            st.executeUpdate();
        } catch (SQLException e){
            System.out.println(e);
        }
    }

    @Override
    public String deleteHospital(int id) {
        String sql = "delete from regHospital where id=?";
        String output;
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
            output = "Successfully Deleted";
        } catch (SQLException e){
            System.out.println(e);
            output = "Error";
        }
            return output;
    }
}
