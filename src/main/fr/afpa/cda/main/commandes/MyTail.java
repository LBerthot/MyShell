package fr.afpa.cda.main.commandes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import fr.afpa.cda.main.dto.CommandeLine;
import fr.afpa.cda.main.helpers.PathMain;
import fr.afpa.cda.main.helpers.ReadAllFile;

public class MyTail {

	public static void exec(CommandeLine cmd) {

		if ((!cmd.getOptions().isEmpty()) && cmd.getOptions().contains("-help")) {
			try {
				ReadAllFile.help(cmd);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			String chemin = cmd.getParams().get(0);
			Path path = Paths.get(PathMain.calculeChemin(chemin)); // indique le paths

			try {
				List<String> lignes = Files.readAllLines(path, StandardCharsets.UTF_8);// initialise une liste pour
																						// compter
																						// le nbr de ligne et caracteres
				FileReader file = new FileReader(chemin);
				BufferedReader buffer = new BufferedReader(file);
				for (int j = lignes.size(); j > 0; j--) {
					// Si le numéro de la ligne est plus grand que total ligne moins 10 alors
					// récupérer les lignes
					if (j < (lignes.size() - 9)) {
						System.out.println(buffer.readLine());
					} else {
						buffer.readLine();
					}
				}
				buffer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

}
