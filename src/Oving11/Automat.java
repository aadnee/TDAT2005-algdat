package Oving11;

public class Automat {
    private char[] input;
    private int[] acceptConditions;
    private int[][] nextCondition;

    public Automat(char[] input, int[] acceptConditions, int[][] nextCondition){
        this.input = input;
        this.acceptConditions = acceptConditions;
        this.nextCondition = nextCondition;
    }

    boolean checkInput(char[] in){
        int condition = 0;
        for (char a : in){
            for (int i = 0; i < input.length; i++)
                if (a == input[i]){
                condition = nextCondition[condition][i];
            }
        }
        for (int a : acceptConditions){
            if (condition == a) return true;
            else return false;
        }return false;
    }

    public static void main(String[] args) {
        char[] inputOne = new char[] {0,1};
        int[] acceptedCon = new int[] {2};
        int[][] nextCon = new int[][] {{1,3}, {1,2}, {2,3}, {3,3}};
        Automat automat = new Automat(inputOne, acceptedCon, nextCon);

        char[] inputTest1 = new char[]{};
        char[] inputTest2 = new char[]{0,1,0};
        char[] inputTest3 = new char[]{1,1,1};
        char[] inputTest4 = new char[]{0,1,0,1,1,0};
        char[] inputTest5 = new char[]{0,0,1,0,0,0};
        System.out.println(automat.checkInput(inputTest1));
        System.out.println(automat.checkInput(inputTest2));
        System.out.println(automat.checkInput(inputTest3));
        System.out.println(automat.checkInput(inputTest4));
        System.out.println(automat.checkInput(inputTest5));



        char[] inputTwo = new char[] {'a','b'};
        int[] acceptedCon1 = new int[] {3};
        int[][] nextCon1 = new int[][] {{1,2}, {4,3}, {3,4}, {3,3}, {4,4}};
        Automat automat1 = new Automat(inputTwo, acceptedCon1, nextCon1);

        char[] inputTest11 = new char[]{'a','b','b','b'};
        char[] inputTest12 = new char[]{'a','a','a','b'};
        char[] inputTest13 = new char[]{'b','a','b','a','b'};
        System.out.println(automat1.checkInput(inputTest11));
        System.out.println(automat1.checkInput(inputTest12));
        System.out.println(automat1.checkInput(inputTest13));
    }
}
