package br.com.cordeldosaber.catalog.dto;

import lombok.Data;
import lombok.EqualsAndHashCode; // Para herdar corretamente
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false) // Herda do pai, mas ignora no equals
public class ErroValidacao extends ErroCustomizado {

    // A lista onde vamos guardar os erros de cada campo
    private List<CampoMensagem> errors = new ArrayList<>();

    public void addError(String campo, String mensagem) {
        errors.add(new CampoMensagem(campo, mensagem));
    }
}