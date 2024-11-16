package com.maonamassa.visualsystem.insideframes.userside.newwindows;

import javax.swing.*;

public class JanelaPropostas extends JDialog {

    public JanelaPropostas() {
        setTitle("Propostas");
        setSize(400, 300); // Define o tamanho da janela
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setModal(true); // Bloqueia a interação com a janela principal enquanto esta estiver aberta

        // Adiciona conteúdo à janela de propostas
        JLabel label = new JLabel("Aqui estarão as propostas.");
        add(label);

        // Adiciona um painel de conteúdo e outros componentes, conforme necessário.
    }

    //teste
    public static void main(String[] args) {
        JanelaPropostas propostasDialog = new JanelaPropostas();
        propostasDialog.setVisible(true);
    }
}
