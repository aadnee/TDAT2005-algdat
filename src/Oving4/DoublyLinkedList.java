package Oving4;

import java.math.BigInteger;

public class DoublyLinkedList {
    private String value;
    private Node hale;
    private Node hode;
    private int antElementer;

    public DoublyLinkedList() {
        /*hale = null;
        for (int i = 0; i <value.length(); i++){
            addFirst(Integer.parseInt(String.valueOf(value.charAt(i))));
        }*/
    }

    public void addValue(String value){
        hale = null;
        for (int i = 0; i <value.length(); i++){
            addFirst(Integer.parseInt(String.valueOf(value.charAt(i))));
        }
    }

    public void addLast(int value){
        Node ny = new Node(value, null, hale);
        if(hale!= null) hale.setNeste(ny);
        else hode = ny;
        hale = ny;
        antElementer++;
    }

    public void addLast(Node node){
        if(hale!=null) hale.setNeste(node);
        else hode = node;
        hale =node;
        antElementer++;
    }

    public void addFirst(int value){
        Node tmp = new Node(value, hode, null);
        if(hode != null) hode.forrige = tmp;
        hode = tmp;
        if(hale == null) hale = tmp;
        antElementer++;
        System.out.println("Adding: " + value);
    }

    public void addFirst(Node node){
        if(hode != null) hode.forrige = node;
        hode = node;
        if(hale == null) hale = node;
        antElementer++;
    }

    public Node getHead(){
        if (hode == null) return null;
        return hode;
    }

    public String adder(DoublyLinkedList list2) {
        Node h1 = hode;
        Node h2 = list2.getHead();
        DoublyLinkedList resList = new DoublyLinkedList();
        String res = "";
        Node forrige = null;
        Node temp;
        int rest = 0;
        int sum;

        while(h1 != null || h2 != null){
            sum = rest + (h1 != null ? h1.element : 0) + (h2 != null ? h2.element : 0);
            //legger til en eventuell rest + h1-verdi om den ikke er 0 og h12-verdien om den ikke er null

            rest = (sum >=10) ? 1 : 0; //setter rest til 1 dersom summen er over 10, 0 hvis ikke
            sum = sum % 10; //Dersom sum er over 10, vil den oppdateres til resten over 10, gjør ingenting om ikke

            temp = new Node(sum, null, null);

            if(resList.getHead() == null){
                resList.addFirst(temp); //dersom dette er den første noden vil den bli satt som hode i listen
            }else if(forrige != null){
                forrige.setNeste(temp);
                temp.setForrige(forrige);
            }
            forrige = temp;

            if (h1 != null){
                h1 = h1.finnNeste(); //setter current node i liste 1 til den neste i listen
            }else h1 = null;
            if (h2 != null){
                h2 = h2.finnNeste(); //setter current node i liste 2 til den neste i listen
            }else h2 = null;

            if (resList.getHead()==null) resList.addFirst(temp);
            else resList.addLast(temp);
            //temp.setNeste(new Node(rest, null, temp)); //oppretter en ny node som blir neste i resultatlisten
            //resList.addLast(temp);
        }
        if (rest != 0){
            resList.addLast(rest);
        }
        return resList.toString();
    }

    public String subtraher(DoublyLinkedList list2) {
        Node h1 = hode;
        Node h2 = list2.getHead();
        Node temp;
        Node forrige = null;
        boolean flipped = false;
        int carry = 0;
        int sum;
        DoublyLinkedList resList = new DoublyLinkedList();


        if ((!this.toString().equals("") && !list2.toString().equals(""))
                && (Integer.parseInt(this.toString()) < Integer.parseInt(list2.toString()))){
            h1 = list2.hode;
            h2 = hode;
            flipped = true;
        }
        while(h1 != null || h2 != null){
            sum = carry + (h1 != null ? h1.element : 0) - (h2 != null ? h2.element : 0);

            if(sum < 0){
                sum += 10;
                carry = -1;
            }else carry = 0;

            temp = new Node(sum, null, null);

            if(resList.getHead() == null){
                resList.addFirst(temp);
            }else if(forrige != null){
                forrige.setNeste(temp);
                temp.setForrige(forrige);
            }
            forrige = temp;

            if (h1 != null){
                h1 = h1.finnNeste();
            }else h1 = null;
            if (h2 != null){
                h2 = h2.finnNeste();
            }else h2 = null;

            if (resList.getHead()==null) resList.addFirst(temp);
            else resList.addLast(temp);
        }
        while(resList.hale.getIntValue() == 0){
            resList.hale = resList.hale.forrige;
        }
        if (flipped) return "-"+resList.toString();
        return resList.toString();
    }

    public void setHode(Node node){
        hode = node;
    }

    public String toString() {
        String res = "";
        Node tail = hale;
        while (tail != null){
            res += tail.getValue();
            tail = tail.forrige;
        }
        return res;
    }

    public static void main(String[] args) {
        DoublyLinkedList liste1 = new DoublyLinkedList();
        DoublyLinkedList liste2 = new DoublyLinkedList();
        DoublyLinkedList liste3 = new DoublyLinkedList();
        DoublyLinkedList liste4 = new DoublyLinkedList();
        liste1.addValue("99999999999999999");
        liste2.addValue("1");
        liste3.addValue("1000000000");
        liste4.addValue("1");
        System.out.println(liste1);
        System.out.println(liste2);
        System.out.println("Res: " + liste1.adder(liste2));
        System.out.println(liste3);
        System.out.println(liste4);
        System.out.println("Res: " + liste3.subtraher(liste4));


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
    public int getIntValue(){
        return element;
    }

    public String getValue(){
        return ""+element;
    }

    public String toString() {
        return " Verdi: " + element;
    }
}
