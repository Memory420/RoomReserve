package com.memory.roomreserve.controller;

import com.memory.roomreserve.model.Room;
import com.memory.roomreserve.repository.RoomRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class RoomController {
    private final RoomRepository roomRepository;

    public RoomController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }
    @GetMapping("/register-room")
    public String showRegistrationForm(@AuthenticationPrincipal User user, Model model) {
        return "register-room";
    }
    @GetMapping("/rooms")
    public String getRooms(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("username", user.getUsername());

        String role = user.getAuthorities().stream()
                .findFirst()
                .map(a -> a.getAuthority())
                .orElse("UNKNOWN");
        model.addAttribute("role", role);

        if (role.equals("ROLE_ADMIN")) {
            // Админ видит все комнаты
            List<Room> rooms = roomRepository.findAll();
            model.addAttribute("rooms", rooms);
            return "admin-room-list";
        } else {
            // Обычный пользователь видит только доступные комнаты
            List<Room> rooms = roomRepository.findByAvailableTrue();
            model.addAttribute("rooms", rooms);
            return "room-list";
        }
    }

    @PostMapping("/register-room")
    public String registerRoom(
            @RequestParam String name,
            @RequestParam float price,
            @RequestParam int size,
            Model model) {
        if (roomRepository.findByName(name) != null) {
            model.addAttribute("errorMessage", "Room with this name already exists!");
            return "register-room";
        }

        Room room = new Room(name, price, size);
        roomRepository.save(room);
        model.addAttribute("successMessage", "Room registered successfully!");
        return "redirect:/rooms";
    }
}
