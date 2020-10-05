package com.example.schedulemessage.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.schedulemessage.exceptions.ScheduleException;
import com.example.schedulemessage.model.Schedule;
import com.example.schedulemessage.repository.ScheduleRepository;

@Service
public class ScheduleService {
	@Autowired
	private ScheduleRepository repository;
	public Schedule addSchedule(String dateTime, String message) throws ScheduleException{
		
		Date time = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy-hh-mm");
		try {
			time = dateFormat.parse(dateTime);
		} catch (ParseException e) {
			throw new ScheduleException(e.getMessage(),e);
		}
		
		Date now = Calendar.getInstance().getTime();
		
			if(time.before(now))
		{
			throw new ScheduleException("Cannot schedule for a past date and time");
		}
		
		Schedule scheduleToSave = new Schedule();
		scheduleToSave.setMessage(message);
		scheduleToSave.setTime(time);
		scheduleToSave.setMessagePosted(false);
		
		return repository.save(scheduleToSave);
		
	}
					
}
