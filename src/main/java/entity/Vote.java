package entity;

public class Vote {
    private Long idFromUser;
    private Long idToUser;
    private Boolean isLike;

    public Vote(Long idFromUser, Long idToUser, Boolean isLike) {
        this.idFromUser = idFromUser;
        this.idToUser = idToUser;
        this.isLike = isLike;
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

    public Boolean getLike() {
        return isLike;
    }

    public void setLike(Boolean like) {
        isLike = like;
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
