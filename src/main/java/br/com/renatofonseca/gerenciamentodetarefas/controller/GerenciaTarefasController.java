package br.com.renatofonseca.gerenciamentodetarefas.controller;

import br.com.renatofonseca.gerenciamentodetarefas.entity.GerenciaTarefas;
import br.com.renatofonseca.gerenciamentodetarefas.service.GerenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/tarefas")
public class GerenciaTarefasController {

    @Autowired
    private GerenciaService gerenciaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<GerenciaTarefas> createTarefa(@RequestBody GerenciaTarefas tarefa) {
        return gerenciaService.create(tarefa);
    }

    @GetMapping
    public Flux<GerenciaTarefas> getAllTarefas() {
        return gerenciaService.getAllTasks();
    }

    @GetMapping("/{id}")
    public Mono<GerenciaTarefas> getTarefaById(@PathVariable String id) {
        return gerenciaService.getTaskById(id);
    }

    @PutMapping("/{id}")
    public Mono<GerenciaTarefas> updateTarefa(@PathVariable String id, @RequestBody GerenciaTarefas tarefa) {
        return gerenciaService.updateTask(id, tarefa);
    }

    /**
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Mono<Void> deleteTarefa(@PathVariable String id) {
        return gerenciaService.deleteTask(id);
    }

    @GetMapping("/status/{realizada}")
    public Flux<GerenciaTarefas> getTarefasByStatus(@PathVariable boolean realizada) {
        return gerenciaService.getTasksByStatus(realizada);
    }
}
