package managers;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import models.Event;
import models.User;
import utils.DB;

public class ManageEvents {
	private DB db = null ;
	
	public ManageEvents() {
		try {
			db = new DB();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void finalize() {
		try {
			db.disconnectBD();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	public List<Event> getEvents(User user) {
		// Do it for every event of the user
		List<Event> events = new ArrayList<Event>();
		int i = 0;
		if(Objects.isNull(user.getSport_interests())) return events;
		for (String sport_interest : user.getSport_interests()) {
			String query = "SELECT eventName,eventDate,location,description,sportsTags, image FROM events WHERE sportsTags = ? ;";
			PreparedStatement statement = null;
			ResultSet rs = null;
			try {
				statement = db.prepareStatement(query);
				statement.setString(1,sport_interest);
				rs = statement.executeQuery();
				if (rs.next()) {
					Event event = new Event();
					event.setEventName(rs.getString("eventName"));
					event.setEventDate(rs.getTimestamp("eventDate"));
					event.setLocation(rs.getString("location"));
					event.setDescription(rs.getString("description"));
					event.setSportsTags(rs.getString("sportsTags"));
					event.setImage(rs.getString("image"));
					events.add(event);
				}
				rs.close();
				statement.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			i++;
		}
		
		return events;
	}
}
