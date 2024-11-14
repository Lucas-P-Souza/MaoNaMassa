package com.maonamassa.visualsystem.profileandsearch;

import javax.swing.*;
import com.maonamassa.usersystem.Disponibilidade;
import com.maonamassa.usersystem.Sessao;
import com.maonamassa.visualsystem.firstinteraction.MainFrame;
import com.maonamassa.banco_de_dados.Insercao;

public class UserInfoPanel extends JPanel {

    private static final int FIELD_WIDTH = 20;

    private JTextField nomeField;
    private JTextField cpfCnpjField;
    private JTextField emailField;
    private JTextField telefoneField;
    private JTextField enderecoField;
    private JTextField profissaoField;
    private JTextField areaAtuacaoField;
    private JComboBox<Disponibilidade> disponibilidadeComboBox;

    public UserInfoPanel(MainFrame mainFrame, Sessao sessao) {

        System.out.println("UserInfoPanel");

        boolean isProfessional = sessao.getIsProfissional();

        setLayout(null);

        int yPos = 50;

        ImageCirclePanel imageCirclePanel = new ImageCirclePanel();
        imageCirclePanel.setBounds(110, yPos, 150, 150);
        add(imageCirclePanel);

        yPos += 180;

        // Campos de informação
        nomeField = addField("Nome:", sessao.getNome(), yPos);
        yPos += 40;
        cpfCnpjField = addField("CPF/CNPJ:", sessao.getCpfCnpj(), yPos);
        yPos += 40;
        emailField = addField("E-mail:", sessao.getEmail(), yPos);
        yPos += 40;
        telefoneField = addField("Telefone:", sessao.getTelefone(), yPos);
        yPos += 40;
        enderecoField = addField("Endereço:", sessao.getEndereco(), yPos);
        yPos += 40;

        if (isProfessional) {
            profissaoField = addField("Profissão:", sessao.getProfissao(), yPos);
            yPos += 40;
            areaAtuacaoField = addField("Área de Atuação:", sessao.getAreaAtuacao(), yPos);
            yPos += 40;
            disponibilidadeComboBox = addComboBoxField("Disponibilidade:", Disponibilidade.values(), yPos, sessao);
            yPos += 40;
        }

        // Botão de atualizar
        JButton updateButton = new JButton("Atualizar");
        updateButton.setBounds(150, yPos, 150, 30);
        updateButton.addActionListener(e -> atualizarDados(sessao, isProfessional));
        add(updateButton);

        // Botão de logout
        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(150, yPos + 40, 150, 30);
        logoutButton.addActionListener(e -> mainFrame.showScreen("LoginScreen"));
        add(logoutButton);

        setVisible(true);

    }

    private JTextField addField(String label, String value, int yPos) {
        JLabel jLabel = new JLabel(label);
        jLabel.setBounds(20, yPos, 150, 30);
        add(jLabel);

        JTextField textField = new JTextField(value);
        textField.setBounds(180, yPos, FIELD_WIDTH * 10, 30);
        add(textField);

        return textField;
    }

    private JComboBox<Disponibilidade> addComboBoxField(String label, Disponibilidade[] options, int yPos, Sessao sessao) {
        JLabel jLabel = new JLabel(label);
        jLabel.setBounds(20, yPos, 150, 30);
        add(jLabel);

        JComboBox<Disponibilidade> comboBox = new JComboBox<>(options);
        comboBox.setBounds(180, yPos, FIELD_WIDTH * 10, 30);
        comboBox.setSelectedItem(sessao.getDisponibilidade());
        add(comboBox);

        return comboBox;
    }

    private void atualizarDados(Sessao sessao, boolean isProfessional) {
        // Atualiza os campos comuns
        sessao.setNome(nomeField.getText());
        sessao.setCpfCnpj(cpfCnpjField.getText());
        sessao.setEmail(emailField.getText());
        sessao.setTelefone(telefoneField.getText());
        sessao.setEndereco(enderecoField.getText());

        if (isProfessional) {
            // Atualiza campos específicos para profissionais
            sessao.setProfissao(profissaoField.getText());
            sessao.setAreaAtuacao(areaAtuacaoField.getText());
            sessao.setDisponibilidade((Disponibilidade) disponibilidadeComboBox.getSelectedItem());
        }

        // Salva as alterações no banco de dados
        try {
            Insercao.atualizarDados(sessao, isProfessional);  // Método que salva as alterações no banco
            JOptionPane.showMessageDialog(this, "Dados atualizados com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar os dados: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
