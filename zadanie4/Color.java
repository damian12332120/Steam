package cwiczeniaKolekcjeIStrumienie.zadanie4;


public enum Color{
    BIALY("biały"), NIEBIESKI("niebieski"),CZARNY("czarny"), CZERWONY("czerwony"), ZIELONY("zielony");

    private String kolor;

    Color(String kolor) {
        this.kolor = kolor;
    }

    public String getKolor() {
        return kolor;
    }

    @Override
    public String toString() {
        return  kolor;
    }
}
