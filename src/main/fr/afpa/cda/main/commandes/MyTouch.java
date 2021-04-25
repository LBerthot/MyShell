package fr.afpa.cda.main.commandes;

import java.io.File;
import java.io.IOException;

import fr.afpa.cda.main.dto.CommandeLine;
import fr.afpa.cda.main.helpers.PathMain;
import fr.afpa.cda.main.helpers.ReadAllFile;

public class MyTouch {

	public static void exec(CommandeLine cmd) {
		if ((!cmd.getOptions().isEmpty()) && cmd.getOptions().contains("-help")) {
			try {
				ReadAllFile.help(cmd);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			for (int i = 0; i < cmd.getParams().size(); i++) { // boucle qui permet de creer plusieurs fichiers
				String chemin = cmd.getParams().get(i);
				chemin = PathMain.calculeChemin(chemin); // indique le path
				File file = new File(chemin);
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
