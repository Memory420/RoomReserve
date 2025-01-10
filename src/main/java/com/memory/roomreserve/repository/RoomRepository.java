package com.memory.roomreserve.repository;

import com.memory.roomreserve.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    Room findById(long id);
    Room findByName(String name);
    List<Room> findByAvailableTrue();
}
