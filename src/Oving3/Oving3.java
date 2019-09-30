package Oving3;

import java.util.Arrays;
import java.util.Date;

public class Oving3 {
    public static void quicksort(int[] tabell, int venstre, int høyre){
        if(høyre-venstre > 2){
            int delepos = splitt(tabell, venstre, høyre);
            quicksort(tabell, venstre, delepos-1);
            quicksort(tabell,delepos+1, høyre);
        } else median3sort(tabell, venstre, høyre);
    }
    private static int median3sort(int[] tabell, int venstre, int høyre){
        int median = (venstre+høyre)/2;
        if(tabell[venstre] > tabell[median]) bytt(tabell, venstre, median);
        if(tabell[median] > tabell[høyre]){
            bytt(tabell, median, høyre);
            if (tabell[venstre] > tabell[median]) bytt(tabell, venstre, median);
        }
        return median;
    }
    public static void bytt(int[] tabell, int i, int j){
        int k = tabell[j];
        tabell[j] = tabell[i];
        tabell[i] = k;
    }
    private static int splitt(int[] tabell, int v, int h){
        int m = median3sort(tabell, v, h);
        int dv = tabell[m];
        bytt(tabell, m, h-1);
        int iv=v;
        int  ih=h-1;
        while(true) {
            while (tabell[++iv] < dv) ;
            while (tabell[--ih] > dv) ;
            if (iv >= ih) break;
            bytt(tabell, iv, ih);
        }
        bytt(tabell, iv, h-1);
        return iv;
    }
    int[] fillTable(int count){
        int[] values = new int[count];

        for (int i = 0; i < count; i++){
            values[i] =(int)(Math.random()*100000);
        }
        return values;
    }



    static void sort(int[] arr, int lowIndex, int highIndex) {

        if(highIndex <= lowIndex) {
            return;
        }

        if(arr[lowIndex] > arr[highIndex]) {
            swap(arr, lowIndex, highIndex);
        }

        int pivot1 = arr[lowIndex];
        int pivot2 = arr[highIndex];

        int lt = lowIndex + 1;
        int gt = highIndex - 1;
        int i = lowIndex + 1;

        while(i <= gt) {
            if(arr[i] < pivot1) {
                swap(arr, i, lt);
                lt++;
                i++;
            } else if(arr[i] > pivot2) {
                swap(arr, i, gt);
                gt--;
            } else {
                i++;
            }
        }

        swap(arr, lowIndex, lt-1);
        swap(arr, gt+1, highIndex);

        lt--;
        gt++;

        sort(arr, lowIndex, lt-1);
        sort(arr, lt+1, gt-1);
        sort(arr, gt+1, highIndex);

    }

    public static void main(String[] args) {
        Oving3 sorting = new Oving3();
        //int[] values = sorting.fillTable(100000);
        int[] values = new int[100000];
        for (int i = 0; i<values.length; i++){
            values[i] = i;
        }







        //int[] values2;


        /*int[] values3;
        for (int i = 0; i < 2; i++){
            values3 = sorting.fillTable(100000);
        }

        sorting.quicksort(values3,0,values3.length-1);

        //values = sorting.fillTable(10000000);
        //values2 = sorting.fillTable(10000000);

        int[] values = new int[25000];
        int[] values2 = new int[25000];*/

        /*for (int i = 0; i<values.length;i++){
            if(i%2 == 0){
                values[i] = 1;
                //values2[i] = 1;
            }else if (i%2 != 0){
                values[i] = 2;
                //values2[i] = 2;
            }
        }*/

        /*for (int i = 0; i<values.length;i++){
            values[i] = i;
            values2[i] = i;
        }

        Date start1 = new Date();
        int runder1 = 0;
        double tid1;
        Date slutt1;
        do {
            sorting.sort(values2, 0, values2.length-1);
            slutt1 = new Date();
            ++runder1;
        } while (slutt1.getTime()-start1.getTime() < 1000);
        tid1 = (double)
                (slutt1.getTime()-start1.getTime()) / runder1;
        System.out.println("Millisekund pr. runde:" + tid1);*/

        Date start = new Date();
        int runder = 0;
        double tid;
        Date slutt;
        do {
            sorting.quicksort( values, 0, values.length-1);
            slutt = new Date();
            ++runder;
        } while (slutt.getTime()-start.getTime() < 1000);
        tid = (double)
                (slutt.getTime()-start.getTime()) / runder;
        System.out.println("Millisekund pr. runde:" + tid);

    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }
}
