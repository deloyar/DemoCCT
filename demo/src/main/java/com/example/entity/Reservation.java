package com.example.entity;
import static javax.persistence.GenerationType.IDENTITY;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "book")
public class Reservation implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private Integer bookId;
	private Room room;
	private User user;
	private Date bookDate;
	private Time startTime;
	private Time endTime;
	private String name;
	private int minutes;
	private double hours;

	public Reservation() {
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "book_id", unique = true, nullable = false)
	public Integer getBookId() {
		return this.bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "room_id", nullable = false)
	public Room getRoom() {
		return this.room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "book_user_id", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "start_time", nullable = false, length = 19)
	public Time getStartTime() {
		return this.startTime;
	}

	public void setStart(String startTime) throws ParseException {
		if (startTime != null) {
			SimpleDateFormat smt = new SimpleDateFormat("HH:mm");
			Date date = smt.parse(startTime);
			this.startTime = new Time(date.getTime());
		}
	}

	public void setStartTime(Time time){
		this.startTime = time;
	}
	
	@Column(name = "end_time", nullable = false, length = 19)
	public Time getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Time time){
		this.endTime = time;
	}
	
	public void setEnd(String endTime) throws ParseException {
		if (endTime != null) {
			SimpleDateFormat smt = new SimpleDateFormat("HH:mm");
			Date date = smt.parse(endTime);
			this.endTime = new Time(date.getTime());
		}
	}

	@Column(name = "name", length = 200)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBookDate() {
		return bookDate;
	}

	public void setDate(String bookDate) throws ParseException {
		if (bookDate != null) {
			SimpleDateFormat smt = new SimpleDateFormat("yyyy-MM-dd");
			Date date = smt.parse(bookDate);
			this.bookDate = new Date(date.getTime());
		}
	}

	public void setBookDate(Date date) {
		this.bookDate = date;
	}

	@Column(name = "minutes")
	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	@Column(name = "hours")
	public double getHours() {
		return hours;
	}

	public void setHours(double hours) {
		this.hours = hours;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", room=" + room + ", user=" + user + ", bookDate=" + bookDate
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", name=" + name + ", comment="
				+ ", minutes=" + minutes + ", hours=" + hours + "]";
	}
}
