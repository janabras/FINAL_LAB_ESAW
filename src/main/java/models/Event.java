package models;

import java.sql.Timestamp;

public class Event {
    private int id;
    private String sportsTags;
    private String eventName;
    private Timestamp eventDate;
    private String location;
    private String description;
    private String image;

    // Constructors (including default constructor, parameterized constructor)

    public Event() {
    }

    public Event(int id, String sportsTags, String eventName, Timestamp eventDate, String location, String description, String image) {
        this.id = id;
        this.sportsTags = sportsTags;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.location = location;
        this.description = description;
        this.image = image;
    }

    // Getters and Setters

    public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSportsTags() {
        return sportsTags;
    }

    public void setSportsTags(String sportsTags) {
        this.sportsTags = sportsTags;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Timestamp getEventDate() {
        return eventDate;
    }

    public void setEventDate(Timestamp eventDate) {
        this.eventDate = eventDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // toString() method

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", sportsTags='" + sportsTags + '\'' +
                ", eventName='" + eventName + '\'' +
                ", eventDate=" + eventDate +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}