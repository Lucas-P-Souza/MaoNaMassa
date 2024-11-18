package com.maonamassa.visualsystem.insideframes.userside.newdialogs;

import javax.swing.*;
import com.maonamassa.projectsystem.Projeto;
import java.awt.*;

public class CartaoProjeto extends JPanel {

    public CartaoProjeto(Projeto projeto) {
        // Verifica se o projeto é válido
        if (projeto == null) {
            throw new IllegalArgumentException("Projeto não pode ser nulo.");
        }

        // Configuração do painel (cartão)
        setLayout(null); // Layout nulo para posicionamento manual
        setPreferredSize(new Dimension(430, 200)); // Tamanho do cartão
        setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Borda para o cartão
        setBackground(Color.LIGHT_GRAY); // Cor de fundo para o cartão

        // Título do projeto
        JLabel label = new JLabel(projeto.getNomeProjeto());
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setBounds(10, 10, 410, 30); // Posição e tamanho do título
        add(label);

        // Descrição do projeto
        JLabel descricao = new JLabel("<html>" + projeto.getDescricaoProjeto() + "</html>");
        descricao.setFont(new Font("Arial", Font.PLAIN, 12));
        descricao.setBounds(10, 50, 410, 100); // Posição e tamanho da descrição
        add(descricao);

        // Status do projeto
        JLabel status = new JLabel("Status: " + projeto.getStatusDoProjeto());
        status.setFont(new Font("Arial", Font.ITALIC, 12));
        status.setBounds(10, 160, 410, 20); // Posição e tamanho do status
        add(status);

        // Efeito hover no cartão
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setBackground(Color.GRAY); // Fundo ao passar o mouse
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                setBackground(Color.LIGHT_GRAY); // Fundo padrão
            }
        });
    }
}
