package org.spotqa.airlines.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by lakkaraju on 21-12-2017.
 * Retrieves property values from the config.properties file.
 *
 */
public class Configuration {

    InputStream inStream;
    String propValue = null;

    public String GetPropValue(String propKey) throws IOException
    {
        try {
            Properties props = new Properties();
            String fileName = "config.properties";

            inStream = getClass().getClassLoader().getResourceAsStream(fileName);
            if(inStream != null){
                props.load(inStream);
            } else {
                throw new FileNotFoundException(fileName + ": property file is not found");
            }
            propValue = props.getProperty(propKey);
        } catch (Exception e) {
            //Logger
            System.out.println(e.getMessage());
        } finally {
            inStream.close();
        }
        return propValue;
    }

    /*
        Configuration config = new Configuration();
        String value = config.GetPropValue("browser");
        System.out.println(value);

     */
}

