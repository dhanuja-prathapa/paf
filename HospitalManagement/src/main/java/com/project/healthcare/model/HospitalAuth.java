package com.project.healthcare.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class HospitalAuth {

    private int id;
    private String name;
    private String licence;

    public HospitalAuth(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    @Override
    public String toString() {
        return "HospitalAuth{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", licence='" + licence + '\'' +
                '}';
    }
}
