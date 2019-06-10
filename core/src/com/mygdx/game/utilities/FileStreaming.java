package com.mygdx.game.utilities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DecimalFormat;

import static com.mygdx.game.com.mygdx.game.screens.Level.NUM_LEVELS;

public class FileStreaming {
    public static File file = new File("GameSave.txt");
    public static int stars[] = new int[NUM_LEVELS];
    public static int unlockedLevel = 1;

    public static void write() {
        try {
            FileWriter fileWriter = new FileWriter(file);
            //write stars
            for (int i = 0; i < NUM_LEVELS; i++) {
                fileWriter.write("stars" + new DecimalFormat("00").format(i + 1) + ": " + stars[i] + "\n");
            }
            //write unlockedLevel
            fileWriter.write("unlockedLevel: " + unlockedLevel);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void read() {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            //read stars
            for (int i = 0; i < NUM_LEVELS; i++) {
                line = br.readLine();
                stars[i] = Integer.parseInt(line.substring(9));
            }
            //read unlockedLevel
            line = br.readLine();
            unlockedLevel = Integer.parseInt(line.substring(15));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(stars[0]);
    }
}