package com.maonamassa.projectsystem;

import java.time.LocalDate;
import javax.persistence.Id;

import com.maonamassa.proposalsystem.Oferta;
import com.maonamassa.usersystem.Contratante;
import com.maonamassa.usersystem.Profissional;
import com.maonamassa.proposalsystem.Demanda;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/*
 * Essa classe é responsável por criar o objeto projeto, que é composto por um nome, descrição, data de início, 
 * data de fim e valor combinado.
 * O método de status deve ser alterado conforme a inserção de informações do projeto, geração de contrato, assinatura, 
 * início do projeto na data prevista, andamento e finalização na data prevista.
 * O ID do projeto é gerado automaticamente, por isso não deve ser alterado.
 */

@Entity
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "profissional_email")
    private Profissional profissional;

    @OneToOne
    @JoinColumn(name = "contratante_email")
    private Contratante contratante;

    private String nomeProjeto;
    private String tipoServico;
    private String descricaoProjeto;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String valorCombinado;

    @Enumerated(EnumType.STRING)
    private TipoDoProjeto tipoDoProjeto;

    @Enumerated(EnumType.STRING)
    private StatusDoProjeto statusDoProjeto;

    public Projeto() {}

    public Projeto(Oferta oferta, String nomeProjeto, String tipoServico, String descricaoProjeto,
            LocalDate dataInicio, LocalDate dataFim, String valorCombinado) {
        this.profissional = oferta.getProfissional();
        this.contratante = oferta.getContratante();
        this.nomeProjeto = nomeProjeto;
        this.tipoServico = tipoServico;
        this.descricaoProjeto = descricaoProjeto;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.valorCombinado = valorCombinado;
        this.tipoDoProjeto = TipoDoProjeto.OFERTA_DE_SERVICO;
        this.statusDoProjeto = StatusDoProjeto.AGUARDANDO_ACEITACAO;
    }

    public Projeto(Demanda demanda, String nomeProjeto, String tipoServico, String descricaoProjeto,
            LocalDate dataInicio, LocalDate dataFim, String valorCombinado) {
        this.profissional = demanda.getProfissional();
        this.contratante = demanda.getContratante();
        this.nomeProjeto = nomeProjeto;
        this.tipoServico = tipoServico;
        this.descricaoProjeto = descricaoProjeto;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.valorCombinado = valorCombinado;
        this.tipoDoProjeto = TipoDoProjeto.DEMANDA_DE_SERVICO;
        this.statusDoProjeto = StatusDoProjeto.AGUARDANDO_ACEITACAO;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    public Contratante getContratante() {
        return contratante;
    }

    public void setContratante(Contratante contratante) {
        this.contratante = contratante;
    }

    public String getNomeProjeto() {
        return nomeProjeto;
    }

    public void setNomeProjeto(String nomeProjeto) {
        this.nomeProjeto = nomeProjeto;
    }

    public String getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(String tipoServico) {
        this.tipoServico = tipoServico;
    }

    public String getDescricaoProjeto() {
        return descricaoProjeto;
    }

    public void setDescricaoProjeto(String descricaoProjeto) {
        this.descricaoProjeto = descricaoProjeto;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public String getValorCombinado() {
        return valorCombinado;
    }

    public void setValorCombinado(String valorCombinado) {
        this.valorCombinado = valorCombinado;
    }

    public TipoDoProjeto getTipoProjeto() {
        return tipoDoProjeto;
    }

    public void setTipoProjeto(TipoDoProjeto tipoDoProjeto) {
        this.tipoDoProjeto = tipoDoProjeto;
    }

    public StatusDoProjeto getStatusDoProjeto() {
        return statusDoProjeto;
    }

    public void setStatusDoProjeto(StatusDoProjeto statusDoProjeto) {
        this.statusDoProjeto = statusDoProjeto;
    }

    public void cancelarProjeto() {
        this.statusDoProjeto = StatusDoProjeto.CANCELADO;
    }
}