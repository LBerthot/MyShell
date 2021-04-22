package fr.afpa.cda.main.commandes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import fr.afpa.cda.main.dto.CommandeLine;

public class MyCp {

	public static void exec(CommandeLine cmd)throws IOException {
		String source = cmd.getParams().get(0);
		String destination = cmd.getParams().get(1);
		String dest = "";
		File file = new File(source);
		destination += "/" + file.getName().toString() + "/";
		copy(source, destination);
		
		File[] files = file.listFiles();

		for (File f : files) {
			if (f.isDirectory()) {
				System.out.println("directory: " + f.toString());
				exec(cmd);
			} else {
				System.out.println("file: " + f.toString());
				dest = destination + f.getName().toString();
				copy(dest, f.toString());
			}

		}

	}

	public static void copy(String source, String destination) throws IOException {

		Path sourcePath = Paths.get(source);
		Path destinationPath = Paths.get(destination);
		Files.copy(sourcePath, destinationPath);

	}


}

