package br.com.cordeldosaber.catalog.dto;

import br.com.cordeldosaber.catalog.model.Livro;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank; // Importante: jakarta, não javax
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class LivroDTO {

    private Long id;

    @NotBlank(message = "O título é obrigatório") // Não pode ser nulo nem vazio
    @Size(min = 2, max = 100, message = "O título deve ter entre 2 e 100 caracteres")
    private String titulo;

    @NotBlank(message = "O nome do autor é obrigatório")
    private String nomeAutor;

    @NotBlank(message = "O ISBN é obrigatório")
    private String isbn;

    @Positive(message = "O preço deve ser um valor positivo") // Deve ser > 0
    private BigDecimal preco;

    // (Mantenha o construtor de cópia que já existia aqui embaixo...)
    public LivroDTO(Livro entidade) {
        this.id = entidade.getId();
        this.titulo = entidade.getTitulo();
        this.nomeAutor = entidade.getNomeAutor();
        this.isbn = entidade.getIsbn();
        this.preco = entidade.getPreco();
    }
}