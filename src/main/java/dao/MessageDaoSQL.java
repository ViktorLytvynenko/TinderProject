package dao;

import entity.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MessageDaoSQL {
    private final Optional<Connection> connection;

    public MessageDaoSQL(Optional<Connection> connection) {
        this.connection = connection;
    }

    public List<Message> getMessagesBetweenTwoUsers(Long idFirstUser, Long idSecondUser) {
        List<Message> usersMessages = new ArrayList<>();
        connection.map(con -> {
            try {
                PreparedStatement preparedStatement = con.prepareStatement(
                        "SELECT id, id_from_user, id_to_user, text, time FROM messages m WHERE m.id_from_user = ? AND m.id_to_user = ? " +
                                "OR m.id_to_user = ? AND m.id_from_user = ? ORDER BY m.time");
                preparedStatement.setLong(1, idFirstUser);
                preparedStatement.setLong(2, idSecondUser);
                preparedStatement.setLong(3, idFirstUser);
                preparedStatement.setLong(4, idSecondUser);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    usersMessages.add(Message.resultSetToMessage(resultSet));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return con;
        });
        return usersMessages;
    }

    public void addMessageBetweenTwoUsers(Long idFirstUser, Long idSecondUser, String message) {
        connection.map(con -> {
            try {
                PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO public.messages (id_from_user, id_to_user, text) VALUES (?, ?, ?)");
                preparedStatement.setLong(1, idFirstUser);
                preparedStatement.setLong(2, idSecondUser);
                preparedStatement.setString(3, message);
                preparedStatement.execute();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return con;
        });
    }
}
