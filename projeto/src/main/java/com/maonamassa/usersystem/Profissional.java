package com.maonamassa.usersystem;

import javax.persistence.Entity;

@Entity
public class Profissional extends User 
{

    private String areaAtuacao;
    private Disponibilidade disponibilidade;
    private String profissao;

    public Profissional() {}

    public Profissional(String nome, String cpfCnpj, String email, String senha){
        super(nome, cpfCnpj, email, senha);
    }

    public String getAreaAtuacao() {return areaAtuacao;}
    public void setAreaAtuacao(String areaAtuacao) {this.areaAtuacao = areaAtuacao;}

    public Disponibilidade getDisponibilidade() {return disponibilidade;}
    public void setDisponibilidade(Disponibilidade disponibilidade) {this.disponibilidade = disponibilidade;}

    public String getProfissao() {return profissao;}
    public void setProfissao(String profissao) {this.profissao = profissao;}

}