package no.hvl.dat100.jplab11.oppgave5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import no.hvl.dat100.jplab11.common.TODO;
import no.hvl.dat100.jplab11.oppgave1.*;
import no.hvl.dat100.jplab11.oppgave2.*;
import no.hvl.dat100.jplab11.oppgave3.*;

import javax.swing.JOptionPane;

public class LesBlogg {

	private static String TEKST = "TEKST";
	private static String BILDE = "BILDE";

	public static Blogg les(String mappe, String filnavn) {
		Blogg blogg = null;
		try {
			File file = new File(mappe + filnavn);
			BufferedReader reader = new BufferedReader(new FileReader(file));

			int antall = Integer.parseInt(reader.readLine());
			String line;

			blogg = new Blogg(antall);

			while ((line = reader.readLine()) != null) {
				if (line.equals(TEKST)) {
					// Alternativ 1: Leser linjer rett inn i parameterlisten til Tekst().
					Innlegg tekstinnlegg = new Tekst(Integer.parseInt(reader.readLine()), reader.readLine(),
							reader.readLine(), Integer.parseInt(reader.readLine()), reader.readLine());
					
					blogg.leggTil(tekstinnlegg);

				} else if (line.equals(BILDE)) {
					// Alternativ 2: Lagrer leste linjer i variabler før de legges i parameterlisten til Bilde().
					int id = Integer.parseInt(reader.readLine());
					String bruker = reader.readLine();
					String dato = reader.readLine();
					int likes = Integer.parseInt(reader.readLine());
					String txt = reader.readLine();
					String url = reader.readLine();
					
					Innlegg bildeinnlegg = new Bilde(id, bruker, dato, likes, txt, url);
					blogg.leggTil(bildeinnlegg);
				}
			}
			reader.close();
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		return blogg;
	}
}
