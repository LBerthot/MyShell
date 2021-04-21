package fr.afpa.cda.main.commandes;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import fr.afpa.cda.exception.OptionInvalidException;
import fr.afpa.cda.main.PathMain;
import fr.afpa.cda.main.dto.CommandeLine;

public class MyRm {

	public static void exec(CommandeLine cmd) {

		if (!cmd.getOptions().isEmpty()) {
			try {
				optionIsValid(cmd.getOptions());
				for (String opt : cmd.getOptions()) {
					if (opt.equals("r")) {
						myRmOptionR(cmd);

					} else if (opt.equals("v")) {
						myRmOptionV(cmd);

					}
				}
			} catch (OptionInvalidException e) {
				System.out.println(e.getMessage());
			}
		} else {
			myRmWithoutOption(cmd);
		}
	}

	private static Boolean optionIsValid(List<String> options) throws OptionInvalidException {

		for (String option : options) {
			if (!option.equals("r") && !option.equals("v")) {
				throw new OptionInvalidException("l'option entrée est invalide");
			}
		}
		return true;

	}

	/*
	 * public static void exec(CommandeLine cmd) { if (!cmd.getOptions().isEmpty()
	 * && cmd.getOptions().get(0).equals("-r")) { myRmOptionR(cmd); } if
	 * (!cmd.getOptions().isEmpty() && cmd.getOptions().get(0).equals("-v")) {
	 * myRmOptionV(cmd); } if (cmd.getOptions().isEmpty()) { myRmWithoutOption(cmd);
	 * }
	 * 
	 * }
	 */

	private static void myRmOptionV(CommandeLine cmd) {
		System.out.println("Option -v");

	}

	private static void myRmOptionR(CommandeLine cmd) {
		System.out.println("Option -r");

	}

	private static void myRmWithoutOption(CommandeLine cmd) {
		String chemin = cmd.getParams().get(0);
		Path path = Paths.get(PathMain.calculeChemin(chemin));
		try {
			Files.delete(path);
		} catch (NoSuchFileException e) {
			System.out.println("Le fichier ou répertoire " + chemin + " n'existe pas");
		} catch (DirectoryNotEmptyException e) {
			System.out.println("Le répertoire " + chemin + " n'est pas vide");
		} catch (IOException e) {
			System.out.println("Impossible de supprimer " + chemin + " : " + e);
		}
	}

}
