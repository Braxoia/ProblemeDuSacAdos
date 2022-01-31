package appli;

public class Objet implements Comparable<Objet> {
    private String nom;
    private float poids;
    private float valeur;

    public Objet(String nom, float poids, float valeur) {
        this.nom = nom;
        this.poids = poids;
        this.valeur = valeur;
    }

    @Override
    public int compareTo(Objet o) {
        return Float.compare(valeur / poids, o.valeur / o.poids);
    }

    public String getNom() {
        return nom;
    }

    public float getPoids() {
        return poids;
    }

    public float getValeur() {
        return valeur;
    }

    @Override
    public String toString() {
        return nom + " ; " + poids + " ; " + valeur;
    }
}
