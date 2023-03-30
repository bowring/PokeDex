module com.example.pokedexapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires PokeDex.PokeDexCore;


    opens com.example.pokedexapp to javafx.fxml;
    exports com.example.pokedexapp;
}