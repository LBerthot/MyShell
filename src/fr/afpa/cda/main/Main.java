package fr.afpa.cda.main;

import java.io.IOException;
import java.util.Scanner;

import fr.afpa.cda.exception.CheminInvalideException;
import fr.afpa.cda.exception.CheminRepertoirInvalideException;
import fr.afpa.cda.exception.CommandeIntrouvableException;
import fr.afpa.cda.exception.CommandeInvalideException;
import fr.afpa.cda.main.commandes.MyFind;
import fr.afpa.cda.main.commandes.MyCp;
import fr.afpa.cda.main.commandes.MyMkdir;
import fr.afpa.cda.main.commandes.MyMv;
import fr.afpa.cda.main.commandes.MyRm;
import fr.afpa.cda.main.commandes.MyRmdir;
import fr.afpa.cda.main.dto.CommandeLine;

public class Main {

	public static void main(String[] arg) throws IOException {
		Scanner sc = new Scanner(System.in);
		sc.useDelimiter("(\r|\n)+");// permet d'accepter les espaces dans le scanner
		String saisieUtilisateur = null;
		System.out.println("Bienvenue sur MyShell. Système d'exploitation : " +System.getProperty("os.name").toLowerCase());
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
			} else if (cmd.getNom().equals("myPwd")) {
				System.out.println(PathMain.pathMiniShell);
				continue;
			}else if (cmd.getNom().equals("myCd")) {
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
			}else if (cmd.getNom().equals("myMkdir")) {
				MyMkdir.exec(cmd);
			}else if (cmd.getNom().equals("myRm")) {
				MyRm.exec(cmd);
			}else if (cmd.getNom().equals("myRmdir")) {
				MyRmdir.exec(cmd);
			}else if (cmd.getNom().equals("myFind")) {
				MyFind.exec(cmd);
			}else if (cmd.getNom().equals("myMv")) {
				MyMv.exec(cmd);
			}else if (cmd.getNom().equals("myTop")) {
				
			}else if (cmd.getNom().equals("myCp")) {
				MyCp.exec(cmd);
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
