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
    private static final long serialVersionUID = 1L;
    HospitalController hospitalController = new HospitalController();
    public HospitalsAPI() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Hospital hospital = new Hospital();
        hospital.setName(request.getParameter("hospitalName"));
        hospital.setType(request.getParameter("hospitalType"));
        hospital.setDescription(request.getParameter("hospitalDesc"));
        hospital.setAddress(request.getParameter("hospitalAddress"));
        hospital.setPhone(request.getParameter("hospitalPhone"));

        String output = hospitalController.createHospital(hospital);
        System.out.println(output);
        response.getWriter().write(output);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPut(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        Map paras = getParasMap(request);
        Hospital hospital = new Hospital();

        hospital.setId(Integer.parseInt((paras.get("hidHosIDSave")).toString()));
        hospital.setName(paras.get("hospitalName").toString());
        hospital.setType(paras.get("hospitalType").toString());
        hospital.setDescription(paras.get("hospitalDesc").toString());
        hospital.setAddress(paras.get("hospitalAddress").toString());
        hospital.setPhone(paras.get("hospitalPhone").toString());

        String output = hospitalController.updateHospital(hospital);

        response.getWriter().write(output);

    }

    protected void doDelete(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        Map paras = getParasMap(request);
        System.out.println(Integer.parseInt(paras.get("hospitalID").toString()));
        String output = hospitalController.deleteHospital(Integer.parseInt(paras.get("hospitalID").toString()));
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
