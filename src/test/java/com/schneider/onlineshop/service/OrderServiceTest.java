package com.schneider.onlineshop.service;

import com.schneider.onlineshop.dto.OrderDto;
import com.schneider.onlineshop.entity.OrderEntity;
import com.schneider.onlineshop.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepositoryMock;

    @InjectMocks
    private OrderService orderService;

    private OrderEntity orderEntityTest1;
    private OrderDto orderDtoTest1;

  //  private final List<OrderDto> orderDtoList = new ArrayList<>();

//    @BeforeEach
//    void setUp() {
//        // Создаём экземпляр сервиса перед каждым тестом
//        orderService = new OrderService(orderRepositoryMock);
//    }

    @Test
    void getAllOrders() {
        // Создаём тестовые объекты OrderDto
        OrderDto order1 = new OrderDto(
                1L, 2L, Timestamp.valueOf("2024-05-15 10:00:00"),
                null, "Böhmerwaldweg 6", "1234567", "Delivered");

        OrderDto order2 = new OrderDto(
                2L, 3L, Timestamp.valueOf("2024-05-16 12:00:00"),
                null, "Böhmerwaldweg 8", "1234533", "Pending");

        // Добавляем заказы в orderService напрямую (если метод addOrder отсутствует)
        List<OrderDto> orderDtoList = orderService.getAllOrders();
        orderDtoList.add(order1);
        orderDtoList.add(order2);


        // Выполняем метод getAllOrders()
//        List<OrderDto> actualOrders = orderService.getAllOrders();

        // Проверка: сравниваем ожидаемый и фактический результат
        assertEquals(1, orderDtoList.get(0).getOrderId());
        assertNotNull(orderDtoList.get(0).getCreatedAt());
        assertEquals(2, orderDtoList.size());
        assertNotNull(orderDtoList.get(1).getCreatedAt());

    }

//    @Test
//    void getOrderByIdTest() {
//        Long testId = 1L;
//
//        List<OrderDto> orderDtoList = new ArrayList<>();
//
//        when(orderRepositoryMock.findById(testId)).thenReturn(Optional.of(orderEntityTest1));
//
//        Optional <OrderDto >actualOrderDto = orderService.getOrderById(testId);
//          assertTrue(actualOrderDto.isPresent());
//     //   assertEquals(testId, actualOrderDto.get().getOrderId());
//     //   assertEquals(orderDtoList.get(0).getOrderId(), actualOrderDto.get().getOrderId());
//     //   assertEquals(orderDtoList.get(0).getCreatedAt(), result.get().getCreatedAt());
//
//
//    }
   @Test
    void createOrderTest() {
       // Arrange
       OrderDto orderDtoInput = new OrderDto(1L,2L,
               null,null,"Burgstr.5",
               "1234567","Delivered");
     //  List<OrderDto> orderDtoList = new ArrayList<>();
      // orderService.setOrderDtoList(orderDtoList); // если это необходимо

       // Act


       OrderDto result = orderService.createOrder(orderDtoInput);
       assertNotNull(result);
       assertNotNull(result.getCreatedAt());
       assertEquals(result.getOrderId(), 1L);



   }

}








