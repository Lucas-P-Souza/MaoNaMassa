package com.maonamassa.visualsystem.profileandsearch;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.maonamassa.visualsystem.firstinteraction.MainFrame;

public class FirstScreen extends JPanel {

    public FirstScreen(MainFrame mainFrame) {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Título
        JLabel titleLabel = new JLabel("MÃO NA MASSA", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 48));
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(Box.createVerticalGlue()); // Adiciona espaço flexível acima para centralizar verticalmente
        add(titleLabel);

        // Painel de botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setAlignmentX(CENTER_ALIGNMENT);

        // Botão "Cadastrar-se"
        JButton registerButton = new JButton("Cadastrar-se");
        registerButton.setPreferredSize(new Dimension(120, 30));
        registerButton.addActionListener(e -> mainFrame.showScreen("RegisterScreen"));
        buttonPanel.add(registerButton);

        buttonPanel.add(Box.createRigidArea(new Dimension(20, 0))); // Espaço entre os botões

        // Botão "Fazer login"
        JButton loginButton = new JButton("Fazer login");
        loginButton.setPreferredSize(new Dimension(120, 30));
        loginButton.addActionListener(e -> mainFrame.showScreen("LoginScreen"));
        buttonPanel.add(loginButton);

        add(Box.createVerticalStrut(20)); // Espaço entre o título e os botões
        add(buttonPanel);
        add(Box.createVerticalGlue()); // Adiciona espaço flexível abaixo para centralizar verticalmente
    }
}
