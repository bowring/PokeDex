module com.example.pokedexapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.pokedexapp to javafx.fxml;
    exports com.example.pokedexapp;
}