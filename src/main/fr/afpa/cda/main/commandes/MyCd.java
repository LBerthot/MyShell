package fr.afpa.cda.main.commandes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.afpa.cda.exception.CheminInvalideException;
import fr.afpa.cda.exception.CheminRepertoirInvalideException;
import fr.afpa.cda.exception.OptionInvalidException;
import fr.afpa.cda.main.dto.CommandeLine;
import fr.afpa.cda.main.helpers.PathMain;
import fr.afpa.cda.main.helpers.ReadAllFile;

public class MyCd {
	private final static List<String> ALL_OPTIONS;

	static {
		String[] allOptions = { "-help" };
		ALL_OPTIONS = new ArrayList<>(Arrays.asList(allOptions));
	}

	public static void exec(CommandeLine cmd) {

		String chemin = cmd.getParams().get(0);
		try {
			if (!cmd.getOptions().isEmpty()) {
				PathMain.isOption(cmd.getOptions(), ALL_OPTIONS);
				if (cmd.getOptions().contains("-help")) {
					ReadAllFile.help(cmd);
				}
			} else {
				chemin = PathMain.calculeCheminDirIfExists(chemin);
				PathMain.pathMiniShell = chemin;
			}
		} catch (CheminInvalideException e) {
			System.out.println(e.getMessage());
		} catch (CheminRepertoirInvalideException e) {
			System.out.println(e.getMessage());
		} catch (OptionInvalidException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
