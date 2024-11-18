package com.maonamassa.visualsystem.insideframes.userside.newdialogs;
import javax.swing.*;
import java.awt.*;

public class CartaoPropostaFactory {

    // Método que cria um cartão para a proposta
    public static JPanel criarCartao(String tipo, String nome, String descricao) {
        // Cria o painel do cartão
        JPanel cartao = new JPanel();
        cartao.setLayout(new BoxLayout(cartao, BoxLayout.Y_AXIS)); // Layout vertical
        cartao.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // Borda do cartão
        cartao.setBackground(Color.WHITE);
        cartao.setPreferredSize(new Dimension(300, 120));

        // Nome da proposta
        JLabel nomeLabel = new JLabel(nome);
        nomeLabel.setFont(new Font("Arial", Font.BOLD, 14));

        // Descrição da proposta
        JLabel descricaoLabel = new JLabel(descricao);
        descricaoLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        // Adiciona os componentes ao cartão
        cartao.add(nomeLabel);
        cartao.add(Box.createVerticalStrut(5)); // Espaçamento
        cartao.add(descricaoLabel);

        return cartao;
    }

    // Método para criar uma lista de cartões de propostas a partir de uma lista de
    // strings
    public static JPanel criarPainelDeCartoes(String tipo, java.util.List<String> propostas) {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS)); // Layout vertical
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margens

        // Cria os cartões para cada proposta
        for (int i = 0; i < propostas.size(); i++) {
            String descricao = propostas.get(i); // Descrição da proposta
            String nome = tipo.equals("Feitas") ? "Profissional " + (i + 1) : "Contratante " + (i + 1);
            JPanel cartao = criarCartao(tipo, nome, descricao);
            painel.add(cartao);
            painel.add(Box.createVerticalStrut(10)); // Espaçamento entre os cartões
        }

        return painel;
    }
}
