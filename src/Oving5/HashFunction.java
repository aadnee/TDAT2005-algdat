package Oving5;

import java.util.Arrays;

public class HashFunction {

    private Object[] table;
    private int m = 113;
    private int count = 0;
    private int crash = 0;

    public HashFunction(){}

    public int insertStrings(String[] strings){
        double n = 0;
        int crashpers = 0;
        count = strings.length;
        table = new Object[(int)(strings.length*1.25)];
        for (int i = 0; i<strings.length; i++){
            crashpers=0;
            int index = hash(calculateAscii(strings[i]), m);
            if (table[index] == null){
                table[index] = strings[i];
            }
            else if (table[index] instanceof Node){
                crash++;
                crashpers++;
                Node node = (Node)table[index];
                System.out.println(strings[i] + " kræsjet med " + node.getElement() + " under innsetting");
                while(node.getNext()!=null) {
                    node = node.getNext();
                    crash++;
                    crashpers++;
                    System.out.println(strings[i] + " kræsjet med " + node.getElement() + " under innsetting");
                }
                Node neste = new Node(strings[i], null);
                //count++;
                node.setNext(neste);
            }else if(table[index] instanceof String){
                System.out.println(strings[i] + " kræsjet med " + (String)table[index] + " under innsetting");
                crash++;
                crashpers++;
                Node ny= new Node(strings[i], null);
                table[index] = new Node((String)table[index],ny);
            }
            //System.out.println(crashpers + " kræsjet med: " + strings[i]);
        }
        return crash;
    }

    public int hash(int k, int m){
        /*if (k%m>table.length){
            return k%m%13;
        }else */
        return k%m;
    }

    public void print(){
        Node tempNode = null;
        for(int i = 0; i < table.length; i++) {
            if(table[i] != null) {
                String res = "";
                String divider = "---- ";
                if(table[i] instanceof String){
                    tempNode = new Node((String)table[i], null);
                }else if(table[i] instanceof Node) {
                    tempNode = (Node) table[i];
                }
                while(tempNode != null) {
                    res += divider + "" + tempNode.element + " ";
                    tempNode = tempNode.next;
                }
                System.out.println("| " + i + "\t|" + res);
            } else {
                System.out.println("| " + i + "\t|");
            }

        }
        System.out.println("Lastfaktor: " + findLoad());
        System.out.println("Totalt antall kræsj under innsetting: " + crash);
        double crashpers = ((double)(crash)/(double)(count));
        System.out.println("Gjennomsnittlig kræsj per pers: " + crashpers);
    }

    public String søk(String string){
        int index = hash(calculateAscii(string),m);
        if (table[index] != null){
            if(table[index] instanceof String && string.equals(table[index])){
                return string + " er med i faget.";
            }
            else if(table[index] instanceof Node){
                Node node = (Node)table[index];
                System.out.println(string + " kræsjet med " + node.getElement() + " under søk");
                if (node.getElement().equals(string)) return string + " er med i faget.";
                else {
                    while(node.getNext() != null){
                        node = node.getNext();
                        if (node.getElement().equals(string)) return string + " er med i faget.";
                        else System.out.println(string + " kræsjet med " + node.getElement() + " under søk");
                    }
                }
            }
        } return string + " er ikke med i faget";
    }

    public double findLoad(){
        return ((double)(count)/(double)(table.length));
    }


    public int calculateAscii(String string){
        int res = 0;
        for (int i = 0; i<string.length();i++){
            res += string.charAt(i)*(i+1)*(i+1);
        }
        return res;
    }

    public static void main(String[] args) {
        String[] names = {"Almås Annabelle Solem", "Andersson William", "Andersson Vegard", "Andresen Sebastian Martin", "Aune Jostein Johansen", "Axell Christian", "Daniel Larsen",
                "Bakhmadov Magomed Khatujevitsj", "Braathen Mathias", "Bui Aria Thuy Dung", "Bø Halvor Fladsrud", "Christiansen Herman", "Dahl Magnus", "Dalseth Christian Mathias", "Debik Karol Jerzy",
                "Elvemo Sebastian Aanesland", "Fossland Ole-Henrik", "Frantzen Odd-Erik", "Fridheim Marius", "Fylling Johan-Henrik", "Garmann Ingelin", "Gram Hans-Jeiger", "Habeeb Khaled Mohammad",
                "Halvarsson Kevin Mentzoni", "Haugum Terje", "Helgeland Kevin Andre", "Hestnes Håkon Dalen", "Hjelle Sara", "Holt Eyvind Nikolai", "Hynne Sigurd", "Iversen Anders Hallem",
                "Jacobsen Sigurd Lø¸ite", "Jacobsen William Chakroun", "Johansen Aleksander", "Johansen Kristaver Birkeland", "Johansen Eivind Alfsvåg", "Kampenhå y Kristian", "Knutsen Yair Day",
                "Knutsen Mathias", "Koteng Markus Thomassen", "Kristoffersen Jonas", "Larsen Knut Johan", "Larsen Albert Ohrem", "Larsson, Øivind Haugerø", "Lervik Eivind Hestnes",
                "Li Jingyi", "Lilleeng Simon", "Martinsen Magnus Revheim", "Martinsen Herman Ryen", "Mediå Fredrik", "Mellemseter Michael", "Midttun Jonathan", "Moan Martin Andreas", "Monsen Fredrik",
                "Nicolausson Sander", "Nordseth Morten Nyang", "Nygård Marius", "Nystuen Ådne", "Opsahl Elisabeth Marie", "Paulshus Sindre Haugland", "Rad Saadat Ilia", "Ramberg Håvard Hammer", "Roll Erling",
                "Rondestvedt Trond Jacob", "Røstgård Kim Richard", "Sandnes Martin", "Siiri Anette Olli", "Skaug Oscar Tideman Borgerud", "Stavseng Ådne Eide", "Strand Snorre Kristoffer", "Strø mhylden Ben Oscar",
                "Sundøy Erlend", "Søther Ane", "Sørlie Lars", "Teiler-Johnsen Mari", "Tengs Simen Sølevik", "Thomassen Sindre", "Thorkildsen Patrick", "Timdal Eivind Rui", "Tokvam Balder",
                "Tran Quan Nguyen", "Tverfjell Julie Isabelle Malmedal", "Ullah Mona", "Valen Ruben Solvang", "Valstadsve Øyvind", "Vatle Jan-Marius", "Vedøy Rune", "Vesterlid Ørjan Bostad",
                "Wangensteen Kim Anners", "Wichstrøm Brage", "Worren Magne", "Østtveit Bjørnar", "Øverland Sveinung", "Øvsthus Vebjørn Hansen", "Ådnanes Stian", "Aasvestad Jørgen"};

        HashFunction hashFunction = new HashFunction();
        double crash = hashFunction.insertStrings(names);
        System.out.println(crash);
        System.out.println(crash/names.length);
        hashFunction.print();
        System.out.println(hashFunction.søk("Worren Magne"));
        System.out.println(hashFunction.søk("Ullah Mona"));
        System.out.println(hashFunction.søk("Stavseng Ådne Eide"));
        System.out.println(hashFunction.søk("Stavseng Ådne Eid"));


    }

}
