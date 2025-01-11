package com.memory.roomreserve;

import com.memory.roomreserve.model.Room;
import com.memory.roomreserve.model.User;
import com.memory.roomreserve.repository.RoomRepository;
import com.memory.roomreserve.repository.UserRepository;
import com.memory.roomreserve.utils.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class RoomReserveApplication {
    private static final Logger log = LoggerFactory.getLogger(RoomReserveApplication.class);
    private final PasswordEncoder passwordEncoder;

    public RoomReserveApplication(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args) {
        SpringApplication.run(RoomReserveApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadAndCheckData(UserRepository userRepository, RoomRepository roomRepository) {
        return args -> {
            // Загрузка пользователей из JSON (если у тебя уже есть этот код)
            List<User> users = JsonParser.loadUsers();
            if (userRepository.count() == 0 && !users.isEmpty()) {
                users.forEach(user -> user.setPassword(passwordEncoder.encode(user.getPassword())));
                userRepository.saveAll(users);
                System.out.println("Loaded users from JSON.");
            }

            // Загрузка комнат из JSON
            List<Room> rooms = JsonParser.loadRooms();
            if (roomRepository.count() == 0 && !rooms.isEmpty()) {
                roomRepository.saveAll(rooms);
                System.out.println("Loaded rooms from JSON.");
            }

            // Проверка данных
            System.out.println("Users in the database: " + userRepository.count());
            System.out.println("Rooms in the database: " + roomRepository.count());
        };
    }
}
