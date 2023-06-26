package com.example.pokedexapp;

import com.example.pokedexcore.utilities.file.PokeDexFileResources;
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
import java.io.IOException;
import java.net.URL;
import java.util.*;

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
    private ImageView pokemonWindow;
    @FXML
    private Image pokeImage;

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Non-FXML Variables~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

    /* Sets */
    private Set<String> pokeFilenames = new HashSet<>();

    private Set<Pokemon> pokemon = new HashSet<>();

    @FXML
    public void initialize(URL location, ResourceBundle resources)  {
        Pokemon poke = new Pokemon();

        try {
            PokeDexFileResources.initLocalResources();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        pokemon = startPokeDex();

        for (Pokemon p : pokemon) {
            System.out.println(p.printToCSV());
        }

        initializePokemon();

        stage = new Stage();

    }

    /**
     * Adds the pre-made Pokemon
     */
    private void initializePokemon() {
        addPokemon("bulbasaur");
        addPokemon("charmander");
        addPokemon("squirtle");
        addPokemon("pikachu");
    }

    public Set<Pokemon> startPokeDex() {
        Pokemon poke = new Pokemon();
        Set<Pokemon> list;

        try {
            list = poke.deserializePokemon(fetchCSV("pokemonDatabase.csv").getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return list;
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
        if (filename.contains("body")) {
            return imgExtractor.extractResourceAsFile("/com/example/pokedexapp/images/pokemonShapes/" + filename);
        }
        else {
            return imgExtractor.extractResourceAsFile("/com/example/pokedexapp/images/pokemonSprites/" + filename);
        }
    }

    public File fetchCSV(String filename) {
        ResourceExtractor fileExtractor = new ResourceExtractor(PokeDexApp.class);
        return fileExtractor.extractResourceAsFile("/com/example/pokedexapp/csvFiles/" + filename);
    }

    /**
     * Event that shows the image and name of the Pokemon given the name from the Text Field
     */
    @FXML
    private void showPokemon() {
        String name = nameField.getText().toLowerCase();

        if (containsPokemon(pokemon, name)) {
            name += ".png";
            pokeImage = new Image(fetchImage(name).getAbsolutePath());
            testLabel.setText(createName(name));
        }
        else {
            pokeImage = null;
            testLabel.setText("No Pokemon Found With That Name");
        }

        pokemonWindow.setImage(pokeImage);

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
            pokemonWindow.setImage(pokeImage);
        }
        else {
            testLabel.setText("Nothing new was selected");
        }
    }

    public boolean containsPokemon(Set<Pokemon> pokemon, String name) {
        for (Pokemon p : pokemon) {
            if (p.getNAME().equals(name)) {
                return true;
            }
        }

        return false;
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