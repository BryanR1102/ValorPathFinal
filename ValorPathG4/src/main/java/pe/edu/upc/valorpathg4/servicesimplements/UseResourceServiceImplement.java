package pe.edu.upc.valorpathg4.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.valorpathg4.entities.UseResources;
import pe.edu.upc.valorpathg4.repositories.IUseResourcesRepository;
import pe.edu.upc.valorpathg4.servicesinterfaces.IUseResourceService;

import java.time.LocalDate;
import java.util.List;
@Service
public class UseResourceServiceImplement implements IUseResourceService {
    @Autowired
    private IUseResourcesRepository iur;

    @Override
    public List<UseResources> listaruso() {
        return iur.findAll();
    }

    @Override
    public void insert(UseResources resour) {
        iur.save(resour);
    }

    @Override
    public void update(UseResources resour) {
        iur.save(resour);
    }

    @Override
    public void delete(Integer id) {
        iur.deleteById(id);

    }

    @Override
    public UseResources listId(Integer id) {
        return iur.findById(id).get();
    }

    @Override
    public List<String[]> Rmenosutilizado() {
        return iur.Rmenosutilizado();
    }

    @Override
    public List<String[]> tiporecursomasutilizad(LocalDate fechaInicio, LocalDate fechaFin) {
        return iur.tiporecursomasutilizado(fechaInicio, fechaFin);
    }
}
