
package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;

/**
 * An implementation of Graph.
 */
public class ConcreteEdgesGraph implements Graph<String> {
    
    private final Set<String> vertices = new HashSet<>();
    private final List<Edge> edges = new ArrayList<>();
    
    // Abstraction function:
    //   Represents a directed graph where each edge has a source, target, and weight.
    
    // Representation invariant:
    //   - Each edge's source and target are in the vertices set.
    //   - No two edges have the same source and target.
    
    // Safety from rep exposure:
    //   vertices and edges are private final fields; we return copies of mutable collections.
    
    public ConcreteEdgesGraph() {
        checkRep();
    }
    
    private void checkRep() {
        for (Edge edge : edges) {
            assert vertices.contains(edge.getSource()) && vertices.contains(edge.getTarget()) 
                   : "Edge vertices must exist in the vertices set";
        }
    }
    
    @Override
    public boolean add(String vertex) {
        if (vertex == null) throw new IllegalArgumentException("Vertex cannot be null");
        boolean added = vertices.add(vertex);
        checkRep();
        return added;
    }
    
    @Override
    public int set(String source, String target, int weight) {
        if (source == null || target == null) throw new IllegalArgumentException("Vertices cannot be null");

        int prevWeight = 0;
        for (Edge edge : edges) {
            if (edge.getSource().equals(source) && edge.getTarget().equals(target)) {
                prevWeight = edge.getWeight();
                edges.remove(edge);
                break;
            }
        }

        if (weight > 0) {
            edges.add(new Edge(source, target, weight));
            vertices.add(source);
            vertices.add(target);
        }
        checkRep();
        return prevWeight;
    }
    
    @Override
    public boolean remove(String vertex) {
        boolean removed = vertices.remove(vertex);
        edges.removeIf(edge -> edge.getSource().equals(vertex) || edge.getTarget().equals(vertex));
        checkRep();
        return removed;
    }
    
    @Override
    public Set<String> vertices() {
        return new HashSet<>(vertices);
    }
    
    @Override
    public Map<String, Integer> sources(String target) {
        Map<String, Integer> sources = new HashMap<>();
        for (Edge edge : edges) {
            if (edge.getTarget().equals(target)) {
                sources.put(edge.getSource(), edge.getWeight());
            }
        }
        return sources;
    }
    
    @Override
    public Map<String, Integer> targets(String source) {
        Map<String, Integer> targets = new HashMap<>();
        for (Edge edge : edges) {
            if (edge.getSource().equals(source)) {
                targets.put(edge.getTarget(), edge.getWeight());
            }
        }
        return targets;
    }
    
    @Override
    public String toString() {
        return "ConcreteEdgesGraph(vertices=" + vertices + ", edges=" + edges + ")";
    }
}

/**
 * Immutable class representing a directed, weighted edge in a graph.
 */
class Edge {
    private final String source;
    private final String target;
    private final int weight;

    // Abstraction Function:
    //   Represents a directed, weighted edge from source to target with a specified weight.

    // Representation Invariant:
    //   weight >= 0
    //   source and target are not null

    // Safety from Rep Exposure:
    //   All fields are private and final. Edge is immutable.

    public Edge(String source, String target, int weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
        checkRep();
    }

    private void checkRep() {
        assert source != null && target != null : "Source and target must not be null";
        assert weight >= 0 : "Weight must be non-negative";
    }

    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return String.format("Edge(%s -> %s, weight=%d)", source, target, weight);
    }
}