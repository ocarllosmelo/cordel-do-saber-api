package br.com.cordeldosaber.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.cordeldosaber.catalog.model.Livro;

/**
 * @Repository: Esta é uma anotação opcional para Repositórios que herdam de JpaRepository,
 * pois o Spring já sabe que são "Beans". Mas é uma boa prática para clareza.
 * (Vamos adicionar na próxima etapa se necessário, por enquanto JpaRepository basta)
 * * Esta interface é o nosso "Contrato de Bibliotecário".
 */
public interface LivroRepository extends JpaRepository<Livro, Long> {

}