package med.vall.api.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import med.vall.api.DTO.DadosEndereco;

@Embeddable
@AllArgsConstructor
@Data
public class EntityEndereco {

    private String logradouro;
    private String bairro;
    private String cep;
    private String cidade;
    private  String uf;
    private String complemento;
    private String numero;


    public EntityEndereco(){

    }

    public EntityEndereco(DadosEndereco dadosEndereco){
        this.logradouro = dadosEndereco.logradouro();
        this.cep = dadosEndereco.cep();
        this.uf = dadosEndereco.uf();
        this.bairro = dadosEndereco.bairro();
        this.cidade = dadosEndereco.cidade();
        this.complemento = dadosEndereco.complemento();
        this.numero = dadosEndereco.numero();
    }
}
