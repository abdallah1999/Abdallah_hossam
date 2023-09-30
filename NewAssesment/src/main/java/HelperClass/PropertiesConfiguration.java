package HelperClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesConfiguration {

    private Properties properties ;
    private FileInputStream FIS;
    public PropertiesConfiguration() throws IOException {
        File srcFile = new File("E:\\abdallah\\NewAssesment\\Properties\\Data.properties");
        FIS = new FileInputStream(srcFile);
        properties = new Properties();
        properties.load(FIS);
    }
    public String getProperty(String Key){
       return properties.getProperty(Key);
    }



}
