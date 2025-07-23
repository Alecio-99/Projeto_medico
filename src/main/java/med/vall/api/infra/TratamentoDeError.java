package med.vall.api.infra;

import jakarta.persistence.EntityNotFoundException;
import med.vall.api.DTO.DadosErrosValidacao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratamentoDeError {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarError404(){
        return ResponseEntity.notFound().build();
    }
    //Essa DTO tem como proposito pegar os error que são apresentados quando mandamos um post de cadastro errado
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarError400(MethodArgumentNotValidException ex){
        var errors = ex.getFieldErrors();

        return ResponseEntity.badRequest().body(errors.stream().map(DadosErrosValidacao::new).toList());
    }
}
