package pe.edu.upc.valorpathg4.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.valorpathg4.entities.Role;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Integer> {


    @Modifying
    @Transactional
    @Query("delete from Role r where r.id=:id")
    public void deleteRole(@Param("id") Integer id);
}
