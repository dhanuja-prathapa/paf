package com.project.healthcare.controller;


import com.project.healthcare.model.Hospital;
import com.project.healthcare.model.HospitalAuth;
import com.project.healthcare.utils.Constants;
import com.project.healthcare.utils.idGenerate;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONObject;

import static com.project.healthcare.utils.DBConnection.*;


public class HospitalController implements IHospitalController {

    private Connection connecton;
    private Statement st;
    private PreparedStatement pt;
    private static final Logger log = Logger.getLogger(HospitalController.class.getName());

    @Override
    public List<Hospital> getHospitals() {
        List<Hospital> hospitals = new ArrayList<>();
        String sql = "select * from hospital";
        connecton = getDBConnection();
        try {
            st = connecton.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Hospital h = new Hospital();
                mapObject(rs, h);
                hospitals.add(h);
            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, e.getMessage());
        } finally {
            stClose(st);
        }
        return hospitals;
    }

    public String getAllHospitals(){
        String output = "<table class='table table-bordered table-hover'><tr><th>ID</th><th>Name</th><th>Type</th><th>Description</th><th>Address</th><th>Phone</th><th>Update</th><th>Remove</th></tr>";
        List<Hospital> lsits = getHospitals();
        for (Hospital h: lsits
        ) {
            String id = Integer.toString(h.getId());
            String name = h.getName();
            String type = h.getType();
            String description = h.getDescription();
            String address = h.getAddress();
            String phone = h.getPhone();

            output += "<tr><td><input id='hidHosIDUpdate' name='hidHosUpdate' type='hidden' value='" + id + "'>"+ id +"</td>";
            output += "<td>" + name + "</td>";
            output += "<td>" + type + "</td>";
            output += "<td>" + description + "</td>";
            output += "<td>" + address + "</td>";
            output += "<td>" + phone + "</td>";

            output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td> <td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-hospitalid='" + id + "'>" + "</td></tr>";
        }
        output += "</table>";
        return output;
    }


    @Override
    public String createHospital(Hospital h) {
        String output = "";
        int hosId = idGenerate.generateHosID((ArrayList<Integer>) getIDs());
        String sql = "insert into hospital values (?,?,?,?,?,?)";
        boolean validate = false;
        List<HospitalAuth> pAuthList = getDetails();
        connecton = getDBConnection();
        for (HospitalAuth hospitalAuth : pAuthList) {
			System.out.println("PAUTH:1 " + hospitalAuth.getName());
			System.out.println("PAUTH:2 " + h.getName());
			System.out.println("PAUTH:1 " + hospitalAuth.getLicence());
			
			if(h.getName().equals(hospitalAuth.getName())) {
			
					validate = true;
					System.out.println("VALIDATE: " + validate);
					break;
				}
			
		}
        if(validate) {
            try {
                pt = connecton.prepareStatement(sql);
                pt.setInt(1, hosId);
                pt.setString(2, h.getName());
                pt.setString(3, h.getType());
                pt.setString(4, h.getDescription());
                pt.setString(5, h.getAddress());
                pt.setString(6, h.getPhone());
                pt.executeUpdate();

                String newHospitals = getAllHospitals();
                output = "{\"status\":\"success\", \"data\": \"" + newHospitals + "\"}";

            } catch (SQLException e) {
                output = "{\"status\":\"error\", \"data\": \"Error while inserting the hospital\"}";
                log.log(Level.SEVERE, e.getMessage());
            } finally {
                ptClose(pt);
            }
        }else {
            System.out.println("Not a valid");
        	output = "{\"status\":\"error\", \"data\": \"Error! Not a valid Hospital\"}";
        }
            return output;
        }



    @Override
    public Hospital getHospital(int id) {
        String sql = "select * from hospital where id=" + id;
        Hospital h = new Hospital();
        connecton = getDBConnection();
        try {
            st = connecton.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                mapObject(rs, h);
            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, e.getMessage());
        } finally {
            stClose(st);
        }
        return h;
    }

    @Override
    public String updateHospital(Hospital h) {
        String sql = "update hospital set name=?, type=?, description=?, address=?, phone=? where id=?";
        connecton = getDBConnection();
        String output;
        try {
            pt = connecton.prepareStatement(sql);
            pt.setString(1, h.getName());
            pt.setString(2, h.getType());
            pt.setString(3, h.getDescription());
            pt.setString(4, h.getAddress());
            pt.setString(5, h.getPhone());
            pt.setInt(6, h.getId());
            pt.executeUpdate();

            String newHospitals = getAllHospitals();
            output = "{\"status\":\"success\", \"data\": \"" + newHospitals + "\"}";

        } catch (SQLException e) {

            output = "{\"status\":\"error\", \"data\": \"Error while updating the hospital\"}";
            log.log(Level.SEVERE, e.getMessage());

        } finally {
            ptClose(pt);
        }
        return output;
    }

    @Override
    public String deleteHospital(int id) {
        String sql = "delete from hospital where id=?";
        String output;
        connecton = getDBConnection();
        try {
            pt = connecton.prepareStatement(sql);
            pt.setInt(1, id);
            pt.executeUpdate();

            String newHospitals = getAllHospitals();
            output = "{\"status\":\"success\", \"data\": \"" + newHospitals + "\"}";

        } catch (SQLException e) {
            log.log(Level.SEVERE, e.getMessage());
            output = "{\"status\":\"error\", \"data\": \"Error while deleting the hospital\"}";
        } finally {
            ptClose(pt);
        }
        return output;
    }

    //Close Statement
    private void stClose(Statement st) {
        try {
            if (st != null) {
                st.close();
            }
            if (connecton != null) {
                connecton.close();
                System.out.println("DB Connection Closed");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    private void ptClose(PreparedStatement pt) {
        try {
            if (pt != null) {
                pt.close();
            }
            if (connecton != null) {
                connecton.close();
                System.out.println("DB Connection Closed");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //Map Object
    private void mapObject(ResultSet rs, Hospital h) throws SQLException {
        h.setId(rs.getInt(Constants.COLUMN_INDEX_ONE));
        h.setName(rs.getString(Constants.COLUMN_INDEX_TWO));
        h.setType(rs.getString(Constants.COLUMN_INDEX_THREE));
        h.setDescription(rs.getString(Constants.COLUMN_INDEX_FOUR));
        h.setAddress(rs.getString(Constants.COLUMN_INDEX_FIVE));
        h.setPhone(rs.getString(Constants.COLUMN_INDEX_SIX));
    }

    //getIDs ArrayList
    private List<Integer> getIDs(){
        List<Integer> arrayList = new ArrayList<Integer>();
        String query = "SELECT hospital.id FROM hospital";
        connecton = getDBConnection();
        try{
            pt = connecton.prepareStatement(query);
            ResultSet resultSet = pt.executeQuery();
            while (resultSet.next()){
                arrayList.add(resultSet.getInt(1));
            }

        }catch (Exception e){
            log.log(Level.SEVERE, e.getMessage());
        }finally {
            ptClose(pt);
        }
        return arrayList;
    }
    
    private List<HospitalAuth> getDetails() {
		
    	List<HospitalAuth> dataList = new ArrayList<HospitalAuth>();
		
		try {

			Client client = Client.create();

			WebResource webResource = client
			   .resource("http://localhost:8090/regHospitals");

			ClientResponse response = webResource.accept("application/json")
	                   .get(ClientResponse.class);

			if (response.getStatus() != 200) {
			   throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatus());
			}

			String output = response.getEntity(String.class);
			JSONArray jsonArr = new JSONArray(output);
		 
		    for (int i = 0; i < jsonArr.length(); i++) {
		        JSONObject jsonObj = jsonArr.getJSONObject(i);
		        HospitalAuth data = new HospitalAuth();
		        data.setName(jsonObj.getString("name"));
		        data.setLicence(jsonObj.getString("licence"));
		        dataList.add(data);
		    }

		  } catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());

		  }
			
		return dataList;
	}

	public String reghos(){
        List<HospitalAuth> dataList = getDetails();
        StringBuilder output = new StringBuilder();

        for (HospitalAuth hospitalAuth : dataList){
            output.append("<option>").append(hospitalAuth.getName()).append("</option>");
        }

        return output.toString();
    }


}
