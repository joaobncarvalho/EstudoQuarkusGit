package my.groupId.jongue.rest.dto;

import java.time.LocalDateTime;


public class CreatePostRequest {
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
}
