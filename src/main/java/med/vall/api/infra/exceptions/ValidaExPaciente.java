package med.vall.api.infra.exceptions;

public class ValidaExPaciente extends RuntimeException {
    public ValidaExPaciente(String mensagem) {
        super(mensagem);
    }
}
