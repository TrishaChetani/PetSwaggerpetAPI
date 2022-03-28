package com.api.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class DefaultConfg {


    public final String CURRENT_DIR = System.getProperty("user.dir");
    public final String BASEURI = System.getProperty("baseURI");
    public final String CREATEENDPOINT = "/pet/{petId}/uploadImage";
    public final String DELETEENDPOINT = "/pet/{petId}";
    public final String UPDATEENDPOINT = "/pet";
    public final String FETCHENDPOINT = "/pet/findByStatus";


    public void test() throws IOException {
        Properties prop = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(System.getProperty("env"));
        prop.load(inputStream);
    }

}






