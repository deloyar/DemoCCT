package com.example.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Reservation;
import com.example.repository.BookRepository;
import com.example.service.BookService;
import com.example.service.DateUtils;

@Service
@Transactional
public class BookServiceImpl extends BaseServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Override
	public List<Reservation> getBooks(String date) {
		Date d = new Date();
		try {
			d = DateUtils.DATE_FORMAT.parse(date);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return bookRepository.findByBookDate(d);
	}

}
