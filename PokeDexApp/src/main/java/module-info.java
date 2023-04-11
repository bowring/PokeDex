module com.example.pokedexapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires PokeDex.PokeDexCore;
    requires commons.bc38781605;


    opens com.example.pokedexapp to javafx.fxml;
    exports com.example.pokedexapp;
}