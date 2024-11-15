package com.maonamassa.visualsystem.firstinteraction;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LoginScreen extends JPanel {

    public LoginScreen(MainFrame mainFrame) {
        setLayout(new BorderLayout());

        // Título da tela de login
        JLabel label = new JLabel("Tela de Login", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        add(label, BorderLayout.NORTH);

        // Painel para os campos de entrada com GridBagLayout
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaçamento entre os componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Campo para o e-mail
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        formPanel.add(new JLabel("E-mail:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        JTextField emailField = new JTextField(20);
        formPanel.add(emailField, gbc);

        // Campo para a senha
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        formPanel.add(new JLabel("Senha:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        JPasswordField passwordField = new JPasswordField(20);
        formPanel.add(passwordField, gbc);

        // CheckBox "Lembrar de mim"
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        JCheckBox rememberMeCheckBox = new JCheckBox("Lembrar de mim");
        formPanel.add(rememberMeCheckBox, gbc);

        add(formPanel, BorderLayout.CENTER);

        // Painel para os botões, agora centralizado
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints buttonGbc = new GridBagConstraints();
        buttonGbc.insets = new Insets(10, 10, 10, 10);
        buttonGbc.gridx = 0;
        buttonGbc.gridy = 0;
        
        // Botão "Voltar"
        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(e -> mainFrame.showScreen("FirstScreen"));
        buttonPanel.add(backButton, buttonGbc);

        // Botão "Login"
        buttonGbc.gridx = 1;
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            String senha = new String(passwordField.getPassword()); 

            if (email.isEmpty() || senha.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                // Verificação se é Profissional ou Contratante
                /*if (LoginService.isProfissional(email, senha)) {
                    // Se for Profissional
                    Profissional profissional = LoginService.getProfissionalByEmail(email);
                    ProfessionalSession.setProfissionalLogado(profissional); // Setando a sessão do Profissional
                    JOptionPane.showMessageDialog(this, "Login Profissional realizado com sucesso!");
                    mainFrame.showScreen("InsideScreen"); // Navega para a tela do perfil do usuário

                } else if (LoginService.isContratante(email, senha)) {
                    // Se for Contratante
                    Contratante contratante = LoginService.getContratanteByEmail(email);
                    ContractorSession.setContratanteLogado(contratante); // Setando a sessão do Contratante
                    JOptionPane.showMessageDialog(this, "Login Contratante realizado com sucesso!");
                    mainFrame.showScreen("InsideScreen"); // Navega para a tela do perfil do usuário

                } else {
                    JOptionPane.showMessageDialog(this, "Credenciais inválidas", "Erro", JOptionPane.ERROR_MESSAGE);
                }*/
            }
        });
        buttonPanel.add(loginButton, buttonGbc);

        add(buttonPanel, BorderLayout.SOUTH); // Adiciona os botões na parte inferior
    }
}
