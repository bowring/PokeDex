package com.example.pokedexcore.utilities.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static com.example.pokedexcore.Pokemon.POKEDEX_RESOURCE_EXTRACTOR;
import static com.example.pokedexcore.constants.PokeDexConstants.*;
import static com.example.pokedexcore.utilities.file.GithubFileExtractor.extractGithubFile;
import static java.nio.charset.StandardCharsets.ISO_8859_1;

public enum PokeDexFileResources {
    ;

    public static void initLocalResources() throws IOException {

        if (POKEDEX_RESOURCES_FOLDER.exists()) {
            FileUtilities.recursiveDelete(POKEDEX_RESOURCES_FOLDER.toPath());
        }

        if(!POKEDEX_RESOURCES_FOLDER.mkdir()) {
            throw new IOException();
        }

        if(!CSV_FOLDER.mkdir()) {
            throw new IOException();
        }

        if(!IMAGE_FOLDER.mkdir()) {
            throw new IOException();
        }

        if(!POKEMON_SHAPES.mkdir()) {
            throw new IOException();
        }

        if(!POKEMON_SPRITES.mkdir()) {
            throw new IOException();
        }

        retrieveResourceFiles(CSV_FOLDER, "csvFiles");

        retrieveResourceFiles(POKEMON_SHAPES, "images/pokemonShapes");
        retrieveResourceFiles(POKEMON_SPRITES, "images/pokemonSprites");

    }

    public static void retrieveResourceFiles(File resourceTargetFolder, String resourceFolderName)
            throws IOException {

        Path listOfResourceFiles = POKEDEX_RESOURCE_EXTRACTOR.extractResourceAsPath(resourceFolderName + "/" + "_listOfResourceFiles.txt");
        if (resourceTargetFolder.exists()) {
            FileUtilities.recursiveDelete(resourceTargetFolder.toPath());
        }
        if (resourceTargetFolder.mkdir() && (null != listOfResourceFiles)) {
            List<String> fileNames = Files.readAllLines(listOfResourceFiles, ISO_8859_1);
            for (String name : fileNames) {
                if (0 < name.trim().length()) {
                    if (name.startsWith("https")) {
                        int fileNameIndex = name.lastIndexOf('/');
                        String fileName = name.substring(fileNameIndex + 1);
                        try {
                            extractGithubFile(
                                    name.trim(),
                                    resourceTargetFolder + "/" + fileName);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        File resourceFileName = POKEDEX_RESOURCE_EXTRACTOR.extractResourceAsFile(resourceFolderName + "/" + name);
                        File resourceLocalFileName = new File(resourceTargetFolder.getCanonicalPath() + "/" + name);
                        if (null != resourceFileName) {
                            boolean renameTo = resourceFileName.renameTo(resourceLocalFileName);
                            if (!renameTo) {
                                throw new IOException();
                            }
                        }
                    }
                }
            }
        }
    }
}
