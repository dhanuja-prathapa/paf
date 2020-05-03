package com.project.healthcare.hospital.resources;

import com.project.healthcare.hospital.controller.HospitalController;
import com.project.healthcare.hospital.model.HospitalAuth;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("regHospitals")
public class HospitalResource {

    HospitalController repo = new HospitalController();

    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public List<HospitalAuth> getHospitals() {
        System.out.println("getHospitals called");
        return repo.getHospitals();
    }


    @GET
    @Path("hospital/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public HospitalAuth getHospital(@PathParam("id") int id) {
        System.out.println("getHospital called");
        return repo.getHospital(id);
    }

    @POST
    @Path("hospital")
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public HospitalAuth createHospital(HospitalAuth h){
        System.out.println(h);
        repo.createHospital(h);
        return h;
    }


    @PUT
    @Path("hospital")
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public HospitalAuth updateHospital(HospitalAuth h){
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
        HospitalAuth h = repo.getHospital(id);
        return repo.deleteHospital(id);
    }
}
