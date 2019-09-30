package Oving7test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Graf1{
    int N,K;
    Node[] node;
    static int uendelig = 1000000000;

    public void ny_ugraf(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        node = new Node[N];
        for(int i=0;i<N;i++){
            node[i] = new Node(i,null);
        }
        K = Integer.parseInt(st.nextToken());
        for(int i=0;i<K;i++){
            st = new StringTokenizer((br.readLine()));
            int fra = Integer.parseInt(st.nextToken());
            int til = Integer.parseInt(st.nextToken());
            node[fra].leggTilKant(node[til]);
        }
    }

    public void skrivUt(){
        String skriv ="";
        for(int i=0;i<node.length;i++){
            if(node[i]!= null){
                skriv+=node[i].skrivUt() + "\n";
            }
        }
        System.out.println(skriv);
    }

    public void initforgj(Node s){
        for(int i=0;i<node.length;i++){
            node[i].ferdigTid=uendelig;
        }
    }

    public void dybdeFørst(Node startNode, int startTid){
        int startNr = 0;
        startNode.ferdigTid=startTid;
        Node neste = getNeste(startNode,startNr);
        while(neste.hjørner.size() > 0){
            if(neste.hjørner.get(startNr).ferdigTid != uendelig){
                break;
            }
            neste = getNeste(neste,startNr);
            System.out.println(neste.nodeNr);
        }
    }

    /*public void dybdeEkta(){
        for(int i=0;i<node.length;i++){
            initforgj(node[i]);
        }
        for(int i=0;i<node.length;i++){
            for(int i=0;i<node[i].)
        }
    }*/

    public Node gåTilbake(Node startNode){
        return startNode.forrige;
    }



    public Node getNeste(Node startNode, int nr){
        Node neste = startNode.hjørner.get(nr);
        neste.forrige = startNode;
        neste.ferdigTid = startNode.ferdigTid+1;
        return neste;
    }

     /*if(neste.ferdigTid!=uendelig){
                for(int i=1;i<neste.hjørner.size();i++){
                    if(neste.ferdigTid == uendelig){
                        neste = getNeste(neste,i);
                    } else if(i==(neste.hjørner.size()-1)&& neste.ferdigTid != uendelig){
                        neste = getNeste(neste.forrige,i);
                    }
                }
            }*/

    public void skrivFerdigTid(){
        String tekst ="";
        for(int i=0;i<node.length;i++){
            tekst+=i+" har tid "+node[i].ferdigTid+"\n";
        }
        System.out.println(tekst);
    }

    public Node getNode(int nr){
        return node[nr];
    }

    public static void main(String[] args)throws Exception{
        Graf1 g = new Graf1();
        File file = new File("C:\\Users\\Ådne\\Google Drive\\NTNU\\3. semester\\Algoritmer og datastrukturer\\Øvinger-algdat\\src\\Oving7test\\L7g6.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        g.ny_ugraf(br);
        Node n = g.getNode(1);
        g.skrivUt();
        g.dybdeFørst(n,0);
        g.skrivFerdigTid();
    }
}

class Node{
    int nodeNr;
    int ferdigTid;
    Node forrige;
    ArrayList<Node> hjørner = new ArrayList<>();

    public Node(int nodeNr,Node forrige){
        this.nodeNr=nodeNr;
    }

    public int getFerdigTid(){
        return ferdigTid;
    }
    public int getNodeNr(){
        return nodeNr;
    }

    public void leggTilKant(Node tilNode){
        hjørner.add(tilNode);
    }

    public String skrivUt(){
        String skriv = "";
        skriv+= nodeNr+ "-->";
        for(int i=0;i<hjørner.size();i++){
            if(i<(hjørner.size()-1)) {
                skriv += hjørner.get(i).nodeNr + "-->";
            } else {
                skriv += hjørner.get(i).nodeNr;
            }
        }
        return skriv;
    }
}



class Forgj{
    int dist;
    Node forgj;
    static int uendelig = 1000000000;
    public int finnDist(){ return dist;}
    public Node finn_forgj(){ return forgj;}
    public Forgj(){
        dist = uendelig;
    }
}