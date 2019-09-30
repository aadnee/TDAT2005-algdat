package Oving5;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

public class Oppgave2 {
    private Integer[] table = new Integer[6250000];
    private int m = 6249949;
    private int crashes = 0;
    public Oppgave2(){

    }

    public void insertNumbers(int[] numbers){
        int index1 = 0;
        for (int i = 0; i < numbers.length; i++) {
            index1 = hash1(numbers[i]);
            if (table[index1] == null) table[index1] = numbers[i];
            else {
                crashes++;
                int index2 = hash2(numbers[i]);
                int j = 1;
                while (true) {
                    int newIndex = (index1 + (j*index2))%m;
                    if(table[newIndex] == null){
                        table[newIndex] = numbers[i];
                        break;
                    }
                    crashes++;
                    j++;
                }
            }
        }
        System.out.println("Success");
    }

    public int hash1(int k){
        return k%m;
    }

    public int hash2(int k){
        return (k%(m-1))+1;
    }

    public static void main(String[] args) {
        int[] values = new int[5000000];
        for (int i = 0; i < values.length;i++){
            values[i] =(int) (Math.random()*50000000);
        }

        HashMap<Integer, Integer> hashMap= new HashMap<Integer, Integer>();
        Oppgave2 hashTable = new Oppgave2();


        Date start = new Date();
        int runder = 0;
        double tid;
        Date slutt;
        do {
            hashTable.insertNumbers(values);
            slutt = new Date();
            ++runder;
        } while (slutt.getTime()-start.getTime() < 1000);
        tid = (double)
                (slutt.getTime()-start.getTime()) / runder;
        System.out.println("Millisekund pr. runde for min:" + tid);
        System.out.println(hashTable.crashes);
        System.out.println((double)hashTable.crashes/(double)5000000);



        /*Date start1 = new Date();
        int runder1 = 0;
        double tid1;
        Date slutt1;
        do {
            for (int i = 0; i< values.length; i++){
                hashMap.put(i,values[i]);
            }
            slutt1 = new Date();
            ++runder1;
        } while (slutt1.getTime()-start1.getTime() < 1000);
        tid1 = (double)
                (slutt1.getTime()-start1.getTime()) / runder1;
        System.out.println("Millisekund pr. runde for hashmap:" + tid1);*/
    }
}
