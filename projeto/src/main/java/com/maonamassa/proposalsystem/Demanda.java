package com.maonamassa.proposalsystem;

import javax.persistence.*;
import com.maonamassa.usersystem.Contratante;
import com.maonamassa.usersystem.Profissional;

@Entity
public class Demanda 
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Profissional profissional;

    @ManyToOne
    private Contratante contratante;

    private String descricao;

    @Enumerated(EnumType.STRING)
    private StatusOfertaDemanda status;

    public Demanda() {}

    public Demanda(Profissional profissional, Contratante contratante, String descricao,
    		       StatusOfertaDemanda status) 
    {
        this.profissional = profissional;
        this.contratante = contratante;
        this.descricao = descricao;
        this.status = status;
    }

    public Profissional getProfissional() {return profissional;}
    public void setProfissional(Profissional profissional) {this.profissional = profissional;}
    
    public Contratante getContratante() {return contratante;}
    public void setContratante(Contratante contratante) {this.contratante = contratante;}

    public String getDescricao() {return descricao;}
    public void setDescricao(String descricao) {this.descricao = descricao;}

    public StatusOfertaDemanda getStatus() {return status;}
    public void setStatus(StatusOfertaDemanda status) {this.status = status;}

    public void aceitarDemanda() {this.status = StatusOfertaDemanda.PROPOSTA_ACEITA;}
    public void recusarDemanda() {this.status = StatusOfertaDemanda.PROPOSTA_REJEITADA;}

	public Long getId() {return id;}
}
