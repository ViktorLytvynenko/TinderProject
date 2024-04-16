package entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {
    private Long id;
    private Long idFromUser;
    private Long idToUser;
    private String text;
    private Long time;

    public Message() {
    }

    public Message(Long id, Long idFromUser, Long idToUser, String text, Long time) {
        this.id = id;
        this.idFromUser = idFromUser;
        this.idToUser = idToUser;
        this.text = text;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdFromUser() {
        return idFromUser;
    }

    public void setIdFromUser(Long idFromUser) {
        this.idFromUser = idFromUser;
    }

    public Long getIdToUser() {
        return idToUser;
    }

    public void setIdToUser(Long idToUser) {
        this.idToUser = idToUser;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public static Message resultSetToMessage(ResultSet resultSet) throws SQLException {
        Message message = new Message();
        message.setId(resultSet.getLong(1));
        message.setIdFromUser(resultSet.getLong(2));
        message.setIdToUser(resultSet.getLong(3));
        message.setText(resultSet.getString(4));
        message.setTime(resultSet.getTimestamp(5).getTime());
        return message;
    }

    public String getTimeString() {
        Date date = new Date(this.time);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd, h:mm a");
        return simpleDateFormat.format(date);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", idFromUser=" + idFromUser +
                ", idToUser=" + idToUser +
                ", text='" + text + '\'' +
                ", time=" + time +
                '}';
    }
}
