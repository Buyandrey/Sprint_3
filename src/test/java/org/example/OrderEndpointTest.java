package org.example;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
public class OrderEndpointTest {

    private String actual;
    private final ArrayList<String> color;

    public OrderEndpointTest(ArrayList<String> color){
        this.color = color;
    }
    @Parameterized.Parameters
    public static Object[] getTestData() {
        ArrayList<String> c0=new ArrayList<>(); // Пустая строка, которая будет в теле запроса, задание 3.3
        ArrayList<String> c1b=new ArrayList<>();// Пустая строка, которая будет в теле запроса, задание 3.1
        ArrayList<String> c1g=new ArrayList<>();// Пустая строка, которая будет в теле запроса, задание 3.1
        ArrayList<String> c2=new ArrayList<>(); //  Пустая строка, которая будет в теле запроса, задание 3.2
        c0.clear();
        c1b.add("BLACK");
        c1g.add("GREY");
        c2.add("BLACK");
        c2.add("GREY");
        return new Object[]{
                c0,
                c1b,
                c1g,
                c2
        };
    }
    @DisplayName("Test orders with different colours set and response contains track")
    @Description("Put different key-value on JSON body request")
    @Test
    public void testOrders()throws Exception{
// Создание объекта заказа
        OrderEndpoint order = new OrderEndpoint();
// Получение ответа после отправки запроса на создания заказа
        actual = order.makeOrder(color);
// Проверка что ответ на запрос с тем или иным набором цвета содержит в теле ответа трэкномер
        assertEquals(true, actual.contains("track"));
    }
}

//работает без параметризации
/*
public class OrderEndpointTest {

    private String actual;
    private JSONObject jsonOrder;
    private OrderEndpoint orderEndpoint;
    private  String expected;
    private  ArrayList<String> colorValue;

    @DisplayName("Test orders with different colours set")
    @Test
    public void testOrders(){

        jsonOrder = new JSONObject();
        jsonOrder.put("firstName"    ,   RandomStringUtils.randomAlphabetic(5));
        jsonOrder.put("lastName"     ,   RandomStringUtils.randomAlphabetic(3));
        jsonOrder.put("address"      ,   RandomStringUtils.randomAlphabetic(15));
        jsonOrder.put("metroStation" ,   "3");
        jsonOrder.put("phone"        ,   "7 800 355 35 35");
        jsonOrder.put("rentTime"     ,   5);
        jsonOrder.put("deliveryDate" ,   "2020-06-06");
        jsonOrder.put("comment"      ,   RandomStringUtils.randomAlphabetic(10));
        jsonOrder.put("color"        ,   colorValue);

        orderEndpoint= new OrderEndpoint();
        actual= orderEndpoint.createOrder(jsonOrder);
        System.err.println(actual);
    }
}

 */