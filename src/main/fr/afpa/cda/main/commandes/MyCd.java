package fr.afpa.cda.main.commandes;

import fr.afpa.cda.exception.CheminInvalideException;
import fr.afpa.cda.exception.CheminRepertoirInvalideException;
import fr.afpa.cda.main.dto.CommandeLine;
import fr.afpa.cda.main.helpers.PathMain;

public class MyCd {

	public static void exec(CommandeLine cmd) {

		String chemin = cmd.getParams().get(0);
		try {
			chemin = PathMain.calculeCheminDirIfExists(chemin);
			PathMain.pathMiniShell = chemin;
		} catch (CheminInvalideException e) {
			System.out.println(e.getMessage());
		} catch (CheminRepertoirInvalideException e) {
			System.out.println(e.getMessage());
		}
	}
}
