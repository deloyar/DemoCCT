package com.example.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.entity.Reservation;

public interface BookRepository extends CrudRepository<Reservation, Integer> {

	List<Reservation> findByBookDate(Date date);
}
