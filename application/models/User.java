package application.models;

import java.util.List;


public class User extends Model {

    private String name;
    private String prenom;
    private String email;
    private String password;
    private String numero;
    private String type;
    private List<Event> ParticipatedEvents;
    private List<Document> createdDocuments;

    public User() {
        super();
    }




    public User(String nom, String prenom, String email, String number, String password, String type) {
        super();
        this.name = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.numero = number;
        this.type = type;
    }


    public User(int id, String nom, String prenom, String email, String number, String password, String type) {
        super();
        this.id = id;
        this.name = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.numero = number;
        this.type = type;
    }


    public String getName() {
        return name;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "User{" +
            "name='" + name + '\'' +
            ", prenom='" + prenom + '\'' +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            ", numero='" + numero + '\'' +
            ", type='" + type + '\'' +
            ", ParticipatedEvents=" + ParticipatedEvents +
            ", createdDocuments=" + createdDocuments +
            ", id=" + id +
            '}';
    }

    public List<Event> getParticipatedEvents() {
        return ParticipatedEvents;
    }

    public void setParticipatedEvents(List<Event> participatedEvents) {
        ParticipatedEvents = participatedEvents;
    }

    public List<Document> getCreatedDocuments() {
        return createdDocuments;
    }

    public void setCreatedDocuments(List<Document> createdDocuments) {
        this.createdDocuments = createdDocuments;
    }
}
