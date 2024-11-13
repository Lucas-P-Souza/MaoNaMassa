package com.maonamassa.visualsystem.profileandsearch;

import javax.swing.*;
import com.maonamassa.usersystem.Sessao;
import com.maonamassa.visualsystem.firstinteraction.MainFrame;

public class UserInfoPanel extends JPanel {

    private static final int FIELD_WIDTH = 20; // Largura dos campos

    public UserInfoPanel(MainFrame mainFrame, boolean isProfessional, Sessao sessao) {
        setLayout(null); // Usando layout absoluto

        int yPos = 50; // Posição inicial vertical para o círculo

        // Adiciona o círculo interativo para upload de imagem
        ImageCirclePanel imageCirclePanel = new ImageCirclePanel();
        imageCirclePanel.setBounds(110, yPos, 150, 150); // Tamanho e posição do círculo
        add(imageCirclePanel);

        // Ajusta a posição inicial dos campos de texto após o círculo
        yPos += 180;

        // Campos de informação usando dados da sessão
        yPos = addField("Nome:", sessao.getNome(), yPos);
        yPos = addField("CPF/CNPJ:", sessao.getCpfCnpj(), yPos);
        yPos = addField("E-mail:", sessao.getEmail(), yPos);
        yPos = addField("Telefone:", sessao.getTelefone(), yPos);
        yPos = addField("Endereço:", sessao.getEndereco(), yPos);

        if (isProfessional) {
            yPos = addField("Profissão:", sessao.getProfissao(), yPos);
            yPos = addField("Área de Atuação:", sessao.getAreaAtuacao(), yPos);
            yPos = addComboBoxField("Disponibilidade:",
                    new String[]{
                            "Disponível - Integral",
                            "Disponível - Diurno (8h às 12h)",
                            "Disponível - Vespertino (13h às 17h)",
                            "Disponível - Noturno (18h às 22h)",
                            "Em Projeto",
                            "Indisponível"
                    },
                    yPos);
        } else {
            yPos = addField("Descrição:", sessao.getDescricao(), yPos);
            yPos = addField("Buscando:", sessao.getBuscando(), yPos);
        }

        // Botão de logout
        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(150, yPos + 20, 150, 30);
        logoutButton.addActionListener(e -> mainFrame.showScreen("LoginScreen"));
        add(logoutButton);
    }

    private int addField(String label, String value, int yPos) {
        JLabel jLabel = new JLabel(label);
        jLabel.setBounds(20, yPos, 150, 30);
        add(jLabel);

        JTextField textField = new JTextField(value);
        textField.setBounds(180, yPos, FIELD_WIDTH * 10, 30);
        add(textField);

        return yPos + 40; // Atualiza a posição vertical para o próximo campo
    }

    private int addComboBoxField(String label, String[] options, int yPos) {
        JLabel jLabel = new JLabel(label);
        jLabel.setBounds(20, yPos, 150, 30);
        add(jLabel);

        JComboBox<String> comboBox = new JComboBox<>(options);
        comboBox.setBounds(180, yPos, FIELD_WIDTH * 10, 30);
        add(comboBox);

        return yPos + 40; // Atualiza a posição vertical para o próximo campo
    }
}
