package com.example.pokedexapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PokeDexAppController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}