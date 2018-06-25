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

        String fileName = "./src/main/resources/Big3_Shredded.txt";//args[0];
        String fileDictionary = "./src/main/resources/Dictionary.txt";//args[1];
        String fileStat = "./src/main/resources/ngrams2.txt";//args[2];

        if (args.length > 0) {
            fileName = args[0];
        }
        if (args.length > 1) {
            fileDictionary = args[1];
        }
        if (args.length > 2) {
            fileStat = args[2];
        }

        BaseGraph graph = new BaseGraph();

        GraphLoader.loadGraph(graph, fileName);

        DictionaryLoader.loadDictionary(DictionaryTrie.getInstance(), fileDictionary);

        StatLoader.loadStat(BaseStat.getInstance(), fileStat);

        INode doc = graph.restoreDocument();

        doc.print();

    }

}
