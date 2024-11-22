package pe.edu.upc.valorpathg4.servicesinterfaces;

import org.springframework.cglib.core.Local;
import pe.edu.upc.valorpathg4.entities.Appointment;

import java.time.LocalDate;
import java.util.List;

public interface IAppointmentService {
    public void insert (Appointment appointment);
    public void update (Appointment appointment);
    public void delete (int id);
    public List<Appointment> list();
    public Appointment listId (int id);
    public List<String[]> cantidadCitasAtendidasPorUsuarios();
    public List<String[]> cantidadCitasAtendidasPorPsicologo();
    public List<String[]> citasporFecha(LocalDate date1);
    public List<String[]> citasCanceladas();
}
