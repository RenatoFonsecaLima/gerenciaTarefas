package br.com.renatofonseca.gerenciamentodetarefas.service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.renatofonseca.gerenciamentodetarefas.entity.GerenciaTarefas;
import br.com.renatofonseca.gerenciamentodetarefas.repository.GerenciaTarefasRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class GerenciaService {

    private final List<GerenciaTarefas> tarefasEmMemoria = new CopyOnWriteArrayList<>();

    @Autowired
    private GerenciaTarefasRepository gerenciaTarefasRepository;

   /* @PostConstruct
    private void init() {
        gerenciaTarefasRepository.findAll().subscribe(tarefasEmMemoria::add);
    }*/

    public Mono<GerenciaTarefas> create(GerenciaTarefas tarefa) {
        return gerenciaTarefasRepository.save(tarefa)
                .doOnSuccess(tarefasEmMemoria::add); // Adiciona à lista em memória após salvar
    }

    public Flux<GerenciaTarefas> getAllTasks() {
        return gerenciaTarefasRepository.findAll()
                .collectList()
                .doOnNext(tarefas -> tarefasEmMemoria.addAll(tarefas))
                .flatMapMany(Flux::fromIterable);
    }

    public Mono<GerenciaTarefas> getTaskById(String id) {
        return gerenciaTarefasRepository.findById(id)
                .doOnNext(tarefa -> {
                    if (tarefa != null) {
                        tarefasEmMemoria.add(tarefa); // Adiciona à lista em memória ao encontrar por ID
                    }
                });
    }

    public Mono<GerenciaTarefas> updateTask(String id, GerenciaTarefas tarefa) {
        return gerenciaTarefasRepository.findById(id)
                .flatMap(existingTask -> {
                    existingTask.setNome(tarefa.getNome());
                    existingTask.setDescricao(tarefa.getDescricao());
                    existingTask.setRealizada(tarefa.isRealizada());
                    existingTask.setPrioridade(tarefa.getPrioridade());
                    return gerenciaTarefasRepository.save(existingTask)
                            .doOnSuccess(updatedTask -> {
                                tarefasEmMemoria.removeIf(t -> t.getId().equals(id));
                                tarefasEmMemoria.add(updatedTask);
                            });
                });
    }

    public Mono<Void> deleteTask(String id) {
        return gerenciaTarefasRepository.findById(id)
                .flatMap(tarefa -> gerenciaTarefasRepository.deleteById(id)
                        .doOnSuccess(v -> tarefasEmMemoria.remove(tarefa)));
    }

    public Flux<GerenciaTarefas> getTasksByStatus(boolean realizada) {
        return Flux.fromIterable(tarefasEmMemoria)
                .filter(tarefa -> tarefa.isRealizada() == realizada);
    }
}
