package main.graphics.path;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import main.parameters.TrajectoryManager;

public class csvConverter {

	public static String[][] A = csvConverter.convert("resources\\simul\\" + TrajectoryManager.CSV_FILENAME + ".csv");

	private static String[][] convert(String path){
		String tab[][] = new String[7][];

		try{
			BufferedReader fichier_source = null;
			fichier_source = new BufferedReader(new FileReader(path));
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
			System.out.println("Le fichier trajectoire est introuvable !");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tab;
	}

}




