package com.kougianos.bloodbank.models;

import java.util.ArrayList;
import java.util.List;


public class BloodGroup {

    private List<String> bloodGroups = new ArrayList<>();

    public BloodGroup() {
       this.bloodGroups.add("A+");
       this.bloodGroups.add("A-");
       this.bloodGroups.add("B+");
       this.bloodGroups.add("B-");
       this.bloodGroups.add("AB+");
       this.bloodGroups.add("AB-");
       this.bloodGroups.add("O+");
       this.bloodGroups.add("O-");
    }

    public List<String> getBloodGroup() {
        return bloodGroups;
    }
}
