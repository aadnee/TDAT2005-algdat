package Oving5;

public class Node {
    public Node next;
    public String element;

    public Node(String element, Node next){
        this.element = element;
        this.next = next;
    }

    public String getElement(){
        return element;
    }

    public Node getNext(){
        return next;
    }

    public void setNext(Node node){
        next = node;
    }

    public String toString() {
        /*String res = "";
        while (next != null){
            res += " --> "+ element;
            next = getNext();
        }return res;*/
        return element;
    }
}
