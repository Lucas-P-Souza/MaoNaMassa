package com.maonamassa.usersystem;

import java.util.List;
import java.util.Arrays;
import javax.persistence.Entity;
import java.util.stream.Collectors;

@Entity
public class Contratante extends User 
{

    private String descricao;
    private String metodosPagamento;

    public Contratante() {}

    public Contratante(String nome, String cpfCnpj, String email, String senha){
        super(nome, email, senha, cpfCnpj);
    }

    public String getDescricao() {return descricao;}
    public void setDescricao(String descricao) {this.descricao = descricao;}
    
    public void setPagamento(List<MetodoDePagamento> pagamento) 
    {
        this.metodosPagamento = pagamento.stream()
                                          .map(MetodoDePagamento::name)
                                          .collect(Collectors.joining(","));  
    }
    
    public List<MetodoDePagamento> getPagamento() 
    {
        return Arrays.stream(metodosPagamento.split(","))
                     .map(MetodoDePagamento::valueOf)  
                     .collect(Collectors.toList());
    }
}
