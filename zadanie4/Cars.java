package cwiczeniaKolekcjeIStrumienie.zadanie4;

import java.util.*;
import java.util.stream.Collectors;

public class Cars {
    private List<Car> samochody;


    public Cars() {
        samochody = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            samochody.add(Car.generujSamochod());
        }
    }

    // • Metoda, która zwraca nową kolekcję elementów Car posortowaną według podanego jako argument metody kryterium
    // . Metoda powinna mieć możliwość sortowania po nazwie modelu, kolorze, cenie oraz przebiegu. Dodatkowo należy określić
// czy sortowanie ma odbywać się malejąco czy rosnąco.
    public List<Car> sortowanie(Kryterium kryterium, String sortowanie) {

        List<Car> posortowana = new ArrayList<>(samochody);

        switch (kryterium) {
            case MODEL:
                posortowana.sort(Comparator.comparing(Car::getModel));
                break;
            case KOLOR:
                posortowana.sort(Comparator.comparing(samochod -> samochod.getColor().getKolor()));
                break;
            case CENA:
                posortowana.sort(Comparator.comparing(Car::getPrice));
                break;
            case PRZEBIEG:
                posortowana.sort(Comparator.comparing(Car::getMileage));
                break;
        }
        if (sortowanie.equals("rosnaco")) {
            Collections.reverse(posortowana);
        }
        return posortowana;
    }

    // Metoda zwraca kolekcję elementów typu Car, które posiadają przebieg o wartości większej niż wartość podana jako
    // argument metody.
    public List<Car> przebieg(int przebieg) {
        List<Car> posortowana = new ArrayList<>(samochody);
        return posortowana.stream()
                .filter(samochod -> samochod.getMileage() > przebieg)
                .collect(Collectors.toList());
    }

    //Metoda zwraca mapę, której kluczem jest kolor, natomiast wartością ilość samochodów, które posiadają taki kolor.
    // Mapa powinna być posortowana malejąco po wartościach.
    public Map<Color, Integer> iloscSamochodow() {
        List<Car> posortowana = new ArrayList<>(samochody);
        Map<Color, Integer> mapka = new HashMap<>();

        for (Color color : Color.values()) {
            int ilosc = (int) posortowana.stream()
                    .filter(samochody -> samochody.getColor() == color)
                    .count();
            mapka.put(color, ilosc);

        }
        return mapka;

    }


    // • Metoda zwraca mapę, której kluczem jest nazwa modelu samochodu, natomiast wartością obiekt klasy Car, który
// reprezentuje najdroższy samochód o tej nazwie modelu. Mapa powinna być posortowana kluczami malejąco.
    public Map<String, Car> najdrozszeModele() {
        Map<String, Car> najdrozszeAuta = new HashMap<>();

        List<String> modele = new ArrayList<>();
        samochody.forEach(car -> modele.add(car.getModel()));

        for (String model : modele) {
            Car samochod = samochody.stream()
                    .filter(car -> car.getModel().equals(model))
                    .max(Comparator.comparing(Car::getPrice))
                    .orElse(new Car("", -1, Color.BIALY, -1, new ArrayList<>()));
            najdrozszeAuta.put(model, samochod);
        }
        return najdrozszeAuta.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (samochod1, samochod2) -> samochod1, LinkedHashMap::new));
    }

    //  Metoda wypisuje statystykę samochodów w zestawieniu. W statystyce powinny znajdować się wartość średnia,
    //  wartość najmniejsza, wartość największa dla pól opisujących cenę oraz przebieg samochodów.
    public void statystyki() {
        int sredniaCena = 0;
        double najmniejszaWartosc = Integer.MAX_VALUE;
        double najwiekszaWartosc = 0;
        int sredniPrzebieg = 0;
        int najmniejszyPrzebieg = Integer.MAX_VALUE;
        int najwiekszyPrzebieg = 0;
        int iloscAut = samochody.size();
        for (Car car : samochody) {
            if (najwiekszaWartosc < car.getPrice()) {
                najwiekszaWartosc = car.getPrice();
            }
            if (najmniejszaWartosc > car.getPrice()) {
                najmniejszaWartosc = car.getPrice();
            }
            if (najwiekszyPrzebieg < car.getMileage()) {
                najwiekszyPrzebieg = car.getMileage();
            }
            if (najmniejszyPrzebieg > car.getMileage()) {
                najmniejszyPrzebieg = car.getMileage();
            }
            sredniPrzebieg += car.getMileage();
            sredniaCena += car.getPrice();
        }
        sredniPrzebieg /= iloscAut;
        sredniaCena /= iloscAut;
        System.out.println("Srednia cena auta wynosi:" + sredniaCena);
        System.out.println("Największa cena auta wynosi:" + najwiekszaWartosc);
        System.out.println("Najmniejsza cena auta wynosi:" + najmniejszaWartosc);
        System.out.println("Sredni przebieg wynosi: " + sredniPrzebieg);
        System.out.println("Największy przebieg wynosi: " + najwiekszyPrzebieg);
        System.out.println("Najmniejszy przebieg wynosi: " + najmniejszyPrzebieg);
    }

    //• Metoda zwraca samochód, którego cena jest największa. W przypadku kiedy więcej niż jeden samochód posiada największą
    // cenę należy zwrócić kolekcję tych samochodów.
    public Car najdrozszeAuto() {
        //po cenie max i potem w kolejnym steamie filtruje liste i zwracam filtrowana
        return samochody.stream().max(Comparator.comparing(Car::getPrice))
                .orElse(new Car("", -1, Color.BIALY, -1, new ArrayList<>()));
    }

    //• Metoda zwraca kolekcję samochodów, w której każdy samochód posiada posortowaną alfabetycznie kolekcję komponentów.
    public List<Car> posortowaneKomponenty() {
        List<Car> posortowana = new ArrayList<>(samochody);
        for (Car car : posortowana) {
            car.getComponents().sort(Comparator.naturalOrder());
        }
        return posortowana;
    }

    // • Metoda zwraca mapę, której kluczem jest nazwa komponentu, natomiast wartością jest kolekcja samochodów,
    // które posiadają ten komponent. Pary w mapie powinny być posortowane malejąco po ilości elementów w kolekcji reprezentującej
    // wartość pary.
    public Map<String, List<Car>> samochodyOTakichSamychComponentach() {

        return samochody.stream()
                .flatMap(car -> car.getComponents().stream())
                .distinct()
                .collect(Collectors.toMap(
                        component -> component,
                        component -> samochody.stream().filter(car -> car.getComponents()
                                .contains(component)).collect(Collectors.toList())
                ));
    }

//• Metoda zwraca kolekcję samochodów, których cena znajduje się w przedziale cenowym <a, b>. Wartości a oraz b
// przekazywane są jako argument metody. Kolekcja powinna być posortowana alfabetycznie według nazw samochodów.

    public List<Car> samochodyZPrzedzialy(double a, double b) {

        return samochody.stream()
                .filter(car -> car.getPrice() >= a && car.getPrice() <= b)
                .sorted()
                .collect(Collectors.toList());
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Car samodod : samochody) {
            sb.append(samodod);
        }
        return sb.toString();
    }
}
