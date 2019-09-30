package A_star;

public class Main {

    public static void main(String[] args) throws Exception{

        GPS gps = new GPS("C:\\Users\\Ådne\\Google Drive\\NTNU\\3. semester\\Algoritmer og datastrukturer\\Øvinger-algdat\\src\\A_star\\kanter.txt",
                "C:\\Users\\Ådne\\Google Drive\\NTNU\\3. semester\\Algoritmer og datastrukturer\\Øvinger-algdat\\src\\A_star\\interessepkt.txt",
                "C:\\Users\\Ådne\\Google Drive\\NTNU\\3. semester\\Algoritmer og datastrukturer\\Øvinger-algdat\\src\\A_star\\noder.txt");
        System.out.println(gps.getStartLog());
        System.out.println(gps.navigateA("Trondheim", "Stjørdal"));
        System.out.println();
        //System.out.println(gps.navigateD("Trondheim", "Stjørdal"));
    }
}