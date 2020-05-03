package com.project.healthcare.controller;

import java.util.List;

import com.project.healthcare.model.Hospital;

public interface IHospitalController {

    public String createHospital(Hospital h);

    public Hospital getHospital(int id);

    public List<Hospital> getHospitals();

    public void updateHospital(Hospital h);

    public String deleteHospital(int id);
}
