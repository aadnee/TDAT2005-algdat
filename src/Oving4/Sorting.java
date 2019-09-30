package Oving4;

public class Sorting {

    public static void quicksort(int[] tabell, int venstre, int høyre) {
        if (høyre - venstre > 2) {
            int delepos = splitt(tabell, venstre, høyre);
            quicksort(tabell, venstre, delepos - 1);
            quicksort(tabell, delepos + 1, høyre);
        } else median3sort(tabell, venstre, høyre);
    }

    private static int median3sort(int[] tabell, int venstre, int høyre) {
        int median = (venstre + høyre) / 2;
        if (tabell[venstre] > tabell[median]) bytt(tabell, venstre, median);
        if (tabell[median] > tabell[høyre]) {
            bytt(tabell, median, høyre);
            if (tabell[venstre] > tabell[median]) bytt(tabell, venstre, median);
        }
        return median;
    }

    public static void bytt(int[] tabell, int i, int j) {
        int k = tabell[j];
        tabell[j] = tabell[i];
        tabell[i] = k;
    }

    private static int splitt(int[] tabell, int v, int h) {
        int m = median3sort(tabell, v, h);
        int dv = tabell[m];
        bytt(tabell, m, h - 1);
        int iv = v;
        int ih = h - 1;
        while (true) {
            while (tabell[++iv] < dv) ;
            while (tabell[--ih] > dv) ;
            if (iv >= ih) break;
            bytt(tabell, iv, ih);
        }
        bytt(tabell, iv, h - 1);
        return iv;
    }

}
class Heap1{
    int len;
    int node[];

    public static void bytt(int[] tabell, int i, int j) {
        int k = tabell[j];
        tabell[j] = tabell[i];
        tabell[i] = k;
    }

    int over(int i){
        return(i-1)>>1;
    }
    int venstre(int i){
        return(i<<1)+1;
    }
    int hoyre(int i){
        return(i+1)<<1;
    }

    public void heapsort(){
        lag_heap();
        int l = len;
        while(len>1){
            int x = hent_maks();
            node[len] = x;
        }
        len = 1;
    }

    public int hent_maks(){
        int maks = node[0];
        node[0] = node[--len];
        fiks_heap(0);
        return maks;
    }
    public void lag_heap(){
        int i = len/2;
        while(i-->0) fiks_heap(i);
    }

    public void fiks_heap(int i){
        int m = venstre(i);
        if(m<len){
            int h = m+1;
            if(h<len && node[h] > node[m])m=h;
            if (node[m]>node[i]){
                bytt(node, i, m);
                fiks_heap(m);
            }
        }
    }

    public void sett_inn(int x){
        int i = len++;
        node[i] = x;
        prio_opp(i,0);
    }

    public void prio_opp(int i, int p){
        int f;
        node[i]+=p;
        while(i>0 && node[i]>node[f=over(i)]){
            bytt(node, i, f);
            i = f;
        }
    }



    public static void main(String[] args) {

    }
}
