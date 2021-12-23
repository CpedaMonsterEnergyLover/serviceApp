package org.serviceapp;

import org.serviceapp.manager.ServiceManager;
import org.serviceapp.ui.MainForm;
import org.serviceapp.util.BaseForm;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) {

        setLookAndFeel();
        new MainForm();
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/service", "root", "1111");
    }

    private static void setLookAndFeel(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
