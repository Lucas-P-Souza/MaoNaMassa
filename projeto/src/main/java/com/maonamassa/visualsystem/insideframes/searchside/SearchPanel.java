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
    private JPanel resultPanel; // Painel para exibir os cartões de resultados
    private JScrollPane scrollPane; // JScrollPane para adicionar o painel de resultados

    public SearchPanel(boolean isProfessional) {
        setLayout(new BorderLayout()); // Layout principal do painel

        // Painel externo com margens
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Margens externas (topo, esquerda, base,
                                                                              // direita)

        // Painel superior para os elementos de busca
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new GridBagLayout()); // Layout flexível para alinhamento
        searchPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margem interna do painel superior

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaçamento entre os componentes

        // Label de busca
        searchLabel = new JLabel("Buscar por:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        searchPanel.add(searchLabel, gbc);

        // Campo de busca
        searchField = new JTextField(" pedreiro, encanador, eletricista...");
        searchField.setForeground(Color.GRAY);
        searchField.setColumns(30); // Define o tamanho inicial do campo
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        searchPanel.add(searchField, gbc);

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
        gbc.gridx = 2;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        searchPanel.add(searchButton, gbc);
        searchButton.addActionListener(e -> executeSearch(isProfessional));

        // Painel de resultados para exibir os "cartões"
        resultPanel = new JPanel();
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS)); // Layout vertical
        resultPanel.setBackground(new Color(240, 240, 240)); // Cor de fundo clara

        // JScrollPane para envolver o painel de resultados
        scrollPane = new JScrollPane(resultPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Barra de rolagem sempre visível
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Desabilita rolagem
                                                                                         // horizontal
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        // Adiciona os componentes ao painel principal
        mainPanel.add(searchPanel, BorderLayout.NORTH); // Painel de busca no topo
        mainPanel.add(scrollPane, BorderLayout.CENTER); // Resultados no centro

        // Adiciona o painel principal ao painel externo
        add(mainPanel, BorderLayout.CENTER);
    }

    // Método de busca que exibe os resultados como "cartões"
    private void executeSearch(boolean isProfessional) {
        String searchTerm = searchField.getText();

        // Limpa o painel de resultados antes de adicionar novos "cartões"
        resultPanel.removeAll();

        if (isProfessional) {
            List<Contratante> contratantes = Consultas.buscarContratantesPorBuscando(searchTerm);
            for (Contratante contratante : contratantes) {
                JPanel cardPanel = CardFactory.criarCartaoContratante(contratante, scrollPane.getWidth());
                resultPanel.add(cardPanel);
            }
        } else {
            List<Profissional> profissionais = Consultas.buscarProfissionaisPorProfissao(searchTerm);
            for (Profissional profissional : profissionais) {
                JPanel cardPanel = CardFactory.criarCartaoProfissional(profissional, scrollPane.getWidth());
                resultPanel.add(cardPanel);
            }
        }

        // Atualiza o painel de resultados
        resultPanel.revalidate();
        resultPanel.repaint();
    }
}
