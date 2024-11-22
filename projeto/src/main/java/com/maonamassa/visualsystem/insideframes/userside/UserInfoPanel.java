package com.maonamassa.visualsystem.insideframes.userside;

import javax.swing.*;
import com.maonamassa.usersystem.Contratante;
import com.maonamassa.usersystem.Disponibilidade;
import com.maonamassa.usersystem.Profissional;
import com.maonamassa.usersystem.Sessao;
import com.maonamassa.visualsystem.firstinteraction.MainFrame;
import com.maonamassa.banco_de_dados.Insercao;
import java.awt.*;

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
        setLayout(new BorderLayout()); // Define layout principal

        // Painel central com margem e layout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Margens externas

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaçamento entre os componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        boolean isProfessional = sessao.getIsProfissional();

        // Imagem do perfil
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        ImageCirclePanel imageCirclePanel = new ImageCirclePanel();
        mainPanel.add(imageCirclePanel, gbc);

        gbc.gridwidth = 1; // Reseta o gridwidth para campos subsequentes
        gbc.anchor = GridBagConstraints.WEST;

        // Campos de informação
        gbc.gridy++;
        nomeField = addField("Nome:", sessao.getNome(), mainPanel, gbc, false);

        gbc.gridy++;
        cpfCnpjField = addField("CPF/CNPJ:", sessao.getCpfCnpj(), mainPanel, gbc, false);

        gbc.gridy++;
        emailField = addField("E-mail:", sessao.getEmail(), mainPanel, gbc, false);

        gbc.gridy++;
        telefoneField = addField("Telefone:", sessao.getTelefone(), mainPanel, gbc, true);

        gbc.gridy++;
        enderecoField = addField("Endereço:", sessao.getEndereco(), mainPanel, gbc, true);

        if (isProfessional) {
            gbc.gridy++;
            profissaoField = addField("Profissão:", sessao.getProfissao(), mainPanel, gbc, true);

            gbc.gridy++;
            areaAtuacaoField = addField("Área de Atuação:", sessao.getAreaAtuacao(), mainPanel, gbc, true);

            gbc.gridy++;
            disponibilidadeComboBox = addComboBoxField("Disponibilidade:", Disponibilidade.values(), mainPanel, gbc,
                    sessao);
        } else {
            gbc.gridy++;
            descricaoField = addField("Descrição:", sessao.getDescricao(), mainPanel, gbc, true);

            gbc.gridy++;
            buscandoField = addField("Buscando:", sessao.getBuscando(), mainPanel, gbc, true);
        }

        // Botão de atualizar informações
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton updateButton = new JButton("Atualizar Informações");
        updateButton.addActionListener(e -> atualizarInformacoes(sessao));
        mainPanel.add(updateButton, gbc);

        // Painel inferior com botões do usuário
        UserButtons userButtons = new UserButtons(mainFrame);

        // Adiciona componentes ao painel principal
        add(mainPanel, BorderLayout.CENTER);
        add(userButtons, BorderLayout.SOUTH);
    }

    private JTextField addField(String label, String value, JPanel panel, GridBagConstraints gbc, boolean editable) {
        JLabel jLabel = new JLabel(label);
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        panel.add(jLabel, gbc);

        JTextField textField = new JTextField(value);
        textField.setColumns(FIELD_WIDTH);
        textField.setEditable(editable);
        gbc.gridx = 1;
        panel.add(textField, gbc);

        return textField;
    }

    private JComboBox<Disponibilidade> addComboBoxField(String label, Disponibilidade[] options, JPanel panel,
            GridBagConstraints gbc, Sessao sessao) {
        JLabel jLabel = new JLabel(label);
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        panel.add(jLabel, gbc);

        JComboBox<Disponibilidade> comboBox = new JComboBox<>(options);
        comboBox.setSelectedItem(sessao.getDisponibilidade());
        gbc.gridx = 1;
        panel.add(comboBox, gbc);

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
        } else {
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
