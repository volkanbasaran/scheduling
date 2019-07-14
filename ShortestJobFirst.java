package de.scheduling.controller;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ShortestJobFirst {

	static Scanner sc = new Scanner(System.in);

	public void sjf(int anzahlProzesse) {

		boolean falscheEingabe = false;

		while (!falscheEingabe) {

			try {

				int cpuLaufzeit[] = new int[anzahlProzesse + 1]; // CPU-Laufzeit der Prozesse
				int wartezeit[] = new int[anzahlProzesse + 1]; // Wartzeit der Prozesse
				int laufzeit[] = new int[anzahlProzesse + 1]; // Laufzeit der Prozesse

				// Variablen fuer die Berechnung von Wartezeit und Laufzeit der Prozesse
				double totalWartezeit = 0;
				double totalLaufzeit = 0;
			
				for (int i = 0; i < anzahlProzesse; i++) { 
					System.out.println("Bitte CPU-Laufzeit eingeben " + (i + 1) + ": ");
					cpuLaufzeit[i] = sc.nextInt();
				}

				for (int i = 0; i < anzahlProzesse; i++) {
					wartezeit[i] = 0;
					laufzeit[i] = 0;
				}
				
				// Sortierung der Prozesse nach CPU-Laufzeit
				for (int i = 0; i < anzahlProzesse; i++) {
					for (int j = 0; j < anzahlProzesse - 1; j++) {
						int temp;
						if (cpuLaufzeit[j] > cpuLaufzeit[j + 1]) { // Vergleich der CPU-Laufzeiten
							temp = cpuLaufzeit[j];
							cpuLaufzeit[j] = cpuLaufzeit[j + 1]; // wird ueberschrieben
							cpuLaufzeit[j + 1] = temp;

							temp = wartezeit[j];
							wartezeit[j] = wartezeit[j + 1];
							wartezeit[j + 1] = temp;
						}
					}
				}

				// Wartzeit wird berechnet
				wartezeit[0] = 0; // erster Prozess betraegt 0
				for (int i = 0; i < anzahlProzesse; i++) {
					laufzeit[i] = cpuLaufzeit[i] + wartezeit[i]; // Formel zur Berechnung der Laufzeit
					wartezeit[i + 1] = laufzeit[i]; // +1, da der erste bereits null betraegt
				}

				// Ausgabe auf der Konsole
				int total = 0;
				
				System.out.println("\nProzess  \tWartezeit \tLaufzeit\n");
				
				for (int i = 0; i < anzahlProzesse; i++) {
					total = laufzeit[i] + total;
					// Ausgabe von CPU, WZ & Gesamtzeit von Laufzeit und Wartezeit
					System.out.println(cpuLaufzeit[i] + "\t \t " + wartezeit[i] + "\t \t " + laufzeit[i]); 
																												
					// Gesamtzeit von Laufzeit und Wartezeit
					totalWartezeit = totalWartezeit + wartezeit[i];
					totalLaufzeit = totalLaufzeit + laufzeit[i];
				}

				// Durchschnitt der Laufzeit und Wartezeit
				double durchschnitt_Wartezeit = totalWartezeit / anzahlProzesse;
				double durchschnitt_Laufzeit = totalLaufzeit / anzahlProzesse;

				System.out.println("\nDurchschnitt Wartezeit ist: " + durchschnitt_Wartezeit + "ms");
				System.out.println("Durchschnitt Laufzeit ist : " + durchschnitt_Laufzeit + "ms");

				break;
			} catch (InputMismatchException e) { // Unerwartete Eingabe wird abgefangen
				System.out.println("Bist du Banane!? Keine Zeichen außer Zahlen!! \nNOCHMAL von vorn...!\n");
				sc.next();
			}
		}
	}
}
