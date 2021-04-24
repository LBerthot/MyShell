package fr.afpa.cda.main.commandes;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.afpa.cda.exception.OptionInvalidException;
import fr.afpa.cda.main.dto.CommandeLine;
import fr.afpa.cda.main.helpers.PathMain;
import fr.afpa.cda.main.helpers.ReadAllFile;

public class MyRmdir {
	private final static List<String> ALL_OPTIONS;
	
	static {
	String[] allOptions = {"-help", "p"};
	ALL_OPTIONS = new ArrayList<>(Arrays.asList(allOptions));
	}
	
	public static void exec(CommandeLine cmd) {
		if (cmd.getOptions().isEmpty()) {
			myRmDirWithoutOption(cmd);
		} else {
			try {
				PathMain.optionIsValid(cmd.getOptions(), ALL_OPTIONS);
				if (cmd.getOptions().contains("-help")) {
					ReadAllFile.help(cmd);
				}if (cmd.getOptions().contains("p")) {
					myRmDirOptionP(cmd);
				}
			} catch (OptionInvalidException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void myRmDirOptionP(CommandeLine cmd) throws IOException {
		String chemin = cmd.getParams().get(0);
		Path path = Paths.get(PathMain.calculeChemin(chemin));
		try {
			Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult postVisitDirectory(Path dir, IOException e) throws IOException {
					Files.delete(dir);
					return FileVisitResult.CONTINUE;
				}
			});
		} catch (NoSuchFileException e) {
			System.out.println("Le répertoire " + chemin + " n'existe pas");
		} catch (DirectoryNotEmptyException e) {
			System.out.println("Le répertoire " + chemin + " n'est pas vide");
		} catch (IOException e) {
			System.out.println("Impossible de supprimer " + chemin + " : " + e);
		}
	}

	public static void myRmDirWithoutOption(CommandeLine cmd) {
		String chemin = cmd.getParams().get(0);
		Path path = Paths.get(PathMain.calculeChemin(chemin));
		try {
			if (Files.isDirectory(path)) {
				Files.delete(path);
			} else {
				System.out.println("Ce n'est pas un répertoire");
			}
		} catch (NoSuchFileException e) {
			System.out.println("Le répertoire " + chemin + " n'existe pas");
		} catch (DirectoryNotEmptyException e) {
			System.out.println("Le répertoire " + chemin + " n'est pas vide");
		} catch (IOException e) {
			System.out.println("Impossible de supprimer " + chemin + " : " + e);
		}
	}
}