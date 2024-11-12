package com.maonamassa.projectsystem;

import java.time.LocalDate;
import javax.persistence.Id;
import com.maonamassa.proposalsystem.Oferta;
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
public class Projeto 
{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "oferta_id")
    private Oferta oferta;

    @OneToOne
    @JoinColumn(name = "demanda_id")
    private Demanda demanda;

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

    public Projeto(Oferta oferta, Demanda demanda, String nomeProjeto, String tipoServico, 
    		       String descricaoProjeto, LocalDate dataInicio, LocalDate dataFim, 
    		       String valorCombinado) 
    {
	 this.oferta = oferta;
	 this.demanda = demanda;
	 this.nomeProjeto = nomeProjeto;
	 this.tipoServico = tipoServico;
	 this.descricaoProjeto = descricaoProjeto;
	 this.dataInicio = dataInicio;
	 this.dataFim = dataFim;
	 this.valorCombinado = valorCombinado;
	 this.tipoDoProjeto = (oferta != null) ? TipoDoProjeto.OFERTA_DE_SERVICO : TipoDoProjeto.DEMANDA_DE_SERVICO;
	 this.statusDoProjeto = StatusDoProjeto.AGUARDANDO_ACEITACAO;
    }

    // Getters e Setters
    public Long getId() {return id;}

    public String getNomeProjeto() {return nomeProjeto;}
    public void setNomeProjeto(String nomeProjeto) {this.nomeProjeto = nomeProjeto;}
    
    public String getTipoServico() {return tipoServico;}
    public void setTipoServico(String tipoServico) {this.tipoServico = tipoServico;}
    
    public String getDescricaoProjeto() {return descricaoProjeto;}
    public void setDescricaoProjeto(String descricaoProjeto) {this.descricaoProjeto = descricaoProjeto;}

    public LocalDate getDataInicio() {return dataInicio;}
    public void setDataInicio(LocalDate dataInicio) {this.dataInicio = dataInicio;}

    public LocalDate getDataFim() {return dataFim;}
    public void setDataFim(LocalDate dataFim) {this.dataFim = dataFim;}

    public String getValorCombinado() {return valorCombinado;}
    public void setValorCombinado(String valorCombinado) {this.valorCombinado = valorCombinado;}
    
    public TipoDoProjeto getTipoProjeto() {return tipoDoProjeto;}
    public void setTipoProjeto(TipoDoProjeto tipoDoProjeto) {this.tipoDoProjeto = tipoDoProjeto;}

    public Oferta getOferta() {return oferta;}
    public void setOferta(Oferta oferta) {this.oferta = oferta;}

    public Demanda getDemanda() {return demanda;}
    public void setDemanda(Demanda demanda) {this.demanda = demanda;}

	public StatusDoProjeto getStatusDoProjeto() {return statusDoProjeto;}
	public void setStatusDoProjeto(StatusDoProjeto statusDoProjeto) {this.statusDoProjeto = statusDoProjeto;}   
}
