package com.maonamassa.visualsystem.profileandsearch;

import javax.swing.*;

import com.maonamassa.banco_de_dados.Consultas;
import com.maonamassa.usersystem.Contratante;
import com.maonamassa.usersystem.Profissional;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.List;

public class SearchPanel extends JPanel {

    private JTextField searchField;
    private JButton searchButton;
    private JLabel searchLabel;
    private JPanel resultPanel;  // Painel para exibir os cartões de resultados

    public SearchPanel(boolean isProfessional) {
        setLayout(null); // Usando layout absoluto

        // Label de busca
        searchLabel = new JLabel("Buscar por:");
        searchLabel.setBounds(30, 20, 100, 30); // Posição (x, y) e tamanho (largura, altura)

        // Campo de busca
        searchField = new JTextField(" pedreiro, encanador, eletricista...");
        searchField.setForeground(Color.GRAY);
        searchField.setBounds(120, 20, 550, 30); // Posição e tamanho

        // Placeholder que some ao clicar
        searchField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals(" pedreiro, encanador, eletricista...")) {
                    searchField.setText("");
                    searchField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText(" pedreiro, encanador, eletricista...");
                    searchField.setForeground(Color.GRAY);
                }
            }
        });

        // Botão de pesquisa
        searchButton = new JButton("Pesquisar");
        searchButton.setBounds(700, 20, 120, 30); // Posição e tamanho
        searchButton.addActionListener(e -> executeSearch(isProfessional));

        // Painel de resultados para exibir os "cartões"
        resultPanel = new JPanel();
        resultPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Layout para os cartões
        resultPanel.setBounds(20, 70, 800, 400); // Posição e tamanho do painel de resultados
        resultPanel.setBackground(new Color(240, 240, 240)); // Cor de fundo clara

        // Adiciona os componentes ao painel
        add(searchLabel);
        add(searchField);
        add(searchButton);
        add(resultPanel);
    }

    // Método de busca que exibe os resultados como "cartões"
    private void executeSearch(boolean isProfessional) {
        String searchTerm = searchField.getText();

        // Limpa o painel de resultados antes de adicionar novos "cartões"
        resultPanel.removeAll();

        // Se for um profissional, buscamos os contratantes com base no campo "buscando"
        if (isProfessional) {
            List<Contratante> contratantes = Consultas.buscarContratantesPorBuscando(searchTerm);
            for (Contratante contratante : contratantes) {
                JPanel cardPanel = criarCartaoContratante(contratante);
                resultPanel.add(cardPanel);
            }
        } else {
            // Se for um contratante, buscamos os profissionais com base na profissão
            List<Profissional> profissionais = Consultas.buscarProfissionaisPorProfissao(searchTerm);
            for (Profissional profissional : profissionais) {
                JPanel cardPanel = criarCartaoProfissional(profissional);
                resultPanel.add(cardPanel);
            }
        }

        // Atualiza a interface com os novos cartões
        resultPanel.revalidate();
        resultPanel.repaint();
    }

    // Método para criar o "cartão" do contratante
    private JPanel criarCartaoContratante(Contratante contratante) {
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS)); // Layout vertical

        // Definindo o estilo do cartão
        cardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Borda preta
        cardPanel.setPreferredSize(new Dimension(200, 150)); // Tamanho fixo do cartão
        cardPanel.setBackground(new Color(240, 240, 240)); // Cor de fundo clara

        // Adicionando informações do contratante ao cartão
        JLabel nomeLabel = new JLabel("Nome: " + contratante.getName());
        JLabel descricaoLabel = new JLabel("Descrição: " + contratante.getDescricao());
        JLabel buscandoLabel = new JLabel("Buscando: " + contratante.getBuscando());

        // Adiciona os labels ao cartão
        cardPanel.add(nomeLabel);
        cardPanel.add(descricaoLabel);
        cardPanel.add(buscandoLabel);

        return cardPanel;
    }

    // Método para criar o "cartão" do profissional
    private JPanel criarCartaoProfissional(Profissional profissional) {
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS)); // Layout vertical

        // Definindo o estilo do cartão
        cardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Borda preta
        cardPanel.setPreferredSize(new Dimension(200, 150)); // Tamanho fixo do cartão
        cardPanel.setBackground(new Color(240, 240, 240)); // Cor de fundo clara

        // Adicionando informações do profissional ao cartão
        JLabel nomeLabel = new JLabel("Nome: " + profissional.getName());
        JLabel profissaoLabel = new JLabel("Profissão: " + profissional.getProfissao());
        JLabel disponibilidadeLabel = new JLabel("Disponibilidade: " + profissional.getDisponibilidade());

        // Adiciona os labels ao cartão
        cardPanel.add(nomeLabel);
        cardPanel.add(profissaoLabel);
        cardPanel.add(disponibilidadeLabel);

        return cardPanel;
    }
}