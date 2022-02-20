package com.api.config;

import java.io.*;
import java.util.Properties;


public class DefaultConfig {


    public final String CURRENT_DIR = System.getProperty("user.dir");
    public final String baseURI = System.getProperty("baseURI");
    public final String createEndPoint = "/user";
    public final String deleteRecordEndpoint = "/user/{username}";


    public void test() throws IOException {
        Properties prop = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(System.getProperty("env"));
        prop.load(inputStream);
    }

}






