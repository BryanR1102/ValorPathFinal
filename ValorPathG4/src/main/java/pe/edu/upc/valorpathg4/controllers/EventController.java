package pe.edu.upc.valorpathg4.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.valorpathg4.dtos.*;
import pe.edu.upc.valorpathg4.entities.Event;
import pe.edu.upc.valorpathg4.servicesinterfaces.IEventService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/eventos")
public class EventController {

    @Autowired
    private IEventService eS;
    @GetMapping
    public List<EventDTO> listar() {
        return eS.list().stream().map(x->{
            ModelMapper m = new ModelMapper();
            return m.map(x, EventDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void insertar(@RequestBody EventDTO edto) {
        System.out.println(edto);
        ModelMapper m = new ModelMapper();
        Event e= m.map(edto, Event.class);
        eS.insert(e);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id")Integer id){
        eS.delete(id);
    }
    @GetMapping("{id}")
    public EventDTO listarPorId(@PathVariable("id") Integer id) {
        ModelMapper m = new ModelMapper();
        EventDTO eventDTO = m.map(eS.listId(id), EventDTO.class);
        return eventDTO;
    }
    @PutMapping
    public void modificar(@RequestBody EventDTO edto) {
        ModelMapper m = new ModelMapper();
        Event e= m.map(edto, Event.class);
        eS.update(e);
    }

    @GetMapping("/citasUltimoMes")
    public List<LastMonthEventsDTO> lastMonthEvents() {
        List<String[]> list = eS.eventosultimomes();
        List<LastMonthEventsDTO> dtos = new ArrayList<>();
        for (String[] columna : list) {
            LastMonthEventsDTO dto = new LastMonthEventsDTO();
            dto.setEventId(Integer.parseInt(columna[0]));
            dto.setEventName(columna[1]);
            dto.setEventDate(LocalDate.parse(columna[2]));
            dtos.add(dto);
        }
        return dtos;
    }

    @GetMapping("/eventosporPsicologo")
    public List<EventsByPsychologyDTO> quantityEventbyPsychology(){
        List<String[]> list = eS.eventoPorPsicologo();
        List<EventsByPsychologyDTO> listdto = new ArrayList<>();
        for (String[] columna : list) {
            EventsByPsychologyDTO dto = new EventsByPsychologyDTO();
            dto.setUserId(Integer.parseInt(columna[0]));
            dto.setUsername(columna[1]);
            dto.setLastname(columna[2]);
            dto.setQuantity(Integer.parseInt(columna[3]));
            listdto.add(dto);
        }
        return listdto;
    }
    @GetMapping("/eventosporVeterano")
    public List<EventsByVeteranDTO> quantityEventbyVeterano(){
        List<String[]> list = eS.eventoPorPsicologo();
        List<EventsByVeteranDTO> listdto = new ArrayList<>();
        for (String[] columna : list) {
            EventsByVeteranDTO dto = new EventsByVeteranDTO();
            dto.setUsername(columna[1]);
            dto.setQuantity(Integer.parseInt(columna[3]));
            listdto.add(dto);
        }
        return listdto;
    }
}
