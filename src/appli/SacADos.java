package appli;

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
        this.objetsStock = objetsStock;


    }

    public void resoudreGloutonne()
    {
        Collections.sort(objetsStock);
        Collections.reverse(objetsStock);

        float poidsAtteint = 0.0F;
        for(Objet objet : objetsStock)
        {
            poidsAtteint += objet.getPoids();
            if(poidsAtteint >= poidsMaximal)
                break;

            objetsSac.add(objet);

        }

    }

    public void resoudreDynamique() {
        
    }

    public List<Objet> getObjetsSac() {
        return objetsSac;
    }
}
