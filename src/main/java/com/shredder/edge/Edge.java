package com.shredder.edge;

import com.shredder.node.INode;

public class Edge implements IEdge, Comparable {
    private INode start;
    private INode end;
    private long volume;

    public Edge(INode start, INode end) {
        this.start = start;
        this.end = end;
    }

    public INode getStart() {
        return start;
    }

    public INode getEnd() {
        return end;
    }

    public String getBigram(int index) {

        INode firstCol = start.getLastNode();
        INode secondCol = end.getFirstNode();

        return Character.toString(firstCol.getLetter(index, 0))+ Character.toString(secondCol.getLetter(index, 1));
    }

    @Override
    public void setVolume(long volume) {
        this.volume = volume;
    }

    public long getVolume() {
        return volume;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Edge) {
            Edge e;
            e = (Edge) o;
            if (this.volume > e.volume) {
                return 1;
            } else if (this.volume < e.volume) {
                return -1;
            }
        }

        return 0;
    }
}
