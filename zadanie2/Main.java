package cwiczeniaKolekcjeIStrumienie.zadanie2;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

//Pobierz od użytkownika listę dowolnych dat. Następnie stosując
//strumienie odrzuć te daty, które mają więcej niż 20 lat. Następnie
//przerób strumień dat na strumień liczb całkowitych, które odpowiadają
//numerowi miesiąca daty. Otrzymany strumień posortuj malejąco i zwróć
//w postaci kolekcji Set.
public class Main {

    public static void main(String[] args) {
        List<LocalDate> daty = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            daty.add(wygenerujDate());
        }

        Set<Integer> miesiace = daty.stream()
                .filter(data -> data.getYear() + 20 >= LocalDate.now().getYear())
                .map(LocalDate::getMonthValue)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toCollection(TreeSet::new));
        miesiace.forEach(System.out::println);
    }


    public static LocalDate wygenerujDate() {
        Random random = new Random();
        int lata = random.nextInt(40);
        int miesiac = random.nextInt(12);
        return LocalDate.now().minusYears(lata).minusMonths(miesiac);
    }
}
