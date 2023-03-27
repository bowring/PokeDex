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

    /* Stages */
    private Stage stage;

    /* Text Fields */
    @FXML
    private TextField nameField;

    /* Labels */
    @FXML
    private Label testLabel;

    /* Buttons */
    @FXML
    private Button showBtn;
    @FXML
    private Button pickBtn;

    /* Images and ImageViews */
    @FXML
    private ImageView pokemon;
    @FXML
    private Image pokeImage;

    /* Strings */
    @FXML
    private String name;

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Non-FXML Variables~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

    /* Hashmaps */
    private HashMap<String, String> pokeNames = new HashMap<>();

    public PokeDexAppController() {
        pokeNames.put("bulbasaur", "C:\\Users\\redfl\\Desktop\\CIRDLES\\PokeDex\\PokeDex\\PokeDexApp\\PokeDex Resources\\Images\\Pokemon\\bulbasaur.png");
        pokeNames.put("charmander", "C:\\Users\\redfl\\Desktop\\CIRDLES\\PokeDex\\PokeDex\\PokeDexApp\\PokeDex Resources\\Images\\Pokemon\\charmander.png");
        pokeNames.put("squirtle", "C:\\Users\\redfl\\Desktop\\CIRDLES\\PokeDex\\PokeDex\\PokeDexApp\\PokeDex Resources\\Images\\Pokemon\\squirtle.png");
        pokeNames.put("pikachu", "C:\\Users\\redfl\\Desktop\\CIRDLES\\PokeDex\\PokeDex\\PokeDexApp\\PokeDex Resources\\Images\\Pokemon\\pikachu.png");

        stage = new Stage();
    }

    /**
     * Event that shows the image and name of the Pokemon given the name from the Text Field
     */
    @FXML
    private void showPokemon() {
        name = nameField.getText().toLowerCase();
        pokeImage = new Image(pokeNames.get(name));
        pokemon.setImage(pokeImage);
        testLabel.setText(createName(name));
    }

    /**
     * Event that shows the image and name of the Pokemon given the location from the File Chooser
     */
    @FXML
    private void pickPokemon() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            testLabel.setText(createName(file.getName()));
            pokeImage = new Image(file.getAbsolutePath());
            pokemon.setImage(pokeImage);
        }
        else {
            testLabel.setText("Nothing new was selected");
        }
    }

    /**
     * Corrects the format of the name (capitalization, extensions, etc.)
     * @param filename
     * @return Corrected name as a String
     */
    private String createName (String filename) {
        String name;
        if (filename.contains(".")) {
            name = removeExtension(filename);
        }
        else {
            name = filename;
        }
        return name.substring(0,1).toUpperCase() + name.substring(1);
    }

    /**
     * Removes the file extension from a file's name
     * @param filename
     * @return The title of the file without the extension
     */
    private String removeExtension (String filename) {
        return filename.split("\\.")[0];
    }
}