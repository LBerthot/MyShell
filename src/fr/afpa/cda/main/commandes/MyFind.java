package fr.afpa.cda.main.commandes;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

import fr.afpa.cda.exception.OptionInvalidException;
import fr.afpa.cda.main.PathMain;
import fr.afpa.cda.main.dto.CommandeLine;
import fr.afpa.cda.main.helpers.ReadAllFile;

public class MyFind {

	public static void exec(CommandeLine cmd) {
		if (cmd.getOptions().isEmpty()) {
			System.out.println("Commande invalide");
		} else {
			try {
				optionIsValid(cmd.getOptions());
				if (cmd.getOptions().contains("-help")) {
					ReadAllFile.help(cmd);
				}if (cmd.getOptions().contains("name")) {
					myFindOptionName(cmd);
				}
			} catch (OptionInvalidException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void myFindOptionName(CommandeLine cmd) throws IOException {
		String chemin = null;
		String glob = "glob:**/";
		if (cmd.getParams().size() >1) {
			chemin = cmd.getParams().get(0);
			glob += cmd.getParams().get(1);
		}else {
			chemin = PathMain.pathMiniShell;
			glob += cmd.getParams().get(0);
		}
		final PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher(glob);
		Files.walkFileTree(Paths.get(PathMain.calculeChemin(chemin)), new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attr) throws IOException {
				if (pathMatcher.matches(file)) {
					System.out.println(file);
				}
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult postVisitDirectory(Path dir, IOException e) throws IOException {
				return FileVisitResult.CONTINUE;
			}
		});
		
	}
	
	private static Boolean optionIsValid(List<String> options) throws OptionInvalidException {
		for (String option : options) {
			if (!option.equals("name") && !option.equals("-help")) {
				throw new OptionInvalidException("l'option entrée est invalide");
			}
		}
		return true;
	}
}
