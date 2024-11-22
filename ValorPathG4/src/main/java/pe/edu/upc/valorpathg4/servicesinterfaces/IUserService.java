package pe.edu.upc.valorpathg4.servicesinterfaces;

import pe.edu.upc.valorpathg4.entities.Users;

import java.util.List;

public interface IUserService {
    public void insert(Users usuario);

    public List<Users> list();

    public void delete(Integer idUsuario);

    public Users listarId(Integer idUsuario);

    public List<Users> listarPorRol(String rol);

    public Users findByUsername(String username);
}
