package pe.edu.upc.valorpathg4.servicesinterfaces;

import pe.edu.upc.valorpathg4.entities.Resource;
import pe.edu.upc.valorpathg4.entities.UseResources;

import java.time.LocalDate;
import java.util.List;

public interface IUseResourceService {
    public List<UseResources> listaruso();
    public void insert(UseResources resour);
    public void update(UseResources resour);
    public void delete(Integer id);
    public UseResources listId(Integer id);
    List<String[]> Rmenosutilizado();
    List<String[]> tiporecursomasutilizad( LocalDate fechaInicio, LocalDate fechaFin);
}
