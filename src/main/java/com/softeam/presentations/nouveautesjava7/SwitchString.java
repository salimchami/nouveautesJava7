package com.softeam.presentations.nouveautesjava7;

public class SwitchString {
	public static String getMoodysNote(String continent) {
		String note;
		switch (continent) {
		case "Europe":
			note = "AA";
			break;
		case "Afrique":
		case "Asie":
			note = "BB";
			break;
		default:
			note = "AAA";
			break;
		}
		return note;
	}
}
