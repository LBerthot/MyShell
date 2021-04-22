package fr.afpa.cda.main.commandes;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.afpa.cda.main.PathMain;
import fr.afpa.cda.main.dto.CommandeLine;

public class MyMkdir {

	public static void exec(CommandeLine cmd) {
		if ((!cmd.getOptions().isEmpty()) && cmd.getOptions().get(0).equals("p")) {
			String[] splitNewDirs = cmd.getParams().get(0).replaceAll("/", " ").split(" ");
			List<String> newDirs = new ArrayList<String>(Arrays.asList(splitNewDirs));
			for (int i = 0; i < newDirs.size(); i++) {
				if (i == 0) {
					String chemin = newDirs.get(0);
					chemin = PathMain.calculeChemin(chemin);
					File f = new File(chemin);
					f.mkdir();
				} else {
					newDirs.set(0, (newDirs.get(0) + "/" + newDirs.get(i)));
					String chemin = newDirs.get(0);
					chemin = PathMain.calculeChemin(chemin);
					File f = new File(chemin);
					f.mkdir();
				}
			}

		} else {
			for (int i = 0; i < cmd.getParams().size(); i++) {
				String chemin = cmd.getParams().get(i);
				chemin = PathMain.calculeChemin(chemin);
				File f = new File(chemin);
				f.mkdir();
			}
		}
	}
}
