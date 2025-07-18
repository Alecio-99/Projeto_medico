package med.vall.api.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import med.vall.api.DTO.DadosEndereco;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "medico")
public class EntityEndereco {

    private String logradouro;
    private String bairro;
    private String cep;
    private String cidade;
    private  String uf;
    private String complemento;
    private String numero;

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
