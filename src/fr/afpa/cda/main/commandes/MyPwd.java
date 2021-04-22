package fr.afpa.cda.main.commandes;

import java.io.IOException;
import java.util.List;

import fr.afpa.cda.exception.OptionInvalidException;
import fr.afpa.cda.main.PathMain;
import fr.afpa.cda.main.dto.CommandeLine;
import fr.afpa.cda.main.helpers.ReadAllFile;

public class MyPwd {

	public static void exec(CommandeLine cmd) {
		try {
			if (cmd.getOptions().isEmpty()) {
				System.out.println(PathMain.pathMiniShell);
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

	private static Boolean optionIsValid(List<String> options) throws OptionInvalidException {
		for (String option : options) {
			if (!option.equals("-help")) {
				throw new OptionInvalidException("l'option entrée est invalide");
			}
		}
		return true;
	}

}
