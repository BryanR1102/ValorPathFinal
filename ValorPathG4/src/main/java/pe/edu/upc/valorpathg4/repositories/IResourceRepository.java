package pe.edu.upc.valorpathg4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.valorpathg4.entities.Resource;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IResourceRepository extends JpaRepository <Resource, Integer       > {


}
