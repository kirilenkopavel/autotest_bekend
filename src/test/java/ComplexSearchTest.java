import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ComplexSearchTest extends AbstractTest {

    @Test
    public void defaultComplexSearch() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("includeNutrition", "false")
                .when()
                .get(getBaseUrl()+"recipes/complexSearch")
                .then()
                .statusCode(200);
    }

    @Test
    public void dieteVegitarianSearch() {
        JsonPath response = given()
                .queryParam("apiKey", getApiKey())
                .queryParam("includeNutrition", "false")
                .when()
                .get(getBaseUrl()+"recipes/complexSearch?diet={diet}", "vegetarian")
                .body()
                .jsonPath();
        assertThat(response.get("number"), equalTo(10));
    }

    @Test
    public void maxCaloriesSearch() {
        JsonPath response = given()
                .queryParam("apiKey", getApiKey())
                .queryParam("includeNutrition", "false")
                .when()
                .get(getBaseUrl()+"recipes/complexSearch?maxCalories={calories}", "800")
                .body()
                .jsonPath();
        assertThat(response.get("number"), equalTo(10));
        assertThat(response.get("unit"), equalTo("kcal"));
    }
}
