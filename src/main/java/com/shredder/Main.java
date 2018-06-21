package com.shredder;

import com.shredder.graph.BaseGraph;
import com.shredder.graph.GraphLoader;
import com.shredder.spelling.DictionaryLoader;
import com.shredder.spelling.DictionaryTrie;

public class Main {

    public static void main(String[] args) {

        String fileName = "/cryptfs/workspace/testtasks/shredder/src/main/resources/ShreddedDocument.txt";//args[0];
        String fileDictionary = "/cryptfs/workspace/testtasks/shredder/src/main/resources/Dictionary.txt";//args[1];

        BaseGraph graph = new BaseGraph();

        GraphLoader.loadGraph(graph, fileName);

        System.out.println(graph.toString());

        DictionaryLoader.loadDictionary(DictionaryTrie.getInstance(), fileDictionary);

        graph.restoreDocument().print();

    }

}
