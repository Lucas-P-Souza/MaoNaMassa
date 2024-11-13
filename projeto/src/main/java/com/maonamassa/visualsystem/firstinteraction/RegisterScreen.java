package com.maonamassa.visualsystem.firstinteraction;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.maonamassa.banco_de_dados.Insercao;

public class RegisterScreen extends JPanel {

    public RegisterScreen(MainFrame mainFrame) {
        setLayout(new BorderLayout());

        // Título da tela de cadastro
        JLabel label = new JLabel("Tela de Cadastro", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        add(label, BorderLayout.NORTH);

        // Painel para os campos de entrada com GridBagLayout para melhor controle
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaçamento entre os componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Adicionando a ComboBox acima do campo de nome
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Faz a ComboBox ocupar a largura de 2 colunas
        gbc.anchor = GridBagConstraints.LINE_START;

        // Criando a ComboBox com algumas opções (exemplo)
        String[] options = {"Selecione como você deseja se cadastrar", "Contratante", "Profissional"};
        JComboBox<String> comboBox = new JComboBox<>(options);
        formPanel.add(comboBox, gbc);

        // Campo para o nome
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1; // A ComboBox ocupava 2 colunas, agora a largura é 1
        gbc.anchor = GridBagConstraints.LINE_END;
        formPanel.add(new JLabel("Nome:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        JTextField nameField = new JTextField(20);
        formPanel.add(nameField, gbc);

        // Campo para CPF/CNPJ
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1; // Largura de 1 coluna
        gbc.anchor = GridBagConstraints.LINE_END;
        formPanel.add(new JLabel("CPF / CNPJ:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        JTextField cpfCnpjField = new JTextField(20);
        formPanel.add(cpfCnpjField, gbc);

        // Campo para o e-mail
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_END;
        formPanel.add(new JLabel("E-mail:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_START;
        JTextField emailField = new JTextField(20);
        formPanel.add(emailField, gbc);

        // Campo para a senha
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LINE_END;
        formPanel.add(new JLabel("Senha:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LINE_START;
        JPasswordField passwordField = new JPasswordField(20);
        formPanel.add(passwordField, gbc);

        // Campo para confirmação de senha
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.LINE_END;
        formPanel.add(new JLabel("Confirmar Senha:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.LINE_START;
        JPasswordField confirmPasswordField = new JPasswordField(20);
        formPanel.add(confirmPasswordField, gbc);

        add(formPanel, BorderLayout.CENTER);

        // Painel para os botões
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(e -> mainFrame.showScreen("FirstScreen"));
        buttonPanel.add(backButton);

        JButton registerButton = new JButton("Cadastrar-se");
        registerButton.addActionListener(e -> {
            
            if (nameField.getText().isEmpty() ||
            cpfCnpjField.getText().isEmpty() ||
            emailField.getText().isEmpty() ||
            passwordField.getPassword().length == 0){

                JOptionPane.showMessageDialog(this, "Preencha todos os campos", "Erro", JOptionPane.ERROR_MESSAGE);
                return;

            }

            if (!Arrays.equals(passwordField.getPassword(), confirmPasswordField.getPassword())){

                JOptionPane.showMessageDialog(this, "As senhas não coincidem", "Erro", JOptionPane.ERROR_MESSAGE);
                return;

            }


            if (passwordField.getPassword().length < 6) {

                JOptionPane.showMessageDialog(this, "A senha deve ter no mínimo 6 caracteres", "Erro", JOptionPane.ERROR_MESSAGE);
                return;

            }
            else {

                String selectedOption = (String) comboBox.getSelectedItem();

                if (selectedOption.equals("Selecione como você deseja se cadastrar")) {

                    JOptionPane.showMessageDialog(this, "Selecione uma opção válida", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else if (selectedOption.equals("Contratante")) {

                    Insercao.cadastrarContratante(nameField.getText(), cpfCnpjField.getText(),  emailField.getText(), passwordField.getPassword().toString());
                } 
                else {
                    
                    Insercao.cadastrarProfissional(nameField.getText(), cpfCnpjField.getText(), emailField.getText(),passwordField.getPassword().toString());
                }
                JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!");
                mainFrame.showScreen("FirstScreen");
            }
        });
        buttonPanel.add(registerButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }
}
