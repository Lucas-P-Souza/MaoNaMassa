package com.maonamassa.usersystem;

import javax.persistence.Entity;

@Entity
public class Profissional extends User 
{

    private String areaAtuacao;
    private String disponibilidade;

    public Profissional() {}

    public Profissional(String nome, String email, String senha, String telefone, String endereco, 
    		            String cpfCnpj, String areaAtuacao, String disponibilidade) 
    {
        super(nome, email, senha, telefone, endereco, cpfCnpj);
        this.areaAtuacao = areaAtuacao;
        this.disponibilidade = disponibilidade;
    }

    public String getAreaAtuacao() {return areaAtuacao;}
    public void setAreaAtuacao(String areaAtuacao) {this.areaAtuacao = areaAtuacao;}

    public String getDisponibilidade() {return disponibilidade;}
    public void setDisponibilidade(String disponibilidade) {this.disponibilidade = disponibilidade;}
}
