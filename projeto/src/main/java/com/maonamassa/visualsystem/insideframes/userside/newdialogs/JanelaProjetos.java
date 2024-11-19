package com.maonamassa.visualsystem.insideframes.userside.newdialogs;

import javax.swing.*;
import com.maonamassa.banco_de_dados.Consultas;
import com.maonamassa.projectsystem.Projeto;
import com.maonamassa.visualsystem.firstinteraction.LoginScreen;
import java.awt.*;
import java.util.List;

public class JanelaProjetos extends JDialog {

    public JanelaProjetos() {
        // Configuração inicial da janela
        setTitle("Meus Projetos");
        setSize(500, 580); // Define o tamanho da janela
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setModal(true); // Bloqueia a interação com a janela principal enquanto esta estiver aberta
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); // Fecha a janela ao clicar no botão fechar
        setLayout(null); // Layout nulo para manter setBounds funcional

        // Título na parte superior
        JLabel titulo = new JLabel("Meus Projetos", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setBounds(150, 10, 200, 30); // Posição e tamanho do título
        add(titulo);

        // Painel de projetos envolvido por JScrollPane
        JPanel painelProjetos = new JPanel();
        painelProjetos.setLayout(null); // Layout nulo para que os cartões sejam posicionados manualmente

        // Adiciona os cartões de projetos ao painel
        adicionarCartoesProjetos(painelProjetos);

        // JScrollPane para permitir rolagem
        JScrollPane scrollPane = new JScrollPane(painelProjetos);
        scrollPane.setBounds(20, 50, 450, 400); // Posição e tamanho do JScrollPane
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Sempre mostra a barra de
                                                                                      // rolagem vertical
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Impede a rolagem horizontal
        scrollPane.getVerticalScrollBar().setUnitIncrement(20); // Ajusta a sensibilidade do scroll
        add(scrollPane);

        // Botão de Fechar na parte inferior
        JButton botaoFechar = new JButton("Fechar");
        botaoFechar.setBounds(200, 480, 100, 30); // Posição e tamanho do botão
        botaoFechar.addActionListener(e -> dispose()); // Fecha a janela ao clicar no botão
        add(botaoFechar);
    }

    // Função para adicionar os cartões de projetos ao painel
    private void adicionarCartoesProjetos(JPanel painelProjetos) {
        System.out.println("Adicionando cartões de projetos...");
        int alturaCartao = 360;

        List<Projeto> projetos;
        if (LoginScreen.getSessao().getIsProfissional()) {
            projetos = Consultas.buscarProjetosPorProfissional(LoginScreen.getSessao().getProfissionalLogado());
        } else {
            projetos = Consultas.buscarProjetosPorContratante(LoginScreen.getSessao().getContratanteLogado());
        }

        System.out.println("Projetos encontrados: " + projetos.size());

        if (projetos.isEmpty()) {
            JLabel vazio = new JLabel("Não há projetos a serem mostrados", SwingConstants.CENTER);
            vazio.setBounds(0, 0, 450, 50);
            painelProjetos.add(vazio);
            painelProjetos.setPreferredSize(new Dimension(450, 50));
        } else {
            for (int i = 0; i < projetos.size(); i++) {
                Projeto projeto = projetos.get(i);
                System.out.println("Criando cartão para: " + projeto.getNomeProjeto());
                CartaoProjeto cartao = new CartaoProjeto(projeto);
                cartao.setBounds(5, alturaCartao * i, 423, alturaCartao);
                painelProjetos.add(cartao);
            }
            painelProjetos.setPreferredSize(new Dimension(450, alturaCartao * projetos.size()));
        }

        painelProjetos.revalidate();
        painelProjetos.repaint();
    }

    public static void main(String[] args) {
        // Criação e exibição do JDialog
        JanelaProjetos projetosDialog = new JanelaProjetos();
        projetosDialog.setVisible(true);
    }
}
