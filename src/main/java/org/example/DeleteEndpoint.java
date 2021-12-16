package org.example;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class DeleteEndpoint {

    private final Courier courier;
    private final String BASE_URL = "https://qa-scooter.praktikum-services.ru";
    private final String DELETE="/api/v1/courier/:id";
    private JSONObject bodyRequestJson = new JSONObject();

    public DeleteEndpoint(Courier courier){
        this.courier=courier;
    }
    @Step("Send request with some JSON-body")
    public String sendRequestWithJsonBody(JSONObject bodyRequestJson){

        //System.out.println(bodyRequestJson.toString()+"\n");

        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(bodyRequestJson.toString())
                .when()
                .post(BASE_URL+DELETE);
        if(response.statusCode()==504) return (response.statusCode()+ "," + response.body().asString());


        return (response.statusCode() + "," + response.body().asString());
    }
    @Step("Delete Courier")
    public String delete(){
        bodyRequestJson.accumulate("id", courier.getId());
        return(sendRequestWithJsonBody(bodyRequestJson));
    }
}
