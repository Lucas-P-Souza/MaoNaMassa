package com.maonamassa.apps;

import com.maonamassa.proposalsystem.*;
import com.maonamassa.projectsystem.Projeto;
import com.maonamassa.usersystem.UserService;
import com.maonamassa.usersystem.Contratante;
import com.maonamassa.usersystem.Profissional;
import com.maonamassa.contractsystem.Contrato;
import com.maonamassa.projectsystem.ProjectService;
import com.maonamassa.contractsystem.ContractService;

public class AppBanco 
{
    public static void main(String[] args) 
    {
        Profissional profissional = UserService.cadastrarProfissional(
                "João Silva", 
                "joao.silva@email.com", 
                "senha123", 
                "123.456.789-00"
        );
        
        System.out.println("Cadastro de Profissional realizado: " + profissional.getName());

        Contratante contratante = UserService.cadastrarContratante(
                "Maria Oliveira", 
                "maria.oliveira@email.com", 
                "senha456", 
                "987.654.321-00"
        );
        
        System.out.println("Cadastro de Contratante realizado: " + contratante.getName());

        Oferta oferta = ProposalService.criarOferta(
                profissional, 
                contratante, 
                "Desenvolvimento de um site institucional", 
                "5000"
        );
        
        System.out.println("Oferta criada: " + oferta.getDescricao());

        Demanda demanda = ProposalService.criarDemanda(
                profissional, 
                contratante, 
                "Recrutamento de desenvolvedor Java"
        );
        
        System.out.println("Demanda criada: " + demanda.getDescricao());
        
        ProposalService.aceitarOferta(oferta.getId());
        System.out.println("Oferta aceita com sucesso!");

        ProposalService.aceitarDemanda(demanda.getId());
        System.out.println("Demanda aceita com sucesso!");

        Projeto projeto = ProjectService.criarProjeto(
                oferta, 
                null, 
                "Desenvolvimento do Site Institucional", 
                "Desenvolvimento Web", 
                "Projeto de desenvolvimento de um site institucional para o contratante", 
                java.time.LocalDate.now(), 
                java.time.LocalDate.now().plusDays(30), 
                "5000"
        );
        System.out.println("Projeto criado: " + projeto.getNomeProjeto());

        Contrato contrato = ContractService.criarContrato(projeto);
        System.out.println("Contrato criado para o projeto: " + contrato.getId());

        ContractService.assinarPeloProfissional(contrato.getId());
        System.out.println("Contrato assinado pelo Profissional.");

        ContractService.assinarPeloContratante(contrato.getId());
        System.out.println("Contrato assinado pelo Contratante.");

        ContractService.cancelarContrato(contrato.getId());
        System.out.println("Contrato cancelado.");

        Contrato contratoBuscado = ContractService.buscarContratoPorId(contrato.getId());
        System.out.println("Contrato buscado pelo ID: " + contratoBuscado.getId() + " | Status: " + contratoBuscado.getStatusAssinatura());
    }
}
