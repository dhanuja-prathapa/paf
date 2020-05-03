package com.project.healthcare;

import com.project.healthcare.controller.HospitalController;
import com.project.healthcare.model.Hospital;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;


@WebServlet("/HospitalsAPI")
public class HospitalsAPI extends HttpServlet {

    HospitalController hospitalController = new HospitalController();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Hospital hospital = new Hospital();
        hospital.setName(request.getParameter("hospitalName"));
        hospital.setType(request.getParameter("hospitalType"));
        hospital.setDescription(request.getParameter("hospitalDesc"));
        hospital.setAddress(request.getParameter("hospitalAddress"));
        hospital.setPhone(request.getParameter("hospitalPhone"));

        String output = hospitalController.createHospital(hospital);
        response.getWriter().write(output);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPut(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        Map paras = getParasMap(request);
        Hospital hospital = new Hospital();

        hospital.setId(Integer.parseInt(String.valueOf(paras.get("hidHosIDSave"))));
        hospital.setName(String.valueOf(paras.get("hospitalName")));
        hospital.setType(String.valueOf(paras.get("hospitalType")));
        hospital.setDescription(String.valueOf(paras.get("hospitalDesc")));
        hospital.setAddress(String.valueOf(paras.get("hospitalAddress")));
        hospital.setPhone(String.valueOf(paras.get("hospitalPhone")));

        String output = hospitalController.updateHospital(hospital);

        response.getWriter().write(output);

    }

    protected void doDelete(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        Map paras = getParasMap(request);

        String output = hospitalController.deleteHospital(Integer.parseInt(String.valueOf(paras.get("hidHosIDDelete"))));
        response.getWriter().write(output);
    }


    private static Map getParasMap(HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        try {
            Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
            String queryString = scanner.hasNext() ?
                    scanner.useDelimiter("\\A").next() : "";
            scanner.close();
            String[] params = queryString.split("&");
            for (String param : params) {
                String[] p = param.split("=");
                map.put(p[0], p[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}
