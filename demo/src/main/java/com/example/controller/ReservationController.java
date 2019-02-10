package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.entity.Reservation;
import com.example.entity.User;
import com.example.service.BookService;

@RestController
public class ReservationController {
	
	@Autowired
	private BookService bookService;
	
	private Reservation myBook = new Reservation();
	private List<Reservation> bookList = new ArrayList<Reservation>();
	private org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(ReservationController.class);
	
	
	@PostMapping(value = "/book/books")
    public ResponseEntity<List<Reservation>> listAllBooks(String date) {
		logger.info("Received: All Book List Request");
		try{
        List<Reservation> books = bookService.getBooks(date);
        return new ResponseEntity<List<Reservation>>(books, HttpStatus.OK);
		}catch(Exception ex){
			logger.info("Exception: "+ex.getMessage());
		}
		return new ResponseEntity<List<Reservation>>(bookList, HttpStatus.OK);
    }

	@PostMapping(value = "/book/save")
	public ResponseEntity<Reservation> save(HttpServletRequest httpRequest, @RequestBody Reservation book) {
		
		try {
			if (!validateBooking(book)) {
				return new ResponseEntity<Reservation>(myBook, HttpStatus.OK);
			}
			book.setUser(new User(1));
			countMinuteAndHour(book);
			Reservation b =bookService.save(book);
			return new ResponseEntity<Reservation>(b, HttpStatus.OK);
		}catch(Exception ex) {
			logger.info("Exception: "+ex.getMessage());
		}
		return new ResponseEntity<Reservation>(myBook, HttpStatus.OK);
	}
	

	@PostMapping(value = "/book/update")
	public ResponseEntity<Reservation> update(HttpServletRequest httpRequest, @RequestBody Reservation book) {
		try {
			if (!validateBooking(book)) {
				return new ResponseEntity<Reservation>(myBook, HttpStatus.OK);
			}
			book.setUser(new User(1));

			countMinuteAndHour(book);

			Reservation b =bookService.update(book);
			return new ResponseEntity<Reservation>(b, HttpStatus.OK);	
		}catch(Exception ex) {
			logger.info("Exception: "+ex.getMessage());
		}	
		return new ResponseEntity<Reservation>(myBook, HttpStatus.OK);
		
	}


	private void countMinuteAndHour(Reservation book) {
		if (book.getStartTime() != null && book.getEndTime() != null) {
			long duration = book.getEndTime().getTime() - book.getStartTime().getTime();
			int minutes = (int) (duration / 1000 / 60);
			double hours = (double) minutes / 60;

			book.setMinutes(minutes);
			book.setHours(hours);
		}
	}

	private boolean validateBooking(Reservation book) {

		if (StringUtils.isEmpty(book.getName())) {
			return false;
		}
		if (book.getStartTime() != null && book.getEndTime() != null) {

		}

		return true;
	}
}
