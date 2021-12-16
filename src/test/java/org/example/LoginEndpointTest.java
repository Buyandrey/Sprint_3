package org.example;


import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoginEndpointTest {
    private String actual,expected;
    private Courier courier;
    private RegistrationEndpoint registrationEndpoint = new RegistrationEndpoint(courier);
    private LoginEndpoint loginEndpoint;
    private DeleteEndpoint deleteEndpoint;

    @Before
    public void beforeTest(){
        courier = new Courier();
        registrationEndpoint = new RegistrationEndpoint(courier);
        registrationEndpoint.registrationWithAllField();
        loginEndpoint = new LoginEndpoint(courier);

    }
    @After
    public void afterTest(){
        //удалит курьера если он был зарегистрирован. Если курьрер был зарегистрирвоан, будет совершен за него вход, узнан id и по id удален
        deleteEndpoint = new DeleteEndpoint(courier);
        loginEndpoint = new LoginEndpoint(courier);
        loginEndpoint.loginWithLoginAndPassword();
        //System.out.println("DELETE:"+ courier.getId() );
        deleteEndpoint.delete();
    }
    @DisplayName("Login with login and password return ID")
    @Test
    public void loginWithLoginAndPasswordReturnId(){
        actual=loginEndpoint.loginWithLoginAndPassword();
        expected=courier.CODE_200+",{"+courier.MESSAGE_200+courier.getId()+'}';
        assertEquals(true, actual.contains(courier.getId()));
    }
    @DisplayName("Login with login and password return code 200 and ok:true")
    @Test
    public void loginWithLoginAndPassword(){
        actual=loginEndpoint.loginWithLoginAndPassword();
        expected=courier.CODE_200+",{"+courier.MESSAGE_200+courier.getId()+'}';
        assertEquals(expected,actual);
    }

    @DisplayName("Login without password return code 400 and bad request")
    @Test
    public void loginWithoutPassword(){
        actual=loginEndpoint.loginWithoutPassword();
        expected=courier.CODE_400_BAD_REQUEST+","+courier.MESSAGE_400_BAD_REQUEST;
        assertEquals(expected,actual);
    }
    @DisplayName("Login without login return code 400 and bad request")
    @Test
    public void loginWithoutLogin(){
        actual=loginEndpoint.loginWithoutLogin();
        expected=courier.CODE_400_BAD_REQUEST_LOGIN+","+courier.MESSAGE_400_BAD_REQUEST_LOGIN;
        assertEquals(expected,actual);
    }
    @DisplayName("Login with wrong login return code 400 and not found")
    @Test
    public void loginWithWrongLogin(){
        //создание нового курьера
        //правильный пароль
        //добавление ошибки в логин
        courier.setLogin(courier.getLoginCourier() + RandomStringUtils.randomAlphabetic(3));
        //логин за нового курьера
        actual=new LoginEndpoint(courier).loginWithLoginAndPassword();
        expected=courier.CODE_404_NOT_FOUND+","+courier.MESSAGE_404_NOT_FOUND;

        assertEquals(expected,actual);
    }
    @DisplayName("Login with wrong password return code 400 and not found")
    @Test
    public void loginWithWrongPassword(){
        //создание нового курьера
        //правильный логин
        //добавление ошибки в пароль
        courier.setPassword(courier.getPasswordCourier() + RandomStringUtils.randomAlphabetic(3));
        //логин за нового курьера
        actual=new LoginEndpoint(courier).loginWithLoginAndPassword();
        expected=courier.CODE_404_NOT_FOUND+","+courier.MESSAGE_404_NOT_FOUND;

        assertEquals(expected,actual);
    }
    //несуществующий
    @DisplayName("Login with wrong unexisted courier return code 404 and not found")
    @Test
    public void loginWithEnexistedCourier(){
        //изменение логина и пароля на новые рендомные, которых нет в системе
        courier.setLogin(RandomStringUtils.randomAlphabetic(10));
        courier.setPassword(RandomStringUtils.randomAlphabetic(10));
        //логин за этого курьера
        actual=new LoginEndpoint(courier).loginWithLoginAndPassword();
        expected=courier.CODE_404_NOT_FOUND+","+courier.MESSAGE_404_NOT_FOUND;
        //assertEquals(expected,expected);
        assertEquals(expected,actual);
    }
}
