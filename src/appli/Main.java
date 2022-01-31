package appli;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        LinkedList<Objet> objets = new LinkedList<>();

        Path chemin = Paths.get("src/appli/items.txt");
        try(BufferedReader lecteur = Files.newBufferedReader(chemin))
        {
            String ligne;
            while((ligne = lecteur.readLine()) != null)
            {
                String[] objetString = ligne.split(";");
                Objet objet = new Objet(
                        objetString[0].trim(),
                        Float.parseFloat(objetString[1].trim()),
                        Float.parseFloat(objetString[2].trim())
                );

                objets.add(objet);
            }
        }
        catch(Exception e) {
            System.err.println("Le fichier /src/appli/items.txt n'a pas été trouvé ou ne peut pas être lu !");
            e.printStackTrace();
        }

        SacADos sacADos = new SacADos(objets, 40.0F);
        Date d1 = new Date();
        sacADos.resoudreGloutonne();
        Date d2 = new Date();

        long dateDiff = (d2.getTime() - d1.getTime());
        System.out.println("Temps d'exec : " + TimeUnit.MILLISECONDS.convert(dateDiff, TimeUnit.MILLISECONDS) + "ms");


       for (Objet o : sacADos.getObjetsSac()) {
            System.out.println(o);
       }
    }
}
