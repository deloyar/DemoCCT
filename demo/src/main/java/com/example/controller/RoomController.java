package com.example.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.entity.Room;
import com.example.service.RoomService;

@RestController
public class RoomController{
	@Autowired
	private RoomService roomService;

	private Room myroom = new Room();
	private List<Room> roomList = new ArrayList<Room>();
	private org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(RoomController.class);
	
	@RequestMapping(value = "/room/rooms", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<List<Room>> getAllRooms() {
		logger.info("Received: Room List Request");
		try {
			roomList = roomService.findAll(Room.class);
			return new ResponseEntity<List<Room>>(roomList, HttpStatus.OK);
		}catch(Exception ex){
			logger.info("Exception: "+ex.getMessage());
		}
		return new ResponseEntity<List<Room>>(roomList, HttpStatus.OK);
		
	}
	
	@PostMapping(value = "/room/save")
	public ResponseEntity<Room> save(HttpServletRequest httpRequest, @RequestBody Room room) {
		logger.info("Received: Add Room request");
		
		try{
			if (room != null) {
				Room r=roomService.save(room);
				return new ResponseEntity<Room>(r, HttpStatus.OK);
			}
			return new ResponseEntity<Room>(myroom, HttpStatus.OK);
			
		}catch(Exception ex) {
			logger.info("Exception: "+ex.getMessage());
		}
		return new ResponseEntity<Room>(myroom, HttpStatus.OK);
	}

	@PostMapping(value = "/room/update")
	public ResponseEntity<Room> update(HttpServletRequest httpRequest, @RequestBody Room room) {
		logger.info("Received room update request for "+room.getName());
		try {
			
			if (room != null) {
				Room r =roomService.update(room);
				return new ResponseEntity<Room>(r, HttpStatus.OK);
			}
		}catch(Exception ex) {
			logger.info("Exception "+ex.getMessage());
		}		
		return new ResponseEntity<Room>(myroom, HttpStatus.OK);

		
	}

	@PostMapping(value = "/room/delete")
	public void delete(HttpServletRequest httpRequest, @RequestBody Room room) {
		logger.info("Received room delete request for "+room.getName());
		try{
			if (room != null) {
				roomService.delete(Room.class, room.getRoomId());
			}
		}catch(Exception ex) {
			logger.info("Exception "+ex.getMessage());
		}
		
		
	}


}