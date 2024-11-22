package pe.edu.upc.valorpathg4.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.valorpathg4.entities.Resource;
import pe.edu.upc.valorpathg4.repositories.IResourceRepository;
import pe.edu.upc.valorpathg4.servicesinterfaces.IResourceService;

import java.time.LocalDate;
import java.util.List;

@Service
public class ResorceServiceImplement implements IResourceService {

    @Autowired
    private IResourceRepository irr;

    @Override
    public List<Resource> list() {
        return irr.findAll();
    }

    @Override
    public void insert(Resource resour) {
        irr.save(resour);
    }

    @Override
    public void update(Resource resour) {
        irr.save(resour);
    }

    @Override
    public void delete(Integer id) {
        irr.deleteById(id);
    }

    @Override
    public Resource listId(Integer id) {
        return irr.findById(id).orElse(new Resource())   ;
    }




}
