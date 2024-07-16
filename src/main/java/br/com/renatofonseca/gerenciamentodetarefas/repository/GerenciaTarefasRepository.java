package br.com.renatofonseca.gerenciamentodetarefas.repository;

import br.com.renatofonseca.gerenciamentodetarefas.entity.GerenciaTarefas;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface GerenciaTarefasRepository extends ReactiveMongoRepository<GerenciaTarefas, String> {
    Flux<GerenciaTarefas> findByRealizada(boolean realizada);
}
