
package factoryRequest;

import io.restassured.response.Response;
import util.ConfigEnv;

import static io.restassured.RestAssured.given;

public class RequestDELETE implements IRequest{
    @Override
    public Response send(RequestInformation information) {
        Response response=given()
                .header("Authorization", ConfigEnv.AuthenticacionBasica)
                .log()
                .all()
                .when()
                .delete(information.getUrl());
        response.then()
                .log()
                .all();
        return response;
    }
}