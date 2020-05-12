package com.se.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.se.demo.models.LiftModel;
import com.se.demo.models.ResponseModel;

public class Lift implements Runnable {

	private int fromFloor;
	private int toFloor;
	private int lift_1_currentFloor;
	private int lift_2_currentFloor;

	private int eta;

	public static LiftModel lift_1 = new LiftModel();
	public static LiftModel lift_2 = new LiftModel();

	static ResponseModel response = new ResponseModel();

	ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
	String json;

	private static final Logger logger = LogManager.getLogger(Lift.class);

	public Lift(int fromFloor, int toFloor) {

		this.fromFloor = fromFloor;
		this.toFloor = toFloor;

	}

	public void run() {

		List<String> states = new ArrayList<String>();
		states.add("IDLE");
		states.add("TO_PICKUP");
		states.add("PICKUP");
		states.add("TO_DROPOFF");
		states.add("DROPOFF");

		List<String> directions = new ArrayList<String>();
		directions.add("NAN");
		directions.add("UP");
		directions.add("DOWN");

		if (lift_1.getState() == null) {
			lift_1.setState(states.get(0));
			lift_1.setDirection(directions.get(0));
		}

		if (lift_2.getState() == null) {
			lift_2.setState(states.get(0));
			lift_2.setDirection(directions.get(0));
		}

		if (lift_1.getState().equals(states.get(0))) {

			if (lift_1_currentFloor == 0) {
				lift_1_currentFloor = 1;
			}

			lift_1.setLiftId(1);
			lift_1.setFloor(lift_1_currentFloor);

			// int floorsToGo = fromFloor - lift_1_currentFloor;
			// eta = floorsToGo * 3;
			eta = generateTime(lift_1_currentFloor, fromFloor);
			response.setETA(eta);

			try {
				json = ow.writeValueAsString(lift_1);
				logger.info(json.toString());
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}

			lift_1.setState(states.get(1));

			if (lift_1.getState().equals(states.get(0)) || lift_1.getState().equals(states.get(1))) {
				lift_1.setPerson(0);
			} else {
				lift_1.setPerson(1);
			}

			if ((fromFloor - lift_1_currentFloor) > 0) {
				lift_1.setDirection(directions.get(1));
			} else if ((fromFloor - lift_1_currentFloor) < 0) {
				lift_1.setDirection(directions.get(2));
			}

			try {
				json = ow.writeValueAsString(lift_1);
				logger.info(json);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}

			synchronized (Thread.currentThread()) {
				try {
					Thread.sleep(eta * 1000);
				} catch (InterruptedException e) {
				}
			}

			lift_1_currentFloor = fromFloor;
			lift_1.setFloor(lift_1_currentFloor);
			lift_1.setState(states.get(2));
			lift_1.setDirection(directions.get(0));

			if (lift_1.getState().equals(states.get(0)) || lift_1.getState().equals(states.get(1))) {
				lift_1.setPerson(0);
			} else {
				lift_1.setPerson(1);
			}

			try {
				json = ow.writeValueAsString(lift_1);
				logger.info(json);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}

			synchronized (Thread.currentThread()) {
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
				}
			}

			lift_1.setState(states.get(3));
			lift_1.setFloor(lift_1_currentFloor);

			if ((toFloor - lift_1_currentFloor) > 0) {
				lift_1.setDirection(directions.get(1));
			} else if ((toFloor - lift_1_currentFloor) < 0) {
				lift_1.setDirection(directions.get(2));
			}

			if (lift_1.getState().equals(states.get(0)) || lift_1.getState().equals(states.get(1))) {
				lift_1.setPerson(0);
			} else {
				lift_1.setPerson(1);
			}

			try {
				json = ow.writeValueAsString(lift_1);
				logger.info(json);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}

			synchronized (Thread.currentThread()) {
				try {
					int time = generateTime(fromFloor, toFloor);
					Thread.sleep(time * 1000);
				} catch (InterruptedException e) {
				}
			}

			lift_1_currentFloor = toFloor;
			lift_1.setState(states.get(4));
			lift_1.setFloor(lift_1_currentFloor);
			lift_1.setDirection(directions.get(0));

			if (lift_1.getState().equals(states.get(0)) || lift_1.getState().equals(states.get(1))) {
				lift_1.setPerson(0);
			} else {
				lift_1.setPerson(1);
			}

			try {
				json = ow.writeValueAsString(lift_1);
				logger.info(json);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}

			synchronized (Thread.currentThread()) {
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
				}
			}

