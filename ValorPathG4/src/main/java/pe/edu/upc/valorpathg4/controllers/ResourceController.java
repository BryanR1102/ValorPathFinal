package pe.edu.upc.valorpathg4.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.valorpathg4.dtos.*;
import pe.edu.upc.valorpathg4.entities.Post;
import pe.edu.upc.valorpathg4.entities.Resource;
import pe.edu.upc.valorpathg4.servicesinterfaces.IResourceService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/recursos")
public class ResourceController {

    @Autowired
    private IResourceService rS;
    @GetMapping
    public List<ResourceDTO> listarecursos()
    {
        return rS.list().stream().map(x->{
            ModelMapper m=new ModelMapper();
            return m.map(x,ResourceDTO.class);
        }).collect(Collectors.toList());
    }
    @PreAuthorize("hasAnyAuthority('PSICOLOGO', 'ADMINISTRADOR')")
    @PostMapping
    public void insertar(@RequestBody ResourceDTO dto)
    {
        ModelMapper m=new ModelMapper();
        Resource r=m.map(dto,Resource.class);
        rS.insert(r);
    }
    @PreAuthorize("hasAnyAuthority('PSICOLOGO', 'ADMINISTRADOR')")
    @PutMapping()
    public void modificar(@RequestBody ResourceDTO dto) {
        ModelMapper m = new ModelMapper();
        Resource rec = m.map(dto, Resource.class);
        rS.update(rec);
    }
    @PreAuthorize("hasAnyAuthority('PSICOLOGO', 'ADMINISTRADOR')")
    @DeleteMapping("{id}")
    public void eliminar(@PathVariable("id") Integer id) {
        rS.delete(id);
    }
    @PreAuthorize("hasAnyAuthority('PSICOLOGO', 'ADMINISTRADOR')")
    @GetMapping("{id}")
    public ResourceDTO listarPorId(@PathVariable("id") Integer id) {
        ModelMapper m = new ModelMapper();
        ResourceDTO rec = m.map(rS.listId(id), ResourceDTO.class);
        return rec;
    }








}
