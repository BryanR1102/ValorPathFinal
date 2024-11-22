package pe.edu.upc.valorpathg4.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.valorpathg4.dtos.UserDTO;
import pe.edu.upc.valorpathg4.entities.Users;
import pe.edu.upc.valorpathg4.servicesinterfaces.IUserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
@PreAuthorize("hasAnyAuthority('ADMINISTRADOR')")
public class UserController {
    @Autowired
    private IUserService uS;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public void registrar(@RequestBody UserDTO dto) {
        System.out.println(dto);
        ModelMapper m = new ModelMapper();
        Users u = m.map(dto, Users.class);
        String encodedPassword = passwordEncoder.encode(u.getPassword());
        u.setPassword(encodedPassword);
        System.out.println(u);
        uS.insert(u);
    }

    @PutMapping
    public void modificar(@RequestBody UserDTO dto) {
        ModelMapper m = new ModelMapper();
        Users u = m.map(dto, Users.class);
        uS.insert(u);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Integer id) {
        uS.delete(id);
    }

    @GetMapping("/{id}")
    public UserDTO listarId(@PathVariable("id") Integer id) {
        ModelMapper m = new ModelMapper();
        UserDTO dto = m.map(uS.listarId(id), UserDTO.class);
        return dto;
    }

    @GetMapping
    public List<UserDTO> listar() {
        return uS.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, UserDTO.class);
        }).collect(Collectors.toList());
    }
    @GetMapping("/listarrol/{rol}")
    public  List<UserDTO>listarPorRol (@PathVariable("rol") String rol) {
        return uS.listarPorRol(rol).stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, UserDTO.class);
        }).collect(Collectors.toList());
    }
    @GetMapping("/buscaruser/{username}")
    public UserDTO listarUsername(@PathVariable("username") String username) {
        ModelMapper m = new ModelMapper();
        UserDTO dto = m.map(uS.findByUsername(username), UserDTO.class);
        return dto;
    }
}
