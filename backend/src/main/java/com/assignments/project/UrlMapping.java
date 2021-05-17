package com.assignments.project;

public class UrlMapping {
    public static final String API_PATH = "/api";

    public static final String AUTH = API_PATH + "/auth";
    public static final String SIGN_IN = "/sign-in";
    public static final String SIGN_UP = "/sign-up";

    public static final String USERS = API_PATH + "/users";
    public static final String CLIENTS = API_PATH + "/clients";
    public static final String CARS = API_PATH + "/cars";
    public static final String APPOINTMENTS = API_PATH + "/appointments";
    public static final String RECEIPT = "/receipt/{receiptType}";
    public static final String FIND_ALL = "/find-all";
    public static final String FIND_ALL_MECHANICS = "/find-all-mechanics";
    public static final String FIND_ALL_BY_DATE_AND_MECHANIC = "/find-all-by-date-and-mechanic";
    public static final String FIND_ALL_BY_CAR = "/find-all-by-car";
    public static final String FIND_BY_ID = "/{id}";
    public static final String SAVE = "/save";
    public static final String UPDATE = "/update";
    public static final String DELETE = FIND_BY_ID;
}