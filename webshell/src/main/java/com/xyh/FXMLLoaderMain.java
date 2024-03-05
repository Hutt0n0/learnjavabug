package com.xyh;

import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.net.URL;

public class FXMLLoaderMain {
    public static void main(String[] args) throws IOException {
        URL url = new URL("http://127.0.0.1:13443/1.fxml");
        FXMLLoader.load(url);


        System.out.println("Hello world!");
    }
}