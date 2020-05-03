package com.project.healthcare;

import com.project.healthcare.controller.HospitalController;
import com.project.healthcare.model.Hospital;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/HospitalsAPI")
public class HospitalsAPI extends HttpServlet {

    HospitalController hospitalController = new HospitalController();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Hospital hospital = new Hospital();
        hospital.setName(request.getParameter("name"));
        hospital.setType(request.getParameter("type"));
        hospital.setDescription(request.getParameter("desc"));
        hospital.setAddress(request.getParameter("address"));
        hospital.setPhone(request.getParameter("phone"));

        String output = hospitalController.createHospital(hospital);
        response.getWriter().write(output);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}
