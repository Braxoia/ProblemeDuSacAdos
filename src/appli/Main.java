package appli;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Objet> objets = new LinkedList<>();

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

        SacADos sacADos = new SacADos(objets, 5.0F);
        sacADos.resoudreGloutonne();

        for (Objet o : sacADos.getObjetsSac()) {
            System.out.println(o);
        }
    }
}
