package com.example.xmlparser;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class StaffParser {

    private static String getText(Element element, String textName) {
        return element.getElementsByTagName(textName).item(0).getTextContent();
    }

    private static String getAttribute(NodeList nodeList, String attributeName) {
        return  nodeList.item(0).getAttributes().getNamedItem(attributeName).getTextContent();
    }

    public static List<Staff> parseStaffXml(final String fileName) {
        List<Staff> staffList = new ArrayList<Staff>();
        try {
            // Instantiate the Factory
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            // Optional, process XML securely, avoid attacks like XML External Entities (XXE)
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            // Instantiate the document builder
            DocumentBuilder db = dbf.newDocumentBuilder();

            // Parse xml file
            Document doc = db.parse(new File(fileName));

            // get <staff> list
            NodeList list = doc.getElementsByTagName("staff");

            for (int temp = 0; temp < list.getLength(); temp++) {
                Node node = list.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    Staff staff = new Staff();

                    // get staff's attribute
                    staff.setId(element.getAttribute("id"));

                    // get texts
                    staff.setFirstname(StaffParser.getText(element, "firstname"));
                    staff.setLastname(StaffParser.getText(element, "lastname"));
                    staff.setNickname(StaffParser.getText(element, "nickname"));
                    staff.setSalary(StaffParser.getText(element, "salary"));

                    // get salary's attribute
                    NodeList salaryNodeList = element.getElementsByTagName("salary");
                    staff.setCurrency(StaffParser.getAttribute(salaryNodeList, "currency"));

                    staffList.add(staff);
                }
            }
        }  catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return staffList;
    }
}
