package org.serviceapp.ui;

import org.serviceapp.entity.ServiceEntity;
import org.serviceapp.manager.ServiceManager;
import org.serviceapp.util.BaseForm;
import org.serviceapp.util.DialogUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class MainForm extends BaseForm {
    private JPanel mainPanel;
    private JButton selectAllButton;
    private JButton insertButton;
    private JButton updateButton;
    private JButton deleteButton;


    public MainForm() {
        super(800, 600);
        setContentPane(mainPanel);

        initButtons();

        setVisible(true);
    }

    private void initButtons(){
        selectAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ServiceList();
            }
        });

        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ServiceCreateForm();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onUpdatePressed();
            }
        });

        deleteButton.addActionListener(e -> onDeletePressed());
    }

    private void onDeletePressed () {
        String input = DialogUtil.showInput(this, "Введите ID записи, которую необходимо удалить");
        int id = -1;
        try {
            id = Integer.parseInt(input);
            if (id < 0)  DialogUtil.showError(this, "Введено некорректное число");
        } catch (Exception e){
           DialogUtil.showError(this, "Введено некорректное число");
        }

        ServiceEntity entity = null;
        try {
            entity = ServiceManager.selectByID(id);
            ServiceManager.delete(id);
        } catch (SQLException exception) {
            DialogUtil.showError(this, "Произошла ошибка\n" + exception.getMessage());
        }

        if (entity == null) {
            DialogUtil.showError(this, "Введенного айди не было в базе данных");
        } else {
            DialogUtil.showInfo(this, "Запись ''" + entity.getTitle() + "'' была успешно удалена");
        }
    }

    private void onUpdatePressed() {

    }
}
