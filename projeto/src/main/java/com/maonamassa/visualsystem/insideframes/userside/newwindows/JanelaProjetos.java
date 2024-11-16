package com.maonamassa.visualsystem.insideframes.userside.newwindows;

import javax.swing.*;

public class JanelaProjetos extends JDialog {

    public JanelaProjetos() {
        setTitle("Projetos");
        setSize(400, 300); // Define o tamanho da janela
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setModal(true); // Bloqueia a interação com a janela principal enquanto esta estiver aberta

        // Adiciona conteúdo à janela de projetos
        JLabel label = new JLabel("Aqui estarão os projetos.");
        add(label);

        // Adiciona um painel de conteúdo e outros componentes, conforme necessário.
    }

    //teste
    public static void main(String[] args) {
        JanelaProjetos projetosDialog = new JanelaProjetos();
        projetosDialog.setVisible(true);
    }
}
