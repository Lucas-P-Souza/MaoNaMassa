package com.maonamassa.visualsystem.insideframes.userside;

import javax.swing.*;
import com.maonamassa.visualsystem.firstinteraction.MainFrame;
import com.maonamassa.visualsystem.insideframes.userside.newdialogs.JanelaPropostas;
import com.maonamassa.visualsystem.insideframes.userside.newdialogs.JanelaProjetos;

public class UserButtons extends JPanel {

    public UserButtons(MainFrame mainFrame) {
        setLayout(null); // Define layout nulo para posicionar os componentes manualmente

        // Botão "Ver Propostas"
        JButton proposalsButton = new JButton("Ver Propostas");
        proposalsButton.setBounds(75, 0, 180, 30); // Define posição e tamanho
        proposalsButton.addActionListener(e -> abrirJanelaPropostas());
        add(proposalsButton);

        // Botão "Ver Projetos"
        JButton projectsButton = new JButton("Ver Projetos");
        projectsButton.setBounds(75, 40, 180, 30); // Define posição e tamanho
        projectsButton.addActionListener(e -> abrirJanelaProjetos());
        add(projectsButton);

        // Botão "Logout"
        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(75, 80, 180, 30); // Define posição e tamanho
        logoutButton.addActionListener(e -> {
            if (mainFrame != null) {
                mainFrame.showScreen("LoginScreen");
            } else {
                System.out.println("MainFrame não está disponível. Apenas um teste.");
            }
        });
        add(logoutButton);
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
        frame.setSize(300, 200); // Tamanho ajustado do frame para caber os botões
        frame.add(new UserButtons(null)); // Adiciona o painel de botões
        frame.setVisible(true);
    }
}
