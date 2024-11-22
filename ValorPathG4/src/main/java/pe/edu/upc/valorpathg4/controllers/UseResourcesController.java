package pe.edu.upc.valorpathg4.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.valorpathg4.dtos.LessUseResourceDTO;
import pe.edu.upc.valorpathg4.dtos.MostUsebetweendateDTO;
import pe.edu.upc.valorpathg4.dtos.ResourceDTO;
import pe.edu.upc.valorpathg4.dtos.UseResourcesDTO;
import pe.edu.upc.valorpathg4.entities.Resource;
import pe.edu.upc.valorpathg4.entities.UseResources;
import pe.edu.upc.valorpathg4.servicesinterfaces.IUseResourceService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usorecurso")
public class UseResourcesController {
    @Autowired
    private IUseResourceService iurs;
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR')")
    @GetMapping
    public List<UseResourcesDTO> listarusos()
    {
        return iurs.listaruso().stream().map(x->
        {
            ModelMapper m= new ModelMapper();
            return m.map(x, UseResourcesDTO.class);
        }).collect(Collectors.toList());
    }
    @PreAuthorize("hasAnyAuthority('VETERANO','ADMINISTRADOR')")
    @PostMapping
    public void insertar(@RequestBody UseResourcesDTO dto)
    {
        ModelMapper m=new ModelMapper();
        UseResources r=m.map(dto, UseResources.class);
        iurs.insert(r);
    }

    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR')")
    @GetMapping("{id}")
    public UseResourcesDTO listarPorId(@PathVariable("id") Integer id) {
        ModelMapper m = new ModelMapper();
        UseResourcesDTO rec = m.map(iurs.listId(id), UseResourcesDTO.class);
        return rec;
    }

    @GetMapping("/menosutilizado")
    //@PreAuthorize("hasAnyAuthority('PSICOLOGO')")
    public List<LessUseResourceDTO> Recursomenosutilizado()
    {
        List<String[]> lista=iurs.Rmenosutilizado();
        List<LessUseResourceDTO> listdto=new ArrayList<>();
        for(String[] column:lista)
        {
            LessUseResourceDTO dto=new LessUseResourceDTO();
            dto.setTiporecurso(column[0]);
            dto.setTotalusos(Integer.parseInt(column[1]));
            listdto.add(dto);
        }
        return listdto;
    }

    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR')")
    @GetMapping("/maasutilizadoportiempo")
    public List<MostUsebetweendateDTO> recursomasutilizadoportiempo(@RequestParam LocalDate fechainicio, @RequestParam LocalDate fechafin) {
        List<String[]> lista = iurs.tiporecursomasutilizad(fechainicio, fechafin);
        List<MostUsebetweendateDTO> listadto = new ArrayList<>();
        for (String[] columna : lista) {
            MostUsebetweendateDTO dto = new MostUsebetweendateDTO();
            dto.setTiporecurso(columna[0]);
            dto.setTotalusos(Integer.parseInt(columna[1]));
            listadto.add(dto);
        }
        return listadto;
    }
}
