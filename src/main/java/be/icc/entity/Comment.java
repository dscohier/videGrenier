package be.icc.entity;

import be.icc.dto.CommentDto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Scohier Dorian on 04-11-18.
 */
@Entity
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String comment;
    @Column(nullable = false)
    private Date date;
    @Column(nullable = false)
    private double note;
    @ManyToOne
    private User given;
    @ManyToOne
    private User received;

    public Comment() {
    }

    public Comment(Long id, String comment, Date date, double note, User given, User received) {
        this.id = id;
        this.comment = comment;
        this.date = date;
        this.note = note;
        this.given = given;
        this.received = received;
    }

    public CommentDto toDto(){
        CommentDto comment = new CommentDto();
        comment.setId(this.getId());
        comment.setComment(this.getComment());
        comment.setDate(this.getDate());
        comment.setNote(this.getNote());
        comment.setGiven(this.getGiven().toDto());
        comment.setReceived(this.getReceived().toDto());
        return comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public User getGiven() {
        return given;
    }

    public void setGiven(User given) {
        this.given = given;
    }

    public User getReceived() {
        return received;
    }

    public void setReceived(User received) {
        this.received = received;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (!id.equals(comment.id)) return false;
        return comment.getGiven().equals(this.getGiven()) && comment.getReceived().equals(this.getReceived());
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + given.hashCode() + received.hashCode();
        return result;
    }
}
