package pe.edu.upc.valorpathg4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.valorpathg4.entities.Forum;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IForumRepository extends JpaRepository<Forum, Integer> {
    @Query(value = "SELECT \n" +
            "f.id, \n" +
            "f.title, \n" +
            "COUNT(p.id) AS num_posts \n" +
            "FROM forums f \n" +
            "JOIN posts p \n" +
            "ON f.id = p.id \n" +
            "WHERE p.date > NOW() - INTERVAL '30 days' \n" +
            "GROUP BY f.id, f.title \n" +
            "ORDER BY num_posts DESC",
            nativeQuery = true)
    //Consulta para obtener los foros más activos en el último mes
    public List<String[]> mostActiveForumsInTheLastMonth();
    //
    @Query(value=" SELECT u.username, u.lastname, COUNT(f.id) AS forum_count\n" +
            "FROM forums as f\n" +
            "JOIN users as u ON f.psychologist_id = u.id\n" +
            "GROUP BY u.username, u.lastname\n" +
            "ORDER BY forum_count DESC",nativeQuery = true)
    //Consulta de Cantidad de foros agrupados por el nombre y apellido del psicólogo.
    public List<String[]> quantityForumsByPsichologyst();
}
