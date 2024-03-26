package dao;

import entity.User;
import entity.Vote;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Function;

public class UserDao {
    private ArrayList<Vote> votes;
    private ArrayList<User> users;

    public UserDao() {
        this.votes = new ArrayList<>();
        this.users = new ArrayList<>();
        users.add(new User(1L, "Vadym", "https://cdn.pixabay.com/photo/2024/03/05/19/26/duck-8615153_960_720.jpg"));
        users.add(new User(12L, "Viktor", "https://cdn.pixabay.com/photo/2024/02/17/15/02/ostrich-8579501_960_720.jpg"));
    }

    public void setVote(Vote vote) {
        votes.add(vote);
        System.out.println(votes);

    }

    public Optional<User> getUnvotedUser(Long currentUserId) {
        return users.stream().filter(u -> {
            return !votes.stream().anyMatch(v -> !u.getId().equals(v.idFromUser));
        }).findFirst();
    }
}
