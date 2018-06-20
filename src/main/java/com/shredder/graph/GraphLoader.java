package com.shredder.graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GraphLoader {

    /**
     * load graph from file
     * @param graph
     * @param filename
     */
    public static void loadGraph(IGraph graph, String filename) {
        BufferedReader reader = null;
        try {
            String line;
            int maxSize = 0;
            
            reader = new BufferedReader(new FileReader(filename));
            while ((line = reader.readLine()) != null) {
                int size = line.length();
                if (size > maxSize) {
                    maxSize = size;
                }
            }
            reader.close();
            int colCount = ((maxSize + 1) / 2);
            maxSize = colCount * 2;
            
            reader = new BufferedReader(new FileReader(filename));
            while ((line = reader.readLine()) != null) {
                line = String.format("%1$-" + maxSize + "s", line);
                for (int col = 0; col < colCount; col++) {
                    graph.addNode(line.substring(2 * col, 2 * col + 2), col);
                }
            }
        } catch (IOException e) {
            System.err.println("ERROR loadGraph : " + filename + " exception: " + e);
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
