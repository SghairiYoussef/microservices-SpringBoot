import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class AuthIntegrationTest {
    @BeforeAll
    static void setUp(){
        RestAssured.baseURI = "http://localhost:4004";
    }

    @Test
    public void shouldReturnOkWithValidToken(){
        String loginPayload = """
                    {
                        "email":"testuser@test.com",
                        "password":"password123"
                    }
                """;

        Response response = given()
                .contentType("application/json")
                .body(loginPayload)
                .when()
                .post("/auth/login")
                .then()
                .statusCode(200)
                .body("token", notNullValue())
                .extract()
                .response();

        System.out.println("Generated Token: " + response.jsonPath().getString("token"));
    }

    @Test
    public void shouldReturnUnauthorizedOnInvalidLogin(){
        String loginPayload = """
                    {
                        "email":"invalidUser@test.com",
                        "password":"WrongPassword123"
                    }
                """;

         given()
                .contentType("application/json")
                .body(loginPayload)
                .when()
                .post("/auth/login")
                .then()
                .statusCode(401);
    }

    @Test
    public void shouldReturnOkWithValidUser(){
        String signUpPayload = """
                    {
                        "email":"signupTest@test.com",
                        "name":"test",
                        "password":"password123",
                        "confirmPassword":"password123",
                        "role":"ADMIN"
                    }
                """;

        given()
                .contentType("application/json")
                .body(signUpPayload)
                .when()
                .post("/auth/signup")
                .then()
                .statusCode(201);
    }

    @Test
    public void shouldReturnBadRequestOnInvalidEmail(){
        String signupPayload = """
                    {
                        "email":"signupTest",
                        "name":"test",
                        "password":"password123",
                        "confirmPassword":"password123",
                        "role":"ADMIN"
                    }
                """;
        Response response = given()
                .contentType("application/json")
                .body(signupPayload)
                .when()
                .post("/auth/signup")
                .then()
                .statusCode(400)
                .body("email", notNullValue())
                .extract()
                .response();
        System.out.println("Response body: " + response.jsonPath().getString("email"));
    }

    @Test
    public void shouldReturnBadRequestOnMissingValues(){
        String signupPayload = """
                    {
                        "email":"signupTest@test.com",
                        "password":"password123",
                        "confirmPassword":"password123",
                        "role":"ADMIN"
                    }
                """;
        given()
                .contentType("application/json")
                .body(signupPayload)
                .when()
                .post("/auth/signup")
                .then()
                .statusCode(400);
    }

    @Test
    public void shouldReturnBadRequestOnPasswordMismatch(){
        String signupPayload = """
                    {
                        "email":"signupTest",
                        "name":"test",
                        "password":"password123",
                        "confirmPassword":"password12345",
                        "role":"ADMIN"
                    }
                """;
        Response response = given()
                .contentType("application/json")
                .body(signupPayload)
                .when()
                .post("/auth/signup")
                .then()
                .statusCode(400)
                .body("passwordMatching", notNullValue())
                .extract()
                .response();
        System.out.println("Response body: " + response.jsonPath().getString("passwordMatching"));
    }
}
