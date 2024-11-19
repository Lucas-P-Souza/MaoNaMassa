package com.maonamassa.visualsystem.insideframes.userside.newdialogs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CartaoPropostaFactory {

    // Método que cria um cartão para a proposta
    public static JPanel criarCartao(String nomeAutor, String descricao, String tipo, Object proposta) {
        JPanel cartao = new JPanel();
        cartao.setLayout(new BoxLayout(cartao, BoxLayout.Y_AXIS)); // Layout vertical
        cartao.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2)); // Borda do cartão
        cartao.setBackground(Color.LIGHT_GRAY);
        cartao.setMaximumSize(new Dimension(540, 140)); // Tamanho máximo do cartão
        cartao.setPreferredSize(new Dimension(540, 140)); // Tamanho preferido do cartão (ajustado)

        // Nome do autor
        JLabel nomeAutorLabel = new JLabel("Autor: " + nomeAutor);
        nomeAutorLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nomeAutorLabel.setAlignmentX(Component.LEFT_ALIGNMENT); // Alinha à esquerda

        // Descrição da proposta
        JLabel descricaoLabel = new JLabel("<html><p style='width:500px;'>" + descricao + "</p></html>");
        descricaoLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        descricaoLabel.setAlignmentX(Component.LEFT_ALIGNMENT); // Alinha à esquerda

        // Botões de ação
        JPanel botoes = new JPanel();
        botoes.setLayout(new FlowLayout(FlowLayout.RIGHT)); // Alinha os botões à direita
        botoes.setOpaque(false);

        if (tipo.equals("Feita")) {
            // Botão de cancelar para a proposta feita
            JButton cancelar = new JButton("Cancelar");
            cancelar.addActionListener((ActionEvent e) -> {
                // Lógica para cancelar a proposta
                JOptionPane.showMessageDialog(cartao, "Proposta cancelada.");
            });
            botoes.add(cancelar);
        } else if (tipo.equals("Recebida")) {
            // Botões de aceitar e recusar para a proposta recebida
            JButton aceitar = new JButton("Aceitar");
            JButton recusar = new JButton("Recusar");

            aceitar.addActionListener((ActionEvent e) -> {
                // Lógica para aceitar a proposta
                JOptionPane.showMessageDialog(cartao, "Proposta aceita.");
            });

            recusar.addActionListener((ActionEvent e) -> {
                // Lógica para recusar a proposta
                JOptionPane.showMessageDialog(cartao, "Proposta recusada.");
            });

            botoes.add(aceitar);
            botoes.add(recusar);
        }

        // Adiciona os componentes ao cartão
        cartao.add(nomeAutorLabel);
        cartao.add(Box.createVerticalStrut(5)); // Espaçamento
        cartao.add(descricaoLabel);
        cartao.add(Box.createVerticalStrut(10)); // Espaçamento
        cartao.add(botoes);

        return cartao;
    }
}
