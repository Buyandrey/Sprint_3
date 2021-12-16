package org.example;

import org.apache.commons.lang3.RandomStringUtils;

public class Courier {
    private String loginCourier,passwordCourier,fistNameCourier,id;

    public final String MESSAGE_404_NOT_FOUND           ="{\"code\":404,\"message\":\"Учетная запись не найдена\"}";
    public final String MESSAGE_400_BAD_REQUEST         ="{\"code\":400,\"message\":\"Недостаточно данных для создания учетной записи\"}";
    public final String MESSAGE_400_BAD_REQUEST_LOGIN   ="{\"code\":400,\"message\":\"Недостаточно данных для входа\"}";
    public final String MESSAGE_409_CONFLICT            ="{\"code\":409,\"message\":\"Этот логин уже используется\"}";
    public final String MESSAGE_201_CREATED             ="{\"ok\":true}";
    public final String MESSAGE_200                     ="id:";

    public final String CODE_404_NOT_FOUND              ="404";
    public final String CODE_400_BAD_REQUEST            ="400";
    public final String CODE_400_BAD_REQUEST_LOGIN      ="400";
    public final String CODE_409_CONFLICT               ="409";
    public final String CODE_201_CREATED                ="201";
    public final String CODE_200                        ="200";

    public Courier(){
        loginCourier= RandomStringUtils.randomAlphabetic(5);
        passwordCourier=RandomStringUtils.randomAlphabetic(6);
        fistNameCourier=RandomStringUtils.randomAlphabetic(3);
    }

    public void setId(String id){
        this.id=id;
    }
    public void setLogin(String loginCourier){
        this.loginCourier=loginCourier;
    }
    public void setPassword(String passwordCourier){
        this.passwordCourier=passwordCourier;
    }

    public String getId()               { return id; }
    public String getLoginCourier(){
        return loginCourier;
    }
    public String getPasswordCourier(){
        return passwordCourier;
    }
    public String getFistNameCourier(){
        return fistNameCourier;
    }
}
