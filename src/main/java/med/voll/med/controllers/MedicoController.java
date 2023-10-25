package med.voll.med.controllers;

import jakarta.validation.Valid;
import med.voll.med.models.Medicos;
import med.voll.med.models.dtos.MedicoAtualizaDTO;
import med.voll.med.models.dtos.MedicoCadastroDTO;
import med.voll.med.models.dtos.MedicoDetalheDTO;
import med.voll.med.models.dtos.MedicoListagemDTO;
import med.voll.med.services.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/medicos")
public class MedicoController {

    @Autowired
    private MedicoService service;
    @PostMapping
    public ResponseEntity<MedicoDetalheDTO> create(@RequestBody @Valid MedicoCadastroDTO obj){
        MedicoDetalheDTO medico = service.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(medico.id()).toUri();
        return ResponseEntity.created(uri).body(medico);
    }

    @GetMapping
    public ResponseEntity<Page<MedicoListagemDTO>> findAll(@PageableDefault(size = 10, sort = "nome") Pageable paginacao){
        var list = service.findAll(paginacao);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoDetalheDTO> findById(@PathVariable Long id){
        var dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody @Valid MedicoAtualizaDTO obj){
       service.update(obj);
       return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
