package application.models;

import java.util.Date;

public class Document extends Model {

    private String name;
    private User prof;
    private String type;
    private Date date;
    private String attachement;

    public Document () { super(); }

    public Document (String name,User prof,String type,Date datecourse){
        super();
        this.name = name;
        this.prof = prof;
        this.type = type;
        this.date = datecourse;
    }

    public Document (int id, String name,User prof,String type,Date date){
        super();
        this.name = name;
        this.prof = prof;
        this.type = type;
        this.date = date;
    }

    public Document(String name, User prof, String type, Date date, String attachement) {
        this.name = name;
        this.prof = prof;
        this.type = type;
        this.date = date;
        this.attachement = attachement;
    }

    public Document(int id, String name, User prof, String type, Date date, String attachement) {
        this.name = name;
        this.prof = prof;
        this.type = type;
        this.date = date;
        this.id = id;
        this.attachement = attachement;
    }

    @Override
    public String toString() {
        return "Document{" +
            "name='" + name + '\'' +
            ", prof=" + prof +
            ", type='" + type + '\'' +
            ", date=" + date +
            ", attachement='" + attachement + '\'' +
            ", id=" + id +
            '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getProf() {
        return prof;
    }

    public void setProf(User prof) {
        this.prof = prof;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAttachement() {
        return attachement;
    }

    public void setAttachement(String attachement) {
        this.attachement = attachement;
    }
}
