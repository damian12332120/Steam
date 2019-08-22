package cwiczeniaKolekcjeIStrumienie.zadanie3;
//Każdy samochód ma pole marka,
////cena, pojemność.

import java.util.Random;

public class Samochod {
    private String marka;
    private int cena;
    private int pojemnosc;

    public Samochod(String marka, int cena, int pojemnosc) {
        this.marka = marka;
        this.cena = cena;
        this.pojemnosc = pojemnosc;
    }

    public static Samochod dajSamochod() {
        String[] marki = new String[]{"Opel", "Fiat", "VW", "Merecedes", "Renault"};
        Random random = new Random();
        int cena = random.nextInt(10000) + 10000;
        int pojemnosc = random.nextInt(1000) + 500;
        String marka = marki[random.nextInt(marki.length)];

        return new Samochod(marka, cena, pojemnosc);
    }

    public String getMarka() {
        return marka;
    }

    public int getCena() {
        return cena;
    }

    public int getPojemnosc() {
        return pojemnosc;
    }

    @Override
    public String toString() {
        return "Samochod{" +
                "marka='" + marka + '\'' +
                ", cena=" + cena +
                ", pojemnosc=" + pojemnosc +
                '}';
    }
}
