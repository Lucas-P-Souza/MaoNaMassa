package com.maonamassa.visualsystem.insideframes.userside.newdialogs;

import javax.swing.*;
import com.maonamassa.banco_de_dados.Consultas;
import com.maonamassa.proposalsystem.Demanda;
import com.maonamassa.proposalsystem.Oferta;
import com.maonamassa.usersystem.Sessao;
import com.maonamassa.visualsystem.firstinteraction.LoginScreen;
import java.awt.Font;
import java.util.List;

public class JanelaPropostas extends JDialog {

    public JanelaPropostas() {

        Sessao sessao = LoginScreen.getSessao();

        setTitle("Propostas");
        setSize(500, 400); // Define o tamanho da janela
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setModal(true); // Bloqueia a interação com a janela principal enquanto esta estiver aberta

        // Criação do JTabbedPane
        JTabbedPane tabbedPane = new JTabbedPane();

        // Definir as listas de propostas com base no tipo de usuário
        List<Oferta> propostasFeitasOFERTA;
        List<Demanda> propostasRecebidasDEMANDA;

        List<Demanda> propostasFeitasDEMANDA;
        List<Oferta> propostasRecebidasOFERTA;

        if (sessao.getIsProfissional()) {
            // Se for um profissional, a oferta é a proposta feita por ele, e a demanda é a
            // proposta recebida por ele
            propostasFeitasOFERTA = Consultas.buscarOfertasEnviadas(sessao.getProfissionalLogado()); // OFERTA (Feitas)
            propostasRecebidasDEMANDA = Consultas.buscarDemandasRecebidas(sessao.getProfissionalLogado()); // DEMANDA
                                                                                                           // (Recebidas)

            tabbedPane.addTab("Propostas Feitas", criarPainelDePropostasEnviadasOFERTA(propostasFeitasOFERTA));
            tabbedPane.addTab("Propostas Recebidas", criarPainelDePropostasRecebidasDEMANDA(propostasRecebidasDEMANDA));

        } else {
            // Se for um contratante, a oferta é a proposta recebida por ele, e a demanda é
            // a proposta feita por ele
            propostasFeitasDEMANDA = Consultas.buscarDemandasEnviadas(sessao.getContratanteLogado()); // DEMANDA
                                                                                                      // (Feitas)
            propostasRecebidasOFERTA = Consultas.buscarOfertasRecebidas(sessao.getContratanteLogado()); // OFERTA
                                                                                                        // (Recebidas)

            tabbedPane.addTab("Propostas Feitas", criarPainelDePropostasEnviadasDEMANDA(propostasFeitasDEMANDA));
            tabbedPane.addTab("Propostas Recebidas", criarPainelDePropostasRecebidasOFERTA(propostasRecebidasOFERTA));

        }

        // Adiciona o JTabbedPane à janela
        add(tabbedPane);
    }

    // Função para criar o painel de "Propostas Recebidas" com uma lista de Demandas
    private JScrollPane criarPainelDePropostasRecebidasDEMANDA(List<Demanda> propostas) {
        JPanel painel = new JPanel();
        painel.setLayout(null); // Usando layout nulo para poder ajustar as posições manualmente

        // Verifica se a lista de propostas está vazia e exibe uma mensagem
        if (propostas.isEmpty()) {
            JLabel mensagem = new JLabel("Não há propostas recebidas.");
            mensagem.setFont(new Font("Arial", Font.BOLD, 18)); // Fonte maior
            mensagem.setBounds(100, 150, 300, 30); // Centraliza a mensagem
            painel.add(mensagem);
        } else {
            // Adiciona os itens da lista de demandas recebidas ao painel
            int yPosition = 10;
            for (Demanda proposta : propostas) {
                JLabel label = new JLabel(proposta.toString()); // Exibe a demanda usando toString()
                label.setBounds(10, yPosition, 460, 30); // Defina a posição de cada proposta
                painel.add(label);
                yPosition += 40; // Espaçamento entre as propostas
            }
        }

        // Coloca o painel dentro de um JScrollPane para permitir rolagem
        JScrollPane scrollPane = new JScrollPane(painel);
        return scrollPane;
    }

