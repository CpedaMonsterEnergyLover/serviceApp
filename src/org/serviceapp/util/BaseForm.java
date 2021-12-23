package org.serviceapp.util;

import org.serviceapp.entity.ServiceEntity;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class BaseForm extends JFrame {

    public static final String APP_TITLE = "Контроль сервисов";
    public static Image APP_ICON = null;

    static {
        try {
            APP_ICON = ImageIO.read(BaseForm.class.getClassLoader().getResource("picture.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BaseForm (int w, int h){
        setSize(w, h);
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - w) / 2,
                (Toolkit.getDefaultToolkit().getScreenSize().height - h) / 2);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle(APP_TITLE);
        if(APP_ICON != null) setIconImage(APP_ICON);

        // setContentPane(pane)
        // setVisible(true);

    }
}
