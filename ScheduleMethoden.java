package de.scheduling.model;

import java.util.Scanner;

public class ScheduleMethoden {

	static Scanner sc = new Scanner(System.in);
	
	//Die Eingabe von CPU-Laufzeit und Ankunftszeit
	public void eingabeCpuUndAnkZeit(int anzahlProzesse, int[][] gitter) {

		for (int i = 1; i <= anzahlProzesse; i++) 	// i +1 Schleife (erst einmal der Wert selbst und dann
													// inkrementiert (wert dazu gerechnet))
													// faengt bei 1 an und laeuft solange bis eingegbenen Prozess
		{
			System.out.println("Bitte CPU-Laufzeit eingeben " + i + ": ");
			gitter[i][1] = sc.nextInt(); // in Array fuer cpuLaufzeit genutzt und 'gefaellt'

			System.out.println("Bitte Ankunftszeit eingeben " + i + ": "); // Ankunftszeit
			gitter[i][0] = sc.nextInt(); // IN Array fuer Ankunftszeit hinterlegt
		}
	}
	
	//Die Ausgabe der Ergebnistabelle
	public void display(int anzahlProzesse, int[][] gitter) {

		System.out.println("\n\nProzess\t Laufzeit  \t Wartezeit  ");
		for (int i = 1; i <= anzahlProzesse; i++) {
			System.out.printf("%d\t%3dms\t\t%3dms", i, gitter[i][3], gitter[i][2]);
			System.out.println();
		}
		System.out.println();

		// Ausgabe des Durchschnitts der WARTEZEIT und LAUFZEITderPROZESSE
		double wartezeit = 0;
		double laufzeitDerProzesse = 0;

		for (int i = 1; i <= anzahlProzesse; i++) {
			wartezeit += gitter[i][2];
			laufzeitDerProzesse += gitter[i][3];
		}

		wartezeit = wartezeit / anzahlProzesse;
		laufzeitDerProzesse = laufzeitDerProzesse / anzahlProzesse;

		System.out.println("Durchschnitt Wartezeit ist: " + wartezeit + "ms");
		System.out.println("Durchschnitt Laufzeit ist: " + laufzeitDerProzesse + "ms\n");
	}
}
