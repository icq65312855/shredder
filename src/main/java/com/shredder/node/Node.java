package com.shredder.node;

import com.shredder.edge.IEdge;

import java.util.HashSet;

public class Node implements INode {
    private String letters;
    private HashSet<IEdge> edges = new HashSet();
}
