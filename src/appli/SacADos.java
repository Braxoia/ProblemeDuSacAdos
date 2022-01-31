package appli;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SacADos {
    private List<Objet> objetsSac = new LinkedList<>(); //objets dans le sac
    private List<Objet> objetsStock; //objets en stock
    private float poidsMaximal;

    public SacADos(List<Objet> objetsStock, float poidsMaximal)
    {
        this.poidsMaximal = poidsMaximal;
        this.objetsStock = new ArrayList<>(objetsStock);
    }

    public void resoudreGloutonne()
    {
        Collections.sort(objetsStock);
        Collections.reverse(objetsStock);

        float poidsAtteint = 0.0F;
        for(Objet objet : objetsStock)
        {
            poidsAtteint += objet.getPoids();
            if(poidsAtteint > poidsMaximal)
                break;

            objetsSac.add(objet);
        }
    }

    public void resoudreDynamique() {
        int virguleAEntier = 100; //pour revenir sans chiffre après la virgule
        int poidsMaximalReel = (int)(poidsMaximal)*virguleAEntier + 1;
        float[][] matrice = new float[objetsStock.size()][poidsMaximalReel];

        int i;
        int j = 0;

        for (; j < poidsMaximalReel; j++) {
            if((int) (objetsStock.get(0).getPoids()*virguleAEntier) > j)
                matrice[0][j] = 0;
            else
                matrice[0][j] = (int) (objetsStock.get(0).getValeur()*virguleAEntier);
        }

        for (i = 1; i < objetsStock.size(); i++) {
            for(j = 0; j < poidsMaximalReel; ++j) {
                if((int) (objetsStock.get(i).getPoids()*virguleAEntier) > j)
                    matrice[i][j] = matrice[i-1][j];
                else
                    matrice[i][j] = (int)
                    (
                        Math.max
                        (
                            matrice[i-1][j],
                            matrice[i-1][(int)(j - objetsStock.get(i).getPoids()*virguleAEntier)] +
                                    (int) (objetsStock.get(i).getValeur()*virguleAEntier)
                        )
                    );
            }
        }

        i = objetsStock.size()-1;
        j = poidsMaximalReel-1;

        while(matrice[i][j] == matrice[i][j-1])
            --j;

        //Récupération des objets

        while(j > 0) {
            while(i > 0 && matrice[i][j] == matrice[i-1][j])
                --i;
            j -= (int) (objetsStock.get(i).getPoids()*virguleAEntier);
            if(j >= 0)
                objetsSac.add(objetsStock.get(i));
            --i;
        }
    }

    public List<Objet> getObjetsSac() {
        return objetsSac;
    }

    public void resoudrePSE() {
        //On organise les meilleurs objets de manière approximative et non-optimale
        resoudreGloutonne();

        ArbreBR arbreBR = new ArbreBR((ArrayList<Objet>) objetsStock, this.poidsMaximal);

        float borneMin = getSumValeurSac();
        ArbreBR.setBorneMin(borneMin);
        arbreBR.construire(0);
        this.objetsSac = ArbreBR.getSolutionOptimale().getSacOptimal();
    }

    private float getSumValeurSac() {
        float somme = 0.0F;
        for(Objet o : objetsSac)
            somme += o.getValeur();
        return somme;
    }
}
