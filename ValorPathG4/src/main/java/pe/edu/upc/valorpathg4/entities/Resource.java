package pe.edu.upc.valorpathg4.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Recursos")
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "autor",length = 50,nullable = false)
    private String autor;
    @Column(name = "titulo",length = 50,nullable = false)
    private String titulo;
    @Column(name="tipo",length = 50,nullable = false)
    private String tipo;
    @Column(name="descripcion",length = 400,nullable = false)
    private String descripcion;

    public Resource() {}

    public Resource(int id, String autor, String titulo, String tipo, String descripcion) {
        this.id = id;
        this.autor = autor;
        this.titulo = titulo;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
