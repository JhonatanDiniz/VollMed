package med.voll.med.controllers;

import jakarta.validation.Valid;
import med.voll.med.models.dtos.AgendamentoConsultaDTO;
import med.voll.med.models.dtos.ConsultaDetalheDTO;
import med.voll.med.services.AgendamentoConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/consultas")
public class ConsultaController {

    @Autowired
    private AgendamentoConsultaService consultaService;

    @PostMapping
    public ResponseEntity agendar(@RequestBody @Valid AgendamentoConsultaDTO agendamento){
        var dto = consultaService.agendaConsulta(agendamento);
        return ResponseEntity.ok().body(dto);
    }
}
