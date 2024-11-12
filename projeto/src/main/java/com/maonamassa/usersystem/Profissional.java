package com.maonamassa.usersystem;

import javax.persistence.Entity;

@Entity
public class Profissional extends User 
{

    private String areaAtuacao;
    private String disponibilidade;

    public Profissional() {}

    public Profissional(String nome, String cpfCnpj, String email, String senha){
        super(nome, cpfCnpj, email, senha);
    }

    public String getAreaAtuacao() {return areaAtuacao;}
    public void setAreaAtuacao(String areaAtuacao) {this.areaAtuacao = areaAtuacao;}

    public String getDisponibilidade() {return disponibilidade;}
    public void setDisponibilidade(String disponibilidade) {this.disponibilidade = disponibilidade;}
}
