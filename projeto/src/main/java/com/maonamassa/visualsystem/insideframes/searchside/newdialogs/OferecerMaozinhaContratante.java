package com.maonamassa.visualsystem.insideframes.searchside.newdialogs;

import java.awt.*;
import javax.swing.*;

import com.maonamassa.usersystem.Contratante;

public class OferecerMaozinhaContratante extends JDialog {

    public OferecerMaozinhaContratante(Contratante contratante) {
        configurarJanela();
        configurarConteudoContratante(contratante);
    }

    private void configurarJanela() {
        setTitle("Pedir uma mãozinha");
        setSize(500, 450); // Aumentei o tamanho da janela para acomodar o campo de valor
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setModal(true); // Bloqueia a interação com a janela principal enquanto esta estiver aberta
    }

    private void configurarConteudoContratante(Contratante contratante) {
        JPanel painelPrincipal = criarPainelPrincipal();

        // Painel superior com foto e informações do contratante
        JPanel painelSuperior = criarPainelSuperior(
                contratante != null ? contratante.getName() : "Não disponível",
                "Descrição: " + (contratante != null ? contratante.getDescricao() : "Não disponível"),
                "Buscando: " + (contratante != null ? contratante.getBuscando() : "Não informado"),
                null // Nenhuma informação adicional
        );

        JPanel painelInferior = criarPainelInferior();

        // Adiciona os painéis ao painel principal
        painelPrincipal.add(painelSuperior, BorderLayout.NORTH);
        painelPrincipal.add(painelInferior, BorderLayout.CENTER);

        // Adiciona o painel principal à janela
        add(painelPrincipal);
    }

    private JPanel criarPainelPrincipal() {
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BorderLayout(10, 10));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margens
        return painelPrincipal;
    }

    private JPanel criarPainelSuperior(String nome, String linha1, String linha2, String linha3) {
        JPanel painelSuperior = new JPanel();
        painelSuperior.setLayout(new BorderLayout(10, 10));

        // Simula uma foto de perfil circular
        JLabel fotoPerfil = new JLabel();
        fotoPerfil.setPreferredSize(new Dimension(80, 80));
        fotoPerfil.setOpaque(true);
        fotoPerfil.setBackground(Color.GRAY);
        fotoPerfil.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        fotoPerfil.setHorizontalAlignment(SwingConstants.CENTER);

        // Informações do usuário
        JPanel painelInfo = new JPanel();
        painelInfo.setLayout(new BoxLayout(painelInfo, BoxLayout.Y_AXIS));

        JLabel nomeLabel = criarLabel(nome);
        nomeLabel.setFont(new Font("Arial", Font.BOLD, 18)); // Fonte maior e em negrito
        painelInfo.add(nomeLabel);

        if (linha1 != null)
            painelInfo.add(criarLabel(linha1));
        if (linha2 != null)
            painelInfo.add(criarLabel(linha2));
        if (linha3 != null)
            painelInfo.add(criarLabel(linha3));

        painelSuperior.add(fotoPerfil, BorderLayout.WEST);
        painelSuperior.add(painelInfo, BorderLayout.CENTER);

        return painelSuperior;
    }

    private JPanel criarPainelInferior() {
        JPanel painelInferior = new JPanel();
        painelInferior.setLayout(new BorderLayout(10, 10));

        // Seção para escrever a proposta
        JLabel propostaLabel = new JLabel("Escreva sua proposta:");
        JTextArea textAreaProposta = new JTextArea(5, 30);
        textAreaProposta.setLineWrap(true);
        textAreaProposta.setWrapStyleWord(true);

        JScrollPane scrollProposta = new JScrollPane(textAreaProposta, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Seção para digitar o valor do serviço
        JLabel valorLabel = new JLabel("Valor do serviço proposto:");
        JTextField campoValor = new JTextField(20); // Campo de texto para o valor

        // Botão para enviar a proposta
        JButton botaoEnviar = new JButton("Enviar Proposta");

        // Painel para o campo de valor e o botão
        JPanel painelValorEEnviar = new JPanel();
        painelValorEEnviar.setLayout(new BorderLayout(5, 5));
        painelValorEEnviar.add(valorLabel, BorderLayout.NORTH);
        painelValorEEnviar.add(campoValor, BorderLayout.CENTER);
        painelValorEEnviar.add(botaoEnviar, BorderLayout.SOUTH);

        // Adiciona os componentes ao painel inferior
        painelInferior.add(propostaLabel, BorderLayout.NORTH);
        painelInferior.add(scrollProposta, BorderLayout.CENTER);
        painelInferior.add(painelValorEEnviar, BorderLayout.SOUTH);

        return painelInferior;
    }

    private JLabel criarLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        return label;
    }

    public static void main(String[] args) {
        // Simulação de dados para teste
        // Contratante contratante = new Contratante("Maria Oliveira", "Preciso de um
        // encanador", "Consertar vazamentos");

        // Testando o construtor para Contratante
        // new PedirMaozinhaContratante(contratante).setVisible(true);
    }
}
