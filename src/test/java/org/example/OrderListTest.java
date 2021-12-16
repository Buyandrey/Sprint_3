package org.example;


import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class OrderListTest {

    @DisplayName("Order without keys return list of orders and code 200")
    @Test
    public void testOrderWithoutKeysReturnListOfOrders()throws Exception{
        OrderEndpoint order= new OrderEndpoint();
// Создание заказа запросом без ключа и Проверка что тело ответа содержит список заказов.
        assertEquals(true,order.getOrgerList().contains("\"orders\""));
    }
}
