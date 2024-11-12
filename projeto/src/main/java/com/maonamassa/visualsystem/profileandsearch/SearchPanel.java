package com.maonamassa.visualsystem.profileandsearch;

import java.awt.BorderLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchPanel extends JPanel {

    private JTextField searchField;

    public SearchPanel(boolean isProfessional) {
        setLayout(new BorderLayout());

        // Label e campo de busca
        JPanel searchLabelPanel = new JPanel(new BorderLayout());
        JLabel searchLabel = new JLabel("Buscar por:");
        searchField = new JTextField(" pedreiro, encanador, eletricista...");
        searchField.setForeground(java.awt.Color.GRAY);

        // Placeholder que some ao clicar
        searchField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals(" pedreiro, encanador, eletricista...")) {
                    searchField.setText("");
                    searchField.setForeground(java.awt.Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText(" pedreiro, encanador, eletricista...");
                    searchField.setForeground(java.awt.Color.GRAY);
                }
            }
        });

        // Botão de pesquisa
        JButton searchButton = new JButton("Pesquisar");
        searchButton.addActionListener(e -> executeSearch(isProfessional));

        searchLabelPanel.add(searchLabel, BorderLayout.WEST);
        searchLabelPanel.add(searchField, BorderLayout.CENTER);
        searchLabelPanel.add(searchButton, BorderLayout.EAST);

        add(searchLabelPanel, BorderLayout.NORTH);
    }

    // Método de busca comentado para integração futura
    private void executeSearch(boolean isProfessional) {
        String searchTerm = searchField.getText();
        if (isProfessional) {
            // Pesquisar na tabela de contratantes
            // Exemplo de função comentada para integração
            // DatabaseUtils.searchForJobRequests(searchTerm);
        } else {
            // Pesquisar na tabela de profissionais
            // Exemplo de função comentada para integração
            // DatabaseUtils.searchForProfessionals(searchTerm);
        }
    }
}
