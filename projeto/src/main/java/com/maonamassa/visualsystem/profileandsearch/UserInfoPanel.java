package com.maonamassa.visualsystem.profileandsearch;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.maonamassa.visualsystem.firstinteraction.MainFrame;

public class UserInfoPanel extends JPanel {

    // Variável para largura dos campos
    private static final int FIELD_WIDTH = 25; // Defina o valor desejado para a largura

    public UserInfoPanel(MainFrame mainFrame, boolean isProfessional) {
        setLayout(new BorderLayout());

        // Painel de informações alinhado à esquerda
        JPanel infoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.LINE_START; // Alinha as informações à esquerda

        // Adiciona informações com base no tipo de usuário
        addField("Nome:", "João Silva", gbc, infoPanel, 0);
        addField("CPF/CNPJ:", "123.456.789-00", gbc, infoPanel, 1);
        addField("E-mail:", "joao@exemplo.com", gbc, infoPanel, 2);
        addField("Telefone:", "1234-5678", gbc, infoPanel, 3);
        addField("Endereço:", "Rua Exemplo, 123", gbc, infoPanel, 4);

        if (isProfessional) {
            addField("Profissão:", "Desenvolvedor de Software", gbc, infoPanel, 5);
            addField("Área de Atuação:", "Desenvolvimento Web", gbc, infoPanel, 6);
            addComboBoxField("Disponibilidade:", 
                            new String[]{"Disponível - Integral",
                                         "Disponível - Diurno (8h às 12h)", 
                                         "Disponível - Vespertino (13h às 17h)",
                                         "Disponível - Noturno (18h às 22h)",
                                         "Em Projeto",
                                         "Indisponível"}, 
                                         gbc, infoPanel, 7);
        } else {
            addField("Descrição:", "Buscando por um pedreiro experiente", gbc, infoPanel, 5);
        }

        // Botão de logout
        JPanel buttonPanel = new JPanel();
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> mainFrame.showScreen("LoginScreen"));
        buttonPanel.add(logoutButton);
        
        gbc.gridx = 0;
        gbc.gridy = (isProfessional ? 8 : 6);
        gbc.gridwidth = 2;
        infoPanel.add(buttonPanel, gbc);

        add(infoPanel, BorderLayout.WEST); // Posiciona o painel de informações no canto esquerdo do painel direito
    }

    private void addField(String label, String value, GridBagConstraints gbc, JPanel panel, int yPos) {
        gbc.gridx = 0;
        gbc.gridy = yPos;
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        JTextField textField = new JTextField(value);
        textField.setColumns(FIELD_WIDTH); // Define a largura
        panel.add(textField, gbc);

        // Função comentada para integração com o banco de dados
        // textField.addFocusListener(new FocusAdapter() {
        //     @Override
        //     public void focusLost(FocusEvent e) {
        //         String newValue = textField.getText();
        //         if (isProfessional) {
        //             // DatabaseUtils.updateProfessionalField(label, newValue);
        //         } else {
        //             // DatabaseUtils.updateContractorField(label, newValue);
        //         }
        //     }
        // });
    }

    private void addComboBoxField(String label, String[] options, GridBagConstraints gbc, JPanel panel, int yPos) {
        gbc.gridx = 0;
        gbc.gridy = yPos;
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        JComboBox<String> comboBox = new JComboBox<>(options);
        comboBox.setPreferredSize(new java.awt.Dimension(FIELD_WIDTH * 10, comboBox.getPreferredSize().height)); // Ajusta a largura da ComboBox
        panel.add(comboBox, gbc);

        // Função comentada para integração com o banco de dados
        // comboBox.addActionListener(e -> {
        //     String selectedValue = (String) comboBox.getSelectedItem();
        //     if (isProfessional) {
        //         // DatabaseUtils.updateProfessionalField(label, selectedValue);
        //     } else {
        //         // DatabaseUtils.updateContractorField(label, selectedValue);
        //     }
        // });
    }
}
