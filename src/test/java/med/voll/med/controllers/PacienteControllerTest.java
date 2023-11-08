package med.voll.med.controllers;

import jakarta.persistence.EntityNotFoundException;
import med.voll.med.models.dtos.PacienteAtualizaDTO;
import med.voll.med.services.PacienteService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
@AutoConfigureJsonTesters
class PacienteControllerTest {

    @Autowired
    private MockMvc mvc;
    @Mock
    private PacienteService service;

    @Autowired
    private JacksonTester<PacienteAtualizaDTO> jsonAtualizaDTO;

    @Test
    @DisplayName("Deveria retornar http 400 caso as informações fornecidas sejam invalidas")
    void create() throws Exception {
        var response = mvc.perform(post("/pacientes")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
    @Test
    @DisplayName("Deveria retornar http 404 caso ID não for encontrado")
    void findByIdInvalido() throws Exception {
        Long id = 19L;

        doThrow(new EntityNotFoundException()).when(service).findById(id);
        var response = mvc.perform(get("/pacientes/{id}", id)).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    @DisplayName("Deveria retornar http 200 caso ID for encontrado")
    void findByIdValido() throws Exception {
        Long id = 1L;

        var response = mvc.perform(get("/pacientes/{id}", id)).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Deveria retornar http 404 caso ID não for encontrado")
    void updateIdInvalido() throws Exception {
        var response = mvc.perform(put("/pacientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonAtualizaDTO.write(
                        new PacienteAtualizaDTO(19L, null, null, null)).getJson())

        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    @DisplayName("Deveria retornar http 200 caso ID for encontrado")
    void updateIdValido() throws Exception {
        var response = mvc.perform(put("/pacientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonAtualizaDTO.write(
                        new PacienteAtualizaDTO(1L, null, null, null)).getJson())

        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Deveria retornar http 404 caso ID não for encontrado")
    void deleteIdInvalido() throws Exception {
        Long id = 19L;

        doThrow(new EntityNotFoundException()).when(service).delete(id);
        var response = mvc.perform(delete("/pacientes/{id}", id)).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    @DisplayName("Deveria retornar http 203 caso ID for encontrado")
    void deleteIdValido() throws Exception {
        Long id = 1L;

        doThrow(new EntityNotFoundException()).when(service).delete(id);
        var response = mvc.perform(delete("/pacientes/{id}", id)).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }
}