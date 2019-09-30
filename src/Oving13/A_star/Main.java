package Oving13.A_star;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Kant {
    Kant neste;
    Node til;
    public Kant(Node n, Kant nst) {
        til = n;
        neste = nst;
    }
}

class VKant extends Kant {
    int vekt;
    public VKant(Node n, VKant nst, int vkt) {
        super(n, nst);
        vekt = vkt;
    }
}

class Node {
    double lat;
    double lng;
    int id;
    Kant kant1;
    Object nodeData;
}

class Graf {
    static int N, K;
    static Node[] node;
    static int sjekket = 0;

    static void astar(Node s, Node m, boolean dijkstra) {
        PriorityQueue<Node> priko = new PriorityQueue<>(N, (o1, o2) -> ((Forgj)o1.nodeData).prio - ((Forgj)o2.nodeData).prio);
        s.nodeData = new Forgj();
        ((Forgj)s.nodeData).dist = 0;
        priko.add(s);

        while(!priko.isEmpty()) {
            Node curr = priko.poll();
            sjekket++;
            if (curr == m) {
                skrivPath(m, s); // Vi har nådd målnoden og må jobbe oss tilbake til start
            }
            for (VKant k = (VKant)curr.kant1; k != null; k = (VKant)k.neste) {
                Node node = k.til;
                if (curr.nodeData != null && node.nodeData != null && ((Forgj)curr.nodeData).dist + k.vekt >= ((Forgj)node.nodeData).dist) continue; // Not a better match
                node.nodeData = new Forgj();
                ((Forgj)node.nodeData).forgj = curr;
                ((Forgj)node.nodeData).time = ((Forgj)curr.nodeData).time + k.vekt;
                ((Forgj)node.nodeData).dist = ((Forgj)curr.nodeData).dist + k.vekt;
                if(dijkstra){
                    ((Forgj)node.nodeData).prio = ((Forgj)curr.nodeData).dist + k.vekt;
                }else{
                    System.out.println(forventetAvstand(curr, m));
                    ((Forgj)node.nodeData).prio = ((Forgj)curr.nodeData).dist + k.vekt + (int)forventetAvstand(curr, m);
                }
                //((Forgj)node.nodeData).prio = ((Forgj)curr.nodeData).dist + k.vekt; //+ (!dijkstra ? (int)forventetAvstand(curr, m) : 0);
                priko.remove(node);
                priko.add(node);
            }
        }
    }

    public static double forventetAvstand(Node start, Node goal) {
        return (2 * 6371 * Math.asin(
                Math.sqrt(
                        Math.sin((Math.toRadians(start.lat)-Math.toRadians(goal.lat))/2) *
                                Math.sin((Math.toRadians(start.lat)-Math.toRadians(goal.lat))/2) +
                                Math.cos(Math.toRadians(start.lat)) *
                                        Math.cos(Math.toRadians(goal.lat)) *
                                        Math.sin((Math.toRadians(start.lng)-Math.toRadians(goal.lng))/2) *
                                        Math.sin((Math.toRadians(start.lng)-Math.toRadians(goal.lng))/2)
                )
        ));
            /*double sin_bredde = Math.sin((start.lat - goal.lat) / 2.0);
            double sin_lengde = Math.sin((start.lng - goal.lng) / 2.0);
            return (int) (41701090.90909090909090909091 * Math.asin(Math.sqrt(
                    sin_bredde * sin_bredde + Math.cos(start.lat) * Math.cos(goal.lat) * sin_lengde * sin_lengde)));*/
        }

    private static void skrivPath(Node n, Node s) {
        Node m = n;
        while(m != s) {
            m = ((Forgj)m.nodeData).forgj;
            System.out.println(m.lat + "," + m.lng); // Skriv ut alle noder baklengs
        }
        System.out.println();
        System.out.println(s.lat + "," + s.lng); // Skriv ut startnode
        System.out.println("Antall sjekkede noder: " + sjekket);
        System.out.println("Kjøretid: " + ((Forgj)n.nodeData).time + "ms");
    }

}

class Forgj {
    int dist;
    int prio;
    int time;
    Node forgj;
    static int uendelig = Integer.MAX_VALUE;
    public Forgj() {
        dist = uendelig;
    }
}


public class Main {
    public void vgraf(String nodePath, String edgePath) throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader(nodePath))) {
            for(String line; (line = br.readLine()) != null; ) {
                // process the line.
                String[] data = line.trim().split("\\s+");
                if (data.length == 1) {
                    Graf.N = Integer.parseInt(data[0]);
                    Graf.node = new Node[Graf.N];
                } else {
                    int id = Integer.parseInt(data[0]);
                    Graf.node[id] = new Node();
                    Graf.node[id].id = id;
                    Graf.node[id].lat = Double.parseDouble(data[1]);
                    Graf.node[id].lng = Double.parseDouble(data[2]);
                }
            }
        }

        try(BufferedReader br = new BufferedReader(new FileReader(edgePath))) {
            for(String line; (line = br.readLine()) != null; ) {
                // process the line.
                String[] data = line.trim().split("\\s+");
                if (data.length == 1) {
                    Graf.K = Integer.parseInt(data[0]);
                } else {
                    int fra = Integer.parseInt(data[0]);
                    int til = Integer.parseInt(data[1]);
                    int vekt = Integer.parseInt(data[2]);
                    VKant k = new VKant(Graf.node[til], (VKant)Graf.node[fra].kant1, vekt);
                    Graf.node[fra].kant1 = k;
                }
            }
        }
    }

    public static void main(String[] args) {
        Main m = new Main();
        String fileRoot = new File("").getAbsolutePath(); // Project root folder
        String nodePath = "C:\\Users\\Ådne\\Google Drive\\NTNU\\3. semester\\Algoritmer og datastrukturer\\Øvinger-algdat\\src\\A_star\\noder.txt"; // Add project relative path to file
        String edgePath = "C:\\Users\\Ådne\\Google Drive\\NTNU\\3. semester\\Algoritmer og datastrukturer\\Øvinger-algdat\\src\\A_star\\kanter.txt"; // Add project relative path to file

        try {
            m.vgraf(nodePath, edgePath);

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        Graf.astar(Graf.node[2822045], Graf.node[2806952], false); // Oslo til Trondheim
        //Graf.astar(Graf.node[2822045], Graf.node[2806952], true); // Oslo til Trondheim

        /*Date start = new Date();
        int runder = 0;
        double tid;
        Date slutt;
        do {
            slutt = new Date();
            ++runder;
        } while (slutt.getTime()-start.getTime() < 1000);
        tid = (double)
                (slutt.getTime()-start.getTime()) / runder;
        System.out.println("Millisekund pr. runde:" + tid);*/

        /*Date start1 = new Date();
        int runder1 = 0;
        double tid1;
        Date slutt1;
        do {
            Graf.astar(Graf.node[2552745], Graf.node[2574557], true); // Oslo til Bergen
            slutt1 = new Date();
            ++runder1;
        } while (slutt1.getTime()-start1.getTime() < 1000);
        tid1 = (double)
                (slutt1.getTime()-start1.getTime()) / runder1;
        System.out.println("Millisekund pr. runde:" + tid1);*/

        //Graf.astar(Graf.node[2418547], Graf.node[2633550], false); // Gjemnes til Kårvåg

        //Graf.astar(Graf.node[2058549], Graf.node[1051859], false); // Kristiansund til Helsinki
        //Graf.astar(Graf.node[2058549], Graf.node[1051859], true); // Samme med djikstra


        //Graf.astar(Graf.node[3237536], Graf.node[1881040], false); // Test


    }
}
