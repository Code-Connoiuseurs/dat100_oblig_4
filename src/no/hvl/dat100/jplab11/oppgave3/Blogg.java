package no.hvl.dat100.jplab11.oppgave3;

import no.hvl.dat100.jplab11.oppgave1.*;
import no.hvl.dat100.jplab11.oppgave2.*;

public class Blogg {

	private Innlegg[] innleggtabell;
	private int nesteLedig;

	public Blogg() {
		innleggtabell = new Innlegg[20];
	}

	public Blogg(int lengde) {
		innleggtabell = new Innlegg[lengde];
	}

	public int getAntall() {
		return nesteLedig;
	}

	public Innlegg[] getSamling() {
		return innleggtabell;
	}

	public int finnInnlegg(Innlegg innlegg) {
		for (int i = 0; i < innleggtabell.length; i++) {
			if (innleggtabell[i] == null) {
				continue;
			}
			if (innleggtabell[i].erLik(innlegg)) {
				return i;
			}
		}
		return -1;
	}

	public boolean finnes(Innlegg innlegg) {
		for (int i = 0; i < innleggtabell.length; i++) {
			if (innleggtabell[i] == null) {
				continue;
			}
			if (innleggtabell[i].erLik(innlegg)) {
				return true;
			}
		}
		return false;
	}

	public boolean ledigPlass() {
		return (getAntall() < getSamling().length);
	}

	public boolean leggTil(Innlegg innlegg) {
		if (!finnes(innlegg)) {
			innleggtabell[nesteLedig] = innlegg;
			nesteLedig++;
			return true;
		}
		return false;
	}

	public String toString() {
		String ut = nesteLedig + "\n";
		for (Innlegg innlegg : innleggtabell) {
			if (innlegg != null) {
				ut += innlegg.toString();
			}
		}
		return ut;
	}

	// valgfrie oppgaver nedenfor

	public void utvid() {
		Innlegg[] nyTab = new Innlegg[innleggtabell.length * 2];
		for (int i = 0; i < innleggtabell.length; i++) {
			nyTab[i] = innleggtabell[i];
		}
		innleggtabell = nyTab;
	}

	public boolean leggTilUtvid(Innlegg innlegg) {
		if (!ledigPlass() && !finnes(innlegg)) {
			utvid();
			leggTil(innlegg);
			return true;
		}
		if (!finnes(innlegg)) {
			leggTil(innlegg);
			return true;
		}
		return false;
	}

	public boolean slett(Innlegg innlegg) {
		if (finnes(innlegg) && finnInnlegg(innlegg) != -1) {
			innleggtabell[finnInnlegg(innlegg)] = null;
			nesteLedig--;
			return true;
		}
		return false;
	}

	public int[] search(String user, String ord) {
		// Søker gjennom hele innlegget, inkludert brukernavn, dato og id,
		// og ikke kun teksten slik oppgaven ber om. Ikke tydelig i oppgaveteksten hva
		// user-parameteret skulle brukes til.

		int[] treffTabell = new int[getSamling().length];
		int treffIndeks = 0;

		for (int i = 0; i < getSamling().length; i++)
			if (innleggtabell[i].toString().contains(ord)) {
				treffTabell[treffIndeks] = innleggtabell[i].getId();
				treffIndeks++;
			}
//		Skriver ut til konsoll for testing. 
//		for (int i : treffTabell) {
//			if (i != 0) {
//				System.out.println("Treff i innlegg med id: " + i);
//			}
//		}
		return treffTabell;

	}
}
