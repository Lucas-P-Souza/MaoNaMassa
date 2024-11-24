package com.maonamassa.visualsystem.insideframes.userside;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageCirclePanel extends JPanel {

    private BufferedImage image;

    public ImageCirclePanel() {
        
        setPreferredSize(new Dimension(200, 200)); // Tamanho do círculo

        // Listener de clique para abrir o upload de imagem
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                uploadImage();
                repaint(); // Atualiza o painel após carregar a imagem
            }
        });
    }

    // Método para fazer upload da imagem
    private void uploadImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecione uma imagem");
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                image = ImageIO.read(selectedFile);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao carregar a imagem", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Método para desenhar o círculo e a imagem
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Habilita antialiasing para melhor qualidade
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Desenha o círculo
        int diameter = Math.min(getWidth(), getHeight());
        int x = (getWidth() - diameter) / 2;
        int y = (getHeight() - diameter) / 2;
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillOval(x, y, diameter, diameter);

        // Desenha a imagem dentro do círculo, se houver uma imagem
        if (image != null) {
            // Corta a imagem para caber dentro do círculo
            g2d.setClip(new java.awt.geom.Ellipse2D.Double(x, y, diameter, diameter));
            g2d.drawImage(image, x, y, diameter, diameter, this);
        }
    }

    // Método principal para testar o painel
    public static void main(String[] args) {
        JFrame frame = new JFrame("Círculo Interativo com Upload de Imagem");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ImageCirclePanel());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
}