package pe.edu.upc.valorpathg4.servicesinterfaces;

import org.springframework.data.repository.query.Param;
import pe.edu.upc.valorpathg4.entities.Resource;

import java.time.LocalDate;
import java.util.List;

public interface IResourceService {
    public List<Resource> list();
    public void insert(Resource resour);
    public void update(Resource resour);
    public void delete(Integer id);
    public Resource listId(Integer id);

}
