package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] lukujono;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm = 0;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        lukujono = new int[KAPASITEETTI];
        this.kasvatuskoko = OLETUSKASVATUS;
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti > 0) {
            lukujono = new int[kapasiteetti];
            this.kasvatuskoko = OLETUSKASVATUS;
        }
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti >= 0 && kasvatuskoko >= 0) {
            lukujono = new int[kapasiteetti];
            this.kasvatuskoko = kasvatuskoko;
        }
    }

    public boolean lisaa(int luku) {
        if (alkioidenLkm == 0) {
            lisaaEka(luku);
            return true;
        }
        return handleLisays(luku);
    }

    private void lisaaEka(int luku) {
        lukujono[0] = luku;
        alkioidenLkm++;
    }

    private boolean handleLisays(int luku) {
        if (!onJoukossa(luku)) {
            lukujono[alkioidenLkm] = luku;
            alkioidenLkm++;
            kasvataTaulukonKokoa();
            return true;
        }
        return false;
    }

    private void kasvataTaulukonKokoa() {
        int[] taulukkoOld = new int[lukujono.length];
        kopioiTaulukko(lukujono, taulukkoOld);
        lukujono = new int[alkioidenLkm + kasvatuskoko];
        kopioiTaulukko(taulukkoOld, lukujono);
    }

    public boolean onJoukossa(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukujono[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukujono[i]) {
                lukujono[i] = 0;
                siirto(i);
                return true;
            }
        }
        return false;
    }

    private void siirto(int kohta) {
        for (int j = kohta; j < alkioidenLkm - 1; j++) {
           int apu = lukujono[j];
            lukujono[j] = lukujono[j + 1];
            lukujono[j + 1] = apu;
        }
        alkioidenLkm--;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }

    }

    public int getAlkioidenLkm() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder("{"+lukujono[0]);
        for (int i = 1; i < alkioidenLkm; i++) {
            sb.append(", " + lukujono[i]);
        }
        sb.append("}");
        return sb.toString();
    }

    public int[] alustaIntJoukoksi() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = lukujono[i];
        }
        return taulu;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko yhdisteJoukko = new IntJoukko();
        int[] aTaulu = yhdisteJoukko.alustaIntJoukoksi();
        for (int i = 0; i < aTaulu.length; i++) {
            yhdisteJoukko.lisaa(aTaulu[i]);
            yhdisteJoukko.lisaa(aTaulu[i]);
        }

        return yhdisteJoukko;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko leikkausJoukko = new IntJoukko();
        int[] bTaulu = leikkausJoukko.alustaIntJoukoksi();
        for (int i = 0; i < bTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (bTaulu[i] == bTaulu[j]) {
                    leikkausJoukko.lisaa(bTaulu[j]);
                }
            }
        }
        return leikkausJoukko;

    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko erotusJoukko = new IntJoukko();
        int[] aTaulu = erotusJoukko.alustaIntJoukoksi();
        for (int i = 0; i < aTaulu.length; i++) {
            erotusJoukko.lisaa(aTaulu[i]);
            erotusJoukko.poista(i);
        }
        return erotusJoukko;
    }

}
