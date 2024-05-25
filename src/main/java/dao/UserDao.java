package dao;

import entity.User;
import entity.Vote;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserDao {
    private ArrayList<Vote> votes;
    private ArrayList<User> users;

    public UserDao() {
        this.votes = new ArrayList<>();
        this.users = new ArrayList<>();
        users.add(new User(1L, "Vadym", "https://cdn.pixabay.com/photo/2024/03/05/19/26/duck-8615153_960_720.jpg"));
        users.add(new User(12L, "Viktor", "https://cdn.pixabay.com/photo/2024/02/17/15/02/ostrich-8579501_960_720.jpg"));
        users.add(new User(15L, "Karina", "https://static.nv.ua/shared/system/MediaPhotoBig/images/000/003/982/original/e352349a0d6bcfdfcf03e9c7c43d5293.png?q=85&stamp=20211118114651&f=webp"));
    }

    public void setVote(Vote vote) {
        votes.add(vote);
        System.out.println(votes);
    }

    public void clearVotes() {
        votes.clear();
    }

    public List<User> getLikedUsers(Long currentUserId) {
        List<Vote> voteStream = votes.stream().filter(v -> {
            return currentUserId.equals(v.getIdFromUser()) && v.getLike();
        }).toList();
        return users.stream().filter(u -> {
            return voteStream.stream().anyMatch(v -> v.getIdToUser().equals(u.getId()));
        }).toList();
    }
    public Optional<User> getUnvotedUser(Long currentUserId) {
        List<Vote> voteStream = votes.stream().filter(v -> {
            return currentUserId.equals(v.getIdFromUser());
        }).collect(Collectors.toList());
        return users.stream().filter(u -> {
            if (u.getId().equals(currentUserId)) {
                return false;
            }
            return voteStream.stream().noneMatch(v -> v.getIdToUser().equals(u.getId()));
        }).findFirst();
    }
}