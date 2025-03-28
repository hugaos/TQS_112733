package tqs;

import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;



public class AppTest 
{
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    @Test
    public void shouldBeAvailableAllTodos()
    {
        given()
                .when()
                .get(BASE_URL + "/todos")
                .then()
                .statusCode(200);
    }

    @Test
    public void shouldReturnRightTitle()
    {
        when().
            get(BASE_URL + "/todos/4").
        then().
            body("title", equalTo("et porro tempora"));
    }
}
