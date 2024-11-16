package com.maonamassa.visualsystem.insideframes.searchside;

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
    private JScrollPane scrollPane; // JScrollPane para adicionar o painel de resultados

    public SearchPanel(boolean isProfessional) {
        setLayout(null); // Usando layout absoluto

        // Label de busca
        searchLabel = new JLabel("Buscar por:");
        searchLabel.setBounds(50, 20, 100, 30); // Posição (x, y) e tamanho (largura, altura)

        // Campo de busca
        searchField = new JTextField(" pedreiro, encanador, eletricista...");
        searchField.setForeground(Color.GRAY);
        searchField.setBounds(140, 20, 1050, 30); // Posição e tamanho

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
        searchButton.setBounds(1230, 20, 120, 30); // Posição e tamanho
        searchButton.addActionListener(e -> executeSearch(isProfessional));

        // Painel de resultados para exibir os "cartões"
        resultPanel = new JPanel();
        resultPanel.setLayout(null); // Layout absoluto para posicionamento manual dos cartões
        resultPanel.setBackground(new Color(240, 240, 240)); // Cor de fundo clara

        // JScrollPane para envolver o painel de resultados
        scrollPane = new JScrollPane(resultPanel);
        scrollPane.setBounds(50, 70, 1300, 900); // Posição e tamanho do JScrollPane
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Barra de rolagem sempre visível
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Desabilita rolagem horizontal

        // Adiciona os componentes ao painel
        add(searchLabel);
        add(searchField);
        add(searchButton);
        add(scrollPane); // Adiciona o JScrollPane em vez do painel diretamente
    }

    // Método de busca que exibe os resultados como "cartões"
    private void executeSearch(boolean isProfessional) {
        String searchTerm = searchField.getText();

        // Limpa o painel de resultados antes de adicionar novos "cartões"
        resultPanel.removeAll();

        // Inicializa o Y para controlar a posição dos cartões
        int yPosition = 10; // Posição inicial do primeiro cartão

        // Calcula a largura do painel de resultados (JScrollPane)
        int larguraPainel = resultPanel.getWidth() - 20; // Ajusta a largura do cartão para o painel com um pequeno espaçamento

        // Se for um profissional, buscamos os contratantes com base no campo "buscando"
        if (isProfessional) {
            List<Contratante> contratantes = Consultas.buscarContratantesPorBuscando(searchTerm);
            for (Contratante contratante : contratantes) {
                JPanel cardPanel = CardFactory.criarCartaoContratante(contratante, larguraPainel);
                // Define a posição Y de cada cartão
                cardPanel.setBounds(10, yPosition, larguraPainel, 150); // x = 10, y = yPosition
                resultPanel.add(cardPanel);
                yPosition += 160; // Atualiza a posição Y para o próximo cartão (160 = altura do cartão + espaçamento)
            }
        } else {
            // Se for um contratante, buscamos os profissionais com base na profissão
            List<Profissional> profissionais = Consultas.buscarProfissionaisPorProfissao(searchTerm);
            for (Profissional profissional : profissionais) {
                JPanel cardPanel = CardFactory.criarCartaoProfissional(profissional, larguraPainel);
                // Define a posição Y de cada cartão
                cardPanel.setBounds(10, yPosition, larguraPainel, 150); // x = 10, y = yPosition
                resultPanel.add(cardPanel);
                yPosition += 160; // Atualiza a posição Y para o próximo cartão
            }
        }

        // Atualiza a interface com os novos cartões
        resultPanel.revalidate();
        resultPanel.repaint();
    }
}
