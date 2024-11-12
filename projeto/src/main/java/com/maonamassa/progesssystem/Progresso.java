package com.maonamassa.progesssystem;

import java.time.LocalDate;
import com.maonamassa.projectsystem.Projeto;
import com.maonamassa.proposalsystem.Oferta;
import com.maonamassa.contractsystem.Contrato;
import java.time.temporal.Temporal;
import java.time.temporal.ChronoUnit;
import com.maonamassa.projectsystem.StatusDoProjeto;
import com.maonamassa.contractsystem.StatusAssinaturas;
import com.maonamassa.proposalsystem.StatusOfertaDemanda;

public class Progresso 
{
    private StatusProgresso status;
    private int porcentagem;

    private Projeto projeto;
    private Contrato contrato;
    private Oferta oferta;
    
    public Progresso(Projeto projeto, Contrato contrato, Oferta oferta) 
    {
        this.projeto = projeto;
        this.contrato = contrato;
        this.oferta = oferta;
    }

    public StatusProgresso getStatus() {return status;}
    public void setStatus(StatusProgresso status) {this.status = status;}

    public int getPorcentagem() {return porcentagem;}
    public void setPorcentagem(int porcentagem){this.porcentagem = porcentagem;}

    // metodo para calcular a porcentagem de progresso baseado nas datas
    public void calculaPorcentagem(Temporal dataInicio, Temporal dataFim) 
    {
        LocalDate today = LocalDate.now();
        long diasDecorridos = ChronoUnit.DAYS.between(dataInicio, today);
        long diasTotais = ChronoUnit.DAYS.between(dataInicio, dataFim);

        if (diasTotais > 0) porcentagem = (int) ((diasDecorridos / (double) diasTotais) * 100);
        else porcentagem = 0;
        if (porcentagem > 100) porcentagem = 100;
    }

    // metodo para atualizar o status do progresso com base nas condições do sistema
    public void updateStatus(Temporal dataInicio, Temporal dataFim) 
    {
        LocalDate today = LocalDate.now();

        boolean propostaAceita = oferta != null && oferta.getStatus() == StatusOfertaDemanda.PROPOSTA_ACEITA;
        boolean projetoAceito = projeto != null && projeto.getStatusDoProjeto() == StatusDoProjeto.PROJETO_ACEITO;
        boolean contratoAssinado = contrato != null && contrato.getStatusAssinatura() == StatusAssinaturas.CONTRATO_FECHADO;

        if (propostaAceita && !projetoAceito && !contratoAssinado)
            status = StatusProgresso.AGUARDANDO_INFORMACOES; 
        else if (propostaAceita && projetoAceito && !contratoAssinado) 
            status = StatusProgresso.AGUARDANDO_ASSINATURAS;
        else if (propostaAceita && projetoAceito && contratoAssinado) 
        {
            if (today.isBefore((LocalDate) dataInicio)) 
                status = StatusProgresso.AGUARDANDO_INICIO;
            else if (today.isEqual((LocalDate) dataInicio) || today.isAfter((LocalDate) dataInicio) && today.isBefore((LocalDate) dataFim))
                status = StatusProgresso.EM_ANDAMENTO;
            else if (today.isEqual((LocalDate) dataFim) || today.isAfter((LocalDate) dataFim)) 
                status = StatusProgresso.FINALIZADO;
        }
    }
}
