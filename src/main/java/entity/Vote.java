package entity;

public class Vote {
    public Long idFromUser;
    public Long idToUser;
    public Boolean isLike;

    public Vote(Long idFromUser, Long idToUser, Boolean isLike) {
        this.idFromUser = idFromUser;
        this.idToUser = idToUser;
        this.isLike = isLike;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "idFromUser=" + idFromUser +
                ", idToUser=" + idToUser +
                ", isLike=" + isLike +
                '}';
    }
}
