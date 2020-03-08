package com.BackEndTest.LogResource;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class LogResource {
	@Autowired
	private LogService logService;

	@GetMapping(path = { "/log", "/log/{userId}", "/log/{userId}/{date}", "/log/{userId}/{date}/{logType}" })
	public String retrieveLog(@PathVariable(required = false) String userId,
			@PathVariable(required = false) String date, @PathVariable(required = false) String logType)
			throws IOException, ParseException {
		if (date.equals("null") && logType.equals("null") && userId != null) {
			return logService.returnUser(userId);
		} else if (userId.equals("null") && logType.equals("null") && date != null) {
			return logService.returnDate(date);
		} else if (userId.equals("null") && date.equals("null") && logType != null) {
			return logService.returnLogType(logType);
		} else if (userId != null && date != null && logType.equals("null")) {
			return logService.returnUserDateSearch(userId, date);
		} else if (userId != null && logType != null && date.equals("null")) {
			return logService.returnUserLogSearch(userId, logType);
		} else if (userId.equals("null") && logType != null && date != null) {
			return logService.returnDateLogSearch(date, logType);
		} else if (userId != null && logType != null && date != null) {
			return logService.returnAll(userId, date, logType);
		} else {
			return "please enter valid search criteria";
		}
	}

	@PostMapping("/newlog")
	public void addLog(@RequestBody Log log) {
		logService.newFile(log);
	}

}
