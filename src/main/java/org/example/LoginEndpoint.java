package org.example;


import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class LoginEndpoint {

    private final String BASE_URL="https://qa-scooter.praktikum-services.ru";
    private final String LOGIN="/api/v1/courier/login";
    private  Courier courier;
    private JSONObject jsonBody= new JSONObject();

    public LoginEndpoint(Courier courier){
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
                .post(BASE_URL+LOGIN);
        if(response.statusCode()==504)
            return (response.statusCode()+ "," + response.body().asString());
        else
            setId(response);
        return (response.statusCode() + "," + response.body().asString());
    }
    @Step("Login with login and password")
    public String loginWithLoginAndPassword(){
        jsonBody.accumulate("login",courier.getLoginCourier());
        jsonBody.accumulate("password",courier.getPasswordCourier());
        //System.out.println(jsonBody.toString());
        return sendRequestWithJsonBody(jsonBody);
    }
    @Step("Login without password")
    public String loginWithoutPassword(){
        jsonBody.accumulate("login",courier.getLoginCourier());
        //System.out.println(jsonBody.toString());
        return sendRequestWithJsonBody(jsonBody);
    }
    @Step("Login without login")
    public String loginWithoutLogin(){
        jsonBody.accumulate("password",courier.getPasswordCourier());
        //System.out.println(jsonBody.toString());
        return sendRequestWithJsonBody(jsonBody);
    }
    @Step("Login without login and password")
    public String loginWithoutLoginAndPassword(){

        return sendRequestWithJsonBody(jsonBody);
    }
    @Step("Set courier id")
    public void setId(Response response){
        this.courier.setId( response.asString().substring(
                response.asString().indexOf(':') + 1,
                response.asString().indexOf('}'))
        );
    }
}
