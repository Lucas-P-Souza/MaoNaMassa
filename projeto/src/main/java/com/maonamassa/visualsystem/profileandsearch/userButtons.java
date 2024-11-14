package com.maonamassa.visualsystem.profileandsearch;

import javax.swing.*;

import com.maonamassa.visualsystem.firstinteraction.MainFrame;

public class UserButtons extends JPanel {

    // As propostas abrangem tanto ofertas recebidas quanto feitas, independentemente de ser profissional ou contratante
    // Responsável por criar os botões de ver propostas e ver projetos

    public UserButtons(MainFrame mainFrame) {
        setLayout(null);

        JButton proposalsButton = new JButton("Ver Propostas");
        proposalsButton.setBounds(10, 20, 150, 30); 
        proposalsButton.addActionListener(e -> mainFrame.showScreen("ProposalsScreen"));
        add(proposalsButton);

        JButton projectsButton = new JButton("Ver Projetos");
        projectsButton.setBounds(10, 60, 150, 30);
        projectsButton.addActionListener(e -> mainFrame.showScreen("ProjectsScreen"));
        add(projectsButton);
    }

    //main para teste visual
    public static void main(String[] args) {
        JFrame frame = new JFrame("User Buttons");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.add(new UserButtons(null));
        frame.setVisible(true);
    }
}
