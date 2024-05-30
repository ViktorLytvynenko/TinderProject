package dao;

import entity.Vote;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

public class VoteDaoSQL {
    private final Optional<Connection> connection;

    public VoteDaoSQL(Optional<Connection> connection) {
        this.connection = connection;
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
}
