package med.vall.api.DTO;

import org.springframework.validation.FieldError;
//Essa DTO tem como proposito pegar os error que são apresentados quando mandamos um post de cadastro errado
public record DadosErrosValidacao(String campo, String mensagem) {

    public DadosErrosValidacao(FieldError error){
      this(error.getField(), error.getDefaultMessage());
    }
}
