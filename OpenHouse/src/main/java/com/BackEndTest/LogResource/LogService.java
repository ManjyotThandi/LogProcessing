package com.BackEndTest.LogResource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

@Service
public class LogService {

	// dummy placeholder to start with some log files
	static File logFile = new File("C:\\Users\\Desktop\\Documents\\LogFile.txt");
	static File logFile1 = new File("C:\\Users\\Desktop\\Documents\\LogFile1.txt");
	static File logFile2 = new File("C:\\Users\\Desktop\\Documents\\LogFile2.txt");
	
	// dummy placeholder to have some sort of storage of the log files
	private static List<Log> logs = new ArrayList<Log>();
	static {
		logs.add(new Log(logFile, logFile.getName()));
		logs.add(new Log(logFile1, logFile1.getName()));
		logs.add(new Log(logFile2, logFile2.getName()));
	}

	JSONParser parser = new JSONParser();

	// if only the userId parameter is specified
	public String returnUser(String userId) throws IOException, ParseException {
		List<String> result = new ArrayList<String>();
		for (Log logFiles : logs) {
			BufferedReader br = new BufferedReader(new FileReader(logFiles.getLogFile()));
			// Object holds JSON Data
			Object obj = parser.parse(br);
			// convert obj into JSON object
			JSONObject jsonObj = (JSONObject) (obj);
			// convert it into a string and compare with user input
			String user = (String) jsonObj.get("userId");
			if (user.equals(userId)) {
				result.add(logFiles.getName());
				// return logFiles;
			}

		}
		if (result.size() != 0) {
			StringBuilder str = new StringBuilder();
			for (String s : result) {
				str.append(s).append(" ");
			}
			return str.toString();
		}
		return "No Logs found with that UserName";
	}

	// Used for the date search
	public String returnDate(String date) throws IOException, ParseException {
		List<String> result = new ArrayList<String>();
		for (Log logFiles : logs) {
			BufferedReader br = new BufferedReader(new FileReader(logFiles.getLogFile()));
			// Object holds JSON Data
			Object obj = parser.parse(br);
			// convert obj into JSON object
			JSONObject jsonObj = (JSONObject) (obj);
			// convert it into a string and compare with user input
			List actions = (ArrayList) jsonObj.get("actions");
			System.out.println(actions);
			for (Object subAction : actions) {
				if (subAction instanceof JSONObject) {
					// convert subAction which is an object, to JSONObject type. This enables the
					// get method below
					JSONObject subActionJSON = (JSONObject) (subAction);
					// Get the time key from each JSON Object
					String dateTime = (String) subActionJSON.get("time");
					String dateTime1 = dateTime.substring(0, 10);
					System.out.println(dateTime1);
					// Now that we have date isolated, check if user input equals our date
					if (dateTime1.equals(date)) {
						result.add(logFiles.getName());
						// return logFile.getName();
					}
				}
			}
		}
		if (result.size() != 0) {
			StringBuilder str = new StringBuilder();
			for (String s : result) {
				str.append(s).append(" ");
			}
			return str.toString();
		}
		return "No Log Files found for that date";
	}

	// Used for the Type search
	public String returnLogType(String logType) throws IOException, ParseException {
		List<String> result = new ArrayList<String>();
		for (Log logFiles : logs) {
			BufferedReader br = new BufferedReader(new FileReader(logFiles.getLogFile()));
			// Object holds JSON Data
			Object obj = parser.parse(br);
			// convert obj into JSON object
			JSONObject jsonObj = (JSONObject) (obj);
			// convert it into a string and compare with user input
			List actions = (ArrayList) jsonObj.get("actions");
			System.out.println(actions);
			for (Object subAction : actions) {
				if (subAction instanceof JSONObject) {
					// convert subAction which is an object, to JSONObject type. This enables the
					// get method below
					JSONObject subActionJSON = (JSONObject) (subAction);
					String type = (String) subActionJSON.get("type");
					System.out.println(type);
					if (type.equals(logType)) {
						result.add(logFiles.getName());
						break;
						// return logFile.getName();
					}
				}
			}
		}
		if (result.size() != 0) {
			StringBuilder str = new StringBuilder();
			for (String s : result) {
				str.append(s).append(" ");
			}
			return str.toString();
		}
		return "No Log Files found for that Type";
	}

	// For a user and date search
	public String returnUserDateSearch(String userId, String date) throws IOException, ParseException {
		List<String> result = new ArrayList<String>();
		for (Log logFiles : logs) {
			BufferedReader br = new BufferedReader(new FileReader(logFiles.getLogFile()));
			// Object holds JSON Data
			Object obj = parser.parse(br);
			// convert obj into JSON object
			JSONObject jsonObj = (JSONObject) (obj);
			// convert it into a string and compare with user input
			String user = (String) jsonObj.get("userId");
			if (user.equals(userId)) {
				// Once the name equals, go into actions and loop through array and compare key
				// of time for each
				// json object in that array. Convert object to array list, loop through it and
				// then check key
				List actions = (ArrayList) jsonObj.get("actions");
				System.out.println(actions);
				for (Object subAction : actions) {
					if (subAction instanceof JSONObject) {
						// convert subAction which is an object, to JSONObject type. This enables the
						// get method below
						JSONObject subActionJSON = (JSONObject) (subAction);
						// Get the time key from each JSON Object
						String dateTime = (String) subActionJSON.get("time");
						String dateTime1 = dateTime.substring(0, 10);
						System.out.println(dateTime1);
						// Now that we have date isolated, check if user input equals our date
						if (dateTime1.equals(date)) {
							result.add(logFiles.getName());
							break;
							// return logFile.getName();
						}
					}
				}
			}
		}
		if (result.size() != 0) {
			StringBuilder str = new StringBuilder();
			for (String s : result) {
				str.append(s).append(" ");
			}
			return str.toString();
		}
		return "No Log Files found with that user name and Date";
	}

