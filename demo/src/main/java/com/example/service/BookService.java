package com.example.service;
import java.util.List;
import com.example.entity.Reservation;
public interface BookService extends BaseService {

	List<Reservation> getBooks(String date);
}