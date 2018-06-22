package com.shredder.node;

import com.shredder.bigrams.BaseStat;
import com.shredder.edge.Edge;
import com.shredder.edge.IEdge;
import com.shredder.spelling.Document;

import java.util.*;

public class DocumentNode implements INode {

    private ArrayList<ColumnNode> nodes;

    private HashSet<IEdge> edges = new HashSet();

    private Document document = null;

    public DocumentNode(ArrayList<ColumnNode> nodes) {
        this.nodes = new ArrayList<>(nodes);
    }

    public DocumentNode(ColumnNode node) {
        this.nodes = new ArrayList<>();
        addColumn(node);
    }

    public void addColumn(ColumnNode node) {
        if (!this.nodes.contains(node)) {
            this.nodes.add(node);
        }
    }

    public void addEdge(DocumentNode node) {
        IEdge edge = new Edge(this, node);
        edges.add(edge);
    }

    public ArrayList<ColumnNode> getNodes() {
        return nodes;
    }

    @Override
    public ArrayList<IEdge> getSortedEdges() {
        List sortedEdges = new ArrayList<>(edges);

        for (IEdge edge : edges) {

            long stat = 0;
            int size = edge.getStart().size();

            for (int i = 0; i < size; i++) {

                String bigram = edge.getBigram(i);
                stat += BaseStat.getInstance().getStat(bigram);
            }

            edge.setVolume(stat);
        }

        Collections.sort(sortedEdges);
        Collections.reverse(sortedEdges);

        return new ArrayList<>(sortedEdges);
    }

    @Override
    public boolean isValid() {
        if (document == null) {
            document = new Document(this);
        }

        return document.isValid();
    }

    @Override
    public void print() {
        document.printDocument();
    }

    public boolean fillEdges(ArrayList<ColumnNode> columnNodes) {
        ArrayList<ColumnNode> columns = new ArrayList<>(columnNodes);

        boolean find = false;

        for (ColumnNode col : columns) {
            if (nodes.contains(col)) { //TODO: use hashset
                continue;
            }
            find = true;
            DocumentNode docNode = new DocumentNode(nodes);
            docNode.addColumn(col);

            edges.add(new Edge(this, docNode));
        }

        return find;
    }

    @Override
    public int size() {
        return nodes.size();
    }

    @Override
    public INode getFirstNode() {
        if (nodes.size() > 0) {
            return nodes.get(0);
        }

        return null;
    }

    @Override
    public INode getLastNode() {
        if (nodes.size() > 0) {
            return nodes.get(nodes.size()-1);
        }

        return null;
    }

    @Override
    public char getLetter(int vertPos, int horizPos) {
        return 0;
    }

    @Override
    public String toString() {

        String str = "";

        for (ColumnNode node : nodes) {
            str = str + node.toString()+ " \n";
        }

        return str;
    }
}
