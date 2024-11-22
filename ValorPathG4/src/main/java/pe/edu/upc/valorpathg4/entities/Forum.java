package pe.edu.upc.valorpathg4.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "forums")
public class Forum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "title",length = 100,nullable = false)
    private String title;
    @Column(name = "date",nullable = false)
    private LocalDate date;
    @Column(name = "description",length = 100,nullable = false)
    private String description;
    @ManyToOne
    @JoinColumn(name = "psychologistId")
    private Users psychologist;
    //constructor

    public Forum() {
    }

    public Forum(int id, String title, LocalDate date, String description, Users psychologist) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.description = description;
        this.psychologist = psychologist;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Users getPsychologist() {
        return psychologist;
    }

    public void setPsychologist(Users psychologist) {
        this.psychologist = psychologist;
    }
}
//Forum
//
//id
//title
//description
//date
//pyschologist_id
