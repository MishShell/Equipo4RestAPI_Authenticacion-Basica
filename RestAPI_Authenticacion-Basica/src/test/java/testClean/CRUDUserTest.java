
package testClean;

import factoryRequest.FactoryRequest;
import factoryRequest.RequestInformation;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import util.ConfigAPI;
import util.GetPropertiesUser;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;


public class CRUDUserTest {

    @BeforeEach
    public void before() throws IOException {
        new GetPropertiesUser().leerPropiedades();
    }

    @Test
    public void verifyCRUDforUser(){
        JSONObject body = new JSONObject();
        body.put("Email","EquipoEquipo2@email.com");
        body.put("Password","12345");
        body.put("FullName","mish@gmail.com");

        RequestInformation request = new RequestInformation(ConfigAPI.CREATE_USER,body.toString());
        Response response = FactoryRequest.make(FactoryRequest.POST2).send(request);
        response.then()
                .statusCode(200)
                .body("Email", equalTo("EquipoEquipo2@email.com"));
                //.body("FullName", equalTo("mish@gmail.com"));



        String ListSortType = response.then().extract().path("ListSortType")+"";

        body.put("Email","mish@gmail.com");

        request = new RequestInformation(ConfigAPI.UPDATE_USER.replace("ListSortType",ListSortType),body.toString());
        response = FactoryRequest.make(FactoryRequest.PUT).send(request);

        response.then()
                .statusCode(200);
                //.body("Email", equalTo("mish@gmail.com"));
/*
        request = new RequestInformation(ConfigAPI.GET_USER.replace("ListSortType",ListSortType),"");
        response = FactoryRequest.make(FactoryRequest.GET).send(request);

        response.then()
                .statusCode(200)
                .body("Email", equalTo("mish@gmail.com"));*/

    }

}
