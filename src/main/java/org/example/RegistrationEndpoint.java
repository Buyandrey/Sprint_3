package org.example;


import org.json.JSONObject;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;


public class RegistrationEndpoint {
    private final String BASE_URL="https://qa-scooter.praktikum-services.ru";
    private final String REGISTRATION="/api/v1/courier";
    private final Courier courier;
    private JSONObject jsonBody= new JSONObject();

    public RegistrationEndpoint(Courier courier){
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
                .post(BASE_URL+REGISTRATION);
        if(response.statusCode()==504)
            return (response.statusCode()+ "," + response.body().asString());
        else
            return (response.statusCode() + "," + response.body().asString());
    }
    @Step("Registration with login, password and firstname")
    public String registrationWithAllField(){
        jsonBody.accumulate("login",courier.getLoginCourier());
        jsonBody.accumulate("password",courier.getPasswordCourier());
        jsonBody.accumulate("firstName",courier.getFistNameCourier());
        //System.out.println(jsonBody.toString());
        return sendRequestWithJsonBody(jsonBody);
    }
    @Step("Registration with login and password")
    public String registrationWithoutFirstname(){
        jsonBody.accumulate("login",courier.getLoginCourier());
        jsonBody.accumulate("password",courier.getPasswordCourier());
        //System.out.println(jsonBody.toString());
        return sendRequestWithJsonBody(jsonBody);
    }
    @Step("Registration with login and firstname")
    public String registrationWithoutPassword(){
        jsonBody.accumulate("login",courier.getLoginCourier());
        jsonBody.accumulate("firstName",courier.getFistNameCourier());
        //System.out.println(jsonBody.toString());
        return sendRequestWithJsonBody(jsonBody);
    }

    @Step("Registration with password and firstname")
    public String registrationWithoutLogin(){
        jsonBody.accumulate("password",courier.getLoginCourier());
        jsonBody.accumulate("firstName",courier.getFistNameCourier());
        //System.out.println(jsonBody.toString());
        return sendRequestWithJsonBody(jsonBody);
    }
}
