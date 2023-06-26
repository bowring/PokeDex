package com.example.pokedexcore.constants;

import java.io.File;

public enum PokeDexConstants {
    ;

    //Images
    public static final String BULBASAUR_IMAGE = "com/example/pokedexapp/images/pokemonSprites/bulbasaur.png";

    public static final String IVYSAUR_IMAGE = "com/example/pokedexapp/images/pokemonSprites/ivysaur.png";

    public static final String VENUSAUR_IMAGE = "com/example/pokedexapp/images/pokemonSprites/venusaur.png";

    //CSV Files
    public static final String POKEDEX_DATABASE = "com/example/pokedexapp/csvFiles/pokemonDatabase.csv";

    //Folders
    public static final File POKEDEX_RESOURCES_FOLDER = new File("PokeDexResource");

    public static final File CSV_FOLDER = new File(POKEDEX_RESOURCES_FOLDER.getAbsolutePath() + File.separator + "CSV_Files");

    public static final File IMAGE_FOLDER = new File(POKEDEX_RESOURCES_FOLDER.getAbsolutePath() + File.separator + "Images");

    public static final File POKEMON_SPRITES = new File(IMAGE_FOLDER.getAbsolutePath() + File.separator + "PokemonSprites");

    public static final File POKEMON_SHAPES = new File(IMAGE_FOLDER.getAbsolutePath() + File.separator + "PokemonShapes");

}
