package de.scheduling.controller;

import java.util.InputMismatchException;
import java.util.Scanner;

import de.scheduling.model.ScheduleMethoden;
import de.scheduling.model.ScheduleObjekt;

public class FirstComeFirstServed{

	static Scanner sc = new Scanner(System.in);

	public void fcfs(int anzahlProzesse) {

		ScheduleMethoden methode = new ScheduleMethoden();
		ScheduleObjekt so = new ScheduleObjekt(anzahlProzesse);
		
		boolean falscheEingabe = false;

		while (!falscheEingabe) {

			try {
				
				methode.eingabeCpuUndAnkZeit(anzahlProzesse, so.getGitter()); // CPU-Laufzeit und Ankunftszeit wird per Methode eingegeben

					for (int i = 1; i <= anzahlProzesse; i++) { 
						so.setLaufzeitDerProzesse(so.getLaufzeitDerProzesse()+so.getGitter()[i][1]);
					}
					

					for (int i = 0; i < so.getLaufzeitDerProzesse(); i++) {
						if (so.getGitter()[so.getSelektierterProzess()][1] == 0) { 	
							so.setSelektierterProzess(so.getSelektierterProzess() + 1); 		
						}
	
	// Berechnung
						so.getGitter()[so.getSelektierterProzess()][1]--;  // entspricht gitter[selektierterProzess][1]-- /  minus restliche Zeit
	
						// Wartzeit und Laufzeit der Prozesse werden berechnet
						for (int j = 1; j <= anzahlProzesse; j++) {
							if (so.getGitter()[j][0] <= i) // getGitter[j][0] = Ankunftszeit
							{
								if (so.getGitter()[j][1] != 0) // getGitter[j][1] = CPU-Laufzeit
								{
									so.getGitter()[j][3]++;		// getGitter[j][3] = Laufzeit der Prozesse
	
									if (j != so.getSelektierterProzess())
										so.getGitter()[j][2]++;		// getGitter[j][2] = Wartezeit
								} else if (j == so.getSelektierterProzess())
									so.getGitter()[j][3]++;
							}
						}
					}

// Ausgabe
				methode.display(anzahlProzesse, so.getGitter()); //Ausgabe der Tabeller per Methode
				break;
				
			} catch (InputMismatchException e) { // Unerwartete Eingabe wird abgefangen
				System.out.println("Bist du Banane!? Keine Zeichen außer Zahlen!! \nNOCHMAL von vorn...!\n");
				sc.next();
			}
		}
	}
}