    // Função para criar o painel de "Propostas Enviadas" com uma lista de Demandas
    private JScrollPane criarPainelDePropostasEnviadasDEMANDA(List<Demanda> propostas) {
        JPanel painel = new JPanel();
        painel.setLayout(null); // Usando layout nulo para poder ajustar as posições manualmente

        // Verifica se a lista de propostas está vazia e exibe uma mensagem
        if (propostas.isEmpty()) {
            JLabel mensagem = new JLabel("Não há propostas enviadas.");
            mensagem.setFont(new Font("Arial", Font.BOLD, 18)); // Fonte maior
            mensagem.setBounds(100, 150, 300, 30); // Centraliza a mensagem
            painel.add(mensagem);
        } else {
            // Adiciona os itens da lista de demandas enviadas ao painel
            int yPosition = 10;
            for (Demanda proposta : propostas) {
                JLabel label = new JLabel(proposta.toString()); // Exibe a demanda usando toString()
                label.setBounds(10, yPosition, 460, 30); // Defina a posição de cada proposta
                painel.add(label);
                yPosition += 40; // Espaçamento entre as propostas
            }
        }

        // Coloca o painel dentro de um JScrollPane para permitir rolagem
        JScrollPane scrollPane = new JScrollPane(painel);
        return scrollPane;
    }

    // Função para criar o painel de "Propostas Enviadas" com uma lista de Ofertas
    private JScrollPane criarPainelDePropostasEnviadasOFERTA(List<Oferta> propostas) {
        JPanel painel = new JPanel();
        painel.setLayout(null); // Usando layout nulo para poder ajustar as posições manualmente

        // Verifica se a lista de propostas está vazia e exibe uma mensagem
        if (propostas.isEmpty()) {
            JLabel mensagem = new JLabel("Não há ofertas enviadas.");
            mensagem.setFont(new Font("Arial", Font.BOLD, 18)); // Fonte maior
            mensagem.setBounds(100, 150, 300, 30); // Centraliza a mensagem
            painel.add(mensagem);
        } else {
            // Adiciona os itens da lista de ofertas enviadas ao painel
            int yPosition = 10;
            for (Oferta proposta : propostas) {
                JLabel label = new JLabel(proposta.toString()); // Exibe a oferta usando toString()
                label.setBounds(10, yPosition, 460, 30); // Defina a posição de cada oferta
                painel.add(label);
                yPosition += 40; // Espaçamento entre as ofertas
            }
        }

        // Coloca o painel dentro de um JScrollPane para permitir rolagem
        JScrollPane scrollPane = new JScrollPane(painel);
        return scrollPane;
    }

    // Função para criar o painel de "Propostas Recebidas" com uma lista de Ofertas
    private JScrollPane criarPainelDePropostasRecebidasOFERTA(List<Oferta> propostas) {
        JPanel painel = new JPanel();
        painel.setLayout(null); // Usando layout nulo para poder ajustar as posições manualmente

        // Verifica se a lista de propostas está vazia e exibe uma mensagem
        if (propostas.isEmpty()) {
            JLabel mensagem = new JLabel("Não há ofertas recebidas.");
            mensagem.setFont(new Font("Arial", Font.BOLD, 18)); // Fonte maior
            mensagem.setBounds(100, 150, 300, 30); // Centraliza a mensagem
            painel.add(mensagem);
        } else {
            // Adiciona os itens da lista de ofertas recebidas ao painel
            int yPosition = 10;
            for (Oferta proposta : propostas) {
                JLabel label = new JLabel(proposta.toString()); // Exibe a oferta usando toString()
                label.setBounds(10, yPosition, 460, 30); // Defina a posição de cada oferta
                painel.add(label);
                yPosition += 40; // Espaçamento entre as ofertas
            }
        }

        // Coloca o painel dentro de um JScrollPane para permitir rolagem
        JScrollPane scrollPane = new JScrollPane(painel);
        return scrollPane;
    }

    public static void main(String[] args) {
        // Criação e exibição do JDialog
        JanelaPropostas propostasDialog = new JanelaPropostas();
        propostasDialog.setVisible(true);
    }
}
