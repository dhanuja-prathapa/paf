package com.project.healthcare.resources;

import com.project.healthcare.controller.HospitalController;
import com.project.healthcare.model.Hospital;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("hospitals")
public class HospitalResource {

    HospitalController repo = new HospitalController();

    public HospitalResource() throws SQLException, ClassNotFoundException {
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public List<Hospital> getHospitals() {
        System.out.println("getHospitals called");
        return repo.getHospitals();
    }

    @GET
    @Path("table")
    @Produces(MediaType.TEXT_PLAIN)
    public String getHospitalsTable() {
        System.out.println("getHospitals called");
        String output = "<table border=\"1\"><tr><th>ID</th><th>Name</th><th>Type</th><th>Description</th><th>Address</th><th>Phone</th></tr>";
        List<Hospital> lsits = repo.getHospitals();
        for (Hospital h: lsits
             ) {
            String id = Integer.toString(h.getId());
            String name = h.getName();
            String type = h.getType();
            String description = h.getDescription();
            String address = h.getAddress();
            String phone = h.getPhone();
            output += "<tr><td>" + id + "</td>";
            output += "<td>" + name + "</td>";
            output += "<td>" + type + "</td>";
            output += "<td>" + description + "</td>";
            output += "<td>" + address + "</td>";
            output += "<td>" + phone + "</td></tr>";
        }
            output += "</table>";
            return output;
        }

    @GET
    @Path("hospital/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Hospital getHospital(@PathParam("id") int id) {
        System.out.println("getHospital called");
        return repo.getHospital(id);
    }

    @POST
    @Path("hospital")
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public String createHospital(Hospital h){
        System.out.println(h);
        return repo.createHospital(h);
        
    }

    @POST
    @Path("form")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String createHospitalForm(@FormParam("name") String name,
                                     @FormParam("type") String type,
                                     @FormParam("description") String description,
                                     @FormParam("address") String address,
                                     @FormParam("phone") String phone
                                       ){

        Hospital h = new Hospital();
            h.setName(name);
            h.setType(type);
            h.setDescription(description);
            h.setAddress(address);
            h.setPhone(phone);
            System.out.println(h);
          return repo.createHospital(h);
          
    }

    @PUT
    @Path("form")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String updateHospitalForm(@FormParam("id") int id,
                                     @FormParam("name") String name,
                                     @FormParam("type") String type,
                                     @FormParam("description") String description,
                                     @FormParam("address") String address,
                                     @FormParam("phone") String phone
    ){
        Hospital h = new Hospital();
        h.setId(id);
        if (h.getId() != 0) {
            h.setName(name);
            h.setType(type);
            h.setDescription(description);
            h.setAddress(address);
            h.setPhone(phone);
            System.out.println(h);
            repo.updateHospital(h);
            if (repo.getHospital(id).getId() != 0) {
                return "Succesfully Updated";
            } else {
                return "Error";
            }
        }else{
            return "ID is REQUIRED";
        }

    }

    @PUT
    @Path("hospital")
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Hospital updateHospital(Hospital h){
        System.out.println(h);
        if(repo.getHospital(h.getId()).getId()== 0){
            repo.createHospital(h);
        }
        else {
            repo.updateHospital(h);
        }
        return h;
    }

    @DELETE
    @Path("hospital/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteHospital(@PathParam("id") int id){
        return repo.deleteHospital(id);
    }
}
