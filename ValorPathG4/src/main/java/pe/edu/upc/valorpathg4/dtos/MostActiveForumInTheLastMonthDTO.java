package pe.edu.upc.valorpathg4.dtos;

public class MostActiveForumInTheLastMonthDTO {
    private int id;
    private String title;
    private int numPosts;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumPosts() {
        return numPosts;
    }

    public void setNumPosts(int numPosts) {
        this.numPosts = numPosts;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
