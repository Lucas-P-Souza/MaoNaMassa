package com.maonamassa.visualsystem.insideframes.searchside.newdialogs;

import java.awt.*;
import javax.swing.*;

import com.maonamassa.usersystem.Profissional;

public class PedirMaozinhaProfissional extends JDialog {

    public PedirMaozinhaProfissional(Profissional profissional) {
        setTitle("Pedir uma mãozinha");
        setSize(500, 400); // Define o tamanho da janela
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setModal(true); // Bloqueia a interação com a janela principal enquanto esta estiver aberta

        // Painel principal
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BorderLayout(10, 10));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margens

        // Painel superior com foto e informações
        JPanel painelSuperior = new JPanel();
        painelSuperior.setLayout(new BorderLayout(10, 10));

        // Simula uma foto de perfil circular
        JLabel fotoPerfil = new JLabel();
        fotoPerfil.setPreferredSize(new Dimension(80, 80));
        fotoPerfil.setOpaque(true);
        fotoPerfil.setBackground(Color.GRAY);
        fotoPerfil.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        fotoPerfil.setHorizontalAlignment(SwingConstants.CENTER);

        // Informações do profissional
        JPanel painelInfo = new JPanel();
        painelInfo.setLayout(new BoxLayout(painelInfo, BoxLayout.Y_AXIS));

        // Configurações do nome com fonte maior e em negrito
        JLabel nomeLabel = criarLabel((profissional != null ? profissional.getName() : "Não disponível"));
        nomeLabel.setFont(new Font("Arial", Font.BOLD, 18)); // Fonte maior e em negrito
        painelInfo.add(nomeLabel);

        // Outros rótulos com estilo padrão
        painelInfo.add(criarLabel("Profissão: " + (profissional != null ? profissional.getProfissao() : "Não disponível")));
        // Adiciona cidade com tratamento de null
        painelInfo.add(criarLabel("Cidade: " + 
        (profissional != null && profissional.getAddress() != null ? profissional.getAddress() : "Não informado")));

        // Adiciona disponibilidade com tratamento de null
        painelInfo.add(criarLabel("Disponibilidade: " + 
        (profissional != null && profissional.getDisponibilidade() != null && profissional.getDisponibilidade().getDescricao() != null 
            ? profissional.getDisponibilidade().getDescricao() 
            : "Não informado")));

        painelSuperior.add(fotoPerfil, BorderLayout.WEST);
        painelSuperior.add(painelInfo, BorderLayout.CENTER);

        // Painel inferior para escrever a proposta
        JPanel painelInferior = new JPanel();
        painelInferior.setLayout(new BorderLayout(10, 10));

        JLabel propostaLabel = new JLabel("Escreva sua proposta:");
        JTextArea textAreaProposta = new JTextArea(5, 30);
        textAreaProposta.setLineWrap(true);
        textAreaProposta.setWrapStyleWord(true);

        JScrollPane scrollProposta = new JScrollPane(textAreaProposta, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JButton botaoEnviar = new JButton("Enviar Proposta");

        painelInferior.add(propostaLabel, BorderLayout.NORTH);
        painelInferior.add(scrollProposta, BorderLayout.CENTER);
        painelInferior.add(botaoEnviar, BorderLayout.SOUTH);

        // Adiciona os painéis ao painel principal
        painelPrincipal.add(painelSuperior, BorderLayout.NORTH);
        painelPrincipal.add(painelInferior, BorderLayout.CENTER);

        // Adiciona o painel principal à janela
        add(painelPrincipal);
    }

    private JLabel criarLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        return label;
    }

    public static void main(String[] args) {
        // Simulação de dados para teste
        Profissional profissional = new Profissional("João da Silva", "Engenheiro", "São Paulo", "Disponível");
        PedirMaozinhaProfissional pedirMaozinhaDialog = new PedirMaozinhaProfissional(profissional);
        pedirMaozinhaDialog.setVisible(true);
    }
}
