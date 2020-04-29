package com.example.demo;

public class Baby {
    private String id;
    private String name;
    private  String religion;
    private String location;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public String getReligion() {
        return religion;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
