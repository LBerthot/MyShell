package fr.afpa.cda.main;

import java.io.IOException;
import java.util.Scanner;

import fr.afpa.cda.exception.CommandeIntrouvableException;
import fr.afpa.cda.exception.CommandeInvalideException;
import fr.afpa.cda.main.commandes.*;
import fr.afpa.cda.main.dto.CommandeLine;


public class Main {

	public static void main(String[] arg) throws IOException {
		Scanner sc = new Scanner(System.in);
		sc.useDelimiter("(\r|\n)+");// permet d'accepter les espaces dans le scanner
		String saisieUtilisateur = null;
		System.out.println("Bienvenue sur MyShell. Syst�me d'exploitation : " +System.getProperty("os.name").toLowerCase());
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
				MyPwd.exec(cmd);
			}else if (cmd.getNom().equals("myCd")) {
				MyCd.exec(cmd);
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
				MyTop.exec();
			}else if (cmd.getNom().equals("myCp")) {
				MyCp.exec(cmd);
			}else if (cmd.getNom().equals("myWc")) {
				MyWc.exec(cmd);
			}else if (cmd.getNom().equals("myCat")) {
				MyCat.exec(cmd);
			}else if (cmd.getNom().equals("myTouch")) {
				MyTouch.exec(cmd);
			}else if (cmd.getNom().equals("myTail")) {
				MyTail.exec(cmd);
			}
		}
		sc.close();
		System.out.println("Au revoir !");
	}
}

