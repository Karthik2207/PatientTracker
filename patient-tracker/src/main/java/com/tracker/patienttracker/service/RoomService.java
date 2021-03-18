package com.tracker.patienttracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tracker.patienttracker.model.Room;
import com.tracker.patienttracker.repository.RoomRepository;

@Service
public class RoomService {
	@Autowired
	RoomRepository roomRepository;
	
	public Room getRoomByNo(int roomNo) {
		return roomRepository.findById(roomNo).get();
	}
}
