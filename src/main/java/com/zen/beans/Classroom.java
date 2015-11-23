package com.zen.beans;

import java.util.Map;

public class Classroom extends AbstractRoom {
	
	private int maxNbrRoom;

	public Classroom() {
		super();
		
	}

	public Classroom(int roomId, String roomName, int superficy/*, Map<Accessory, Integer> roomAccesory*/, int maxNbrRoom) {
		super(roomId, roomName, superficy/*, roomAccesory*/);
		this.maxNbrRoom = maxNbrRoom;
	}

	public int getMaxNbrRoom() {
		return maxNbrRoom;
	}

	public void setMaxNbrRoom(int maxNbrRoom) {
		this.maxNbrRoom = maxNbrRoom;
	}
	
	
	
	

}
