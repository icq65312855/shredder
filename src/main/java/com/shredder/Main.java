package com.shredder;

import com.shredder.bigrams.BaseStat;
import com.shredder.bigrams.StatLoader;
import com.shredder.graph.BaseGraph;
import com.shredder.graph.GraphLoader;
import com.shredder.node.INode;
import com.shredder.spelling.DictionaryLoader;
import com.shredder.spelling.DictionaryTrie;

public class Main {

    public static void main(String[] args) {

        String fileName = "./src/main/resources/Big.txt";//args[0];
//        String fileName = "./src/main/resources/ShreddedDocument.txt";//args[0];
        String fileDictionary = "./src/main/resources/Dictionary.txt";//args[1];
        String fileStat = "./src/main/resources/ngrams2.txt";//args[1];


        BaseGraph graph = new BaseGraph();

        GraphLoader.loadGraph(graph, fileName);

//        System.out.println(graph.toString());

        DictionaryLoader.loadDictionary(DictionaryTrie.getInstance(), fileDictionary);

        StatLoader.loadStat(BaseStat.getInstance(), fileStat);

        INode doc = graph.restoreDocument();

        System.out.println(doc);

        doc.print();

    }

}
