package com.example.auth;

import com.example.entity.Reservation;
import com.example.service.DateUtils;


public class Booking {

	public enum BookType {
		FREE, BOOK, NO_FILL
	}

	private Integer id;
	private String name;
	private String startTime;
	private String endTime;
	private BookType type = BookType.FREE;
	private int rowSpan;
	private Integer roomId;

	public Booking() {

	}

	public Booking(Reservation book) {
		this.id = book.getBookId();
		this.name = book.getName();
		this.startTime = DateUtils.timeFormat(book.getStartTime());
		this.endTime = DateUtils.timeFormat(book.getEndTime());
		this.type = BookType.BOOK;
		this.rowSpan = DateUtils.getRowSpan(startTime, endTime);
		this.roomId = book.getRoom().getRoomId();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public BookType getType() {
		return type;
	}

	public void setType(BookType type) {
		this.type = type;
	}

	public int getRowSpan() {
		return rowSpan;
	}

	public void setRowSpan(int rowSpan) {
		this.rowSpan = rowSpan;
	}

	@Override
	public String toString() {
		return "Booking [id=" + id + ", name=" + name + ", comment=" + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", type=" + type + ", rowSpan=" + rowSpan + "]";
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}
}