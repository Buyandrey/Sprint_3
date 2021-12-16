package org.example;

import io.qameta.allure.junit4.DisplayName;
import jdk.jfr.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RegistrationEndpointTest {
    String expected,actual;
    Courier courier;
    RegistrationEndpoint registrationEndpoint;
    LoginEndpoint loginEndpoint;
    DeleteEndpoint deleteEndpoint;
    @After
    public void delete(){
        //удалит курьера если он был зарегистрирован. Если курьрер был зарегистрирвоан, будет совершен за него вход, узнан id и по id удален
        deleteEndpoint = new DeleteEndpoint(courier);
        loginEndpoint = new LoginEndpoint(courier);
        loginEndpoint.loginWithLoginAndPassword();
        //System.out.println("DELETE:"+ courier.getId() );
        deleteEndpoint.delete();
    }
    @Before
    public void before(){
        courier = new Courier();
        registrationEndpoint = new RegistrationEndpoint(courier);
    }
    @DisplayName("Registration with all field return code 201 and ok:true message")
    @Test
    public void registrationWithALlField(){
        expected=courier.CODE_201_CREATED+','+courier.MESSAGE_201_CREATED;
        actual=registrationEndpoint.registrationWithAllField();
        assertEquals(expected,actual);
    }
    @DisplayName("Registration the same courier twice return code 409 and conflict message")
    @Test
    public void registrationWithALlFieldTwice(){
        registrationEndpoint.registrationWithAllField();
        expected=courier.CODE_409_CONFLICT+','+courier.MESSAGE_409_CONFLICT;
        actual=registrationEndpoint.registrationWithAllField();
        assertEquals(expected,actual);
    }
    @DisplayName("Registration courier without password return code 400 and bad request")
    @Test
    public void registrationCourierWithoutPassword(){
        assertEquals(courier.CODE_400_BAD_REQUEST+','+courier.MESSAGE_400_BAD_REQUEST,
                registrationEndpoint.registrationWithoutPassword());
    }
    @DisplayName("Registration courier without login return code 400 and bad request")
    @Test
    public void registrationCourierWithoutLogin(){
        expected=courier.CODE_400_BAD_REQUEST+','+courier.MESSAGE_400_BAD_REQUEST;
        actual=registrationEndpoint.registrationWithoutLogin();
        assertEquals(expected,actual);
    }
    @DisplayName("Registration courier without FirstName return code 400 and bad request")
    @Test
    public void registrationCourierWithoutFirstName(){
        expected=courier.CODE_400_BAD_REQUEST+','+courier.MESSAGE_400_BAD_REQUEST;
        actual=registrationEndpoint.registrationWithoutFirstname();
        assertEquals(expected,actual);
    }
    @DisplayName("Registration courier with existed login return code 409 and conflict")
    @Description("Create courier, create dopelganger courier with the same login")
    @Test
    public void registrationCourierWithExistedLogin(){
        registrationEndpoint.registrationWithAllField();
        Courier dopelganger = new Courier();
        dopelganger.setLogin(courier.getLoginCourier());
        RegistrationEndpoint registrationEndpoint2 = new RegistrationEndpoint(dopelganger);

        expected=courier.CODE_409_CONFLICT+','+courier.MESSAGE_409_CONFLICT;
        actual=registrationEndpoint2.registrationWithoutFirstname();

        assertEquals(expected,actual);
    }
}

