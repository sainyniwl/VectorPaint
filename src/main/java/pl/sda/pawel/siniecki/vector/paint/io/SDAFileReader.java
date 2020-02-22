package pl.sda.pawel.siniecki.vector.paint.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SDAFileReader {

    private File file;

    public SDAFileReader(File file) {
        this.file = file;
    }

    public List<String> readFile() {
        BufferedReader br = null;
        List<String> data = new ArrayList<>();

        try {
            System.out.println(file.getAbsolutePath());
            java.io.FileReader fileReader = new java.io.FileReader(file);
            br = new BufferedReader(fileReader);

            /* czytanie paczkami danych
            char[] buffer = new char[64];
            int isSucces = fileReader.read(buffer); */

            String nextLine = br.readLine();
            while (nextLine != null) {
                data.add(nextLine);
                nextLine = br.readLine();
            }
            System.out.println("Koniec pliku");
            System.out.println(data);
            br.close();
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }
}