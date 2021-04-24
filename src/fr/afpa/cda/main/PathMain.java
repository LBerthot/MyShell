package fr.afpa.cda.main;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.afpa.cda.exception.CheminInvalideException;
import fr.afpa.cda.exception.CheminRepertoirInvalideException;
import fr.afpa.cda.exception.CommandeIntrouvableException;
import fr.afpa.cda.exception.CommandeInvalideException;
import fr.afpa.cda.main.dto.CommandeLine;

public class PathMain {
	private final static List<String> ALL_COMMANDES;
	public static String pathMiniShell;
	static {

		String[] allCommandes = { "myPwd", "myCd", "myMkdir", "myExit", "myMv", "myRmdir", "myRm","myTop", "myCp", "myFind", "myCat","myWc","myTouch","myTail"};
		ALL_COMMANDES = new ArrayList<>(Arrays.asList(allCommandes));

		Path monChemin = Paths.get(".");// permet de se situer avec le "."
		if (!monChemin.isAbsolute())
			monChemin = monChemin.toAbsolutePath(); // retourne le chemin absolu du chemin

		pathMiniShell = monChemin.toAbsolutePath().normalize().toString();// virer tt les caratere inutile //affiche le
																			// chemin en string
	}

	public static String calculeCheminDirIfExists(String chemin)
			throws CheminInvalideException, CheminRepertoirInvalideException {
		String cheminCalcule = calculeCheminIfExists(chemin);
		File f = new File(cheminCalcule);
		if (!f.isDirectory())
			throw new CheminRepertoirInvalideException("ce n'est pas un repertoire");
		return cheminCalcule;
	}

	public static String calculeCheminIfExists(String chemin) throws CheminInvalideException {
		String cheminCalcule = calculeChemin(chemin);
		File f = new File(cheminCalcule);
		if (!f.exists())
			throw new CheminInvalideException("le chemin n'existe pas");
		return cheminCalcule;
	}

	public static String calculeChemin(String chemin) {
		Path path = Paths.get(chemin);
		if (!path.isAbsolute()) {
			path = Path.of(pathMiniShell, chemin);// recupere dabord le chemin et ajoute le chemin saisi .normalize
		}
		return path.normalize().toString();
	}

	public static CommandeLine decouperCommande(String saisieUtilisateur)
			throws CommandeInvalideException, CommandeIntrouvableException {
		if (saisieUtilisateur == null || saisieUtilisateur.isBlank())
			throw new CommandeIntrouvableException("La commande <" + saisieUtilisateur + "> n'existe pas");

		String[] partsOfCommandeLineTab = saisieUtilisateur.trim().replaceAll(" +", " ").split(" ");// regex ds split
		List<String> partsOfCommandeLine = new ArrayList<String>(Arrays.asList(partsOfCommandeLineTab));

		if (!isCommande(partsOfCommandeLine.get(0)))
			throw new CommandeInvalideException("La commande " + partsOfCommandeLine.get(0) + "n'existe pas");
		CommandeLine cmd = new CommandeLine();
		cmd.setNom(partsOfCommandeLine.remove(0));
		for (String part : partsOfCommandeLine) {
			if (part.startsWith("-")) { // l'option -v | -p ...
				cmd.addOption(part.substring(1));
			} else {
				cmd.addParam(part);
			}

		}
		return cmd;
	}

	public static boolean isCommande(String commande) {
		return ALL_COMMANDES.contains(commande);
	}
	
	
}
