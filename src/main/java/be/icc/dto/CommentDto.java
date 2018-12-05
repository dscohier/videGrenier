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
    private int note;
    private UserDto given;
    private UserDto received;

    public CommentDto() {
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

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
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
        return comment.getGiven().equals(this.given) && comment.getReceived().equals(this.getReceived());
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + given.hashCode() + received.hashCode();
        return result;
    }
}
