package com.memory.roomreserve.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Room room;
    @ManyToOne
    private User user;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    protected Reservation() {

    }
    public Reservation(User user, Room room, LocalDateTime startDate, LocalDateTime endDate) {
        this.user = user;
        this.room = room;
        this.startTime = startDate;
        this.endTime = endDate;
        if (room != null) {
            room.setAvailable(false);
        }
    }
    public int getId() {
        return id;
    }

    public Room getRoom() {
        return room;
    }

    public User getUser() {
        return user;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", room=" + room.getName() +
                ", user=" + user.getUsername() +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
