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

import com.maonamassa.banco_de_dados.Consultas;
import com.maonamassa.banco_de_dados.LoginManager;
import com.maonamassa.usersystem.Contratante;
import com.maonamassa.usersystem.Profissional;
import com.maonamassa.usersystem.Sessao;

public class LoginScreen extends JPanel {

    private static Sessao sessao = new Sessao();
    private LoginManager loginManager = new LoginManager(); // Instanciando o LoginManager

    public static Sessao getSessao() {
        return sessao;
    }

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

        // Painel para os botões
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
            boolean rememberMe = rememberMeCheckBox.isSelected();

            if (email.isEmpty() || senha.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos", "Erro", JOptionPane.ERROR_MESSAGE);
            } 
            else {
                // Verifique se o login é válido
                boolean loginValido = Consultas.validarLogin(email, senha);
                if (!loginValido) {
                    JOptionPane.showMessageDialog(this, "Usuário ou senha inválidos", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Salvar preferências se "Lembrar de mim" estiver marcado
                if (rememberMe) {
                    loginManager.savePreferences(email, senha, rememberMe);
                } else {
                    loginManager.clearPreferences();
                }

                // Verificar tipo de usuário e fazer o login
                boolean isProfissional = Consultas.isProfessional(email);
                if (isProfissional) {
                    Profissional profissional = Consultas.consultarProfissional(email);
                    sessao.logarProfissional(profissional);
                    sessao.setIsProfissional(true);
                } 
                else {
                    Contratante contratante = Consultas.consultarContratante(email);
                    sessao.logarContratante(contratante);
                    sessao.setIsContratante(true);
                }

                System.out.println("Sessão do Profissional: " + sessao.getIsProfissional());
                System.out.println("Sessão do Contratante: " + sessao.getIsContratante());

                System.out.println("Login efetuado com sucesso");
                // Mudar para a próxima tela
                mainFrame.showScreen("InsideScreen");
                System.out.println("Login efetuado com sucesso 2");
            }
        });
        
        System.out.println("teste antes login button");

        buttonPanel.add(loginButton, buttonGbc);
        add(buttonPanel, BorderLayout.SOUTH); // Adiciona os botões na parte inferior

        // Carregar as preferências salvas (se existirem)
        String[] savedPrefs = loginManager.loadPreferences();
        String savedEmail = savedPrefs[0];
        String savedPassword = savedPrefs[1];
        boolean rememberMeChecked = Boolean.parseBoolean(savedPrefs[2]);

        emailField.setText(savedEmail);
        passwordField.setText(savedPassword);
        rememberMeCheckBox.setSelected(rememberMeChecked);
    }
}

