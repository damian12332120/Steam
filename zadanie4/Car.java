package cwiczeniaKolekcjeIStrumienie.zadanie4;

//Zaimplementuj klasę Car. Klasa posiada pola składowe model, price, color, mileage oraz kolekcję napisów components
// reprezentująca wyposażenie samochodu. Dla klasy przygotuj podstawowe metody ułatwiające korzystanie z klasy.
// Przygotuj również logikę, która pozwoli walidować pola składowe klasy. Model musi składać się tylko i wyłącznie z
// dużych liter oraz białych znaków. Kolor przyjmuje wartości typu wyliczeniowego Color  (przygotuj przykładowe wartości
// dla typu wyliczeniowego). Pole milleage oraz price mogą przyjmować wartości tylko nieujemne.
//Kolekcja components może składać się z napisów, które  zawierają tylko i wyłącznie duże litery i białe znaki.
// Możesz zastosować wzorzec projektowy builder.

//W klasie Cars przygotuj metody, które pozwolą uzyskać następujące informacje:
//• Przesłonięta metoda toString, która zwróci napis pokazujący dane wszystkich samochodów z kolekcji w przejrzystym formacie.
//• Metoda, która zwraca nową kolekcję elementów Car posortowaną według podanego jako argument metody kryterium
// . Metoda powinna mieć możliwość sortowania po nazwie modelu, kolorze, cenie oraz przebiegu. Dodatkowo należy określić
// czy sortowanie ma odbywać się malejąco czy rosnąco.
//• Metoda zwraca kolekcję elementów typu Car, które posiadają przebieg o wartości większej niż wartość podana jako
// argument metody.

import java.util.*;

public class Car {
    private String model;
    private double price;
    private Color color;
    private int mileage;
    private List<String> components;

    public Car(String model, double price, Color color, int mileage, List<String> components) {
        setModel(model);
        setPrice(price);
        this.color = color;
        setMileage(mileage);
        this.components = components;
    }

    public static Car generujSamochod() {
        String[] modele = new String[]{"Astra", "Passat", "Golf", "Ibiza", "Candy"};
        Random random = new Random();
        String model = modele[random.nextInt(modele.length)];
        int cena = random.nextInt(1000) + 4000;
        Color color = Color.values()[random.nextInt(Color.values().length)];
        int przebieg = random.nextInt(10000) + 1000;
        return new Car(model, cena, color, przebieg, generujWyposazenie(random.nextInt(5)+1) );
    }

    private static List<String> generujWyposazenie(int iloscKomponentow) {
        LinkedList<String> wyposazenie = new LinkedList<>();
        wyposazenie.add("klimatyzacja");
        wyposazenie.add("radio");
        wyposazenie.add("ABS");
        wyposazenie.add("ESP");
        wyposazenie.add("szyberdach");
        Collections.shuffle(wyposazenie);
        List<String> wylosowaneElementy = new ArrayList<>();
        if (wyposazenie.size() < iloscKomponentow) {
            return wylosowaneElementy;
        }
        for (int i = 0; i < iloscKomponentow; i++) {
            wylosowaneElementy.add(wyposazenie.poll());
        }
        return wylosowaneElementy;
    }


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model.toUpperCase();
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = Math.abs(price);
    }

    public Color getColor() {
        return color;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = Math.abs(mileage);
    }

    public List<String> getComponents() {
        return components;
    }

    public void setComponents(List<String> components) {
        for (int i = 0; i < components.size(); i++) {
            components.set(i, components.get(i).toUpperCase());
        }
    }

    @Override
    public String toString() {
        return String.format("%s = %.2fzł, %s, %dkm,\n %s\n\n",model,price,color,mileage,components);
    }
}
//TODO serializacja i wzorzec builder