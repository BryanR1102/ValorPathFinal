package pe.edu.upc.valorpathg4.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.valorpathg4.entities.Event;
import pe.edu.upc.valorpathg4.repositories.IEventRepository;
import pe.edu.upc.valorpathg4.servicesinterfaces.IEventService;

import java.util.List;

@Service
public class EventServiceImplement implements IEventService {

    @Autowired
    private IEventRepository eR;

    @Override
    public List<Event> list() {
        return eR.findAll();
    }

    @Override
    public Event listId(Integer id) {
        return eR.findById(id).get();
    }

    @Override
    public void insert(Event e) {
        eR.save(e);
    }


    @Override
    public void delete(int id) {
        eR.deleteById(id);
    }

    @Override
    public void update(Event e) {
        eR.save(e);
    }

    @Override
    public List<String[]> eventosultimomes() {
        return eR.eventdMonthBefore();
    }

    @Override
    public List<String[]> eventoPorPsicologo() {
        return eR.quantityEventbyPsicologo();
    }

    @Override
    public List<String[]> quantityEventbyVeterano() {
        return eR.quantityEventbyVeterano();
    }
}
