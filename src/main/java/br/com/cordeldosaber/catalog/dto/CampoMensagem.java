package br.com.cordeldosaber.catalog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampoMensagem {
    private String campo;    // Ex: "preco"
    private String mensagem; // Ex: "deve ser positivo"
}