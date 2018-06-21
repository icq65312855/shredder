package com.shredder.bigrams;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class StatLoader {

    public static void loadStat(IStat stat, String filename) {
        BufferedReader reader = null;
        stat.resetStat();
        try {
            String line;
            int lineNo = 0;
            
            reader = new BufferedReader(new FileReader(filename));
            while ((line = reader.readLine()) != null) {
                lineNo++;
                String[] data = line.split(",");
                if (data[0].length() == 2) {
                    try {
                        long count = Long.parseLong(data[1]);
                        stat.addStat(data[0], count);
                    } catch (Exception e) {
                        System.err.println("ERROR loadStat : " + filename + " line = " + lineNo + " exception: " + e);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("ERROR loadStat : " + filename + " exception: " + e);
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        stat.loadingComplete();
    }
}
