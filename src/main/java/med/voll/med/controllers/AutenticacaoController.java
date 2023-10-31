package med.voll.med.controllers;

import jakarta.validation.Valid;
import med.voll.med.infra.security.TokenDTO;
import med.voll.med.infra.security.TokenService;
import med.voll.med.models.Usuarios;
import med.voll.med.models.dtos.UsuarioAutenticaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid UsuarioAutenticaDTO usuario){
       var authenticationToken = new UsernamePasswordAuthenticationToken(usuario.login(), usuario.senha());
       var authentication = manager.authenticate(authenticationToken);
       var tokenJWT = tokenService.gerarToken((Usuarios) authentication.getPrincipal());
       return ResponseEntity.ok(new TokenDTO(tokenJWT));
    }
}
