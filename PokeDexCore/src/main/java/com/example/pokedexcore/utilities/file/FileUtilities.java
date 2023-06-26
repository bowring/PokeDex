package com.example.pokedexcore.utilities.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

/**
 * @author James F. Bowring
 */
public enum FileUtilities {
    ;

    public static void recursiveDelete(Path pathToBeDeleted)
            throws IOException {
        Files.walk(pathToBeDeleted)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
    }
}
