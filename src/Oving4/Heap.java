package Oving4;

import java.util.Arrays;
import java.util.Date;

public class Heap{
    int len;
    int node[];

    public Heap(int[] node){
        this.node = node;
        len = node.length;
    }
    private int over(int i){
        return (i-1)>>1;
    }

    private int venstre(int i){
        return (i << 1) + 1;
    }

    private int hoyre(int i){
        return (i+1) << 1;
    }

    private void change(int[] t, int i, int j){
        int k = t[j];
        t[j] = t[i];
        t[i] = k;
    }

    public void fixHeap(int i){
        int m = venstre(i);

        if(m < len){
            int h = m+1;

            if(h < len && node[h] > node[m]){
                m = h;
            }

            if(node[m] > node[i]){
                change(node, i, m);
                fixHeap(m);
            }
        }
    }

    public void createHeap(){
        int i = len/2;
        while(i-- > 0){
            fixHeap(i);
        }
    }

    public int getMax(){
        int max = node[0];
        node[0] = node[--len];
        fixHeap(0);
        return max;
    }

    public void heapSort(){
        createHeap();
        int l = len;

        while (len > 1){
            int x = getMax();
            node[len] = x;
        }
        len = l;
    }

    int[] fillTable(int count){
        int[] values = new int[count];

        for (int i = 0; i < count; i++){
            values[i] =(int)(Math.random()*10000000);
        }
        return values;
    }


    public static void main(String[] args) {
        final int amuntOfNumbers = 100000;
        int[] node = new int[amuntOfNumbers];
        /*for (int i = 0; i < amuntOfNumbers; i++){
            node[i] =(int)(Math.random()*100000);
        }*/

        /*for (int i = 0; i<node.length;i++){
            if(i%2 == 0){
                node[i] = 1;
            }else if (i%2 != 0){
                node[i] = 2;
            }
        }*/

        for (int i = 0; i<node.length; i++){
            node[i] = i;
        }

        Heap testHeap = new Heap(node);

        Date start1 = new Date();
        int runder1 = 0;
        double tid1;
        Date slutt1;
        do {
            testHeap.heapSort();
            slutt1 = new Date();
            ++runder1;
        } while (slutt1.getTime()-start1.getTime() < 1000);
        tid1 = (double)
                (slutt1.getTime()-start1.getTime()) / runder1;
        System.out.println("Millisekund pr. runde:" + tid1);




        //node = testHeap.fillTable(amuntOfNumbers);
        //Arrays.toString(testHeap.node);

        //System.out.println(Arrays.toString(testHeap.node));
        //System.out.println(Arrays.toString(testHeap.node));
        /*long start;
        long elapsedTime;
        start = System.currentTimeMillis();*/
        /*elapsedTime = System.currentTimeMillis()-start;
        System.out.println(elapsedTime);*/
    }
}
