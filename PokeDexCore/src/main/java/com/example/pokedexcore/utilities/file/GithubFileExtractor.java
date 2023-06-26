package com.example.pokedexcore.utilities.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public enum GithubFileExtractor {
    ;

    public static void extractGithubFile(String fileRawURI, String fileName) {
        java.net.URL url;

        try {
            url = new java.net.URL(fileRawURI);
            java.net.URLConnection uc;
            uc = url.openConnection();

            uc.setRequestProperty("X-Requested-With", "Curl");

            BufferedReader reader = new BufferedReader(new InputStreamReader(uc.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder fileContents = new StringBuilder();
            String line;
            while (null != (line = reader.readLine()))
                fileContents.append(line).append("\n");

            Path path = Paths.get(fileName);
            byte[] strToBytes = fileContents.toString().getBytes(StandardCharsets.UTF_8);

            Files.write(path, strToBytes);

        } catch (IOException e) {
            System.out.println("Could not read " + fileRawURI);
        }
    }
}
