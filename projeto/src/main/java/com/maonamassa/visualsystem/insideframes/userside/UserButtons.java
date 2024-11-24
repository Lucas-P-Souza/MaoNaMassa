package com.maonamassa.visualsystem.insideframes.userside;

import javax.swing.*;
import java.awt.*;
import com.maonamassa.visualsystem.firstinteraction.MainFrame;
import com.maonamassa.visualsystem.insideframes.userside.newdialogs.JanelaPropostas;
import com.maonamassa.visualsystem.insideframes.userside.newdialogs.JanelaProjetos;

public class UserButtons extends JPanel {

    public UserButtons(MainFrame mainFrame) {
        // Configura o layout do painel como FlowLayout, centralizando os botões
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10)); // Espaço horizontal: 20px, vertical: 10px

        // Botão "Ver Propostas"
        JButton proposalsButton = new JButton("Ver Propostas");
        proposalsButton.addActionListener(e -> abrirJanelaPropostas());
        add(proposalsButton); // Adiciona o botão ao painel

        // Botão "Ver Projetos"
        JButton projectsButton = new JButton("Ver Projetos");
        projectsButton.addActionListener(e -> abrirJanelaProjetos());
        add(projectsButton); // Adiciona o botão ao painel

        // Botão "Logout"
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> {
            if (mainFrame != null) {
                mainFrame.showScreen("LoginScreen");
            } else {
                System.out.println("MainFrame não está disponível. Apenas um teste.");
            }
        });
        add(logoutButton); // Adiciona o botão ao painel
    }

    // Método para abrir a janela de propostas
    private void abrirJanelaPropostas() {
        JanelaPropostas propostasDialog = new JanelaPropostas();
        propostasDialog.setVisible(true);
    }

    // Método para abrir a janela de projetos
    private void abrirJanelaProjetos() {
        JanelaProjetos projetosDialog = new JanelaProjetos();
        projetosDialog.setVisible(true);
    }

    // main para teste visual
    public static void main(String[] args) {
        JFrame frame = new JFrame("User Buttons");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 150); // Tamanho inicial do frame
        frame.add(new UserButtons(null)); // Adiciona o painel de botões
        frame.setVisible(true);
    }
}
