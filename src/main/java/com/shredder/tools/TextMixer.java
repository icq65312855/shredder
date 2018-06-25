package com.shredder.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class TextMixer {

    public static void mixText(String inputfile, String outputfile) throws Exception {
    	if (inputfile.compareTo(outputfile) == 0) {
    		throw new Exception("Input & Output files must be different names!");
    	}
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            String line;
            int maxSize = 0;
            
            reader = new BufferedReader(new FileReader(inputfile));
            while ((line = reader.readLine()) != null) {
                int size = line.length();
                if (size > maxSize) {
                    maxSize = size;
                }
            }
            reader.close();
            int colCount = ((maxSize + 1) / 2);
            maxSize = colCount * 2;
            
            int[] newSort = new int[colCount];
            Random randomGenerator = new Random();
            for(int i=0; i < colCount; i++) {
            	int colPos = randomGenerator.nextInt(colCount);
            	while (newSort[colPos] > 0) {
            		colPos++;
            		if (colPos >= colCount) {
            			colPos = 0;
            		}
            	}
            	newSort[colPos] = i;
            }
            
            reader = new BufferedReader(new FileReader(inputfile));
            writer = new BufferedWriter(new FileWriter(outputfile));
            while ((line = reader.readLine()) != null) {
                line = String.format("%1$-" + maxSize + "s", line);
                String newLine = "";
                for (int col = 0; col < colCount; col++) {
                	int pos = 2 * newSort[col];  
                	newLine = newLine.concat(line.substring(pos, pos + 2));
                }
            	writer.write(newLine);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("ERROR mixText : " + inputfile + "-->" + outputfile + " exception: " + e);
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {   
            	if (null != writer) writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
