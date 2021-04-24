package fr.afpa.cda.main.helpers;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import fr.afpa.cda.main.dto.CommandeLine;

public class ReadAllFile {

	public static void help(CommandeLine cmd) throws IOException {
		Path path = FileSystems.getDefault().getPath("./help/" + cmd.getNom()+".txt");
		List<String> lignes = Files.readAllLines(path, StandardCharsets.UTF_8);
		for (String ligne : lignes) {
			System.out.println(ligne);
		}
		
	}

}
