package no.hvl.dat100.jplab11.oppgave3;

import no.hvl.dat100.jplab11.common.TODO;
import no.hvl.dat100.jplab11.oppgave1.*;

public class Blogg {

	protected Innlegg[] innleggtabell;
	protected int nesteledig;
	
	public Blogg() {
		this.innleggtabell = new Innlegg[20];
	}

	public Blogg(int lengde) {
		this.innleggtabell = new Innlegg[lengde];
	}

	public void økAntall() {
		this.nesteledig++;
	}
	
	public void minskAntall() {
		this.nesteledig--;
	}
	
	public int getAntall() {
		return this.nesteledig;
	}
	
	public Innlegg[] getSamling() {
		return this.innleggtabell;
	}
	
	public boolean finnes(Innlegg innlegg) {
		if (innlegg == null) return false;
		Innlegg[] innleggSamling = getSamling();
		for (int i = 0; i <= getAntall(); i++) {
			if (innlegg.erLik(innleggSamling[i])) return true;
//			if (innleggSamling[i] == null) continue;
//			if (innleggSamling[i].getId() == innlegg.getId()) return true; 
		}
		return false;
	}
	
	public int finnInnlegg(Innlegg innlegg) {
		if (innlegg == null) return -1;
		Innlegg[] innleggSamling = getSamling();
		for (int i = 0; i<= getAntall(); i++) {
			if (innlegg.erLik(innleggSamling[i])) return i;
//			if (innleggSamling[i] == null) continue;
//			if (innleggSamling[i].getId() == innlegg.getId()) return i;
		}
		return -1;
	}
	
	public boolean ledigPlass() {
		return getAntall() < getSamling().length;
	}
	
	public boolean leggTil(Innlegg innlegg) {
		if(ledigPlass()) {
			Innlegg[] innleggSamling = getSamling();
			if(finnes(innlegg)) {
				int indeks = finnInnlegg(innlegg);
				if (indeks == -1) {
					innleggSamling[getAntall()] = innlegg;
					this.økAntall();
					return true;
				}
				return false;
			} else {
				innleggSamling[getAntall()] = innlegg;
				this.økAntall();
				return true;
			}
		}
		return false;
	}
	
	public String toString() {
		String streng = getAntall() + "\n";
		Innlegg[] samling = getSamling();
		for (Innlegg innlegg : samling) {
			if (innlegg != null) streng += innlegg.toString();
		}
		return streng;
	}

	// valgfrie oppgaver nedenfor
	
	public void utvid() {
		Innlegg[] gammelSamling = getSamling();
		Innlegg[] nySamling = new Innlegg[gammelSamling.length * 2];
		for (int i = 0; i <= gammelSamling.length - 1; i++) {
			nySamling[i] = gammelSamling[i];
		}
		innleggtabell = nySamling;
	}
	
	public boolean leggTilUtvid(Innlegg innlegg) {
		if(!ledigPlass()) {
			utvid();
			leggTil(innlegg);
			return true;
		}
		return false;
	}
	
	public boolean slett(Innlegg innlegg) {
		// SUS ඞ
		if (finnes(innlegg)) {
			int indeks = finnInnlegg(innlegg);
			if (indeks != -1) {
				getSamling()[indeks] = null;
				minskAntall();
				return true;
			}
			return false;
		}
		return false;
	}
	
	public int[] search(String keyword) {
		
		throw new UnsupportedOperationException(TODO.method());

	}
}