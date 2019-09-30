package Oving7_2;

// Brukte Kosarajus algoritme for å finne sterkt sammenhengende komponenter
import java.io.*;
import java.util.*;
import java.util.LinkedList;

public class Graph
{
    private int V;   //Antall kanter
    private LinkedList<Integer> adj[]; //Adjacency List

    public Graph(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i=0; i<v; ++i)
            adj[i] = new LinkedList();
    }

    public void addEdge(int v, int w)  { adj[v].add(w); }

    // A recursive function to print DFS starting from v
    void DFSUtil(int v,boolean visited[])
    {
        // Mark the current node as visited and print it
        visited[v] = true;
        System.out.print(v + " ");

        int n;

        // Recur for all the vertices adjacent to this vertex
        Iterator<Integer> i =adj[v].iterator();
        while (i.hasNext())
        {
            n = i.next();
            if (!visited[n])
                DFSUtil(n,visited);
        }
    }

    // Traverserer grafen og returnerer den
    Graph getTranspose() {
        Graph g = new Graph(V);
        for (int v = 0; v < V; v++) {
            Iterator<Integer> i =adj[v].listIterator();
            while(i.hasNext())
                g.adj[i.next()].add(v);
        }
        return g;
    }

    void fillOrder(int v, boolean visited[], Stack stack) {
        visited[v] = true;

        Iterator<Integer> i = adj[v].iterator();
        while (i.hasNext()) {
            int n = i.next();
            if(!visited[n])
                fillOrder(n, visited, stack);
        }

        stack.push(new Integer(v));
    }

    // The main function that finds and prints all strongly
    // connected components
    void printSCCs()
    {
        Stack stack = new Stack();

        boolean visited[] = new boolean[V];
        for(int i = 0; i < V; i++)
            visited[i] = false;

        for (int i = 0; i < V; i++)
            if (visited[i] == false)
                fillOrder(i, visited, stack);

        Graph gr = getTranspose();

        for (int i = 0; i < V; i++)
            visited[i] = false;

        while (stack.empty() == false)
        {
            int v = (int)stack.pop();

            if (visited[v] == false)
            {
                gr.DFSUtil(v, visited);
                System.out.println();
            }
        }
    }

    // Driver method
    public static void main(String args[]) throws Exception {
        File file = new File("C:\\Users\\Ådne\\Google Drive\\NTNU\\3. semester\\Algoritmer og datastrukturer\\Øvinger-algdat\\src\\Oving7_2\\L7g1.txt");
        //File file = new File("C:\\Users\\Ådne\\Google Drive\\NTNU\\3. semester\\Algoritmer og datastrukturer\\Øvinger-algdat\\src\\Oving7_2\\L7g6.txt");
        //File file = new File("C:\\Users\\Ådne\\Google Drive\\NTNU\\3. semester\\Algoritmer og datastrukturer\\Øvinger-algdat\\src\\Oving7_2\\L7g2.txt");
        //File file = new File("C:\\Users\\Ådne\\Google Drive\\NTNU\\3. semester\\Algoritmer og datastrukturer\\Øvinger-algdat\\src\\Oving7_2\\L7g5.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        Graph graph = new Graph(N);
        int K = Integer.parseInt(st.nextToken());
        for (int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            graph.addEdge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        System.out.println("Sterke komponenter i denne grafen: ");
        graph.printSCCs();
    }
}
