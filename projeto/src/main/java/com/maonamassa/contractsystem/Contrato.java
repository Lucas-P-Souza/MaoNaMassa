package com.maonamassa.contractsystem;

import javax.persistence.*;
import java.time.LocalDate;
import com.maonamassa.projectsystem.Projeto;
import com.maonamassa.projectsystem.TipoDoProjeto;

@Entity
public class Contrato 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Projeto projeto;
    
    private String nomeContratante;
    private String cpfCnpjContratante;
    private String enderecoContratante;
    private String nomeProfissional;
    private String cpfCnpjProfissional;
    private String enderecoProfissional;
    private String descricaoDetalhada;
    private String valorCombinado;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    
    @Enumerated(EnumType.STRING)
	private StatusAssinaturas statusAssinatura;

    public Contrato() {}

    public Contrato(Projeto projeto) 
    {
        this.projeto = projeto;
        if (projeto.getTipoProjeto() == TipoDoProjeto.OFERTA_DE_SERVICO) 
        {
            this.nomeContratante = projeto.getOferta().getContratante().getName();
            this.cpfCnpjContratante = projeto.getOferta().getContratante().getCpfcnpj();
            this.enderecoContratante = projeto.getOferta().getContratante().getAddress();
            this.nomeProfissional = projeto.getOferta().getProfissional().getName();
            this.cpfCnpjProfissional = projeto.getOferta().getProfissional().getCpfcnpj();
            this.enderecoProfissional = projeto.getOferta().getProfissional().getAddress();
        } 
        else if (projeto.getTipoProjeto() == TipoDoProjeto.DEMANDA_DE_SERVICO) 
        {
            this.nomeContratante = projeto.getDemanda().getContratante().getName();
            this.cpfCnpjContratante = projeto.getDemanda().getContratante().getCpfcnpj();
            this.enderecoContratante = projeto.getDemanda().getContratante().getAddress();
            this.nomeProfissional = projeto.getDemanda().getProfissional().getName();
            this.cpfCnpjProfissional = projeto.getDemanda().getProfissional().getCpfcnpj();
            this.enderecoProfissional = projeto.getDemanda().getProfissional().getAddress(); 
        }
        this.descricaoDetalhada = projeto.getDescricaoProjeto();
        this.valorCombinado = projeto.getValorCombinado().toString();
        this.dataInicio = projeto.getDataInicio();
        this.dataFim = projeto.getDataFim();
        this.setStatusAssinatura(StatusAssinaturas.AGUARDANDO_ASSINATURAS);
    }
    
    public Projeto getProjeto() {return projeto;}
    public Long getId() {return id;}
    
    public String getNomeContratante() {return nomeContratante;}
    public String getCpfCnpjContratante() {return cpfCnpjContratante;}
    
    public String getEnderecoContratante() {return enderecoContratante;}
    public String getNomeProfissional() {return nomeProfissional;}
    
    public String getCpfCnpjProfissional() {return cpfCnpjProfissional;}
    public String getEnderecoProfissional(){return enderecoProfissional;}
   
    public String getDescricaoDetalhada() { return descricaoDetalhada;}
    public String getValorCombinado() { return valorCombinado;}
    
    public LocalDate getDataInicio() {return dataInicio;}
    public LocalDate getDataFim() {return dataFim; }
    
    public void assinarPeloProfissional() 
    {
        if (statusAssinatura == StatusAssinaturas.AGUARDANDO_ASSINATURAS) 
            this.statusAssinatura = StatusAssinaturas.ASSINADO_PELO_PROFISSIONAL;
        else if (statusAssinatura == StatusAssinaturas.ASSINADO_PELO_CONTRATANTE)
        	this.statusAssinatura = StatusAssinaturas.CONTRATO_FECHADO;
    }

    public void assinarPeloContratante() 
    {
        if (statusAssinatura == StatusAssinaturas.AGUARDANDO_ASSINATURAS)
            this.statusAssinatura = StatusAssinaturas.ASSINADO_PELO_CONTRATANTE;
        else if (statusAssinatura == StatusAssinaturas.ASSINADO_PELO_PROFISSIONAL)
            this.statusAssinatura = StatusAssinaturas.CONTRATO_FECHADO;
    }

    public void cancelarContrato() {this.statusAssinatura = StatusAssinaturas.CONTRATO_CANCELADO;}

	public StatusAssinaturas getStatusAssinatura() {return statusAssinatura;}
	public void setStatusAssinatura(StatusAssinaturas statusAssinatura) {this.statusAssinatura = statusAssinatura;}

}
