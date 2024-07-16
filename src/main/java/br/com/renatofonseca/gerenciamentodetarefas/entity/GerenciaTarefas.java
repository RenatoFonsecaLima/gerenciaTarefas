package br.com.renatofonseca.gerenciamentodetarefas.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "gerenciamentoTarefas")
public class GerenciaTarefas {

    @Id
    private String id;

    @Field("nome")
    private String nome;

    @Field("descricao")
    private String descricao;

    @Field("realizada")
    private boolean realizada;

    @Field("prioridade")
    private int prioridade;

    // Construtores, getters e setters

    public GerenciaTarefas() {
    }

    public GerenciaTarefas(String nome, String descricao, boolean realizada, int prioridade) {
        this.nome = nome;
        this.descricao = descricao;
        this.realizada = realizada;
        this.prioridade = prioridade;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isRealizada() {
        return realizada;
    }

    public void setRealizada(boolean realizada) {
        this.realizada = realizada;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }
}
