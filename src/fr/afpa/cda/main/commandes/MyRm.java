package fr.afpa.cda.main.commandes;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

import fr.afpa.cda.exception.OptionInvalidException;
import fr.afpa.cda.main.PathMain;
import fr.afpa.cda.main.dto.CommandeLine;
import fr.afpa.cda.main.helpers.ReadAllFile;

public class MyRm {

	public static void exec(CommandeLine cmd) {
		Boolean optionV = false;
		if (!cmd.getOptions().isEmpty()) {
			try {
				optionIsValid(cmd.getOptions());
				if (cmd.getOptions().contains("-help")) {
					ReadAllFile.help(cmd);
				}
				if (cmd.getOptions().contains("v")) {
					optionV = myRmOptionV(cmd);
				}
				if (cmd.getOptions().contains("r")) {
					myRmOptionR(cmd, optionV);
				}
			} catch (OptionInvalidException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (cmd.getOptions().isEmpty() || cmd.getOptions().contains("v")) {
			myRmWithoutOption(cmd, optionV);
		}
	}

	private static Boolean optionIsValid(List<String> options) throws OptionInvalidException {
		for (String option : options) {
			if (!option.equals("r") && !option.equals("v") && !option.equals("-help")) {
				throw new OptionInvalidException("l'option entrée est invalide");
			}
		}
		return true;
	}

	private static Boolean myRmOptionV(CommandeLine cmd) {
		return true;

	}

	private static void myRmOptionR(CommandeLine cmd, Boolean optionV) throws IOException {
		String chemin = cmd.getParams().get(0);
		Path path = Paths.get(PathMain.calculeChemin(chemin));
		Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attr) throws IOException {
				Files.delete(file);
				if (optionV) {
					System.out.println("Suppression de " + file.getFileName());
				}
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult postVisitDirectory(Path dir, IOException e) throws IOException {
				Files.delete(dir);
				if (optionV) {
					System.out.println("Suppression de " + dir.getFileName());
				}
				return FileVisitResult.CONTINUE;
			}
		});
	}

	private static void myRmWithoutOption(CommandeLine cmd, Boolean optionV) {
		String chemin = cmd.getParams().get(0);
		Path path = Paths.get(PathMain.calculeChemin(chemin));
		try {
			Files.delete(path);
			if (optionV) {
				System.out.println("Suppression de " + path.getFileName());
			}
		} catch (NoSuchFileException e) {
			System.out.println("Le fichier ou répertoire " + chemin + " n'existe pas");
		} catch (DirectoryNotEmptyException e) {
			System.out.println("Le répertoire " + chemin + " n'est pas vide");
		} catch (IOException e) {
			System.out.println("Impossible de supprimer " + chemin + " : " + e);
		}
	}

}
