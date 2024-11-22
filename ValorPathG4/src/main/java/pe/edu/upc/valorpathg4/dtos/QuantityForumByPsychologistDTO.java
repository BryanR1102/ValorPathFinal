package pe.edu.upc.valorpathg4.dtos;

public class QuantityForumByPsychologistDTO {
    private String username;
    private String psychologistLastName;
    private int forumCount;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPsychologistLastName() {
        return psychologistLastName;
    }

    public void setPsychologistLastName(String psychologistLastName) {
        this.psychologistLastName = psychologistLastName;
    }

    public int getForumCount() {
        return forumCount;
    }

    public void setForumCount(int forumCount) {
        this.forumCount = forumCount;
    }
}
