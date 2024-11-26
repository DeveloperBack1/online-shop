package com.schneider.onlineshop.service;

import com.schneider.onlineshop.dto.UserDto;
import com.schneider.onlineshop.entity.UserEntity;
import com.schneider.onlineshop.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
//
//@Service
//public class UsersService {
//
//    private List<UserDto> userDtoList;
//
//
//    @PostConstruct
//    void init() {
//        userDtoList = new ArrayList<>();
//        userDtoList.add(new UserDto(1L, "Вася Пупкин", "a@test.us", "1111111", "12345"));
//        userDtoList.add(new UserDto(2L, "Дуня Петрова", "duna@test.us", "2222222", "34567"));
//
//        System.out.println("Выполняем логику при создании объекта "+this.getClass().getName());
//    }
//    public List<UserDto> getAllUsers() {
//        return userDtoList;
//    }
//
//    public UserDto getUserById(Long id) {
//        return userDtoList.stream()
//                .filter(userDto -> userDto.getUserID()==id)
//                .findFirst()
//                .orElse(null);
//    }
//
//    public UserDto updateUser(UserDto userDTO) {
//        UserDto result = userDtoList.stream()
//                .filter(u -> u.getUserID() == userDTO.getUserID())
//                .findFirst()
//                .orElse(null);
//        if(result!=null) {
//            result.setName(userDTO.getName());
//            result.setEmail(userDTO.getEmail());
//            result.setPhoneNumber(userDTO.getPhoneNumber());
//        }
//        return result;
//    }
//}


@RequiredArgsConstructor
public class UsersService {

    private final UserRepository usersRepository;

    @PostConstruct
    void init() {
        UserEntity user1 = new UserEntity(null, "Вася Пупкин", "a@test.us",
                "1111111", "12345");
        usersRepository.save(user1);
        UserEntity user2 = new UserEntity(null, "Дуня Петрова", "duna@test.us",
                "2222222", "34567");
        usersRepository.save(user2);

        System.out.println("Выполняем логику при создании объекта " + this.getClass().getName());
    }

    public List<UserDto> getAllUsers() {
        List<UserEntity> usersEntities = usersRepository.findAll();
        return usersEntities.stream()
                .map(entity -> new UserDto(entity.getUserID(), entity.getName(),
                        entity.getEmail(), entity.getPhoneNumber(), entity.getPasswordHash()))
                .collect(Collectors.toList());
    }

    public UserDto getUserById(Long id) {
        UserEntity usersEntity = usersRepository.findById(id).orElse(new UserEntity());
        return new UserDto(usersEntity.getUserID(), usersEntity.getName(), usersEntity.getEmail(),
                usersEntity.getPhoneNumber(), usersEntity.getPasswordHash());
    }

//    public UserDto getUserByEmail(String email) {
//        UserEntity usersEntity = usersRepository.findByEmail(email); // OQL method assumed
//        return new UserDto(usersEntity.getUserID(), usersEntity.getName(),
//                usersEntity.getEmail(), usersEntity.getPhoneNumber(), usersEntity.getPasswordHash());
//    }

    public UserDto getUserByEmail(String email) {
        UserEntity usersEntity = usersRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
        return new UserDto(usersEntity.getUserID(), usersEntity.getName(),
                usersEntity.getEmail(), usersEntity.getPhoneNumber(), usersEntity.getPasswordHash());
    }


    public boolean createUser(UserDto newUser) {
        UserEntity createUserEntity = new UserEntity(null, newUser.getName(),
                newUser.getEmail(), newUser.getPhoneNumber(), newUser.getPasswordHash());
        UserEntity returnUserEntity = usersRepository.save(createUserEntity);
        return returnUserEntity.getUserID() != null;
    }

    public UserDto updateUser(UserDto updUser) {
        UserEntity updateUserEntity = new UserEntity(updUser.getUserID(), updUser.getName(), updUser.getEmail(),
                updUser.getPhoneNumber(), updUser.getPasswordHash());
        UserEntity returnUserEntity = usersRepository.save(updateUserEntity);
        return new UserDto(returnUserEntity.getUserID(), returnUserEntity.getName(),
                returnUserEntity.getEmail(), returnUserEntity.getPhoneNumber(), returnUserEntity.getPasswordHash());
    }

    public void deleteUser(Long id) {
        usersRepository.deleteById(id);
    }
}
