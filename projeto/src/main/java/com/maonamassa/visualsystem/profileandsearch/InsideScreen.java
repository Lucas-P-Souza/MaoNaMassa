package com.maonamassa.visualsystem.profileandsearch;

import java.awt.BorderLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

import com.maonamassa.visualsystem.firstinteraction.MainFrame;

public class InsideScreen extends JPanel {

    private static final double DIVIDER_RATIO = 0.75; // Proporção do painel esquerdo

    public InsideScreen(MainFrame mainFrame, boolean isProfessional) {
        setLayout(new BorderLayout());

        // Divisão de layout principal
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setResizeWeight(DIVIDER_RATIO); // Ajusta a proporção inicial do painel esquerdo
        
        // Painel de busca
        SearchPanel searchPanel = new SearchPanel(isProfessional);

        // Painel de informações do usuário
        UserInfoPanel userInfoPanel = new UserInfoPanel(mainFrame, isProfessional);

        // Adiciona componentes ao layout principal
        splitPane.setLeftComponent(searchPanel);
        splitPane.setRightComponent(userInfoPanel);

        // Listener para redimensionamento
        mainFrame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int newDividerLocation = (int) (mainFrame.getWidth() * DIVIDER_RATIO);
                splitPane.setDividerLocation(newDividerLocation); // Atualiza a posição do divisor com base na nova largura da janela
            }
        });

        add(splitPane, BorderLayout.CENTER);
    }
}
