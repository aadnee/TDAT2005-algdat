package Oving7;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

import static Oving7.Forgj.uendelig;

class Graf{
    int N;
    int K;
    Node[] node;

    public Node getNode(int nr){
        return node[nr];
    }
    public void ny_ugraf(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        node = new Node[N];
        for(int i=0;i<N;i++){
            node[i] = new Node(i);
        }
        K = Integer.parseInt(st.nextToken());
        for(int i=0;i<K;i++){
            st = new StringTokenizer((br.readLine()));
            int fra = Integer.parseInt(st.nextToken());
            int til = Integer.parseInt(st.nextToken());
            System.out.println("fra "+ fra+"     til "+til+ "      node[til]"+ node[til].element+ "      node{fra]"+node[fra]);
            node[fra].tilNoder.add(node[til]);
            //Kant k = new Kant(node[til], node[fra].kant1);
            //node[fra].kant1 = k;
        }
    }

    public String skrivFerdigTid(){
        String res= "";
        for (int i = 0; i < node.length; i++){
            if(node[i].ferdig_tid != uendelig)
            res += "Node " + node[i] + " har ferdig tid: " + node[i].ferdig_tid + "\n";
        }return res;
    }

    public String skrivUt(){
        String res ="";
        for(int i=0;i<node.length;i++){
            res += node[i];
            if(node[i] != null){
                for (int j = 0; j< node[i].tilNoder.size(); j++){
                    res += " ---> " + node[i].tilNoder.get(j);
                }
            }res += "\n";
        }
        return res;
    }
    public void initforgj(Node s){
        for(int i=N;i-->0;){
            node[i].d = new Forgj();
        }
        ((Forgj)s.d).dist=0;
    }
    public void dfs_init(){
        for(int i=N;i-->0;){
            node[i].d = new Dfs_forgj();
        }
        Dfs_forgj.null_tid();
    }

    public void df_sok(Node n){
        Dfs_forgj nd = (Dfs_forgj)n.d;
        nd.funnet_tid = Dfs_forgj.les_tid();
        //if(n.tilNoder.size() != 0){
        for (int i = 0; i < n.tilNoder.size(); i++){
            Dfs_forgj md = (Dfs_forgj)n.tilNoder.get(i).d;
            if(md.funnet_tid == 0){
                md.forgj = n;
                //if(n.tilNoder.get(i).tilNoder.size() > i && ((Dfs_forgj) n.tilNoder.get(i).tilNoder.get(i).d).dist == uendelig)df_sok(n.tilNoder.get(i));
                if(((Dfs_forgj) n.tilNoder.get(i).d).dist == uendelig){
                    md.dist = nd.dist + 1;
                    df_sok(n.tilNoder.get(i));
                }
                else{
                    int j=1;
                    //while((i+j) < n.tilNoder.get(i).tilNoder.size() && ((Dfs_forgj)n.tilNoder.get(i).tilNoder.get(i+j).d).dist == uendelig){
                    while((i+j) < n.tilNoder.size() && ((Dfs_forgj)n.tilNoder.get(i+j).d).dist == uendelig){
                        df_sok(n.tilNoder.get(i+j));
                        j++;
                    }
                }
                md.dist = nd.dist + 1;
                //if(((Dfs_forgj) n.tilNoder.get(i).d).dist == uendelig) df_sok(n.tilNoder.get(i));
                //System.out.println("ferdigTid for node " + n.element + ": " + md.ferdig_tid);
                //System.out.println("Ferdig tid for node " + n.element + ": " + md.funnet_tid);
            }
        }
        nd.ferdig_tid = Dfs_forgj.les_tid();
        //nd.finn_forgj().ferdig_tid=Dfs_forgj.les_tid();
        nd.finn_forgj().ferdig_tid=nd.les_tid();
    }

    public void dfs(Node s){
        dfs_init();
        ((Dfs_forgj)s.d).dist=0;
        df_sok(s);
    }
    public static void main(String[] args)throws Exception{
        Graf graf = new Graf();
        File file = new File("C:\\Users\\Ådne\\Google Drive\\NTNU\\3. semester\\Algoritmer og datastrukturer\\Øvinger-algdat\\src\\Oving7\\L7g6.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        graf.ny_ugraf(br);
        System.out.println(graf.skrivUt());
        Node n = graf.getNode(1);
        graf.dfs_init();
        //System.out.println(graf.skrivFerdigTid());
        graf.dfs(n);
        System.out.println(graf.skrivFerdigTid());
        //System.out.println(Arrays.toString(graf.node));

    }
}

class Kant{
    Kant neste;
    Node til;
    public Kant(Node n, Kant nst){
        til =n;
        neste =nst;
    }


}

class Node{
    ArrayList<Node> tilNoder;
    int ferdig_tid;
    int element;
    Node neste;
    Object d;
    public Node(int e){
        tilNoder = new ArrayList<Node>();
        element=e;
    }

    public int getFerdig_tid(){
        return ferdig_tid;
    }
    public double finnElement(){
        return element;
    }

    public Node finnNeste(){
        return neste;
    }
    public String toString(){
        return ""+element;
    }
}

class Forgj{
    int dist;
    Node forgj;
    static int uendelig = -1;
    public int finn_dist(){
        return dist;
    }
    public Node finn_forgj(){
        return forgj;
    }
    public Forgj(){
        dist =uendelig;
    }

    public String toString(){
        return "Distanse: " + dist + " - Forgjenger: " + forgj;
    }

}

class Dfs_forgj extends Forgj{
    int funnet_tid, ferdig_tid;
    static int tid;
    static void null_tid(){ tid=0;}
    static int les_tid(){ return tid++;}
    public String toString(){return super.toString() + " - Funnet tid: " + funnet_tid + " - Ferdig tid: " + ferdig_tid;}
}
