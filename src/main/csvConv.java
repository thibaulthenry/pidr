package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class csvConv {
	
	public csvConv() {}

    public static String[][] csvConverter (String chemin,int tabsize){
        String tab[][] = new String[6][tabsize];
        try{
            BufferedReader fichier_source = null;
            fichier_source = new BufferedReader(new FileReader(chemin));
            String chaine;
            int i = 1;
            try {
                while((chaine = fichier_source.readLine())!= null)
                {
                    tab[i] = chaine.split(",");
                    i++;
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            fichier_source.close();

        }
        catch (FileNotFoundException e)
        {
            System.out.println("Le fichier est introuvable !");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tab;
    }
}




