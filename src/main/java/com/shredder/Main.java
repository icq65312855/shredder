package com.shredder;

import com.shredder.graph.BaseGraph;
import com.shredder.graph.GraphLoader;
import com.shredder.spelling.BaseDictionary;
import com.shredder.spelling.DictionaryLoader;

public class Main {

    public static void main(String[] args) {

        String fileName = "ShreddedDocument.txt";//args[0];
        String fileDictionary = "Dictionary.txt";//args[1];

        BaseGraph graph = new BaseGraph();

        GraphLoader.loadGraph(graph, fileName);

        DictionaryLoader.loadDictionary(BaseDictionary.getInstance(), fileDictionary);

        graph.restoreDocument().print();

    }

}
