package dao;

import entity.Message;
import entity.User;
import entity.Vote;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoSQL {
    private final Optional<Connection> connection;

    public UserDaoSQL(Optional<Connection> connection) {
        this.connection = connection;
    }


    public Optional<User> getUnvotedUser(Long currentUserId) {
        return connection.flatMap(con -> {
            try {
                PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM users u WHERE NOT EXISTS(SELECT * FROM votes WHERE id_to_user = u.id AND id_from_user = ?) AND u.id != ? LIMIT 1");
                preparedStatement.setLong(1, currentUserId);
                preparedStatement.setLong(2, currentUserId);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    return Optional.of(User.resultSetToUser(resultSet));
                }
                return Optional.empty();
            } catch (SQLException e) {
                return Optional.empty();
            }
        });
    }

    public void saveVote(Vote vote) {
        connection.map(con -> {
            try {
                PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO public.votes (id_from_user, id_to_user, is_like) VALUES (?, ?, ?)");
                preparedStatement.setLong(1, vote.getIdFromUser());
                preparedStatement.setLong(2, vote.getIdToUser());
                preparedStatement.setBoolean(3, vote.getLike());
                preparedStatement.execute();
            } catch (SQLException e) {
                return con;
            }
            return con;
        });
    }

    public List<User> getLikedUsers(Long currentUserId) {
        List<User> users = new ArrayList<>();
        connection.map(con -> {
            try {
                PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM users u WHERE u.id IN(SELECT (id_to_user) FROM votes WHERE id_from_user = ? AND is_like = TRUE)");
                preparedStatement.setLong(1, currentUserId);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    users.add(User.resultSetToUser(resultSet));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return con;
        });
        return users;
    }

    public Optional<User> findUserById(Long id) {
        return connection.flatMap(con -> {
            try {
                PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM users u WHERE u.id = ?");
                preparedStatement.setLong(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    return Optional.of(User.resultSetToUser(resultSet));
                }
            } catch (SQLException e) {
                return Optional.empty();
            }
            return Optional.empty();
        });
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

    public Optional<User> findUserByEmail(String email) {
        return connection.flatMap(con -> {
            try {
                PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM public.users u WHERE u.email = ?");
                preparedStatement.setString(1, email);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    return Optional.of(User.resultSetToUser(resultSet));
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return Optional.empty();
            }
            return Optional.empty();
        });
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