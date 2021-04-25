package fr.afpa.cda.main.commandes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.afpa.cda.exception.OptionInvalidException;
import fr.afpa.cda.main.dto.CommandeLine;
import fr.afpa.cda.main.helpers.PathMain;
import fr.afpa.cda.main.helpers.ReadAllFile;

public class MyMv {
	private final static List<String> ALL_OPTIONS;

	static {
		String[] allOptions = { "-help", "v" };
		ALL_OPTIONS = new ArrayList<>(Arrays.asList(allOptions));
	}

	public static void exec(CommandeLine cmd) {
		String cheminSource = null; 
		String cheminDestination = null;
		File file = null;
		
		try {
			if (!cmd.getOptions().isEmpty()) {
				PathMain.isOption(cmd.getOptions(), ALL_OPTIONS);
				if (cmd.getOptions().contains("-help")) {
					ReadAllFile.help(cmd);
				}
			} else {
				cheminSource = cmd.getParams().get(0);
				cheminDestination = cmd.getParams().get(1);
				file = new File(cheminSource);
				cheminDestination += "/" + file.getName().toString() + "/";
				Files.move(Paths.get(cheminSource), Paths.get(cheminDestination), StandardCopyOption.REPLACE_EXISTING);
			}
		} catch (IOException e) {
			System.out.println("Impossible, le fichier existe deja");
		} catch (OptionInvalidException e) {
			System.out.println(e.getMessage());
		} finally {
			if ((!cmd.getOptions().isEmpty()) && cmd.getOptions().get(0).equals("v")) {
				System.out.println("renamed" + "'" + cheminSource + "'" + "->" + "'" + cheminDestination + "'");
			}
		}
	}
}
