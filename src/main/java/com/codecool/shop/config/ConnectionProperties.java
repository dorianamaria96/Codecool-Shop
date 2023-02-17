package com.codecool.shop.config;

import com.google.common.base.Splitter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

public class ConnectionProperties {

    private static ConnectionProperties instance;
    private String filename = "src/main/resources/connection.properties";


    private String url;
    private String database;
    private String user;
    private String password;
    private String dao;

    private ConnectionProperties() {
        String fileContent = readFromFile(filename);
        Map<String, String> connectionProperties = Splitter.on(' ').withKeyValueSeparator('=').split(fileContent);
        url = connectionProperties.get("url");
        database = connectionProperties.get("database");
        user = connectionProperties.get("user");
        password = connectionProperties.get("password");
        dao = connectionProperties.get("dao");
    }

    public static ConnectionProperties getInstance() {
        if (instance == null) {
            instance = new ConnectionProperties();
        }
        return instance;
    }

    public static String readFromFile(String filename) {
        StringBuilder content = new StringBuilder();
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                content.append(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return content.toString();
    }


    public String getUrl() {
        return url;
    }

    public String getDatabase() {
        return database;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getDao() {
        return dao;
    }
}
