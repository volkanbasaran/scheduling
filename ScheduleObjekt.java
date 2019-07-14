package de.scheduling.model;

public class ScheduleObjekt {

	private int gitter[][];
	private int laufzeitDerProzesse, selektierterProzess;

	//Konstruktor
	public ScheduleObjekt(int anzahlDerProzesse) {
		super();
		this.gitter = new int[anzahlDerProzesse + 1][4]; // Die zweite Dimesion des Arrays [4] sind für 
		this.laufzeitDerProzesse = 0;					//0 = ANKUNFTSZEIT, 1 = CPU-Laufzeit  
		this.selektierterProzess = 1;					//2 = WARTEZEIT, 3 = LAUFZEITderPROZESSE	
								 
	}

	//Getter und Setter Methoden 
	public int[][] getGitter() {
		return gitter;
	}

	public void setGitter(int[][] gitter) {
		this.gitter = gitter;
	}

	public int getLaufzeitDerProzesse() {
		return laufzeitDerProzesse;
	}

	public void setLaufzeitDerProzesse(int pointer) {
		this.laufzeitDerProzesse = pointer;
	}

	public int getSelektierterProzess() {
		return selektierterProzess;
	}

	public void setSelektierterProzess(int derProzess) {
		this.selektierterProzess = derProzess;
	}

}
