package com.project.healthcare.hospital.controller;

import java.util.List;

import com.project.healthcare.hospital.model.HospitalAuth;

public interface IHospitalController {

    public void createHospital(HospitalAuth h);

    public HospitalAuth getHospital(int id);

    public List<HospitalAuth> getHospitals();

    public void updateHospital(HospitalAuth h);

    public String deleteHospital(int id);
}
