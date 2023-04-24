package com.example.pokedexapp;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import com.example.pokedexcore.Pokemon;

import org.cirdles.commons.util.ResourceExtractor;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class PokeDexAppController implements Initializable {

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
    private Set<String> pokeFilenames = new HashSet<>();

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        Pokemon myPoke = new Pokemon();

        initializePokemon();

        stage = new Stage();
    }

    private void initializePokemon() {
        addPokemon("bulbasaur");
        addPokemon("charmander");
        addPokemon("squirtle");
        addPokemon("pikachu");
    }

    /**
     * Adds Pokemon to the pokeFilenames Set
     * @param pokemon name of wanted Pokemon
     */
    public void addPokemon(String pokemon) {
        pokeFilenames.add(pokemon + ".png");
    }

    /**
     * Fetches the resources for an image
     * @param filename name of the targeted file
     * @return the targeted resources
     */
    private File fetchImage(String filename) {
        ResourceExtractor imgExtractor = new ResourceExtractor(PokeDexApp.class);
        return imgExtractor.extractResourceAsFile("/com/example/pokedexapp/images/pokemonSprites/" + filename);
    }

    /**
     * Event that shows the image and name of the Pokemon given the name from the Text Field
     */
    @FXML
    private void showPokemon() {
        name = nameField.getText().toLowerCase() + ".png";

        if (pokeFilenames.contains(name)) {
            pokeImage = new Image(fetchImage(name).getAbsolutePath());
            testLabel.setText(createName(name));
        }
        else {
            pokeImage = null;
            testLabel.setText("No Pokemon Found With That Name");
        }

        pokemon.setImage(pokeImage);

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