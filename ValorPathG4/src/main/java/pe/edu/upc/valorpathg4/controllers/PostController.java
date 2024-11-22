package pe.edu.upc.valorpathg4.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.valorpathg4.dtos.PostDTO;
import pe.edu.upc.valorpathg4.dtos.QuantityPostsByVeteranDTO;
import pe.edu.upc.valorpathg4.entities.Post;
import pe.edu.upc.valorpathg4.servicesinterfaces.IPostService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/publicaciones")
public class PostController {
    @Autowired
    private IPostService pS;
    @PostMapping()
    @PreAuthorize("hasAnyAuthority('VETERANO', 'ADMINISTRADOR')")
    public void registrar(@RequestBody PostDTO postDTO) {
        ModelMapper m = new ModelMapper();
        Post post = m.map(postDTO, Post.class);
        pS.insert(post);
    }
    @PreAuthorize("hasAnyAuthority('VETERANO', 'ADMINISTRADOR')")
    @PutMapping()
    public void modificar(@RequestBody PostDTO postDTO) {
        ModelMapper m = new ModelMapper();
        Post post = m.map(postDTO, Post.class);
        pS.update(post);
    }

    @GetMapping()
    public List<PostDTO> listar() {
        return pS.list().stream().map(y -> {
            ModelMapper m = new ModelMapper();
            return m.map(y, PostDTO.class);
        }).collect(Collectors.toList());
    }
    @PreAuthorize("hasAnyAuthority('VETERANO', 'ADMINISTRADOR')")
    @DeleteMapping("{id}")
    public void eliminar(@PathVariable("id") Integer id) {
        pS.delete(id);
    }

    @GetMapping("{id}")
    public PostDTO listarPorId(@PathVariable("id") Integer id) {
        ModelMapper m = new ModelMapper();
        PostDTO postDTO = m.map(pS.listId(id), PostDTO.class);
        return postDTO;
    }
    @GetMapping("/quantity")
    public List<QuantityPostsByVeteranDTO> quantityPostByVeterans(){
        List<String[]> list = pS.cantidadPublicacionesPorVeteranos();
        List<QuantityPostsByVeteranDTO> listdto = new ArrayList<>();
        for(String[] columna : list){
            QuantityPostsByVeteranDTO dto = new QuantityPostsByVeteranDTO();
            dto.setUsername(columna[0]);
            dto.setLastnameUser(columna[1]);
            dto.setQuantityPosts(Integer.parseInt(columna[2]));
            listdto.add(dto);
        }
        return listdto;
    }
}
