package pekan9;

import java.util.*;

public class TugasSearchingGraf {

    private final Map<String, List<String>> adjacencyList;

    public TugasSearchingGraf() {
        adjacencyList = new HashMap<>();
        addEdge("A", "B");
        addEdge("A", "C");
        addEdge("B", "D");
        addEdge("C", "D");
        addEdge("C", "E");
        addEdge("D", "F");
        addEdge("E", "H"); 
        addEdge("E", "F");
        addEdge("F", "G");
        addEdge("G", "H");
    }

    private void addEdge(String node1, String node2) {
        adjacencyList.putIfAbsent(node1, new ArrayList<>());
        adjacencyList.putIfAbsent(node2, new ArrayList<>());
        adjacencyList.get(node1).add(node2);
        adjacencyList.get(node2).add(node1);
    }

    public void search(String startNode, String goalNode) {
        System.out.println("Algoritma: DFS (Depth-First Search)");
        System.out.println();
        Stack<String> stack = new Stack<>();
        Set<String> visited = new HashSet<>();
        Map<String, String> parentMap = new HashMap<>();
        stack.push(startNode);
        parentMap.put(startNode, null);
        int step = 1;
        boolean found = false;
        while (!stack.isEmpty()) {
            String currentNode = stack.pop();
            if (visited.contains(currentNode)) {
                continue;
            }
            visited.add(currentNode);
            System.out.println("Langkah " + step++ + ": Kunjungi " + currentNode);
            if (currentNode.equals(goalNode)) {
                System.out.println();
                System.out.println("Tujuan " + goalNode + " ditemukan");
                found = true;
                break;
            }
            List<String> neighbors = adjacencyList.get(currentNode);
            for (String neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    stack.push(neighbor);
                    parentMap.put(neighbor, currentNode);
                }
            }
        }
        if (found) {
            System.out.println();
            printRoute(startNode, goalNode, parentMap);
        } else {
            System.out.println("\nNode tujuan tidak dapat ditemukan.");
        }
    }

    private void printRoute(String startNode, String goalNode, Map<String, String> parentMap) {
        LinkedList<String> route = new LinkedList<>();
        String currentNode = goalNode;
        while (currentNode != null) {
            route.addFirst(currentNode);
            currentNode = parentMap.get(currentNode);
        }
        System.out.print("Rute: ");
        System.out.println(String.join(" -> ", route));
    }

    public static void main(String[] args) {
        TugasSearchingGraf graphSearch = new TugasSearchingGraf();
        String namaMahasiswa = "Aufan Taufiqurrahman";
        String nimMahasiswa = "2411532011";
        String startNode = "A";
        String goalNode = "G";
        
        System.out.println("Nama: " + namaMahasiswa);
        System.out.println("NIM: " + nimMahasiswa);
        System.out.println();
        System.out.println("Node awal: " + startNode);
        System.out.println("Node tujuan: " + goalNode);
        System.out.println();
        
        graphSearch.search(startNode, goalNode);
    }
}