package appli;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ArbreBR {
    private ArbreBR filsGauche;
    private ArbreBR filsDroit;
    private ArrayList<Objet> objetsStock;
    private List<Objet> objetsSac;
    private static float poidsMaximal;
    private static float borneMin;
    private float borneMax;
    private static ArbreBR meilleurSolution;


    public ArbreBR(ArrayList<Objet> objetsStock, float poidsMaximal) {
        this.objetsSac = new ArrayList<>();
        this.objetsStock = new ArrayList<>(objetsStock);
        ArbreBR.poidsMaximal = poidsMaximal;
        borneMax = 0;
    }

    public ArbreBR(ArbreBR pere) {
        this.objetsStock = new ArrayList<>(pere.objetsStock);
        this.objetsSac = new ArrayList<>(pere.objetsSac);
    }


    public void construire(int offset) {

        if(getSumPoidsSac() > poidsMaximal)
            return;

        borneMax = getSumValeurStock(offset) + getSumValeurSac();

        if(meilleurSolution == null || borneMin < this.getSumValeurSac()) {
            meilleurSolution = this;
            borneMin = meilleurSolution.getSumValeurSac();
        }

        if(offset >= objetsStock.size() || borneMax < borneMin)
            return;

        this.filsDroit = new ArbreBR(this);
        this.filsDroit.objetsSac.add(this.filsDroit.objetsStock.get(offset));

        this.filsGauche = new ArbreBR(this);

        this.filsDroit.construire(offset + 1);
        this.filsGauche.construire(offset + 1);
    }

    private float getSumPoidsSac() {
        if(objetsSac == null)
            return 0.0F;

        float somme = 0.0F;
        for(Objet o : objetsSac)
            somme += o.getPoids();
        return somme;
    }

    private float getSumValeurSac() {
        if(objetsSac == null)
            return 0.0F;

        float somme = 0.0F;
        for(Objet o : objetsSac)
            somme += o.getValeur();
        return somme;
    }

    private float getSumValeurStock(int offset) {
        float somme = 0.0F;

        for (int i = offset; i < objetsStock.size() ; i++)
            somme +=  objetsStock.get(i).getValeur();

        return somme;
    }

    public static ArbreBR getSolutionOptimale() {
        return meilleurSolution;
    }

    public List<Objet> getSacOptimal () {
        return objetsSac;
    }

    public static void setBorneMin(float borneMin) {
        ArbreBR.borneMin = borneMin;
    }
}
