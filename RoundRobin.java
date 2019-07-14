package de.scheduling.controller;

import java.util.InputMismatchException;
import java.util.Scanner;

import de.scheduling.model.ScheduleMethoden;
import de.scheduling.model.ScheduleObjekt;

public class RoundRobin {

	static Scanner sc = new Scanner(System.in); // wird als static deklariert damit man es nicht schlieﬂen muss

	public void rr(int anzahlProzesse) {

		int quantum = 0;
		ScheduleObjekt so = new ScheduleObjekt(anzahlProzesse);
		ScheduleMethoden methode = new ScheduleMethoden();

		boolean falscheEingabe = false;

		while (!falscheEingabe) {

			try {

				System.out.println("Bitte Zeitquantum eingeben ");
				quantum = sc.nextInt();

				for (int i = 1; i <= anzahlProzesse; i++) {
					System.out.println("Bitte CPU-Laufzeit eingeben " + i + ": ");
					so.getGitter()[i][1] = sc.nextInt();
				}

				for (int i = 1; i <= anzahlProzesse; i++) {
					so.setLaufzeitDerProzesse(so.getLaufzeitDerProzesse() + so.getGitter()[i][1]);
				}
				

				for (int i = 0; i < so.getLaufzeitDerProzesse(); i++) // Laeuft so lange bis Laufzeit der Prozesse sein Ende erreicht hat
				{
					
					so.getGitter()[so.getSelektierterProzess()][1]--;

					for (int j = 1; j <= anzahlProzesse; j++) {
						if (so.getGitter()[j][0] <= i) 
						{
							if (so.getGitter()[j][1] != 0)
							{
								so.getGitter()[j][3]++;

								if (j != so.getSelektierterProzess())
									so.getGitter()[j][2]++;
							} else if (j == so.getSelektierterProzess())
								so.getGitter()[j][3]++;
						}
					}
					
					int ausfuehrungsreihenfolge[] = new int[so.getLaufzeitDerProzesse()];
					ausfuehrungsreihenfolge[i] = so.getSelektierterProzess(); // derProzess wird dann zu
																				// "ausfuehrungsreihenfolge[]" hinzugefuegt
					
					// Prozess Ausfuehrungsreihenfolge wird erstellt und ausgegeben
					if (i != 0) {
						if (so.getSelektierterProzess() != ausfuehrungsreihenfolge[i - 1])

						{
							System.out.print("--" + i + "--P" + so.getSelektierterProzess());
						}
					} else
						System.out.print(i + "--P" + so.getSelektierterProzess());
					if (i == so.getLaufzeitDerProzesse() - 1)
						System.out.print("--" + (i + 1));


					int startZeit = 0; // Variable fuer quantum ; Es wird mit dem weiter gerechnet, was eingegeben wurde.
					
					startZeit++;
					
					if (startZeit == quantum || so.getGitter()[so.getSelektierterProzess()][1] == 0) {

						for (int j = 0; j < anzahlProzesse; j++) {
							so.setSelektierterProzess(so.getSelektierterProzess() + 1);
							if (so.getSelektierterProzess() == (anzahlProzesse + 1))
								so.setSelektierterProzess(1);
							if (so.getGitter()[so.getSelektierterProzess()][1] != 0)
								break;
						}
					}
				}
// Ausgabe
				System.out.println("\nAusf¸hrungsreihenefolge: ");
				methode.display(anzahlProzesse, so.getGitter()); // Herholen der DisplayMethode

				break;
				
			} catch (InputMismatchException e) { // Unerwartete Eingabe wird abgefangen
				System.out.println("Bist du Banane!? Keine Zeichen auﬂer Zahlen!! \nNOCHMAL von vorn...!\n");
				sc.next();
			}
		}
	}
}
