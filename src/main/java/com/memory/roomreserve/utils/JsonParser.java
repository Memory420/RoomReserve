package com.memory.roomreserve.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.memory.roomreserve.model.Room;
import com.memory.roomreserve.model.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class JsonParser {
    public static List<Room> loadRooms(){
        ObjectMapper mapper = new ObjectMapper();
        List<Room> rooms = List.of();
        try {
            InputStream inputStream = JsonParser.class.getClassLoader().getResourceAsStream("rooms.json");

            if (inputStream == null) {
                System.out.println("Не удалось найти файл в resources!");
                return new ArrayList<>();
            }

            rooms = mapper.readValue(inputStream,
                    mapper.getTypeFactory().constructCollectionType(List.class, Room.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rooms;
    }
    public static List<User> loadUsers(){
        ObjectMapper mapper = new ObjectMapper();
        List<User> users = List.of();
        try {
            InputStream inputStream = JsonParser.class.getClassLoader().getResourceAsStream("users.json");

            if (inputStream == null) {
                System.out.println("Не удалось найти файл в resources!");
                return new ArrayList<>();
            }

            users = mapper.readValue(inputStream,
                    mapper.getTypeFactory().constructCollectionType(List.class, User.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static void main(String[] args) {
        List<Room> rooms = loadRooms();
        rooms.forEach(room -> System.out.println(room.getName()));
        List<User> users = loadUsers();
        users.forEach(user -> System.out.println(user.getUsername()));
    }
}
