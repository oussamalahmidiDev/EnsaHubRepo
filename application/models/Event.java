package application.models;

import java.util.Date;
import java.util.List;

public class Event extends Model {
    private String nom;
    private Date date;
    private String type;
    private String description;
    private String location;

    private List<User> participants;


    public Event(String nom, Date date, String type, String description, String location) {
        this.nom = nom;
        this.date = date;
        this.type = type;
        this.description = description;
        this.location = location;
    }
    public Event() {}
    public Event(int id, String nom, Date date, String type, String description, String location) {
        this.id = id;
        this.nom = nom;
        this.date = date;
        this.type = type;
        this.description = description;
        this.location = location;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Event{" +
            "nom='" + nom + '\'' +
            ", date=" + date +
            ", type='" + type + '\'' +
            ", description='" + description + '\'' +
            ", location='" + location + '\'' +
            ", participants=" + participants +
            ", id=" + id +
            '}';
    }
}
