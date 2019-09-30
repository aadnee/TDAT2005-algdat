package Oving1;

import java.util.Date;

public class Stock {
    //private int change;
    final int MIN = -10;
    final int MAX = 10;


    int[] fillTable(int count){
        int[] stocks = new int[count];

        for (int i = 0; i < count; i++){
            stocks[i] = MIN + (int)(Math.random() * ((MAX - MIN)+1));
        }
        return stocks;
    }

    private int[] algorithm(int[] stockchange){
        int profit = 0;
        int buyingDayIndex = -1;
        int sellingDayIndex = -1;

        for (int i = 0; i < stockchange.length; i++){
            int currValue = stockchange[i];

            for (int j = i+1 ; j < stockchange.length; j++){

                currValue += stockchange[j];
                int currProfit = currValue - stockchange[i];

                if (currProfit > profit) {
                    profit = currProfit;
                    buyingDayIndex = i;
                    sellingDayIndex = j;
                }
            }
        }

        int[] result = {profit, buyingDayIndex, sellingDayIndex};
        return result;
    }

    public static void main(String[] args) {
        Stock testdata = new Stock();
        int[] stocks = testdata.fillTable(10000);
        //int iterations = stockchange.length;
        //int[] stocks = {-1, 3, -9, 2, 2, -1, 2, -1, -5};
        //for (int i = 0; i< stocks.length; i++) System.out.println(stocks[i] + " - Day: " +(i+1));

        int[] r;

        Date start = new Date();
        int runder = 0;
        double tid;
        Date slutt;
        do {
            r = testdata.algorithm(stocks);
            slutt = new Date();
            ++runder;
        } while (slutt.getTime()-start.getTime() < 1000);
        tid = (double)
                (slutt.getTime()-start.getTime()) / runder;
        System.out.println("Millisekund pr. runde:" + tid);



        //int[] stockresult = stock.algorithm(stocks);
        System.out.println("Biggest profit: " + r[0] +
                            " - Buying day: " +(r[1]+1) +
                            " - Selling day: " + (r[2]+1));
    }
}