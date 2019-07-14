package de.scheduling;

import java.util.InputMismatchException;
import java.util.Scanner;

import de.scheduling.controller.FirstComeFirstServed;
import de.scheduling.controller.RoundRobin;
import de.scheduling.controller.ShortestJobFirst;
import de.scheduling.controller.ShortestRemainingTimeFirst;

public class SchedulingAusfuehren {

	static Scanner sc = new Scanner(System.in);
	
	//Main
	public static void main(String[] args) {
		new SchedulingAusfuehren();
	}

	//Konstruktor
	public SchedulingAusfuehren() {
		ausfuehren();
	}
	
	public static void ausfuehren() {
		boolean falscheEingabe = false;

		while (!falscheEingabe) { // While-Schleife, damit bei falscher Eingabe das Programm wieder von Anfang startet

			try {
				System.out.println("Welches Scheduling Algorithmus wollen Sie ausführen?\n"
						+ "\nDrücken Sie (1) für First Come First Served" + "\nDie (2) für Shortest Job First"
						+ "\nDie (3) für Shortest Remaining Time First" + "\nund die (4) für Round Robin: ");
					
				int wahl = sc.nextInt();

				if (wahl <= 4 && wahl != 0) { //Zahlen unter 1 und größer 4 werden abgefangen
					System.out.println("Bitte Anzahl der Prozesse eingeben: ");
					int prozesse = sc.nextInt();

					switch (wahl) {
					case 1:
						FirstComeFirstServed a = new FirstComeFirstServed();
						a.fcfs(prozesse);
						break;
					case 2:
						ShortestJobFirst b = new ShortestJobFirst();
						b.sjf(prozesse);
						break;
					case 3:
						ShortestRemainingTimeFirst c = new ShortestRemainingTimeFirst();
						c.srtf(prozesse);
						break;
					case 4:
						RoundRobin d = new RoundRobin();
						d.rr(prozesse);
						break;
					}
				} else {
					System.out.println("Sie haben eine andere Zahl als 1 bis 4 eingegeben. Bitte erneut starten!");
				}
				break;
				
			} catch (InputMismatchException e) { // Unerwartete bzw. falsche Eingabe wird abgefangen
				System.out.println("Bist du Banane!? Keine Zeichen außer Zahlen!! \nNOCHMAL von vorn...!\n");
				sc.next();
			}
		}
	}
}
