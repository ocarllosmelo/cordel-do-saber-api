package br.com.cordeldosaber.catalog.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.cordeldosaber.catalog.model.Livro;
import br.com.cordeldosaber.catalog.service.LivroService;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import java.net.URI;
import br.com.cordeldosaber.catalog.dto.LivroDTO;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;

/**
 * @RestController: Esta anotação mágica transforma a classe em um "Controlador REST".
 * Ela diz ao Spring: "A responsabilidade desta classe é receber requisições HTTP
 * e os resultados dos métodos devem ser convertidos para JSON automaticamente."
 */
@RestController

/**
 * @RequestMapping: Define o "endereço base" (ou "rota") para todos os métodos
 * dentro desta classe. Todos os pedidos para esta classe deverão começar com /livros.
 * Ex: http://localhost:8080/livros
 */
@RequestMapping("/livros")
public class LivroController {

    // 1. Injetando o "Gerente" (Service)
    // O Recepcionista (Controller) precisa ter o contato do Gerente (Service).
    // Usamos @Autowired para que o Spring nos dê a instância do LivroService.
    @Autowired
    private LivroService livroService;

    // 2. Criando nosso primeiro "Endpoint"
// Método ANTES
// public List<Livro> buscarTodos() { ... }

    // Método DEPOIS (agora retorna DTO)
    @GetMapping
    public List<LivroDTO> buscarTodos() {
        return livroService.buscarTodos();
    }

    // Retorna por id;
    @GetMapping("/{id}")
    public ResponseEntity<LivroDTO> buscarPorId(@PathVariable Long id) {
        LivroDTO dto = livroService.buscarPorId(id);
        return ResponseEntity.ok(dto);
    }

    /**
     * @PostMapping: Mapeia este método para o verbo HTTP POST (usado para Criar).
     * Ele responderá em "POST /livros".
     */
// Adicione o import: import jakarta.validation.Valid;

    @PostMapping
    public ResponseEntity<LivroDTO> inserir(@Valid @RequestBody LivroDTO dto) {
        // O resto continua igual...
        dto = livroService.inserir(dto);
        URI uri = URI.create("/livros/" + dto.getId());
        return ResponseEntity.created(uri).body(dto);
    }

    /**
     * @PutMapping: Mapeia este método para o verbo HTTP PUT (usado para Atualizar).
     * @PathVariable: Pega o {id} da URL e o injeta na variável 'id'.
     * @RequestBody: Pega o JSON do corpo e o injeta no 'dto'.
     *
     * Endereço completo: PUT http://localhost:8080/livros/1
     */
    @PutMapping("/{id}")
    public ResponseEntity<LivroDTO> atualizar(@PathVariable Long id, @Valid @RequestBody LivroDTO dto) {
        // O resto continua igual...
        LivroDTO dtoAtualizado = livroService.atualizar(id, dto);
        return ResponseEntity.ok().body(dtoAtualizado);
    }

    /**
     * @DeleteMapping: Mapeia este método para o verbo HTTP DELETE.
     * @PathVariable: Pega o {id} da URL e o injeta na variável 'id'.
     *
     * Endereço completo: DELETE http://localhost:8080/livros/1
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        // 1. Delega a tarefa para o Gerente (Service)
        livroService.deletar(id);

        // 2. Resposta Padrão para DELETE (204 No Content)
        //    Quando uma deleção é bem-sucedida, a convenção REST
        //    é retornar o status "204 No Content", que significa:
        //    "Eu fiz o que você pediu (deletei), e agora não tenho
        //    nenhum conteúdo (void) para te retornar no corpo."
        return ResponseEntity.noContent().build();
    }

    // (Aqui adicionaremos os outros endpoints: buscarPorId, salvar, etc.)
}
