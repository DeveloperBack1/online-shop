package com.schneider.onlineshop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.schneider.onlineshop.dto.UserDto;

import com.schneider.onlineshop.service.UsersService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UsersController.class)
class UsersControllerTest {

        @Autowired
        private MockMvc mockMvc; // для имитации запросов пользователей

        @MockBean
        private UsersService usersServiceMock;

        @Autowired
        private ObjectMapper objectMapper;



    @Test
    void getAllUsers() throws Exception {

        when(usersServiceMock.getAllUsers()).thenReturn(List.of(new UserDto(1L,
                "Test","max@yahoo.org","234567","123321")));
        this.mockMvc.perform(get("/users"))
                .andDo(print()) //печать лога вызова
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..phoneNumber").exists())
                .andExpect(jsonPath("$..userId").value(1))
                .andExpect(jsonPath("$..name").value("Test"));

    }

    @Test
    void getUserById() throws Exception {

        when(usersServiceMock.getUserById(anyLong())).thenReturn(new UserDto(1L,"Test",
                "max@yahoo.org","234567","123321"));
        this.mockMvc.perform(get("/users/{id}", 1))
                .andDo(print()) //печать лога вызова
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.userId").exists())
                .andExpect(jsonPath("$.userId").value(1));

    }

    @Test
    void updateUser() throws Exception {
        // Исходные данные
        UserDto requestUserDto = new UserDto(1L, "Anton", "anton@mail.org ","657879","4321");
        UserDto responseUserDto = new UserDto(1L, "Anton", "anton@yahoo.org ","657339","4321");


        // Настройка моков
        Mockito.when(usersServiceMock.updateUser(Mockito.any(UserDto.class)))
                .thenReturn(responseUserDto);

        // Выполнение PUT-запроса с проверкой результата
        mockMvc.perform(put("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestUserDto))) // JSON тело запроса
                .andExpect(status().isCreated()) // Проверка статуса HTTP 201

                .andExpect(jsonPath("$.userId").value(1L))
                .andExpect(jsonPath("$.name").value("Anton")) // Проверка Name
                .andExpect(jsonPath("$.email").value("anton@yahoo.org ")) // Проверка email
                .andExpect(jsonPath("$.phoneNumber").value("657339"))
                .andExpect(jsonPath("$.passwordHash").value("4321"));

    }
}

