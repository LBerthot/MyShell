package fr.afpa.cda.main;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc =new Scanner(System.in);
		
		
		while (true) {
			String[] inputUser = sc.nextLine().split(" ");
			String[] command = new String[2];
			command[0]=inputUser[0];
			
			switch (command[0]) {
			case "myPwd": 
				MyPwd.exec() ;
				break;
			case"myCd": 
				MyCd.exec();
				break;
			case"myRmdir":
				MyRmdir.exec();
				break;
			case "myRm":
				MyRm.exec();
				break;
			case "myTouch":
				MyTouch.exec();
				break;
			case "myCat":
				MyCat.exec();
				break;
			case "myTail":
				MyTail.exec();
				break;
			case "myWc":
				MyWc.exec();
				break;
			case "myCp":
				MyCp.exec();
				break;
			case "myMv":
				MyMv.exec();
				break;
			case "myTop":
				MyTop.exec();
				break;
			case "myFind":
				MyFind.exec();
				break;
			case "myExit":
				MyExit.exec();
				break;
			case "myMan":
				MyMan.exec();
				break;
			
			default:System.out.println("Commande invalide ");
				
			}
			
		}
	}

}
