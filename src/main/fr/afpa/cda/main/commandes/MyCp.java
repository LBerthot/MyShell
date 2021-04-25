package fr.afpa.cda.main.commandes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.afpa.cda.exception.OptionInvalidException;
import fr.afpa.cda.main.dto.CommandeLine;
import fr.afpa.cda.main.helpers.PathMain;
import fr.afpa.cda.main.helpers.ReadAllFile;

public class MyCp {
	private final static List<String> ALL_OPTIONS;

	static {
		String[] allOptions = { "-help" };
		ALL_OPTIONS = new ArrayList<>(Arrays.asList(allOptions));
	}

	public static void exec(CommandeLine cmd) {
		String source, destination, dest;
		File file = null;

		try {
			if (!cmd.getOptions().isEmpty()) {
				PathMain.isOption(cmd.getOptions(), ALL_OPTIONS);
				if (cmd.getOptions().contains("-help")) {
					ReadAllFile.help(cmd);
				}
			} else {
				source = cmd.getParams().get(0);
				destination = cmd.getParams().get(1);
				dest = "";
				file = new File(source);
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
		} catch (IOException e) {
			System.out.println("cp: cannot copy '" + file.getName().toString() + "' : No such file or directory");
		} catch (NullPointerException e) {
			System.out.println("cp: cannot copy '" + file.getName().toString() + "' : No such file or directory");
		} catch (OptionInvalidException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void copy(String source, String destination) {
		Path sourcePath = Paths.get(source);
		Path destinationPath = Paths.get(destination);
		try {
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			System.out.println("cp: cannot copy '" + sourcePath.getFileName() + "' : No such file or directory");
			System.out.println();
			;
		}

	}

}
