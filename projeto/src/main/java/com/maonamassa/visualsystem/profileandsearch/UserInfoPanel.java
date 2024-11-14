package com.maonamassa.visualsystem.profileandsearch;

import javax.swing.*;

import com.maonamassa.usersystem.Contratante;
import com.maonamassa.usersystem.Disponibilidade;
import com.maonamassa.usersystem.Profissional;
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
    private JTextField descricaoField;
    private JTextField buscandoField;
    private JComboBox<Disponibilidade> disponibilidadeComboBox;

    public UserInfoPanel(MainFrame mainFrame, Sessao sessao) {

        boolean isProfessional = sessao.getIsProfissional();

        System.out.println("isProfessional: " + isProfessional);

        setLayout(null);

        int yPos = 50;

        ImageCirclePanel imageCirclePanel = new ImageCirclePanel();
        imageCirclePanel.setBounds(110, yPos, 150, 150);
        add(imageCirclePanel);

        yPos += 180;
        // Campos de informação
        nomeField = addField("Nome:", sessao.getNome(), yPos);
        nomeField.setEditable(false);
        yPos += 40;
        cpfCnpjField = addField("CPF/CNPJ:", sessao.getCpfCnpj(), yPos);
        cpfCnpjField.setEditable(false);
        yPos += 40;
        emailField = addField("E-mail:", sessao.getEmail(), yPos);
        emailField.setEditable(false);
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
        else{
            descricaoField = addField("Descrição:", sessao.getDescricao(), yPos);
            yPos += 40;
            buscandoField = addField("Buscando:", sessao.getBuscando(), yPos);
            yPos += 40;
        }

        // Botao de atualizar informações
        JButton updateButton = new JButton("Atualizar Informações");
        updateButton.setBounds(100, yPos, 200, 30); // Posição e tamanho do botão

        updateButton.addActionListener(e -> {

            if (sessao == null) {
                JOptionPane.showMessageDialog(null, "Sessão não encontrada. Faça login.");
                return;
            }

            if (sessao.getIsProfissional() == false) {
                // Atualiza informações do contratante
                Contratante contratante = sessao.getContratanteLogado();
                contratante.setAddress(enderecoField.getText());
                contratante.setPhone(telefoneField.getText());
                contratante.setDescricao(descricaoField.getText());
                contratante.setBuscando(buscandoField.getText());
                if (contratante != null) {
                    Insercao.atualizarContratante(contratante);
                    JOptionPane.showMessageDialog(null, "Informações do contratante atualizadas com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Contratante não encontrado!");
                }
            } 
            else if (sessao.getIsProfissional() == true) {
                // Atualiza informações do profissional
                Profissional profissional = sessao.getProfissionalLogado();
                profissional.setAddress(enderecoField.getText());
                profissional.setPhone(telefoneField.getText());
                profissional.setProfissao(profissaoField.getText());
                profissional.setAreaAtuacao(areaAtuacaoField.getText());
                profissional.setDisponibilidade((Disponibilidade) disponibilidadeComboBox.getSelectedItem());
                if (profissional != null) {
                Insercao.atualizarProfissional(profissional);
                JOptionPane.showMessageDialog(null, "Informações do profissional atualizadas com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Profissional não encontrado!");
                }
            } 
            else {
                JOptionPane.showMessageDialog(null, "Usuário inválido.");
            }
        });

        // Adiciona o botão no painel ou tela
        add(updateButton);


        // Botão de logout
        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(125, yPos + 40, 150, 30);
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

}
