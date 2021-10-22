package com.example.xmlparser;


import java.util.List;

public class Main {

    private static final String FILENAME = "resources/staff.xml";

    public static void main(String[] args) {
        List<Staff> company = StaffParser.parseStaffXml(FILENAME);
        for (Staff staff : company) {
            System.out.println(staff.toString());
        }
    }

}
