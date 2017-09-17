package com.mommoo.game.screen.view.dialog;

import com.mommoo.main.GameDescriptionObject;
import com.mommoo.manager.FontManager;
import com.mommoo.manager.ScreenManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by mommoo on 2017-04-21.
 */
public class SimpleDialog{
    private static final ScreenManager SM = ScreenManager.getInstance();
    private static final int INSET_VALUE = SM.dip2px(22);
    private static final Font BUTTON_FONT = FontManager.getTrackFont(Font.PLAIN,SM.dip2px(8));
    private JDialog DIALOG = new JDialog();

    private SimpleDialog(String title, String message, Point point,ArrayList<JButton> buttonList){
        DIALOG.setTitle(title);
        DIALOG.setLayout(new GridBagLayout());
        DIALOG.setIconImage(GameDescriptionObject.getMineIcon().getScaledInstance(50,50,Image.SCALE_SMOOTH));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        constraints.gridheight = 3;
        constraints.weighty = 0.7;
        constraints.insets = new Insets(INSET_VALUE,INSET_VALUE,INSET_VALUE,INSET_VALUE);
        JLabel label = new JLabel(message,JLabel.CENTER);

        DIALOG.add(label,constraints);

        constraints.fill = GridBagConstraints.NONE;
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 3;
        constraints.gridheight = 1;
        constraints.weighty = 0.3;
        constraints.anchor = GridBagConstraints.EAST;
        constraints.insets = new Insets(0,INSET_VALUE,INSET_VALUE,INSET_VALUE);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT,SM.dip2px(5),SM.dip2px(5)));
        for(JButton button : buttonList){
            initButton(button);
            panel.add(button);
        }
        JButton button = new JButton("EXIT");
        button.addActionListener((e)->DIALOG.dispose());
        initButton(button);
        panel.add(button);
        DIALOG.add(panel,constraints);
        if(point != null) DIALOG.setLocation(point);
        DIALOG.pack();
    }

    private void initButton(JButton button){
        button.setFont(BUTTON_FONT);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(button.getPreferredSize().width,SM.dip2px(22)));
    }

    public void show(){
        DIALOG.setVisible(true);
    }

    public void hide(){
        DIALOG.setVisible(false);
    }

    public static class Builder{
        private String title="";
        private String message = "";
        private ArrayList<JButton> buttonList = new ArrayList<>();
        private Point point;

        public Builder setTitle(String title){
            this.title = title;
            return this;
        }

        public Builder setMessage(String message){
            this.message = message;
            return this;
        }

        public Builder setLocation(Point point){
            this.point = point;
            return this;
        }

        public Builder addButton(JButton button){
            buttonList.add(button);
            return this;
        }

        public SimpleDialog build(){
            return new SimpleDialog(title,message,point,buttonList);
        }
    }
}
