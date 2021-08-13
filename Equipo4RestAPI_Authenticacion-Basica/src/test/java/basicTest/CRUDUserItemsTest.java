package basicTest;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class CRUDUserItemsTest {

    @Test
    public void crudItemsRestApiUser() {
        // Creacion
        JSONObject body = new JSONObject();
        body.put("Email", "Equip4@email.com");
        body.put("Password", "12345");
        body.put("FullName", "mish@gmail.com");

        Response response = given()
                .body(body.toString())
                .log()
                .all()
                .when()
                .post("http://todo.ly/api/user.json");

        response.then()
                .statusCode(200)
                //.body("Content", equalTo("Equipo4"))
                .body("Email", equalTo("Equip4@email.com"))
                .log()
                .all();

        // Actualizacion
        body.put("Email", "mish@gmail.com");


        response = given()
                .header("Authorization", "Basic bWlzaEBnbWFpbC5jb206MTIzNDU=")
                .body(body.toString())
                .log()
                .all()
                .when()
                .put("http://todo.ly/api/items/" + 0 + ".json");

        response.then()
                .statusCode(200)
                .log()
                .all();
    }

    @Test
    public void crudItemsRestApiITEM() {
        // Creacion
        JSONObject body = new JSONObject();
        body.put("Content", "Equipo4");

        Response response = given()
                .header("Authorization", "Basic bWlzaEBnbWFpbC5jb206MTIzNDU=")
                .body(body.toString())
                .log()
                .all()
                .when()
                .post("http://todo.ly/api/items.json");

        response.then()
                .statusCode(200)
                .body("Content", equalTo("Equipo4"))
                .log()
                .all();

        int id = response.then().extract().path("Id");

        // Actualizacion
        body.put("Content", "Equipo4Update2");
        body.put("Checked", true);


        response = given()
                .header("Authorization", "Basic bWlzaEBnbWFpbC5jb206MTIzNDU=")
                .body(body.toString())
                .log()
                .all()
                .when()
                .put("http://todo.ly/api/items/" + id + ".json");

        response.then()
                .statusCode(200)
                .body("Content", equalTo("Equipo4Update2"))
                .body("Checked", equalTo(true))
                .log()
                .all();
        // Get
        response = given()
                .header("Authorization", "Basic bWlzaEBnbWFpbC5jb206MTIzNDU=")
                .log()
                .all()
                .when()
                .get("http://todo.ly/api/items/" + id + ".json");

        response.then()
                .statusCode(200)
                .body("Content", equalTo("Equipo4Update2"))
                .body("Checked", equalTo(true))
                .log()
                .all();
        // Delete
        response = given()
                .header("Authorization", "Basic bWlzaEBnbWFpbC5jb206MTIzNDU=")
                .log()
                .all()
                .when()
                .delete("http://todo.ly/api/items/" + id + ".json");

        response.then()
                .statusCode(200)
                .body("Content", equalTo("Equipo4Update2"))
                .body("Checked", equalTo(true))
                .body("Deleted", equalTo(true))
                .log()
                .all();
    }


}
