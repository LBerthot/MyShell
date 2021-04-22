package fr.afpa.cda.main.commandes;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import fr.afpa.cda.exception.OptionInvalidException;
import fr.afpa.cda.main.PathMain;
import fr.afpa.cda.main.dto.CommandeLine;
import fr.afpa.cda.main.helpers.ReadAllFile;

public class MyCat {

	public static void exec(CommandeLine cmd) {
		try {
			if (cmd.getOptions().isEmpty()) {
				myCatWithoutOption(cmd);
			} else {
				optionIsValid(cmd.getOptions());
				if (cmd.getOptions().contains("-help")) {
					ReadAllFile.help(cmd);
				}
			}
		} catch (OptionInvalidException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void myCatWithoutOption(CommandeLine cmd) throws IOException {
		for (int i = 0; i < cmd.getParams().size(); i++) {
			String chemin = cmd.getParams().get(i);
			Path path = Paths.get(PathMain.calculeChemin(chemin));
			List<String> lignes = Files.readAllLines(path, StandardCharsets.UTF_8);
			for (String ligne : lignes) {
				System.out.println(ligne);
			}
		}
	}

	private static Boolean optionIsValid(List<String> options) throws OptionInvalidException {
		for (String option : options) {
			if (!option.equals("-help")) {
				throw new OptionInvalidException("l'option entrée est invalide");
			}
		}
		return true;
	}

}
