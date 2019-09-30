package Oving7_2;


import java.io.*;
import java.util.StringTokenizer;

public class Graf{
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
            System.out.println("fra "+ fra+"     til "+til+ "      node[til]"+ node[til].element+ "      node{fra]"+node[fra].kant1);
            Kant k = new Kant(node[til], node[fra].kant1);
            node[fra].kant1 = k;
        }
    }

    public void skrivUt(){
        for(int i=0;i<node.length;i++){
            if(node[i].kant1!= null) {
                System.out.println(node[i].kant1.til.element);
            }
        }
    }

    public String skrivUtFerdig(){
        String res ="";
        for (int i = 0; i < node.length; i++){
            res += "Node " + node[i] + " har ferdig tid: " + node[i].ferdig_tid + "\n";
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
        for(Kant k = n.kant1; k!= null; k= k.neste){
            Dfs_forgj md = (Dfs_forgj)k.til.d;
            if(md.funnet_tid == 0){
                md.forgj = n;
                md.dist = nd.dist + 1;
                df_sok(k.til);
            }
        }
        nd.ferdig_tid = Dfs_forgj.les_tid();
        nd.finn_forgj().ferdig_tid=Dfs_forgj.les_tid();
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
        //graf.skrivUt();
        Node n = graf.getNode(1);
        System.out.println(graf.skrivUtFerdig());
        graf.dfs(n);
        System.out.println(graf.skrivUtFerdig());
    }
}

class Kant{
    Kant neste;
    Node til;
    public Kant(Node n, Kant nst){
        til =n;
        neste =nst;
    }

    public String toString() {
       String res ="";
       while(neste != null){
           res += neste.til + "\n";
       }return res;
    }
}

class Node{
    Kant kant1;
    int ferdig_tid;
    int element;
    Node neste;
    Object d;
    public Node(int e){
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
        return "" + element;
    }
}

class Forgj{
    int dist;
    Node forgj;
    static int uendelig = 1000000000;
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