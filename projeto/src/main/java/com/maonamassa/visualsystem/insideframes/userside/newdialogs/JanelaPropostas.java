package com.maonamassa.visualsystem.insideframes.userside.newdialogs;

import javax.swing.*;
import com.maonamassa.banco_de_dados.Consultas;
import com.maonamassa.proposalsystem.Demanda;
import com.maonamassa.proposalsystem.Oferta;
import com.maonamassa.usersystem.Sessao;
import com.maonamassa.visualsystem.firstinteraction.LoginScreen;
import java.awt.*;
import java.util.List;

public class JanelaPropostas extends JDialog {

    public JanelaPropostas() {
        Sessao sessao = LoginScreen.getSessao();

        setTitle("Propostas");
        setSize(600, 500); // Define o tamanho da janela
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
            propostasFeitasOFERTA = Consultas.buscarOfertasEnviadas(sessao.getProfissionalLogado());
            propostasRecebidasDEMANDA = Consultas.buscarDemandasRecebidas(sessao.getProfissionalLogado());

            tabbedPane.addTab("Propostas Feitas", criarPainelDePropostasOFERTA(propostasFeitasOFERTA, "Feita"));
            tabbedPane.addTab("Propostas Recebidas",
                    criarPainelDePropostasDEMANDA(propostasRecebidasDEMANDA, "Recebida"));

        } else {
            // Se for um contratante, a oferta é a proposta recebida por ele, e a demanda é
            // a proposta feita por ele
            propostasFeitasDEMANDA = Consultas.buscarDemandasEnviadas(sessao.getContratanteLogado());
            propostasRecebidasOFERTA = Consultas.buscarOfertasRecebidas(sessao.getContratanteLogado());

            tabbedPane.addTab("Propostas Feitas", criarPainelDePropostasDEMANDA(propostasFeitasDEMANDA, "Feita"));
            tabbedPane.addTab("Propostas Recebidas",
                    criarPainelDePropostasOFERTA(propostasRecebidasOFERTA, "Recebida"));
        }

        // Adiciona o JTabbedPane à janela
        add(tabbedPane);
    }

    // Método para criar o painel com cartões de propostas do tipo Oferta
    private JScrollPane criarPainelDePropostasOFERTA(List<Oferta> propostas, String tipo) {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));

        if (propostas.isEmpty()) {
            JLabel mensagem = new JLabel("Não há ofertas " + tipo.toLowerCase() + "s.");
            mensagem.setFont(new Font("Arial", Font.BOLD, 18));
            painel.add(mensagem);
        } else {
            for (Oferta proposta : propostas) {
                JPanel cartao = CartaoPropostaFactory.criarCartao(
                        proposta.getProfissional().getName(),
                        proposta.getDescricao(),
                        tipo,
                        proposta);
                painel.add(cartao);
                painel.add(Box.createVerticalStrut(10)); // Espaçamento entre cartões
            }
        }

        // Remover a rolagem horizontal e permitir apenas rolagem vertical
        JScrollPane scrollPane = new JScrollPane(painel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);

        return scrollPane;
    }

    // Método para criar o painel com cartões de propostas do tipo Demanda
    private JScrollPane criarPainelDePropostasDEMANDA(List<Demanda> propostas, String tipo) {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));

        if (propostas.isEmpty()) {
            JLabel mensagem = new JLabel("Não há demandas " + tipo.toLowerCase() + "s.");
            mensagem.setFont(new Font("Arial", Font.BOLD, 18));
            painel.add(mensagem);
        } else {
            for (Demanda proposta : propostas) {
                JPanel cartao = CartaoPropostaFactory.criarCartao(
                        proposta.getContratante().getName(),
                        proposta.getDescricao(),
                        tipo,
                        proposta);
                painel.add(cartao);
                painel.add(Box.createVerticalStrut(10)); // Espaçamento entre cartões
            }
        }

        // Remover a rolagem horizontal e permitir apenas rolagem vertical
        JScrollPane scrollPane = new JScrollPane(painel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);

        return scrollPane;
    }

    public static void main(String[] args) {
        JanelaPropostas propostasDialog = new JanelaPropostas();
        propostasDialog.setVisible(true);
    }
}
