package com.example.pokedexcore;

import org.cirdles.commons.util.ResourceExtractor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Pokemon implements Comparable<Pokemon> {

    //Declaring the descriptive variables for the Pokemon object
    private final String NAME;
    private final String TYPE;
    private final String ABILITIES;
    private final double HEIGHT;
    private final double WEIGHT;
    private final String SHAPE;
    private final String COLOR;
    private final String DESCRIPTION;

    //Resource Extractor
    public static final ResourceExtractor POKEDEX_RESOURCE_EXTRACTOR = new ResourceExtractor(Pokemon.class);

    public Pokemon() {
        this("", "", "", 0.0, 0.0, "", "", "");
    }

    public Pokemon(String name, String type, String abilities, double height, double weight, String shape, String color, String description) {
        this.NAME = name;
        this.TYPE = type;
        this.ABILITIES = abilities;
        this.HEIGHT = height;
        this.WEIGHT = weight;
        this.SHAPE = shape;
        this.COLOR = color;
        this.DESCRIPTION = description;
    }

    //Method that writes the components of the object to a csv file
    public void serializeToCSV(Pokemon poke, String filename)
            throws IOException {

        //Creates a path to access the file
        Path pokedex = Paths.get(filename);

        //Writes the attributes of the different objects in the list to file
        Files.write(pokedex, poke.printToCSV().getBytes(), StandardOpenOption.APPEND);

    }

    //Counts the amount of lines in a file
    public static int countLines(String filename)
            throws IOException {

        Path pokedex = Paths.get(filename);

        long lines = Files.lines(pokedex).count();

        return (int) lines;
    }

    //Takes the data from filename and then applies it to another new object
    public Set<Pokemon> deserializePokemon(String filename) throws IOException {

        Set<Pokemon> pokeList = new HashSet<>();

        Path pokedex = Paths.get(filename);

        List<String> data = Files.readAllLines(pokedex);
        data.remove(0);

        String[] dataParts = {};

        for (String line : data) {
            dataParts = line.split(",");

            pokeList.add(new Pokemon(dataParts[0].trim(), //NAME
                                     dataParts[1].trim(), //TYPE
                                     dataParts[2].trim(), //ABILITIES
                                     Double.parseDouble(dataParts[3].trim()), //HEIGHT
                                     Double.parseDouble(dataParts[4].trim()), //WEIGHT
                                     dataParts[5].trim(), //SHAPE
                                     dataParts[6].trim(), //COLOR
                                     dataParts[7].trim())); //DESCRIPTION
        }

        return pokeList;
    }

    public String printToCSV() {
        return ("" + NAME + "," + TYPE + "," + ABILITIES + "," + HEIGHT + "," + WEIGHT + "," + SHAPE + "," + COLOR + "," + DESCRIPTION + "\n");
    }

    //Checks to see if the new Pokemon object is equal to the original one
    /*@Override
    public boolean equals(Object poke) {

        if (poke == this) {
            return true;
        }

        if (!(poke instanceof Pokemon)) {
            return false;
        }

        Pokemon other = (Pokemon) poke;

        boolean typeEquals = ((this.type == null) && (other.type == null)) || ((this.type != null) && (other.type.equals(this.type)));

        return ((this.level == other.level) && (this.height == other.height)) && ((this.weight == other.weight) && typeEquals);
    }

    @Override
    public int compareTo (Pokemon other) {

        boolean same =
                ((Double.compare(this.weight, other.weight)) == 0) &&
                        ((Double.compare(this.height, other.height)) == 0);

        boolean greater =
                ((Double.compare(this.weight, other.weight)) >= 0) ||
                        ((Double.compare(this.height, other.height)) >= 0);

        return same ? 0 : greater ? 1 : -1;
    }*/

    //Getters
    public String getNAME() {
        return NAME;
    }
    public String getTYPE() {
        return TYPE;
    }
    public double getHEIGHT() {
        return HEIGHT;
    }
    public double getWEIGHT() {
        return WEIGHT;
    }
    public String getSHAPE() {
        return SHAPE;
    }
    public String getCOLOR() {
        return COLOR;
    }
    public String getDESCRIPTION() {
        return DESCRIPTION;
    }


    @Override
    public int compareTo(Pokemon o) {
        return 0;
    }
}