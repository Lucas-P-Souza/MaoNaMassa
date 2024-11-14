package com.maonamassa.usersystem;

import javax.persistence.Entity;

@Entity
public class Contratante extends User 
{
    private String descricao;
    private String buscando;

    public Contratante() {}

    public Contratante(String nome, String cpfCnpj, String email, String senha){
        super(nome, cpfCnpj, email, senha);
    }

    public String getDescricao() {return descricao;}
    public void setDescricao(String descricao) {this.descricao = descricao;}

    public String getBuscando() {return buscando;}
    public void setBuscando(String buscando) {this.buscando = buscando;}

    
}