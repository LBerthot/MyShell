package fr.afpa.cda.main.commandes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.afpa.cda.exception.OptionInvalidException;
import fr.afpa.cda.main.dto.CommandeLine;
import fr.afpa.cda.main.helpers.PathMain;
import fr.afpa.cda.main.helpers.ReadAllFile;

public class MyPwd {
	private final static List<String> ALL_OPTIONS;
	
	static {
	String[] allOptions = {"-help"};
	ALL_OPTIONS = new ArrayList<>(Arrays.asList(allOptions));
	}
	
	public static void exec(CommandeLine cmd) {
		try {
			if (cmd.getOptions().isEmpty()) {
				System.out.println(PathMain.pathMiniShell);
			} else {
				PathMain.optionIsValid(cmd.getOptions(), ALL_OPTIONS);
				if (cmd.getOptions().contains("-help")) {
					ReadAllFile.help(cmd);
				}
			}
		} catch (OptionInvalidException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
