package org.serviceapp.util;

import javax.swing.*;
import java.awt.*;

public class DialogUtil {

    public static String showInput(Component component, String message) {
        return JOptionPane.showInputDialog(component, message);
    }

    public static void showError(Component component, String message) {
        JOptionPane.showMessageDialog(component, message, "Ошибка", JOptionPane.ERROR_MESSAGE);
    }

    public static void showInfo(Component component, String message) {
        JOptionPane.showMessageDialog(component, message, "Информация", JOptionPane.INFORMATION_MESSAGE);

    }
}
