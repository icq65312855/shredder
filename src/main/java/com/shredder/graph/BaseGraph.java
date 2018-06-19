package com.shredder.graph;

import com.shredder.node.ColumnNode;
import com.shredder.node.ContainerNode;

import java.util.ArrayList;

public class BaseGraph {
    ArrayList<ColumnNode> columns;

    public void addNode(ColumnNode node) {
        columns.add(node);
    }

    public void build() {

        for (ColumnNode colNode : columns) {



        }

    }
}
