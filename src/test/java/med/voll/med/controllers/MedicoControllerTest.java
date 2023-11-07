package med.voll.med.controllers;

import jakarta.persistence.EntityNotFoundException;
import med.voll.med.models.dtos.MedicoAtualizaDTO;
import med.voll.med.services.MedicoService;
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

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
@AutoConfigureJsonTesters
class MedicoControllerTest {

    @Autowired
    private MockMvc mvc;
    @Mock
    private MedicoService service;

    @Autowired
    private JacksonTester<MedicoAtualizaDTO> jsonAtualizaDTO;

    @Test
    @DisplayName("Deveria retornar http 400 caso as informações fornecidas sejam inválidas")
    void create() throws Exception {
        var response = mvc.perform(MockMvcRequestBuilders.post("/medicos"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria retornar http 404 caso não encontre o ID")
    void findByIdInvalido() throws Exception {
        Long id = 19L;

        doThrow(new EntityNotFoundException()).when(service).findById(id);

        var response = mvc.perform(get("/medicos/{id}", id)).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    @DisplayName("Deveria retornar http 200 caso ID for encontrado")
    void findByIdValido() throws Exception {
        Long id = 2L;

        doThrow(new EntityNotFoundException()).when(service).findById(id);

        var response = mvc.perform(get("/medicos/{id}", id)).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Deveria retornar http 404 caso não encontre o ID")
    void updateComIdInvalido() throws Exception {

        var response = mvc.perform(MockMvcRequestBuilders.put("/medicos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonAtualizaDTO.write(
                        new MedicoAtualizaDTO(19L, null, null, null)
                ).getJson())

        ).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    @DisplayName("Deveria retornar http 200 caso ID for encontrado")
    void updateComIdValido() throws Exception {

        var response = mvc.perform(MockMvcRequestBuilders.put("/medicos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonAtualizaDTO.write(
                        new MedicoAtualizaDTO(2L, null, null, null)
                ).getJson())

        ).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Deveria retornar http 404 caso não encontre o ID")
    void deleteComIdInvalido() throws Exception {
        Long id = 19L;

        doThrow(new EntityNotFoundException()).when(service).delete(id);

        var response = mvc.perform(delete("/medicos/{id}", id)).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    @DisplayName("Deveria retornar http 203 caso o ID for encontrado")
    void deleteComIdValido() throws Exception {
        Long id = 2L;

        var response = mvc.perform(delete("/medicos/{id}", id)).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }
}