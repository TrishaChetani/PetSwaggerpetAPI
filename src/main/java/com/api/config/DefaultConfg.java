package com.api.config;

import java.io.*;
import java.util.Properties;


public class DefaultConfg {


    public final String CURRENT_DIR = System.getProperty("user.dir");
    public final String baseURI = System.getProperty("baseURI");
    public final String createEndPoint = "/pet/{petId}/uploadImage";
    public final String deleteRecordEndpoint = "/pet/{petId}";
    public final String updatePet = "/pet";
    public final String fetchPet = "/pet/findByStatus";


    public void test() throws IOException {
        Properties prop = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(System.getProperty("env"));
        prop.load(inputStream);
    }

}






