package med.voll.med.controllers;

import med.voll.med.models.Pacientes;
import med.voll.med.models.dtos.PacienteAtualizaDTO;
import med.voll.med.models.dtos.PacienteCadastroDTO;
import med.voll.med.models.dtos.PacienteListagemDTO;
import med.voll.med.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService service;

    @PostMapping
    public ResponseEntity<Pacientes> create(@RequestBody PacienteCadastroDTO obj){
        var paciente = service.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(paciente);
    }

    @GetMapping
    public ResponseEntity<Page<PacienteListagemDTO>> findAll(@PageableDefault(size = 10, sort = "nome") Pageable paginacao){
        var list = service.findAll(paginacao);
        return ResponseEntity.ok().body(list);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody PacienteAtualizaDTO dados){
        service.update(dados);
        return ResponseEntity.ok().build();
    }
}
