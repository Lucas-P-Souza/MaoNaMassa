package com.maonamassa.visualsystem.insideframes.userside;

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
        setLayout(null);

        int yPos = 20;

        // Imagem do perfil
        ImageCirclePanel imageCirclePanel = new ImageCirclePanel();
        imageCirclePanel.setBounds(240, yPos, 150, 150);
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
        } else {
            descricaoField = addField("Descrição:", sessao.getDescricao(), yPos);
            yPos += 40;

            buscandoField = addField("Buscando:", sessao.getBuscando(), yPos);
            yPos += 40;
        }

        // Botão de atualizar informações
        JButton updateButton = new JButton("Atualizar Informações");
        updateButton.setBounds(185, yPos, 210, 30);
        updateButton.addActionListener(e -> atualizarInformacoes(sessao));
        add(updateButton);

        yPos += 50;

        // Painel de botões (UserButtons)
        UserButtons userButtons = new UserButtons(mainFrame);
        userButtons.setBounds(125, yPos, 600, 700); // Ajuste dinâmico
        add(userButtons);

        setVisible(true);
    }

    private JTextField addField(String label, String value, int yPos) {
        JLabel jLabel = new JLabel(label);
        jLabel.setBounds(110, yPos, 150, 30);
        add(jLabel);

        JTextField textField = new JTextField(value);
        textField.setBounds(270, yPos, FIELD_WIDTH * 10, 30);
        add(textField);

        return textField;
    }

    private JComboBox<Disponibilidade> addComboBoxField(String label, Disponibilidade[] options, int yPos, Sessao sessao) {
        JLabel jLabel = new JLabel(label);
        jLabel.setBounds(110, yPos, 150, 30);
        add(jLabel);

        JComboBox<Disponibilidade> comboBox = new JComboBox<>(options);
        comboBox.setBounds(270, yPos, FIELD_WIDTH * 10, 30);
        comboBox.setSelectedItem(sessao.getDisponibilidade());
        add(comboBox);

        return comboBox;
    }

    private void atualizarInformacoes(Sessao sessao) {
        if (sessao == null) {
            JOptionPane.showMessageDialog(null, "Sessão não encontrada. Faça login.");
            return;
        }

        if (!sessao.getIsProfissional()) {
            // Atualiza informações do contratante
            Contratante contratante = sessao.getContratanteLogado();
            contratante.setAddress(enderecoField.getText());
            contratante.setPhone(telefoneField.getText());
            contratante.setDescricao(descricaoField.getText());
            contratante.setBuscando(buscandoField.getText());

            Insercao.atualizarContratante(contratante);
            JOptionPane.showMessageDialog(null, "Informações do contratante atualizadas com sucesso!");
        }
        else {
            // Atualiza informações do profissional
            Profissional profissional = sessao.getProfissionalLogado();
            profissional.setAddress(enderecoField.getText());
            profissional.setPhone(telefoneField.getText());
            profissional.setProfissao(profissaoField.getText());
            profissional.setAreaAtuacao(areaAtuacaoField.getText());
            profissional.setDisponibilidade((Disponibilidade) disponibilidadeComboBox.getSelectedItem());

            Insercao.atualizarProfissional(profissional);
            JOptionPane.showMessageDialog(null, "Informações do profissional atualizadas com sucesso!");
        }
    }
}
