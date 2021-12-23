package org.serviceapp.ui;

import org.serviceapp.entity.ServiceEntity;
import org.serviceapp.manager.ServiceManager;
import org.serviceapp.util.BaseForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceList extends BaseForm {
    private JPanel mainPanel;
    private JTextArea listText;
    private JButton backButton;

    public ServiceList() {
        super(800, 600);

        setContentPane(mainPanel);

        initTextArea();
        initButtons();

        setVisible(true);
    }

    private void initTextArea(){

        StringBuilder sb = new StringBuilder();


        try {
            List<ServiceEntity> entityList = ServiceManager.selectAll();
            entityList.forEach(serviceEntity -> {
                sb.append(serviceEntity).append("\n");
            });
        } catch (SQLException exception) {
            sb.append("Не удалось загрузить список сущностей\n")
            .append(exception.getMessage());
        }

        listText.setText(sb.toString());
    }

    private void initButtons(){
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MainForm();
            }
        });
    }
}
