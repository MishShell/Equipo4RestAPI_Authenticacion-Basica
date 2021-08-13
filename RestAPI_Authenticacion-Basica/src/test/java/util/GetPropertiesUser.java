package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetPropertiesUser {

    public void leerPropiedades() throws IOException {

        Properties properties = new Properties();
        String namePropertiesFile="qa.properties";

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(namePropertiesFile);
        if (inputStream !=null)
            properties.load(inputStream);

        ConfigEnv.host=properties.getProperty("host");
        ConfigEnv.AuthenticacionBasica=properties.getProperty("AuthenticacionBasica");

        System.out.println("********");
        System.out.println("ConfigEnv.host: "+ConfigEnv.host);
        System.out.println("ConfigEnv.AuthenticacionBasica: "+ConfigEnv.AuthenticacionBasica);
        System.out.println("********");

        inputStream.close();

    }


}

