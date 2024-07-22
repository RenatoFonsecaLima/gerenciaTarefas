package br.com.renatofonseca.gerenciamentodetarefas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.renatofonseca.gerenciamentodetarefas.entity.GerenciaTarefas;
import br.com.renatofonseca.gerenciamentodetarefas.service.GerenciaService;
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
