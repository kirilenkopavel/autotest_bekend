import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class RecipesAnalyzeTest extends AbstractTest {


    @BeforeAll
    static void setUp(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    public void recipesAnalyse() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("language", "en")
                .queryParam("includeNutrition", "false")
                .queryParam("includeTaste", "false")
                .log().all()
                .when()
                .get(getBaseUrl()+"recipes/analyze")
                .then()
                .statusCode(200);
    }

}
