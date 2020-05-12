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

public class LiftService {
	
	public static ResponseModel pickAndDrop(int fromFloor, int toFloor) throws JsonProcessingException {
		
		ResponseModel response = new ResponseModel();
		
		Runnable r = new Lift(fromFloor, toFloor);
		new Thread(r).start();
		response = Lift.getValue();
		
		return response;
	}

}
