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

public class MyRm {

	public static void exec(CommandeLine cmd) {
		if (cmd.getOptions().isEmpty()) {
			myRmWithoutOption(cmd);
		} else {
			try {
				optionIsValid(cmd.getOptions());
				for (String opt : cmd.getOptions()) {
					if (opt.equals("r") && cmd.getOptions().size() == 1) {
						myRmOptionR(cmd);
					}
					if (opt.equals("r") && cmd.getOptions().size() > 1) {
						myRmOptionR(cmd);
						myRmOptionV(cmd);
						break;
					} else if (opt.equals("v") && cmd.getOptions().size() == 1) {
						myRmWithoutOption(cmd);
						myRmOptionV(cmd);
					} else if (opt.equals("v") && cmd.getOptions().size() > 1) {
						myRmOptionR(cmd);
						myRmOptionV(cmd);
						break;
					}
				}
			} catch (OptionInvalidException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static Boolean optionIsValid(List<String> options) throws OptionInvalidException {
		for (String option : options) {
			if (!option.equals("r") && !option.equals("v")) {
				throw new OptionInvalidException("l'option entrée est invalide");
			}
		}
		return true;
	}

	private static void myRmOptionV(CommandeLine cmd) {
		String chemin = cmd.getParams().get(0);
		Path path = Paths.get(PathMain.calculeChemin(chemin));
		System.out.println("Suppression de " + path.getFileName());

	}

	private static void myRmOptionR(CommandeLine cmd) throws IOException {
		String chemin = cmd.getParams().get(0);
		Path path = Paths.get(PathMain.calculeChemin(chemin));
		Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attr) throws IOException {
				Files.delete(file);
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult postVisitDirectory(Path dir, IOException e) throws IOException {
				Files.delete(dir);
				return FileVisitResult.CONTINUE;
			}
		});

	}

	private static void myRmWithoutOption(CommandeLine cmd) {
		String chemin = cmd.getParams().get(0);
		Path path = Paths.get(PathMain.calculeChemin(chemin));
		try {
			Files.delete(path);
		} catch (NoSuchFileException e) {
			System.out.println("Le fichier ou répertoire " + chemin + " n'existe pas");
		} catch (DirectoryNotEmptyException e) {
			System.out.println("Le répertoire " + chemin + " n'est pas vide");
		} catch (IOException e) {
			System.out.println("Impossible de supprimer " + chemin + " : " + e);
		}
	}

}