	// For a user and Log search
	public String returnUserLogSearch(String userId, String logType) throws IOException, ParseException {
		List<String> result = new ArrayList<String>();
		for (Log logFiles : logs) {
			BufferedReader br = new BufferedReader(new FileReader(logFiles.getLogFile()));
			// Object holds JSON Data
			Object obj = parser.parse(br);
			// convert obj into JSON object
			JSONObject jsonObj = (JSONObject) (obj);
			// convert it into a string and compare with user input
			String user = (String) jsonObj.get("userId");
			if (user.equals(userId)) {
				// Once the name equals, go into actions and loop through array and compare key
				// of time for each
				// json object in that array. Convert object to array list, loop through it and
				// then check key
				List actions = (ArrayList) jsonObj.get("actions");
				System.out.println(actions);
				for (Object subAction : actions) {
					if (subAction instanceof JSONObject) {
						// convert subAction which is an object, to JSONObject type. This enables the
						// get method below
						JSONObject subActionJSON = (JSONObject) (subAction);
						String type = (String) subActionJSON.get("type");
						System.out.println(type);
						if (type.equals(logType)) {
							result.add(logFiles.getName());
							break;
							// return logFile.getName();
						}
					}
				}
			}
		}
		if (result.size() != 0) {
			StringBuilder str = new StringBuilder();
			for (String s : result) {
				str.append(s).append(" ");
			}
			return str.toString();
		}
		return "No Log Files found with that user name and Log Type";
	}

	// For a date and log search
	public String returnDateLogSearch(String date, String logType) throws IOException, ParseException {
		List<String> result = new ArrayList<String>();
		for (Log logFiles : logs) {
			BufferedReader br = new BufferedReader(new FileReader(logFiles.getLogFile()));
			// Object holds JSON Data
			Object obj = parser.parse(br);
			// convert obj into JSON object
			JSONObject jsonObj = (JSONObject) (obj);
			// convert it into a string and compare with user input
			List actions = (ArrayList) jsonObj.get("actions");
			System.out.println(actions);
			for (Object subAction : actions) {
				if (subAction instanceof JSONObject) {
					// convert subAction which is an object, to JSONObject type. This enables the
					// get method below
					JSONObject subActionJSON = (JSONObject) (subAction);
					// Get the time key from each JSON Object
					String dateTime = (String) subActionJSON.get("time");
					String dateTime1 = dateTime.substring(0, 10);
					System.out.println(dateTime1);
					// Now that we have date isolated, check if user input equals our date
					if (dateTime1.equals(date)) {
						String type = (String) subActionJSON.get("type");
						System.out.println(type);
						if (type.equals(logType)) {
							result.add(logFiles.getName());
							break;
							// return logFile.getName();
						}
					}
				}
			}
		}
		if (result.size() != 0) {
			StringBuilder str = new StringBuilder();
			for (String s : result) {
				str.append(s).append(" ");
			}
			return str.toString();
		}
		return "No Log Files found for that date and log type";
	}

	// Used if date, logtype and userid are all passed
	public String returnAll(String userId, String date, String logType) throws IOException, ParseException {
		List<String> result = new ArrayList<String>();
		for (Log logFiles : logs) {
			BufferedReader br = new BufferedReader(new FileReader(logFiles.getLogFile()));
			// Object holds JSON Data
			Object obj = parser.parse(br);
			// convert obj into JSON object
			JSONObject jsonObj = (JSONObject) (obj);
			// convert it into a string and compare with user input
			String user = (String) jsonObj.get("userId");
			if (user.equals(userId)) {
				// Once the name equals, go into actions and loop through array and compare key
				// of time for each
				// json object in that array. Convert object to array list, loop through it and
				// then check key
				List actions = (ArrayList) jsonObj.get("actions");
				System.out.println(actions);
				for (Object subAction : actions) {
					if (subAction instanceof JSONObject) {
						// convert subAction which is an object, to JSONObject type. This enables the
						// get method below
						JSONObject subActionJSON = (JSONObject) (subAction);
						// Get the time key from each JSON Object
						String dateTime = (String) subActionJSON.get("time");
						String dateTime1 = dateTime.substring(0, 10);
						System.out.println(dateTime1);
						// Now that we have date isolated, check if user input equals our date
						if (dateTime1.equals(date)) {
							String type = (String) subActionJSON.get("type");
							System.out.println(type);
							if (type.equals(logType)) {
								result.add(logFiles.getName());
								break;
								// return logFile.getName();
							}
						}
					}
				}
			}
		}
		if (result.size() != 0) {
			StringBuilder str = new StringBuilder();
			for (String s : result) {
				str.append(s).append(" ");
			}
			return str.toString();
		}
		return "No Log Files found with that UserID, Date, and Log Type";
	}

	// To add a new file. Gets called on a post
	public void newFile(Log log) {
		logs.add(log);
	}

}
