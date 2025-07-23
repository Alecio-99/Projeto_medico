package med.vall.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.vall.api.DTO.AtualizarCadastroMedicoDTO;
import med.vall.api.DTO.DadosDetalhamentosDTO;
import med.vall.api.DTO.MedicosDTO;
import med.vall.api.DTO.MedicosListaDTO;
import med.vall.api.entity.EntityMedico;
import med.vall.api.repository.RepositoryMedico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("medicos")
public class Controller {

    @Autowired
    private RepositoryMedico repositoryMedico;

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid MedicosDTO medicosDTO, UriComponentsBuilder uriComponentsBuilder){
        var medico = new EntityMedico(medicosDTO);
        repositoryMedico.save(medico);
        // Esse trecho de codigo serve para mostra qual o caminho esta sendo salvo o cadastro mostra também o id do cliente
        var uri = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentosDTO(medico));
    }
   @GetMapping
   // Serve para configurar a quantidades de paginas e ordem que executa o json @PageableDefault(size = 10, sort = {"nome"}
    public ResponseEntity <Page<MedicosListaDTO>> medicosListaDTOS(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable){
       var pages = repositoryMedico.findAllByAtivoTrue(pageable).map(MedicosListaDTO::new);
       return ResponseEntity.ok(pages);
   }
   @PutMapping
   @Transactional
    public ResponseEntity atualizarCadastro (@RequestBody @Valid AtualizarCadastroMedicoDTO atualizarCadastroMedico){
      var medico = repositoryMedico.getReferenceById(atualizarCadastroMedico.id());
      medico.atualizarDadosMedico(atualizarCadastroMedico);
      return ResponseEntity.ok(new DadosDetalhamentosDTO(medico));
   }
   @DeleteMapping("/{id}")
   @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
       var medico = repositoryMedico.getReferenceById(id);
       medico.excluir();

       return ResponseEntity.noContent().build();
   }
   @GetMapping("/{id}")
    public ResponseEntity buscarMedico(@PathVariable Long id){
        var medico = repositoryMedico.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentosDTO(medico));
   }

}
