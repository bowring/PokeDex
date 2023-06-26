package com.example.pokedexapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PokeDexApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PokeDexApp.class.getResource("pokedex.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 950, 700);
        stage.setTitle("PokeDex");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}