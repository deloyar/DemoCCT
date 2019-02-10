package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.entity.Room;

public interface RoomRepository extends CrudRepository<Room, Integer> {

}

