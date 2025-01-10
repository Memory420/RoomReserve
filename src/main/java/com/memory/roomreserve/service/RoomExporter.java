package com.memory.roomreserve.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.memory.roomreserve.model.Room;
import com.memory.roomreserve.repository.RoomRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class RoomExporter {
    private static final Logger log = LoggerFactory.getLogger(RoomExporter.class);

    private final RoomRepository roomRepository;

    public RoomExporter(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }
    public void exportRoomsToJson() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        List<Room> rooms = roomRepository.findAll();
        File file = new File("src/main/resources/rooms.json");

        try {
            mapper.writeValue(file, rooms);
            log.info("Exported rooms to json file");
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Failed to export rooms to json file");
        }
    }
}
