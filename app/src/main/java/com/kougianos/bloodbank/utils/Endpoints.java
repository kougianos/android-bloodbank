package com.kougianos.bloodbank.utils;

public class Endpoints {

    // Private constructor
    private Endpoints() {

    }

    private static final String baseUrl = "https://nikolas-opalios.000webhostapp.com/";
    public static final String registerUrl = baseUrl + "register.php";
    public static final String loginUrl = baseUrl + "login.php";
    public static final String uploadUrl = baseUrl + "upload_request.php";
    public static final String requestsUrl = baseUrl + "get_requests.php";

}
