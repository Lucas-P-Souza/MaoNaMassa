package com.maonamassa.visualsystem.profileandsearch;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class SearchPanel extends JPanel {

    private JTextField searchField;
    private JButton searchButton;
    private JLabel searchLabel;

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

        // Adiciona os componentes ao painel
        add(searchLabel);
        add(searchField);
        add(searchButton);
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
    //main para teste visual
    public static void main(String[] args) {
        JFrame frame = new JFrame("Search Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 100);
        frame.add(new SearchPanel(true));
        frame.setVisible(true);
    }
}
