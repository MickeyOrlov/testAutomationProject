package services;

import builders.UserBuilder;
import dto.TokenDTO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static config.EnvironmentURL.BASE_URL;
import static io.restassured.RestAssured.given;

public class AuthService {

    public static final String AUTH = "auth";
    UserBuilder getUser = new UserBuilder();

    public TokenDTO requestToGetAuthorizationToken() {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(getUser.AdminUser())
                .when()
                .post(BASE_URL + AUTH)
                .then()
                .statusCode(200)
                .extract()
                .response();
        return response.as(TokenDTO.class);
    }

    public String getToken() {
        return matchToken(requestToGetAuthorizationToken());
    }

    private String matchToken(TokenDTO response) {
        return "token=" + response.getToken();
    }

}