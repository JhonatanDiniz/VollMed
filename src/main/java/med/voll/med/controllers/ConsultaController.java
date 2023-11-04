package med.voll.med.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.med.models.dtos.AgendamentoConsultaDTO;
import med.voll.med.models.dtos.CancelamentoConsultaDTO;
import med.voll.med.models.dtos.ConsultaDetalheDTO;
import med.voll.med.services.AgendamentoConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private AgendamentoConsultaService consultaService;

    @PostMapping
    public ResponseEntity agendar(@RequestBody @Valid AgendamentoConsultaDTO agendamento){
        var dto = consultaService.agendaConsulta(agendamento);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping
    public ResponseEntity cancelar(@RequestBody @Valid CancelamentoConsultaDTO cancelamento){
        consultaService.cancelarConsulta(cancelamento);
        return ResponseEntity.noContent().build();
    }
}
