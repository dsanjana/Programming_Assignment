package com.se.demo.controllers;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.se.demo.models.RequestModel;
import com.se.demo.models.ResponseModel;
import com.se.demo.services.LiftService;

@RestController
public class LiftController {

	@RequestMapping(method = RequestMethod.POST, value = "/pickAndDrop")
	public ResponseModel pickAndDrop(@RequestBody RequestModel request) throws SQLException, IOException, InterruptedException {
		
		ResponseModel response = LiftService.pickAndDrop(request.getFromFloor(), request.getToFloor());
		return response;
	}
}
