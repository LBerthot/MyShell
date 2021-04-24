package fr.afpa.cda.main.commandes;

import java.io.File;
import java.io.IOException;

import fr.afpa.cda.main.PathMain;
import fr.afpa.cda.main.dto.CommandeLine;

public class MyTouch {

	public static void exec(CommandeLine cmd) {
		for (int i = 0; i < cmd.getParams().size(); i++) { //boucle qui permet de creer plusieurs fichiers 
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
