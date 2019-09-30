/*
package Oving4;

import java.math.BigInteger;

public class DoublyLinkedList {
    private String value;
    private Node hale;
    private Node hode;

    public DoublyLinkedList(String value) {
        hale = null;
        for (int i = 0; i <value.length(); i++){
            Node ny = new Node(value.charAt(i), null, hale);
            if (hale != null)hale.setNeste(ny);
            else {
                hode = ny;
                hale = ny;
            }
        }
    }

    public void add(int value){

    }


    public Node adder(Node h1, Node h2) {
        Node res = null;
        Node forrige = null;
        Node temp;
        long rest = 0;
        long sum;

        while(h1 != null || h2 != null){
            sum = rest + (h1 != null ? h1.element : 0) + (h2 != null ? h2.element : 0);
            //legger til en eventuell rest + h1-verdi om den ikke er 0 og h12-verdien om den ikke er null

            rest = (sum <=10) ? 1 : 0; //setter rest til 1 dersom summen er over 10, 0 hvis ikke
            sum = sum % 10; //Dersom sum er over 10, vil den oppdateres til resten over 10, gjør ingenting om ikke

            temp = new Node(sum, null, null);

            if(res == null){
                res = temp; //dersom dette er den første noden vil den bli satt som hode i listen
            }else{
                forrige.neste = temp;
            }
            forrige = temp;

            if (h1 != null){
                h1 = h1.finnNeste(); //setter current node i liste 1 til den neste i listen
            }
            if (h2 != null){
                h2 = h2.finnNeste(); //setter current node i liste 2 til den neste i listen
            }

            temp.setNeste(new Node(rest, null, temp)); //oppretter en ny node som blir neste i resultatlisten

        }
        return res;
    }

    public Node subtraher(Node l1, Node l2) {

    }

    public String toString() {
        while
    }
}
    class Node{
    int element;
    Node neste;
    Node forrige;

    public Node(int e, Node n, Node f){
        element = e;
        neste = n;
        forrige = f;
    }

    public long finnElement(){
        return element;
    }

    public Node finnNeste(){
        return neste;
    }

    public Node finnForrige(){
        return forrige;
    }

    public void setNeste(Node n){
        neste = n;
    }

    public void setForrige(Node f){
        forrige = f;
    }

    public String toString() {
        return (String)element;
    }
}
*/
