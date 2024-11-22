package pe.edu.upc.valorpathg4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.valorpathg4.entities.Event;

import java.util.List;

@Repository
public interface IEventRepository extends JpaRepository<Event, Integer> {

    @Query(value = "SELECT e.id_event, e.name, e.start_date\n" +
            "FROM evento e\n" +
            "WHERE start_date >= CURRENT_DATE - INTERVAL '30 days'  ",nativeQuery = true)
    public List<String[]> eventdMonthBefore();

    @Query(value = "SELECT u.id AS user_id, u.username, u.lastname, COUNT(e.id_event) AS cantidad_eventos\n" +
            "FROM evento e\n" +
            "JOIN users u " +
            "ON e.id_psicologo = u.id\n" +
            "GROUP BY u.id, u.username, u.lastname\n" +
            "ORDER BY cantidad_eventos DESC",nativeQuery = true)
    public List<String[]> quantityEventbyPsicologo();
    @Query(value = "SELECT u.id AS user_id, u.username, u.lastname, COUNT(e.id_event) AS cantidad_eventos\n" +
            "FROM evento e\n" +
            "JOIN users u " +
            "ON e.id_veterano = u.id\n" +
            "GROUP BY u.id, u.username, u.lastname\n" +
            "ORDER BY cantidad_eventos DESC",nativeQuery = true)
    public List<String[]> quantityEventbyVeterano();
}