			lift_1.setState(states.get(0));
			lift_1.setDirection(directions.get(0));

			if (lift_1.getState().equals(states.get(0)) || lift_1.getState().equals(states.get(1))) {
				lift_1.setPerson(0);
			} else {
				lift_1.setPerson(1);
			}

			try {
				json = ow.writeValueAsString(lift_1);
				logger.info(json);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (lift_2.getState().equals(states.get(0))) {

			if (lift_2_currentFloor == 0) {
				lift_2_currentFloor = 1;
			}

			lift_2.setLiftId(2);
			lift_2.setFloor(lift_1_currentFloor);

			// int floorsToGo = fromFloor - lift_1_currentFloor;
			// eta = floorsToGo * 3;
			eta = generateTime(lift_2_currentFloor, fromFloor);
			response.setETA(eta);

			try {
				json = ow.writeValueAsString(lift_2);
				logger.info(json);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}

			lift_1.setState(states.get(1));

			if ((fromFloor - lift_2_currentFloor) > 0) {
				lift_1.setDirection(directions.get(1));
			} else if ((fromFloor - lift_2_currentFloor) < 0) {
				lift_1.setDirection(directions.get(2));
			}

			if (lift_2.getState().equals(states.get(0)) || lift_2.getState().equals(states.get(1))) {
				lift_2.setPerson(0);
			} else {
				lift_2.setPerson(1);
			}

			try {
				json = ow.writeValueAsString(lift_2);
				logger.info(json);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}

			synchronized (Thread.currentThread()) {
				try {
					Thread.sleep(eta * 1000);
				} catch (InterruptedException e) {
				}
			}

			lift_2_currentFloor = fromFloor;
			lift_2.setFloor(lift_2_currentFloor);
			lift_2.setState(states.get(2));
			lift_2.setDirection(directions.get(0));

			if (lift_2.getState().equals(states.get(0)) || lift_2.getState().equals(states.get(1))) {
				lift_2.setPerson(0);
			} else {
				lift_2.setPerson(1);
			}

			try {
				json = ow.writeValueAsString(lift_2);
				logger.info(json);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}

			synchronized (Thread.currentThread()) {
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
				}
			}

			lift_2.setState(states.get(3));
			lift_2.setFloor(lift_2_currentFloor);

			if ((toFloor - lift_2_currentFloor) > 0) {
				lift_2.setDirection(directions.get(1));
			} else if ((toFloor - lift_2_currentFloor) < 0) {
				lift_2.setDirection(directions.get(2));
			}

			if (lift_2.getState().equals(states.get(0)) || lift_2.getState().equals(states.get(1))) {
				lift_2.setPerson(0);
			} else {
				lift_2.setPerson(1);
			}

			try {
				json = ow.writeValueAsString(lift_2);
				logger.info(json);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}

			synchronized (Thread.currentThread()) {
				try {
					int time = generateTime(fromFloor, toFloor);
					Thread.sleep(time * 1000);
				} catch (InterruptedException e) {
				}
			}

			lift_2_currentFloor = toFloor;
			lift_2.setState(states.get(4));
			lift_2.setFloor(lift_1_currentFloor);
			lift_2.setDirection(directions.get(0));

			if (lift_2.getState().equals(states.get(0)) || lift_2.getState().equals(states.get(1))) {
				lift_2.setPerson(0);
			} else {
				lift_2.setPerson(1);
			}

			try {
				json = ow.writeValueAsString(lift_2);
				logger.info(json);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}

			synchronized (Thread.currentThread()) {
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
				}
			}

			lift_2.setState(states.get(0));
			lift_2.setDirection(directions.get(0));

			if (lift_2.getState().equals(states.get(0)) || lift_2.getState().equals(states.get(1))) {
				lift_2.setPerson(0);
			} else {
				lift_2.setPerson(1);
			}

			try {
				json = ow.writeValueAsString(lift_2);
				logger.info(json);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static ResponseModel getValue() {
		return response;
	}

	public int generateTime(int cur, int to) {
		int number = (to - cur) * 3;
		// get positive value
		return number < 0 ? -number : number;
	}

}
