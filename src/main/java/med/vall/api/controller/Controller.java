package med.vall.api.controller;

import jakarta.validation.Valid;
import med.vall.api.DTO.MedicosDTO;
import med.vall.api.DTO.MedicosListaDTO;
import med.vall.api.entity.EntityMedico;
import med.vall.api.repository.RepositoryMedico;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<MedicosListaDTO> medicosListaDTOS(){
        return  repositoryMedico.findAll().stream().map(MedicosListaDTO::new).toList();
   }
}
