package ir.maktab.shop;

import ir.maktab.shop.frame.LoginFrame;

import java.util.Properties;

public class MainForm {
    public static void main(String[] args) {
        Properties props = System.getProperties();
        props.setProperty("javax.accessibility.assistive_technologies", "");
        LoginFrame.createObject();
    }
}
