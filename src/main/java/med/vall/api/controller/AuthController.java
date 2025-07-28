package med.vall.api.controller;

import jakarta.validation.Valid;
import med.vall.api.DTO.DadosAutenticacao;
import med.vall.api.DTO.DadosTokenJWTDTO;
import med.vall.api.entity.EntityUsuario;
import med.vall.api.infra.security.TokenService;
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
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin (@RequestBody @Valid DadosAutenticacao dadosAutenticacao){
        try {
            var Authtoken = new UsernamePasswordAuthenticationToken(dadosAutenticacao.login(), dadosAutenticacao.senha());
            var authentication = authenticationManager.authenticate(Authtoken);

            var tokenJWT = tokenService.gerarToken((EntityUsuario) authentication.getPrincipal());

            return ResponseEntity.ok(new DadosTokenJWTDTO(tokenJWT));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
