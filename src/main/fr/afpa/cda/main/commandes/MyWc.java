package fr.afpa.cda.main.commandes;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import fr.afpa.cda.main.dto.CommandeLine;
import fr.afpa.cda.main.helpers.PathMain;
import fr.afpa.cda.main.helpers.ReadAllFile;

public class MyWc {

	public static void exec(CommandeLine cmd) {
		if ((!cmd.getOptions().isEmpty()) && cmd.getOptions().contains("-help")) {
			try {
				ReadAllFile.help(cmd);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			Integer count = 0;
			String chemin = cmd.getParams().get(0);
			Path path = Paths.get(PathMain.calculeChemin(chemin)); // indique le path
			try {
				List<String> lignes = Files.readAllLines(path, StandardCharsets.UTF_8);
				File fichier = new File(chemin);

				if (cmd.getNom().equals("myWc") & cmd.getOptions().isEmpty()) {
					for (String ligne : lignes) {
						count += ligne.length();
					}
					System.out.println(
							"Chars :" + count + "|  line : " + lignes.size() + "|  Octet : " + fichier.length());
				} else {
					for (String opt : cmd.getOptions()) {
						if (opt.equals("w") & cmd.getOptions().contains("w")) {
							for (String ligne : lignes) {
								count += ligne.length();
							}
							System.out.println("Chars :" + count);
						}
						if (opt.equals("l") & cmd.getOptions().contains("l")) {
							System.out.println("line :" + lignes.size());
						}
						if (opt.equals("m") & cmd.getOptions().contains("m")) {
							System.out.println("Octets :" + fichier.length());
						}
					}
				}
			} catch (IOException e) {
				System.err.println("le fichier n'existe pas ");
			}
		}
	}
}
