package services;

import dto.BookDTO;
import dto.BookListIdResponse;
import dto.BookResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.List;

import static config.EnvironmentURL.BASE_URL;
import static io.restassured.RestAssured.given;

public class BookingService {

    public static final String BOOKING = "booking";

    public BookResponse createBook(String token, BookDTO dto) {
        Response response = given()
                .header("Cookie", "token=" + token)
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                .post(BASE_URL + BOOKING)
                .then()
                .statusCode(200)
                .extract()
                .response();
        return response.as(BookResponse.class);
    }

    public List<BookListIdResponse> getBooks(String token) {
        Response response = given()
                .header("Cookie", "token=" + token)
                .contentType(ContentType.JSON)
                .when()
                .get(BASE_URL + BOOKING)
                .then()
                .statusCode(200)
                .extract()
                .response();
        return List.of(response.as(BookListIdResponse[].class));
    }

    public BookDTO getBookId(String token, int id) {
        Response response = given()
                .header("Cookie", "token=" + token)
                .contentType(ContentType.JSON)
                .when()
                .get(BASE_URL + BOOKING + "/" + id)
                .then()
                .statusCode(200)
                .extract()
                .response();
        return response.as(BookDTO.class);
    }

    public BookDTO updateBook(BookDTO dto, String token, int id) {
        Response response = given()
                .header("Cookie", "token=" + token)
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                .put(BASE_URL + BOOKING + "/" + id)
                .then()
                .statusCode(200)
                .extract()
                .response();
        return response.as(BookDTO.class);
    }

    public BookDTO partialUpdateBook(BookDTO dto, String token, int id) {
        Response response = given()
                .header("Cookie", "token=" + token)
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                .patch(BASE_URL + BOOKING + "/" + id)
                .then()
                .statusCode(200)
                .extract()
                .response();
        return response.as(BookDTO.class);
    }

    public void deleteBook(String token, int id) {
        given()
                .header("Cookie", "token=" + token)
                .contentType(ContentType.JSON)
                .when()
                .delete(BASE_URL + BOOKING + "/" + id)
                .then()
                .statusCode(201)
                .extract()
                .response();
    }

}