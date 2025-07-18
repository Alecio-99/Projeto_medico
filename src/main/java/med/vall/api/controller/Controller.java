package med.vall.api.controller;

import jakarta.validation.Valid;
import med.vall.api.DTO.MedicosDTO;
import med.vall.api.DTO.MedicosListaDTO;
import med.vall.api.entity.EntityMedico;
import med.vall.api.repository.RepositoryMedico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medicos")
public class Controller {

    @Autowired
    private RepositoryMedico repositoryMedico;

    @PostMapping
    public void cadastrar(@RequestBody @Valid MedicosDTO medicosDTO){
        repositoryMedico.save(new EntityMedico(medicosDTO));
    }
   @GetMapping
   // Serve para configurar a quantidades de paginas e ordem que executa o json @PageableDefault(size = 10, sort = {"nome"}
    public Page<MedicosListaDTO> medicosListaDTOS(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable){
        return  repositoryMedico.findAll(pageable).map(MedicosListaDTO::new);
   }
}
