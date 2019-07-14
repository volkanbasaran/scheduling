package de.scheduling.controller;

import java.util.InputMismatchException;
import java.util.Scanner;

import de.scheduling.model.ScheduleMethoden;
import de.scheduling.model.ScheduleObjekt;

public class ShortestRemainingTimeFirst {


	static Scanner sc = new Scanner(System.in);

	public void srtf(int anzahlProzesse) {

		ScheduleMethoden methode = new ScheduleMethoden();
		ScheduleObjekt so = new ScheduleObjekt(anzahlProzesse);
		
		boolean falscheEingabe = false;

		while (!falscheEingabe) {

			try {

				methode.eingabeCpuUndAnkZeit(anzahlProzesse, so.getGitter());

				for (int i = 1; i <= anzahlProzesse; i++)													
				{
					so.setLaufzeitDerProzesse(so.getLaufzeitDerProzesse() + so.getGitter()[i][1]);
				}
				
				int ausfuehrungsreihenfolge[] = new int[so.getLaufzeitDerProzesse()]; // Variable für die ausführungsreihenfolge wird
																						//mit dem Array mit der Laufzeit der Prozesse befüllt
				System.out.println("\nAusführungsreihenfolge:");

				for (int i = 0; i < so.getLaufzeitDerProzesse(); i++)
				{
					
					so.setSelektierterProzess(0);
					int zahl = 1000000;
					for (int j = 1; j <= anzahlProzesse; j++) {
						if (so.getGitter()[j][0] <= i)
						{
							if (so.getGitter()[j][1] < zahl && so.getGitter()[j][1] != 0) // Wenn CPU-Laufzeit kleiner als 9999 und ungleich 0
							{
								zahl = so.getGitter()[j][1];
								so.setSelektierterProzess(j);
							}
						}
					}
				
					// selektierterProzess wird dann zu "ausfuehrungsreihenfolge[]" hinzugefuegt
					ausfuehrungsreihenfolge[i] = so.getSelektierterProzess(); 
// Berechnung
					so.getGitter()[so.getSelektierterProzess()][1]--; // minus restliche Zeit

					// Wartzeit und Laufzeit werden berechnet
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

					
					
//Prozess Ausfuehrungsreihenfolge wird erstellt und ausgegeben

					if (i != 0) {
						if (so.getSelektierterProzess() != ausfuehrungsreihenfolge[i - 1])

						{
							System.out.print("--" + i + "--P" + so.getSelektierterProzess());
						}
					} else
						System.out.print(i + "--P" + so.getSelektierterProzess());
					if (i == so.getLaufzeitDerProzesse() - 1)
						System.out.print("--" + (i + 1));
				}

// Ausgabe
				methode.display(anzahlProzesse, so.getGitter());
				break;
				
			} catch (InputMismatchException e) { // Unerwartete Eingabe wird abgefangen
				System.out.println("Bist du Banane!? Keine Zeichen außer Zahlen!! \nNOCHMAL von vorn...!\n");
				sc.next();
			}
		}
	}
}