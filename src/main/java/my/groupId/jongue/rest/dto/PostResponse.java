package my.groupId.jongue.rest.dto;

import my.groupId.quarkustest.domain.user.Posts;

import java.time.LocalDateTime;

public class PostResponse {
    private String text;
    private LocalDateTime dateTime;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public static PostResponse fromEntity(Posts post){
        var response = new PostResponse();
        response.setText(post.getText());
        response.setDateTime(post.getDateTime());
        return response;
    }
}
