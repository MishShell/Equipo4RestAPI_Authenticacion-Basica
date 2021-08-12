package basicTest;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class CRUDItemsTest {

    @Test
    public void crudItemsRestApi(){
        // Creacion
        JSONObject body= new JSONObject();
        body.put("Content","Equipo4");

        Response response=given()
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
        body.put("Content","Equipo4Update2");
        body.put("Checked",true);


        response=given()
                .header("Authorization", "Basic bWlzaEBnbWFpbC5jb206MTIzNDU=")
                .body(body.toString())
                .log()
                .all()
                .when()
                .put("http://todo.ly/api/items/"+id+".json");

        response.then()
                .statusCode(200)
                .body("Content", equalTo("Equipo4Update2"))
                .body("Checked",equalTo(true))
                .log()
                .all();
        // Get
        response=given()
                .header("Authorization", "Basic bWlzaEBnbWFpbC5jb206MTIzNDU=")
                .log()
                .all()
                .when()
                .get("http://todo.ly/api/items/"+id+".json");

        response.then()
                .statusCode(200)
                .body("Content", equalTo("Equipo4Update2"))
                .body("Checked",equalTo(true))
                .log()
                .all();
        // Delete
        response=given()
                .header("Authorization", "Basic bWlzaEBnbWFpbC5jb206MTIzNDU=")
                .log()
                .all()
                .when()
                .delete("http://todo.ly/api/items/"+id+".json");

        response.then()
                .statusCode(200)
                .body("Content", equalTo("Equipo4Update2"))
                .body("Checked",equalTo(true))
                .body("Deleted",equalTo(true))
                .log()
                .all();
    }
}
