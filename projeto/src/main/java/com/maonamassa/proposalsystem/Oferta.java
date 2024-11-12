package com.maonamassa.proposalsystem;

import javax.persistence.*;
import com.maonamassa.usersystem.Contratante;
import com.maonamassa.usersystem.Profissional;

@Entity
public class Oferta
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Profissional profissional;

    @ManyToOne
    private Contratante contratante;
    
    private String descricao;
    private String valor;

    @Enumerated(EnumType.STRING)
    private StatusOfertaDemanda status;

    public Oferta() {}

    public Oferta(Profissional profissional, Contratante contratante, String descricao, String valor)
    {
        this.profissional = profissional;
        this.contratante = contratante;
        this.descricao = descricao;
        this.valor = valor;
        this.status = StatusOfertaDemanda.AGUARDANDO_ACEITACAO;
    }

    public Profissional getProfissional() {return profissional;}
    public void setProfissional(Profissional profissional) {this.profissional = profissional;}
    
    public Contratante getContratante() {return contratante;}
    public void setContratante(Contratante contratante) {this.contratante = contratante;}

    public String getDescricao() {return descricao;}
    public void setDescricao(String descricao) {this.descricao = descricao;}

    public String getValor() {return valor;}
    public void setValor(String valor) {this.valor = valor;}

    public StatusOfertaDemanda getStatus() {return status;}
    public void setStatus(StatusOfertaDemanda status) {this.status = status;}
    
    public void aceitarOferta() {this.status = StatusOfertaDemanda.PROPOSTA_ACEITA;}
    public void recusarOferta() {this.status = StatusOfertaDemanda.PROPOSTA_REJEITADA;}

	public Long getId() {return id;}
}
