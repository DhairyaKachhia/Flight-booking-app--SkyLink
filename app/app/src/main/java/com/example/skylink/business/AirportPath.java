package com.example.skylink.business;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class AirportPath {
    private final Graph<String, DefaultWeightedEdge> airportGraph;

    public AirportPath() {
        airportGraph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
        createTable();
    }

    private void createTable() {
        addAirport("YYZ");
        addAirport("YUL");
        addAirport("YVR");
        addAirport("YYC");
        addAirport("YOW");

        addConnection("YYZ", "YUL", 541);
        addConnection("YYZ", "YVR", 3361);
        addConnection("YUL", "YVR", 3307);
        addConnection("YUL", "YYC", 2699);
        addConnection("YVR", "YYC", 971);
        addConnection("YYZ", "YOW", 269);
    }

    private void addAirport(String airport) {
        airportGraph.addVertex(airport);
    }

    private void addConnection(String source, String target, double weight) {
        DefaultWeightedEdge edge = airportGraph.addEdge(source, target);
        airportGraph.setEdgeWeight(edge, weight);
    }

    public List<List<String>> findAllPaths(String source, String destination) {
        List<List<String>> allPaths = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        List<String> path = new ArrayList<>();
        path.add(source);
        findAllPathsDFS(source, destination, visited, path, allPaths);
        return allPaths;
    }

    private void findAllPathsDFS(String current, String destination, Set<String> visited, List<String> path, List<List<String>> allPaths) {
        visited.add(current);

        if (current.equals(destination)) {
            allPaths.add(new ArrayList<>(path));
        } else {
            for (DefaultWeightedEdge edge : airportGraph.outgoingEdgesOf(current)) {
                String neighbor = airportGraph.getEdgeTarget(edge);
                if (!visited.contains(neighbor)) {
                    path.add(neighbor);
                    findAllPathsDFS(neighbor, destination, visited, path, allPaths);
                    path.remove(path.size() - 1);
                }
            }
        }

        visited.remove(current);
    }

    public double calculatePathDistance(List<String> path) {
        double distance = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            String source = path.get(i);
            String target = path.get(i + 1);
            DefaultWeightedEdge edge = airportGraph.getEdge(source, target);
            distance += airportGraph.getEdgeWeight(edge);
        }
        return distance;
    }
}
