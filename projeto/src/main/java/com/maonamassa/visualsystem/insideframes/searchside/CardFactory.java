package com.maonamassa.visualsystem.insideframes.searchside;

import javax.swing.*;
import java.awt.*;
import com.maonamassa.usersystem.Contratante;
import com.maonamassa.usersystem.Profissional;
import com.maonamassa.visualsystem.insideframes.searchside.newdialogs.PedirMaozinhaProfissional;

public class CardFactory {

    // Método para criar o "cartão" do contratante
    public static JPanel criarCartaoContratante(Contratante contratante, int larguraPainel) {
        JPanel cardPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.GRAY); // Cor para a simulação da foto
                g.fillOval(10, 10, 50, 50); // Desenha o círculo
            }
        };
        cardPanel.setLayout(null); // Layout absoluto para o cartão
        cardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Borda preta
        cardPanel.setBackground(new Color(240, 240, 240)); // Cor de fundo clara
        cardPanel.setPreferredSize(new Dimension(larguraPainel, 150)); // Largura definida pelo parâmetro

        // Adicionando informações do contratante ao cartão
        JLabel nomeLabel = new JLabel(contratante.getName());
        nomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nomeLabel.setBounds(70, 10, larguraPainel - 120, 30);

        JLabel descricaoLabel = new JLabel("Descrição: " + contratante.getDescricao());
        descricaoLabel.setBounds(70, 40, larguraPainel - 120, 30);

        JLabel buscandoLabel = new JLabel("Buscando: " + contratante.getBuscando());
        buscandoLabel.setBounds(70, 70, larguraPainel - 120, 30);

        JButton pedirMaozinhaButton = new JButton("Oferecer uma mãozinha");
        pedirMaozinhaButton.setBounds(larguraPainel - 280, 40, 240, 30); // Posição e tamanho fixo

        // Carrega a imagem de ícone para o botão
        ImageIcon icon = new ImageIcon(CardFactory.class.getClassLoader().getResource("images/maozinha.png"));

        // Verifica se a imagem foi carregada corretamente
        if (icon.getIconWidth() != -1) {
            Image img = icon.getImage();
            Image resizedImg = img.getScaledInstance(45, 40, java.awt.Image.SCALE_SMOOTH); // Redimensiona
            icon = new ImageIcon(resizedImg);
            pedirMaozinhaButton.setIcon(icon); // Define o ícone do botão
        }

        pedirMaozinhaButton.addActionListener(e -> {
            System.out.println("Abrindo a janela 'Pedir uma mãozinha' para " + contratante.getName());
            PedirMaozinhaProfissional pedirMaozinhaDialog = new PedirMaozinhaProfissional(contratante);
            pedirMaozinhaDialog.setVisible(true);
        });

        // Adiciona os componentes ao painel
        cardPanel.add(nomeLabel);
        cardPanel.add(descricaoLabel);
        cardPanel.add(buscandoLabel);
        cardPanel.add(pedirMaozinhaButton);

        return cardPanel;
    }

    // Método para criar o "cartão" do profissional
    public static JPanel criarCartaoProfissional(Profissional profissional, int larguraPainel) {
        JPanel cardPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.GRAY); // Cor para a simulação da foto
                g.fillOval(10, 10, 50, 50); // Desenha o círculo
            }
        };
        cardPanel.setLayout(null); // Layout absoluto para o cartão
        cardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Borda preta
        cardPanel.setBackground(new Color(240, 240, 240)); // Cor de fundo clara
        cardPanel.setPreferredSize(new Dimension(larguraPainel, 150)); // Largura definida pelo parâmetro

        // Adicionando informações do profissional ao cartão
        JLabel nomeLabel = new JLabel(profissional.getName());
        nomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nomeLabel.setBounds(70, 10, larguraPainel - 120, 30);

        JLabel profissaoLabel = new JLabel("Profissão: " + profissional.getProfissao());
        profissaoLabel.setBounds(70, 40, larguraPainel - 120, 30);

        JLabel enderecoLabel = new JLabel("Cidade: " +
                (profissional != null && profissional.getAddress() != null && !profissional.getAddress().isEmpty()
                        ? profissional.getAddress()
                        : "Não informado"));
        enderecoLabel.setBounds(70, 70, larguraPainel - 120, 30);

        JLabel disponibilidadeLabel = new JLabel();
        if (profissional.getDisponibilidade() != null && profissional.getDisponibilidade().getDescricao() != null) {
            disponibilidadeLabel.setText("Disponibilidade: " + profissional.getDisponibilidade().getDescricao());
        } else {
            disponibilidadeLabel.setText("Disponibilidade: Não informado");
        }
        disponibilidadeLabel.setBounds(70, 100, larguraPainel - 120, 30);

        JButton pedirMaozinhaButton = new JButton("Pedir uma mãozinha");
        pedirMaozinhaButton.setBounds(larguraPainel - 280, 40, 240, 30); // Tamanho fixo

        // Carrega a imagem de ícone para o botão
        ImageIcon icon = new ImageIcon(CardFactory.class.getClassLoader().getResource("images/maozinha.png"));

        // Verifica se a imagem foi carregada corretamente
        if (icon.getIconWidth() != -1) {
            Image img = icon.getImage();
            Image resizedImg = img.getScaledInstance(45, 40, java.awt.Image.SCALE_SMOOTH); // Redimensiona
            icon = new ImageIcon(resizedImg);
            pedirMaozinhaButton.setIcon(icon); // Define o ícone do botão
        }

        pedirMaozinhaButton.addActionListener(e -> {
            System.out.println("Pedir uma mãozinha para " + profissional.getName());
            PedirMaozinhaProfissional pedirMaozinhaDialog = new PedirMaozinhaProfissional(profissional);
            pedirMaozinhaDialog.setVisible(true);
        });

        // Adiciona os componentes ao painel
        cardPanel.add(nomeLabel);
        cardPanel.add(profissaoLabel);
        cardPanel.add(enderecoLabel);
        cardPanel.add(disponibilidadeLabel);
        cardPanel.add(pedirMaozinhaButton);

        return cardPanel;
    }
}
