package com.maonamassa.visualsystem.insideframes.userside.newdialogs;

import javax.swing.*;
import com.maonamassa.banco_de_dados.Insercao;
import com.maonamassa.contractsystem.Contrato;
import com.maonamassa.contractsystem.LaTeXToPDFConverter;
import com.maonamassa.projectsystem.Projeto;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CartaoProjeto extends JPanel {

    private boolean editMode = false; // Para controlar se o cartão está em modo de edição ou não
    private Projeto projeto;

    private JTextField tfNomeProjeto;
    private JTextArea taDescricao;
    private JTextField tfValor;
    private JTextField tfDataInicio;
    private JTextField tfDataFim;

    private JButton btnGerarContrato;
    private JButton btnCancelarProjeto;
    private JButton btnAtualizarInformacoes;
    private JButton btnEntrar;

    private JLabel descricaoLabel, valorLabel, contratanteLabel, profissionalLabel, dataInicioLabel, dataFimLabel,
            nomeProjetoLabel;

    // Formatter para o padrão brasileiro de datas
    private static final DateTimeFormatter BR_DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public CartaoProjeto(Projeto projeto) {
        if (projeto == null) {
            throw new IllegalArgumentException("Projeto não pode ser nulo.");
        }
        this.projeto = projeto;
        setLayout(null);
        setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Borda para o cartão
        setPreferredSize(new Dimension(500, 700));
        setBackground(Color.LIGHT_GRAY); // Cor de fundo para o cartão

        // Nome do Projeto: label
        nomeProjetoLabel = new JLabel("Nome do Projeto: " + projeto.getNomeProjeto());
        nomeProjetoLabel.setFont(new Font("Arial", Font.BOLD, 12));
        nomeProjetoLabel.setBounds(10, 10, 400, 20);
        add(nomeProjetoLabel);

        // Campo para edição do nome do projeto
        tfNomeProjeto = new JTextField(projeto.getNomeProjeto());
        tfNomeProjeto.setFont(new Font("Arial", Font.PLAIN, 12));
        tfNomeProjeto.setBounds(10, 30, 400, 30);
        tfNomeProjeto.setVisible(false); // Apenas no modo de edição
        add(tfNomeProjeto);

        // Nome do contratante
        contratanteLabel = new JLabel("Contratante: " + projeto.getContratante().getName());
        contratanteLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        contratanteLabel.setBounds(10, 70, 400, 20);
        add(contratanteLabel);

        // Nome do profissional
        profissionalLabel = new JLabel("Profissional: " + projeto.getProfissional().getName());
        profissionalLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        profissionalLabel.setBounds(10, 100, 400, 20);
        add(profissionalLabel);

        // Data de Início
        dataInicioLabel = new JLabel("Data de Início: " + formatDate(projeto.getDataInicio()));
        dataInicioLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        dataInicioLabel.setBounds(10, 130, 400, 20);
        add(dataInicioLabel);

        tfDataInicio = new JTextField(
                projeto.getDataInicio() != null ? projeto.getDataInicio().format(BR_DATE_FORMAT) : "dd/mm/yyyy");
        tfDataInicio.setFont(new Font("Arial", Font.PLAIN, 12));
        tfDataInicio.setForeground(Color.GRAY);
        tfDataInicio.setBounds(10, 150, 180, 30);
        tfDataInicio.setVisible(false); // Apenas no modo de edição
        tfDataInicio.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (tfDataInicio.getText().equals("dd/mm/yyyy")) {
                    tfDataInicio.setText("");
                    tfDataInicio.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (tfDataInicio.getText().isEmpty()) {
                    tfDataInicio.setText("dd/mm/yyyy");
                    tfDataInicio.setForeground(Color.GRAY);
                }
            }
        });
        add(tfDataInicio);

        // Data de Fim
        dataFimLabel = new JLabel("Data de Fim: " + formatDate(projeto.getDataFim()));
        dataFimLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        dataFimLabel.setBounds(10, 190, 400, 20);
        add(dataFimLabel);

        tfDataFim = new JTextField(
                projeto.getDataFim() != null ? projeto.getDataFim().format(BR_DATE_FORMAT) : "dd/mm/yyyy");
        tfDataFim.setFont(new Font("Arial", Font.PLAIN, 12));
        tfDataFim.setForeground(Color.GRAY);
        tfDataFim.setBounds(10, 210, 180, 30);
        tfDataFim.setVisible(false); // Apenas no modo de edição
        tfDataFim.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (tfDataFim.getText().equals("dd/mm/yyyy")) {
                    tfDataFim.setText("");
                    tfDataFim.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (tfDataFim.getText().isEmpty()) {
                    tfDataFim.setText("dd/mm/yyyy");
                    tfDataFim.setForeground(Color.GRAY);
                }
            }
        });
        add(tfDataFim);

        // Descrição
        descricaoLabel = new JLabel("Descrição:");
        descricaoLabel.setFont(new Font("Arial", Font.BOLD, 12));
        descricaoLabel.setBounds(10, 250, 200, 20);
        add(descricaoLabel);

        taDescricao = new JTextArea(projeto.getDescricaoProjeto());
        taDescricao.setFont(new Font("Arial", Font.PLAIN, 12));
        taDescricao.setEditable(false); // Não editável inicialmente
        taDescricao.setBackground(getBackground()); // Remover a caixa branca
        taDescricao.setBounds(10, 270, 400, 100);
        taDescricao.setWrapStyleWord(true);
        taDescricao.setLineWrap(true);
        taDescricao.setForeground(Color.BLACK);
        add(taDescricao);

        // Valor
        valorLabel = new JLabel("Valor:");
        valorLabel.setFont(new Font("Arial", Font.BOLD, 12));
        valorLabel.setBounds(10, 380, 400, 20);
        add(valorLabel);

        tfValor = new JTextField(projeto.getValorCombinado());
        tfValor.setFont(new Font("Arial", Font.PLAIN, 12));
        tfValor.setForeground(Color.GRAY);
        tfValor.setBounds(10, 400, 180, 30);
        tfValor.setEditable(false); // Não editável inicialmente
        tfValor.setBackground(getBackground()); // Remover a caixa branca
        add(tfValor);

        // Botão Cancelar
        btnCancelarProjeto = new JButton("Cancelar");
        btnCancelarProjeto.setBounds(200, 500, 180, 30);
        btnCancelarProjeto.addActionListener((ActionEvent e) -> cancelarProjeto());
        add(btnCancelarProjeto);

        // Botão Entrar
        btnEntrar = new JButton("Entrar");
        btnEntrar.setBounds(10, 450, 180, 30);
        btnEntrar.addActionListener((ActionEvent e) -> toggleEditMode());
        add(btnEntrar);

        // Botão Atualizar Informações
        btnAtualizarInformacoes = new JButton("Atualizar Informações");
        btnAtualizarInformacoes.setBounds(200, 450, 180, 30);
        btnAtualizarInformacoes.setVisible(false); // Inicialmente invisível
        btnAtualizarInformacoes.addActionListener((ActionEvent e) -> atualizarInformacoes());
        add(btnAtualizarInformacoes);

        // Botão Gerar Contrato
        btnGerarContrato = new JButton("Gerar Contrato");
        btnGerarContrato.setBounds(10, 500, 180, 30);
        btnGerarContrato.addActionListener((ActionEvent e) -> {
            if (validarCamposPreenchidos()) {
                Contrato contrato = new Contrato(projeto);
                Insercao.criarContrato(contrato);
                LaTeXToPDFConverter converter = new LaTeXToPDFConverter();
                converter.convertToPDF(contrato);
                JOptionPane.showMessageDialog(this, "Contrato gerado com sucesso!");
                bloquearEdicao();
            } else {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos antes de gerar o contrato.");
            }
        });
        add(btnGerarContrato);
    }

    private void toggleEditMode() {
        editMode = !editMode;

        tfNomeProjeto.setVisible(editMode);
        tfDataInicio.setVisible(editMode);
        tfDataFim.setVisible(editMode);
        taDescricao.setEditable(editMode);
        tfValor.setEditable(editMode);

        // Agora a caixa de texto de descrição e valor aparecem quando Entrar for clicado
        taDescricao.setBackground(Color.WHITE);
        tfValor.setBackground(Color.WHITE);

        btnEntrar.setVisible(!editMode);
        btnAtualizarInformacoes.setVisible(editMode);
    }

    private void atualizarInformacoes() {
        try {
            // Atualizar o nome do projeto e a descrição normalmente
            projeto.setNomeProjeto(tfNomeProjeto.getText());
            projeto.setDescricaoProjeto(taDescricao.getText());

            // Atualizar o valor do projeto, se o campo não estiver vazio
            if (!tfValor.getText().isEmpty()) {
                projeto.setValorCombinado(tfValor.getText());
            }

            // Atualizar a data de início apenas se o campo tiver sido modificado
            if (!tfDataInicio.getText().equals("dd/mm/yyyy") && !tfDataInicio.getText().isEmpty()) {
                projeto.setDataInicio(LocalDate.parse(tfDataInicio.getText(), BR_DATE_FORMAT));
            }

            // Atualizar a data de fim apenas se o campo tiver sido modificado
            if (!tfDataFim.getText().equals("dd/mm/yyyy") && !tfDataFim.getText().isEmpty()) {
                projeto.setDataFim(LocalDate.parse(tfDataFim.getText(), BR_DATE_FORMAT));
            }

            // Atualizar o projeto no banco de dados
            Insercao.atualizarProjeto(projeto);
            JOptionPane.showMessageDialog(this, "Informações atualizadas com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar as informações: " + ex.getMessage());
        }
    }

    private boolean validarCamposPreenchidos() {
        return !tfNomeProjeto.getText().isEmpty() && !taDescricao.getText().isEmpty() && !tfValor.getText().isEmpty()
                && !tfDataInicio.getText().equals("dd/mm/yyyy") && !tfDataFim.getText().equals("dd/mm/yyyy");
    }

    private void bloquearEdicao() {
        tfNomeProjeto.setEditable(false);
        taDescricao.setEditable(false);
        tfValor.setEditable(false);
        tfDataInicio.setEditable(false);
        tfDataFim.setEditable(false);
    }

    private void cancelarProjeto() {
        Insercao.cancelarProjeto(projeto);
        JOptionPane.showMessageDialog(this, "Projeto cancelado.");
    }

    private String formatDate(LocalDate date) {
        return date != null ? date.format(BR_DATE_FORMAT) : "N/A";
    }
}