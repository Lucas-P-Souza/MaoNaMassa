package com.maonamassa.visualsystem.insideframes.userside.newdialogs;

import javax.swing.*;
import com.maonamassa.projectsystem.Projeto;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class CartaoProjeto extends JPanel {

    private boolean editMode = false; // Para controlar se o cartão está em modo de edição ou não
    private Projeto projeto;

    private JTextField tfNomeProjeto;
    private JTextArea taDescricao;
    private JTextField tfValor;
    private JButton btnGerarContrato;
    private JButton btnCancelarProjeto;
    private JButton btnAtualizarInformacoes;
    private JButton btnEntrar;

    private JLabel descricaoLabel, valorLabel, contratanteLabel, profissionalLabel;

    public CartaoProjeto(Projeto projeto) {
        if (projeto == null) {
            throw new IllegalArgumentException("Projeto não pode ser nulo.");
        }
        this.projeto = projeto;
        setLayout(null);
        setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Borda para o cartão
        setPreferredSize(new Dimension(500, 600));
        setBackground(Color.LIGHT_GRAY); // Cor de fundo para o cartão

        // Nome do Projeto: label
        JLabel nomeProjetoLabel = new JLabel("Nome do Projeto:");
        nomeProjetoLabel.setFont(new Font("Arial", Font.BOLD, 12));
        nomeProjetoLabel.setBounds(10, 10, 200, 20);
        add(nomeProjetoLabel);

        // Campo para digitar o nome do projeto
        tfNomeProjeto = new JTextField("Insira o nome do projeto");
        tfNomeProjeto.setBounds(10, 30, 400, 30);
        tfNomeProjeto.setForeground(Color.GRAY); // Cor do texto placeholder
        tfNomeProjeto.setEditable(false); // Inicialmente desabilitado
        tfNomeProjeto.setVisible(false); // Inicialmente oculto
        tfNomeProjeto.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (tfNomeProjeto.getText().equals("Insira o nome do projeto")) {
                    tfNomeProjeto.setText("");
                    tfNomeProjeto.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (tfNomeProjeto.getText().isEmpty()) {
                    tfNomeProjeto.setText("Insira o nome do projeto");
                    tfNomeProjeto.setForeground(Color.GRAY);
                }
            }
        });
        add(tfNomeProjeto);

        // Nome do contratante (não editável)
        contratanteLabel = new JLabel("Contratante: " + projeto.getContratante().getName());
        contratanteLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        contratanteLabel.setBounds(10, 70, 400, 20);
        contratanteLabel.setVisible(false); // Inicialmente oculto
        add(contratanteLabel);

        // Nome do profissional (não editável)
        profissionalLabel = new JLabel("Profissional: " + projeto.getProfissional().getName());
        profissionalLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        profissionalLabel.setBounds(10, 90, 400, 20);
        profissionalLabel.setVisible(false); // Inicialmente oculto
        add(profissionalLabel);

        // Descrição do projeto: label
        descricaoLabel = new JLabel("Descrição:");
        descricaoLabel.setFont(new Font("Arial", Font.BOLD, 12));
        descricaoLabel.setBounds(10, 120, 200, 20);
        descricaoLabel.setVisible(false); // Inicialmente oculto
        add(descricaoLabel);

        // Campo de descrição
        taDescricao = new JTextArea("Insira a descrição do projeto");
        taDescricao.setFont(new Font("Arial", Font.PLAIN, 12));
        taDescricao.setEditable(false);
        taDescricao.setWrapStyleWord(true);
        taDescricao.setLineWrap(true);
        taDescricao.setBounds(10, 140, 400, 100);
        taDescricao.setForeground(Color.GRAY);
        taDescricao.setVisible(false); // Inicialmente oculto
        taDescricao.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (taDescricao.getText().equals("Insira a descrição do projeto")) {
                    taDescricao.setText("");
                    taDescricao.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (taDescricao.getText().isEmpty()) {
                    taDescricao.setText("Insira a descrição do projeto");
                    taDescricao.setForeground(Color.GRAY);
                }
            }
        });
        add(taDescricao);

        // Campo de valor
        valorLabel = new JLabel("Valor:");
        valorLabel.setFont(new Font("Arial", Font.BOLD, 12));
        valorLabel.setBounds(10, 250, 400, 20);
        valorLabel.setVisible(false); // Inicialmente oculto
        add(valorLabel);

        tfValor = new JTextField("Insira o valor do projeto");
        tfValor.setFont(new Font("Arial", Font.PLAIN, 12));
        tfValor.setForeground(Color.GRAY); // Placeholder
        tfValor.setBounds(10, 270, 180, 30);
        tfValor.setEditable(false); // Inicialmente desabilitado
        tfValor.setVisible(false); // Inicialmente oculto
        tfValor.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (tfValor.getText().equals("Insira o valor do projeto")) {
                    tfValor.setText("");
                    tfValor.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (tfValor.getText().isEmpty()) {
                    tfValor.setText("Insira o valor do projeto");
                    tfValor.setForeground(Color.GRAY);
                }
            }
        });
        add(tfValor);

        // Botão Entrar
        btnEntrar = new JButton("Entrar");
        btnEntrar.setBounds(10, 310, 180, 30);
        btnEntrar.addActionListener((ActionEvent e) -> {
            toggleEditMode();
        });
        add(btnEntrar);

        // Botão Atualizar Informações
        btnAtualizarInformacoes = new JButton("Atualizar Informações");
        btnAtualizarInformacoes.setBounds(200, 270, 180, 30);
        btnAtualizarInformacoes.setVisible(false); // Inicialmente oculto
        btnAtualizarInformacoes.addActionListener((ActionEvent e) -> {
            JOptionPane.showMessageDialog(this, "Informações atualizadas com sucesso!");
        });
        add(btnAtualizarInformacoes);

        // Botão Gerar Contrato
        btnGerarContrato = new JButton("Iniciar Projeto / Gerar Contrato");
        btnGerarContrato.setBounds(10, 310, 180, 30);
        btnGerarContrato.setVisible(false); // Inicialmente oculto
        add(btnGerarContrato);

        // Botão Cancelar Projeto
        btnCancelarProjeto = new JButton("Cancelar Projeto");
        btnCancelarProjeto.setBounds(200, 310, 180, 30);
        btnCancelarProjeto.setVisible(false); // Inicialmente oculto
        add(btnCancelarProjeto);
    }

    private void toggleEditMode() {
        editMode = !editMode;

        // Alterna a visibilidade e habilitação dos campos
        tfNomeProjeto.setEditable(editMode);
        tfNomeProjeto.setVisible(editMode);

        contratanteLabel.setVisible(editMode);
        profissionalLabel.setVisible(editMode);

        descricaoLabel.setVisible(editMode);
        taDescricao.setEditable(editMode);
        taDescricao.setVisible(editMode);

        valorLabel.setVisible(editMode);
        tfValor.setEditable(editMode);
        tfValor.setVisible(editMode);

        btnAtualizarInformacoes.setVisible(editMode);
        btnGerarContrato.setVisible(editMode);
        btnCancelarProjeto.setVisible(editMode);

        btnEntrar.setVisible(!editMode); // Botão "Entrar" desaparece no modo de edição
    }
}
