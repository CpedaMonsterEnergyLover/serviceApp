package org.serviceapp.ui;

import com.google.protobuf.DescriptorProtos;
import org.serviceapp.entity.ServiceEntity;
import org.serviceapp.manager.ServiceManager;
import org.serviceapp.util.BaseForm;
import org.serviceapp.util.DialogUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ServiceCreateForm extends BaseForm {

    private JTextField descriptionField;
    private JTextField imageField;
    private JTextField titleField;
    private JSpinner durationSpinner;
    private JSpinner costSpinner;
    private JSpinner saleSpinne;

    private JButton saveButton;
    private JButton backButton;
    private JPanel mainPanel;

    public ServiceCreateForm() {
        super(600, 400);
        setContentPane(mainPanel);

        initButtons();

        setVisible(true);
    }

    private void initButtons(){
        saveButton.addActionListener(e -> {

            String title = titleField.getText();
            if(!validateString(title, "Название", 100)) return;

            double cost = (int) costSpinner.getValue();
            if(!validateNum(cost, "Стоимость")) return;

            int duration = (int) durationSpinner.getValue();
            if(!validateNum(duration, "Длительность")) return;

            String description = descriptionField.getText();
            if(!validateString(description, "Описание", Integer.MAX_VALUE)) return;

            double sale = (int) saleSpinne.getValue();
            if(!validateNum(sale, "Скидка")) return;

            String image = imageField.getText();
            if(!validateString(image, "Изображение", 1000)) return;


            ServiceEntity entity = new ServiceEntity(-1, title, cost, duration, description, sale, image);

            try {
                ServiceManager.insert(entity);
                DialogUtil.showInfo(this, entity.getTitle() + " была добавлена в БД");
            } catch (SQLException exception) {
                DialogUtil.showError(this, "Не удалось добавить сущность\n " + exception.getMessage());
            }

            dispose();
            new MainForm();

        });


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MainForm();
            }
        });

    }

    private boolean validateString(String toValidate, String fieldName, int maxlength){
        boolean isValid = !toValidate.isEmpty() && toValidate.length() <= maxlength;
        if(!isValid) DialogUtil.showError(this, "Значение поля " + fieldName + " должно быть не пустым или содержать больше " + maxlength + " символов!");
        return isValid;
    }

    private boolean validateNum(Number num, String fieldName){
        boolean isValid = num.intValue() >= 0.0;
        if(!isValid) DialogUtil.showError(this, "Значение поля " + fieldName + " должно быть больше нуля и меньше " + Integer.MAX_VALUE);
        return isValid;
    }
}
