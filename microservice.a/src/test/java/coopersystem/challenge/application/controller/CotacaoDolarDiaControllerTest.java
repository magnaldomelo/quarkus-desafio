package coopersystem.challenge.application.controller;

import io.quarkus.test.junit.QuarkusTest;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

@QuarkusTest
public class CotacaoDolarDiaControllerTest {

    public void testHelloEndpoint() {
        given()
          .when().get("/dolar_cotacao")
          .then()
             .statusCode(200)
             .body(is("Hello RESTEasy"));
    }
}