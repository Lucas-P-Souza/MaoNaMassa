package com.maonamassa.visualsystem.profileandsearch;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JPanel;

import com.maonamassa.usersystem.Sessao;
import com.maonamassa.visualsystem.firstinteraction.MainFrame;
import com.maonamassa.visualsystem.firstinteraction.LoginScreen;

public class InsideScreen extends JPanel {  

    private static final double DIVIDER_RATIO = 0.70; // Proporção do painel esquerdo

    public InsideScreen(MainFrame mainFrame) {

        Sessao sessao = LoginScreen.getSessao();  

        if (sessao == null) {
            System.out.println("Erro: Sessão está nula!");
            return;
        }

        System.out.println("InsideScreen: Sessão válida, nome do usuário: " + sessao.getNome());

        setLayout(null); // Usando layout absoluto

        boolean isProfessional = sessao.getIsProfissional();

        SearchPanel searchPanel = new SearchPanel(isProfessional);
        UserInfoPanel userInfoPanel = new UserInfoPanel(mainFrame, sessao);

        // Definindo tamanhos iniciais
        int mainFrameWidth = mainFrame.getWidth();
        int mainFrameHeight = mainFrame.getHeight();

        int leftPanelWidth = (int) (mainFrameWidth * DIVIDER_RATIO);
        int rightPanelWidth = mainFrameWidth - leftPanelWidth;

        // Configurando posições e tamanhos iniciais
        searchPanel.setBounds(0, 0, leftPanelWidth, mainFrameHeight);
        userInfoPanel.setBounds(leftPanelWidth, 0, rightPanelWidth, mainFrameHeight);

        add(searchPanel);
        add(userInfoPanel);

        // Listener para redimensionamento
        mainFrame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int newWidth = mainFrame.getWidth();
                int newHeight = mainFrame.getHeight();

                int newLeftPanelWidth = (int) (newWidth * DIVIDER_RATIO);
                int newRightPanelWidth = newWidth - newLeftPanelWidth;

                // Atualiza as posições e tamanhos dos painéis
                searchPanel.setBounds(0, 0, newLeftPanelWidth, newHeight);
                userInfoPanel.setBounds(newLeftPanelWidth, 0, newRightPanelWidth, newHeight);
            }
        });

        setVisible(true);
    }

    //main para teste visual somente da tela InsideScreen
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.showScreen("RegisterScreen");
    }
}
