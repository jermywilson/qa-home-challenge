package com.example.schedulemessage.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.schedulemessage.exceptions.ScheduleException;
import com.example.schedulemessage.model.Schedule;
import com.example.schedulemessage.services.ScheduleService;


@RestController
public class ScheduleController {

@Autowired
private ScheduleService service;
	
@RequestMapping("/schedule/{dateTime}/{message}")
public ResponseEntity<String> schedule(@PathVariable String dateTime, @PathVariable String message)
{
	
	try {
		Schedule saved=service.addSchedule(dateTime, message);
		return ResponseEntity
				.status(HttpStatus.ACCEPTED)
				.body("Message Scheduled");
	} catch (ScheduleException e) {
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(e.getMessage());
	}
	
}	
	
}
