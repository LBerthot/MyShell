package fr.afpa.cda.main.commandes;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import fr.afpa.cda.main.PathMain;
import fr.afpa.cda.main.dto.CommandeLine;

public class MyWc {

	public static void exec(CommandeLine cmd) {

		String chemin = cmd.getParams().get(0);
		Path path = Paths.get(PathMain.calculeChemin(chemin)); // indique le path
		try {
			List<String> lignes = Files.readAllLines(path, StandardCharsets.UTF_8);// initialise une liste pour																// compter le nbr de ligne																	// et caracteres
			File fichier = new File(chemin);
			
			/* if (condition) {
				
			}else {
				
			}*/
			System.out.println("octect : " + fichier.length());
			for (String ligne : lignes) {
				System.out.println(
						"Chars :" + ligne.length() + "|  line : " + lignes.size() + "|  Octet : " + fichier.length());// permetd'afficher
																														// le
																														// nombres
																														// de
																														// caractere
			}
			for (String opt : cmd.getOptions()) {
				if (opt.equals("w") & cmd.getOptions().contains("w")) {
					for (String ligne : lignes) {
						System.out.println("Chars :" + ligne.length());
					}
				}
				if (opt.equals("l") & cmd.getOptions().contains("l")) {
					System.out.println("line :" + lignes.size());
				}
				if (opt.equals("m") & cmd.getOptions().contains("m")) {
					System.out.println("Octets :" + fichier.length());
				}
			}
		} catch (IOException e) {
			System.out.println("le fichier n'existe pas ");
		}

	}

}
