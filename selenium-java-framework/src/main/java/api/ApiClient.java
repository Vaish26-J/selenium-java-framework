package api;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import utils.ConfigReader;

public class ApiClient {
    public RequestSpecification getRequestConfig(){
        return RestAssured.given()
                .headers("x-api-key", ConfigReader.getProperties("api_key"))
                .baseUri(ConfigReader.getProperties("api_base_url"))
                .contentType("application/json");
    }
}
