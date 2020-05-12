package com.se.demo.models;

public class LiftModel {

	private int liftId;
	private String state;
	private String direction;
	private int person;
	private int floor;
	
	public int getLiftId() {
		return liftId;
	}
	public void setLiftId(int liftId) {
		this.liftId = liftId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public int getPerson() {
		return person;
	}
	public void setPerson(int person) {
		this.person = person;
	}
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	
}
