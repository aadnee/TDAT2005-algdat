package Oving2;

import java.util.Date;

public class Mathematics {

    private double power(double x, int n){
        if (n <= 0) return 1;
        return x * (power(x, (n-1)));
    }

    private double power2(double x, int n){
        if(n==0) return 1;
        else if(n%2 == 0){
            return power2(x*x,(n/2));
        }else if(n%2 != 0){
            return (x * power2(x*x, (n-1)/2));
        }
        return -1;
    }


    public static void main(String[] args) {
        Mathematics math = new Mathematics();
        /*System.out.println(math.power(3,50));
        System.out.println(math.power2(3, 50));
        System.out.println((int)Math.pow(3,50));*/

        /*Date start = new Date();
        int runder = 0;
        double tid;
        Date slutt;
        do {
            math.power(1.00001,5000);
            slutt = new Date();
            ++runder;
        } while (slutt.getTime()-start.getTime() < 1000);
        tid = (double)
                (slutt.getTime()-start.getTime()) / runder;
        System.out.println("Millisekund pr. runde:" + tid);*/

        Date start1 = new Date();
        int runder1 = 0;
        double tid1;
        Date slutt1;
        do {
            math.power2(1.00001,10000000);
            slutt1 = new Date();
            ++runder1;
        } while (slutt1.getTime()-start1.getTime() < 1000);
        tid1 = (double)
                (slutt1.getTime()-start1.getTime()) / runder1;
        System.out.println("Millisekund pr. runde:" + tid1);

        Date start2 = new Date();
        int runder2 = 0;
        double tid2;
        Date slutt2;
        do {
            Math.pow(1.00001,100000000);
            slutt2 = new Date();
            ++runder2;
        } while (slutt2.getTime()-start2.getTime() < 1000);
        tid2 = (double)
                (slutt2.getTime()-start2.getTime()) / runder2;
        System.out.println("Millisekund pr. runde:" + tid2);
    }
}
