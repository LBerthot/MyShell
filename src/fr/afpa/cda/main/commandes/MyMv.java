package fr.afpa.cda.main.commandes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import fr.afpa.cda.main.PathMain;
import fr.afpa.cda.main.dto.CommandeLine;

public class MyMv {

	public static void exec(CommandeLine cmd) {
		
		String cheminSource = cmd.getParams().get(0);
		System.out.println(cheminSource);
		Path source = Paths.get(PathMain.calculeChemin(cheminSource));
		String cheminDestination = cmd.getParams().get(1);
		System.out.println(cheminDestination);
		Path destination = Paths.get(PathMain.calculeChemin(cheminDestination));
		try {
			Files.move(source, destination,StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			System.out.println("Impossible, le fichier existe deja");
		} finally {
			if ((!cmd.getOptions().isEmpty()) && cmd.getOptions().get(0).equals("v")) {
			System.out.println("renamed" + "'" + source.toAbsolutePath() + "'" + "->" + "'" + destination.toAbsolutePath() + "'");
			}
		}
		
	}

}
