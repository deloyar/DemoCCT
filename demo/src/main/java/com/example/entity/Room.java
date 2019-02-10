package com.example.entity;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "room")
public class Room implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer roomId;
	private String name;
	private boolean status;
	private Set<Reservation> books = new HashSet<Reservation>(0);

	public Room() {
	}

	public Room(String name) {
		this.name = name;
	}

	@Id
	@GeneratedValue
	@Column(name = "room_id", unique = true, nullable = false)
	public Integer getRoomId() {
		return this.roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	@Column(name = "name", nullable = false, length = 200)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@OneToMany(fetch = FetchType.LAZY, mappedBy = "room")
	public Set<Reservation> getBooks() {
		return this.books;
	}

	public void setBooks(Set<Reservation> books) {
		this.books = books;
	}

	@Column(name = "status")
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
