package com.kougianos.bloodbank.utils;

import java.util.Arrays;
import java.util.List;


public class BloodGroup {

    public static List<String> getBloodGroups() {
        return Arrays.asList(
                "A+",
                "A-",
                "B+",
                "B-",
                "AB+",
                "AB-",
                "O+",
                "O-"
        );
    }
}
