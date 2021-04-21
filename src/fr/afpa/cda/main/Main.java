package fr.afpa.cda.main;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import fr.afpa.cda.exception.CheminInvalideException;
import fr.afpa.cda.exception.CheminRepertoirInvalideException;
import fr.afpa.cda.exception.CommandeIntrouvableException;
import fr.afpa.cda.exception.CommandeInvalideException;
import fr.afpa.cda.main.dto.CommandeLine;

public class Main {

	public static void main(String[] arg) {
		Scanner sc = new Scanner(System.in);
		sc.useDelimiter("(\r|\n)+");// permet d'accepter les espaces dans le scanner
		String saisieUtilisateur = null;

		while (true) {
			System.out.print("$myShell:" + PathMain.pathMiniShell + "> ");
			saisieUtilisateur = sc.next();
			CommandeLine cmd = null;
			try {
				cmd = PathMain.decouperCommande(saisieUtilisateur);
			} catch (CommandeInvalideException | CommandeIntrouvableException e) {
				System.err.println(e.getMessage());
				continue;
			}
			if (cmd.getNom().equals("myExit")) {
				break;
			}

			if (cmd.getNom().equals("myPwd")) {
				System.out.println(PathMain.pathMiniShell);
				continue;
			}
			if (cmd.getNom().equals("myCd")) {
				String chemin = cmd.getParams().get(0);
				try {
					chemin = PathMain.calculeCheminDirIfExists(chemin);
					PathMain.pathMiniShell = chemin;
				} catch (CheminInvalideException e) {
					System.out.println(e.getMessage());
					continue;
				} catch (CheminRepertoirInvalideException e) {
					System.out.println(e.getMessage());
					continue;
				}

			}
			if (cmd.getNom().equals("myMkdir")) {

				if ((!cmd.getOptions().isEmpty()) && cmd.getOptions().get(0).equals("p")) {
					String[] splitNewDirs = cmd.getParams().get(0).replaceAll("/", " ").split(" ");
					List<String> newDirs = new ArrayList<String>(Arrays.asList(splitNewDirs));
					System.out.println("inside the p");
					for (int i = 0; i < newDirs.size(); i++) {
						if (i == 0) {
							String chemin = newDirs.get(0);
							chemin = PathMain.calculeChemin(chemin);
							File f = new File(chemin);
							f.mkdir();
						} else {
							newDirs.set(0, (newDirs.get(0) + "/" + newDirs.get(i)));
							String chemin = newDirs.get(0) + "/" + newDirs.get(i);
							chemin = PathMain.calculeChemin(chemin);
							File f = new File(chemin);
							f.mkdir();
						}
					}

				} else {
					for (int i = 0; i < cmd.getParams().size(); i++) {
						String chemin = cmd.getParams().get(i);
						chemin = PathMain.calculeChemin(chemin);
						File f = new File(chemin);
						f.mkdir();
					}
				}
			}

			if (cmd.getNom().equals("myPwd")) {
				System.out.println(PathMain.pathMiniShell);
				continue;
			}
			if (cmd.getNom().equals("myCd")) {
				String chemin = cmd.getParams().get(0);
				try {
					chemin = PathMain.calculeCheminDirIfExists(chemin);
					PathMain.pathMiniShell = chemin;
				} catch (CheminInvalideException e) {
					System.out.println(e.getMessage());
					continue;
				} catch (CheminRepertoirInvalideException e) {
					System.out.println(e.getMessage());
					continue;
				}
			}
			if (cmd.getNom().equals("myRm")) {
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
		System.out.println("Au revoir !");
	}
}

/*
 * public static void main(String[] args) {
 * 
 * Scanner sc =new Scanner(System.in);
 * 
 * 
 * while (true) { String[] inputUser = sc.nextLine().split(" "); String[]
 * command = new String[2]; command[0]=inputUser[0];
 * 
 * switch (command[0]) { case "myPwd": MyPwd.exec() ; break; case"myCd":
 * MyCd.exec(); break; case"myRmdir": MyRmdir.exec(); break; case "myRm":
 * MyRm.exec(); break; case "myTouch": MyTouch.exec(); break; case "myCat":
 * MyCat.exec(); break; case "myTail": MyTail.exec(); break; case "myWc":
 * MyWc.exec(); break; case "myCp": MyCp.exec(); break; case "myMv":
 * MyMv.exec(); break; case "myTop": MyTop.exec(); break; case "myFind":
 * MyFind.exec(); break; case "myExit": MyExit.exec(); break; case "myMan":
 * MyMan.exec(); break;
 * 
 * default:System.out.println("Commande invalide "); }
 * 
 * }
 * 
 * }
 */
