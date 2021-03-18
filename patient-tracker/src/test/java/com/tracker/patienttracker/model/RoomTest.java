package com.tracker.patienttracker.model;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RoomTest {

	@Test
	void getRoomNo()
	{
		Room room=new Room();
		room.setRoomNo(1);
		assertEquals(room.getRoomNo(),1);
	}
	@Test
	void getRoomType()
	{
		Room room=new Room();
		room.setRoomType("Single");
		assertEquals(room.getRoomType(),"Single");
	}
	@Test
	void getRoomCharges()
	{
		Room room=new Room();
		double d=100.00;
		room.setRoomCharges(d);
		assertEquals(room.getRoomCharges(),d,0);
	}
	@Test
	void getNursingCharges()
	{
		Room room=new Room();
		double d=100.00;
		room.setNursingCharges(d);
		assertEquals(room.getNursingCharges(),d,0);
	}
	@Test
	void getTariffCharges()
	{
		Room room=new Room();
		double d=100.00;
		room.setTariff(d);
		assertEquals(room.getTariff(),d,0);
	}
	@Test
	void getAllArgsConst()
	{
		Room room=new Room(1, null, 0, 0, 0);
		String str=room.toString();
		assertEquals(room.toString(),str);
	}
}
