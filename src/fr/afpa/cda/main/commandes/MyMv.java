package fr.afpa.cda.main.commandes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import fr.afpa.cda.main.dto.CommandeLine;

public class MyMv {

	public static void exec(CommandeLine cmd) {
		
		String cheminSource = cmd.getParams().get(0);
		String cheminDestination = cmd.getParams().get(1);
		File file = new File(cheminSource);
		cheminDestination += "/" + file.getName().toString() + "/";
		try {
			Files.move(Paths.get(cheminSource), Paths.get(cheminDestination),StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			System.out.println("Impossible, le fichier existe deja");
		} finally {
			if ((!cmd.getOptions().isEmpty()) && cmd.getOptions().get(0).equals("v")) {
			System.out.println("renamed" + "'" + cheminSource + "'" + "->" + "'" + cheminDestination + "'");
			}
		}
		
	}
}
