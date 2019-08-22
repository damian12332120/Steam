package cwiczeniaKolekcjeIStrumienie.zadanie3;

//Zad 4
//Sporządź listę obiektów klasy Samochód. Każdy samochód ma pole marka,
//cena, pojemność. Klasę należy zaopatrzyć w niezbędne metody potrzebne
//do jej prawidłowego działania i zarządzania nią. Stosując strumienie
//zwróć średnią arytmetyczną cen samochodów w strumieniu oraz w stosując
//drugi strumień posortowaną listę samochodów malejąco według
//pojemności.

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Samochod> samochody = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            samochody.add(Samochod.dajSamochod());
        }

        double sredniaCena = samochody.stream()
                .mapToDouble(Samochod::getCena)
                .average().orElse(-1);
        System.out.println(sredniaCena);

        samochody.stream()
                .sorted(Comparator.comparing(Samochod::getPojemnosc).reversed())
                .forEach(System.out::println);

    }
}
