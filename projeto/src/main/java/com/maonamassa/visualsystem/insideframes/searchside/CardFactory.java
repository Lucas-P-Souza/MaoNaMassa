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
            // Sobrescreve o método paintComponent para desenhar o círculo
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Desenha um círculo (foto do usuário) na posição (10, 10) com diâmetro 50
                g.setColor(Color.GRAY); // Cor para a simulação da foto
                g.fillOval(10, 10, 50, 50); // Desenha o círculo
            }
        };
        cardPanel.setLayout(null); // Layout absoluto para o cartão

        // Estilo do cartão
        cardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Borda preta
        cardPanel.setBackground(new Color(240, 240, 240)); // Cor de fundo clara
        cardPanel.setPreferredSize(new Dimension(larguraPainel, 150)); // Largura definida pelo parâmetro

        // Adicionando informações do contratante ao cartão
        JLabel nomeLabel = new JLabel(contratante.getName());
        nomeLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Fonte em negrito
        nomeLabel.setBounds(70, 10, larguraPainel - 120, 30); // Posição e tamanho (ajustado para a foto)

        JLabel descricaoLabel = new JLabel("Descrição: " + contratante.getDescricao());
        descricaoLabel.setBounds(70, 40, larguraPainel - 120, 30); // Posição e tamanho (ajustado para a foto)

        JLabel buscandoLabel = new JLabel("Buscando: " + contratante.getBuscando());
        buscandoLabel.setBounds(70, 70, larguraPainel - 120, 30); // Posição e tamanho (ajustado para a foto)

        String texto = "Oferecer uma Mãozinha para " + contratante.getName();
        int larguraTexto = texto.length() * 10; // Aproximação da largura do texto em pixels
        JButton pedirMaozinhaButton = new JButton("Pedir uma Mãozinha para " + contratante.getName());
        pedirMaozinhaButton.setBounds(larguraPainel - 30 - larguraTexto, 40, larguraTexto, 30); // Posição e tamanho

        // Carrega a imagem de ícone para o botão
        ImageIcon icon = new ImageIcon(CardFactory.class.getClassLoader().getResource("images/maozinha.png")); // Caminho                                                                                                    // correto

        if (icon.getIconWidth() == -1) {
            System.out.println("Erro ao carregar a imagem.");
        } else {
            System.out.println("Imagem carregada com sucesso.");
        }

        // Redimensiona a imagem para ajustar ao tamanho do botão
        Image img = icon.getImage(); // Obtém a imagem
        Image resizedImg = img.getScaledInstance(45, 40, java.awt.Image.SCALE_SMOOTH); // Redimensiona a imagem
        icon = new ImageIcon(resizedImg); // Cria um novo ImageIcon com a imagem redimensionada

        pedirMaozinhaButton.setIcon(icon); // Define o ícone do botão

        pedirMaozinhaButton.addActionListener(e -> {
            // Aqui você pode adicionar a lógica do que acontece ao clicar no botão
            System.out.println("Pedir uma mãozinha para " + contratante.getName());
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
            // Sobrescreve o método paintComponent para desenhar o círculo
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Desenha um círculo (foto do usuário) na posição (10, 10) com diâmetro 50
                g.setColor(Color.GRAY); // Cor para a simulação da foto
                g.fillOval(10, 10, 50, 50); // Desenha o círculo
            }
        };
        cardPanel.setLayout(null); // Layout absoluto para o cartão

        // Estilo do cartão
        cardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Borda preta
        cardPanel.setBackground(new Color(240, 240, 240)); // Cor de fundo clara
        cardPanel.setPreferredSize(new Dimension(larguraPainel, 150)); // Largura definida pelo parâmetro

        // Adicionando informações do profissional ao cartão
        JLabel nomeLabel = new JLabel(profissional.getName());
        nomeLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Fonte em negrito
        nomeLabel.setBounds(70, 10, larguraPainel - 120, 30); // Posição e tamanho (ajustado para a foto)

        JLabel profissaoLabel = new JLabel("Profissão: " + profissional.getProfissao());
        profissaoLabel.setBounds(70, 40, larguraPainel - 120, 30); // Posição e tamanho (ajustado para a foto)

        JLabel enderecoLabel = new JLabel("Cidade: " +
                (profissional != null && profissional.getAddress() != null && !profissional.getAddress().isEmpty()
                        ? profissional.getAddress()
                        : "Não informado"));
        enderecoLabel.setBounds(70, 70, larguraPainel - 120, 30); // Posição e tamanho (ajustado para a foto)

        JLabel disponibilidadeLabel = new JLabel();
        if (profissional.getDisponibilidade() != null && profissional.getDisponibilidade().getDescricao() != null) {
            disponibilidadeLabel.setText("Disponibilidade: " + profissional.getDisponibilidade().getDescricao());
        } else {
            disponibilidadeLabel.setText("Disponibilidade: Não informado");
        }
        disponibilidadeLabel.setBounds(70, 100, larguraPainel - 120, 30); // Posição e tamanho (ajustado para a foto

        String texto = "Pedir uma Mãozinha para " + profissional.getName();
        int larguraTexto = texto.length() * 10; // Aproximação da largura do texto em pixels
        JButton pedirMaozinhaButton = new JButton("Pedir uma Mãozinha para " + profissional.getName());
        pedirMaozinhaButton.setBounds(larguraPainel - 30 - larguraTexto, 40, larguraTexto, 30);

        // Carrega a imagem de ícone para o botão
        ImageIcon icon = new ImageIcon(CardFactory.class.getClassLoader().getResource("images/maozinha.png")); // Caminho
                                                                                                               // correto

        // Redimensiona a imagem para ajustar ao tamanho do botão
        Image img = icon.getImage(); // Obtém a imagem
        Image resizedImg = img.getScaledInstance(45, 40, java.awt.Image.SCALE_SMOOTH); // Redimensiona a imagem
        icon = new ImageIcon(resizedImg); // Cria um novo ImageIcon com a imagem redimensionada

        pedirMaozinhaButton.setIcon(icon); // Define o ícone do botão

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