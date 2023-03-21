package com.example.pokedexapp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.HashMap;

public class PokeDexAppController {
    private Stage stage;
    @FXML
    private TextField nameField;

    @FXML
    private String name;

    @FXML
    private Label testLabel;

    @FXML
    private Button showBtn;

    @FXML
    private Button pickBtn;

    @FXML
    private ImageView pokemon;

    @FXML
    private Image pokeImage;

    private HashMap<String, String> pokeNames = new HashMap<>();

    public PokeDexAppController() {
        pokeNames.put("bulbasaur", "C:\\Users\\redfl\\Desktop\\CIRDLES\\PokeDex\\PokeDex\\PokeDexApp\\PokeDex Resources\\bulbasaur.png");
        pokeNames.put("charmander", "C:\\Users\\redfl\\Desktop\\CIRDLES\\PokeDex\\PokeDex\\PokeDexApp\\PokeDex Resources\\charmander.png");
        pokeNames.put("squirtle", "C:\\Users\\redfl\\Desktop\\CIRDLES\\PokeDex\\PokeDex\\PokeDexApp\\PokeDex Resources\\squirtle.png");
        pokeNames.put("pikachu", "C:\\Users\\redfl\\Desktop\\CIRDLES\\PokeDex\\PokeDex\\PokeDexApp\\PokeDex Resources\\pikachu.png");

        stage = new Stage();
    }

    @FXML
    private void showPokemon() {
        name = nameField.getText();
        pokeImage = new Image(pokeNames.get(name));
        pokemon.setImage(pokeImage);
        testLabel.setText(name);
    }
    @FXML
    private void pickPokemon() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            testLabel.setText(removeExtension(file.getName()));
            pokeImage = new Image(file.getAbsolutePath());
            pokemon.setImage(pokeImage);
        }
        else {
            testLabel.setText("Nothing new was selected");
        }
    }

    private String removeExtension (String filename) { return filename.split("\\.")[0]; }
}