package be.icc.dto;

import be.icc.entity.Comment;

import java.util.Date;

/**
 * Created by Scohier Dorian on 04-11-18.
 */
public class CommentDto {

    private Long id;
    private String comment;
    private Date date;
    private double note;
    private UserDto given;
    private UserDto received;

    public CommentDto() {

    }

    public CommentDto(Long id, String comment, Date date, double note, UserDto given, UserDto received) {
        this.id = id;
        this.comment = comment;
        this.date = date;
        this.note = note;
        this.given = given;
        this.received = received;
    }

    public Comment toEntity(){
        Comment comment = new Comment();
        comment.setId(this.getId());
        comment.setComment(this.getComment());
        comment.setDate(this.getDate());
        comment.setNote(this.getNote());
        comment.setGiven(this.getGiven().toEntity());
        comment.setReceived(this.getReceived().toEntity());
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

    public UserDto getGiven() {
        return given;
    }

    public void setGiven(UserDto given) {
        this.given = given;
    }

    public UserDto getReceived() {
        return received;
    }

    public void setReceived(UserDto received) {
        this.received = received;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommentDto comment = (CommentDto) o;

        if (!id.equals(comment.id)) return false;
        return comment.getGiven().equals(this.given) && comment.getReceived().equals(this.received);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + given.hashCode() + received.hashCode();
        return result;
    }
}
