package cwiczeniaKolekcjeIStrumienie.zadanie1;
//        Zad 1
//        Pobierz od użytkownika listę dowolnych marek samochodów. Następnie
//        stosując strumienie zwróć kolekcję Set marek, które mają w nazwie
//        składającej się z samych dużych liter co najmniej 3 samogłoski, których
//        suma kodów ASCII jest liczbą parzystą o nieparzystej cyfrze dziesiątek.


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner odczyt = new Scanner(System.in);
        System.out.println("podaj marki samochodow,");
        List<String> listaSamochodow = new ArrayList<>();
        String samochod;
        do {
            samochod = odczyt.nextLine();
            if (!samochod.equals("q")) {
                listaSamochodow.add(samochod);
            }
        } while (!samochod.equals("q"));

        for (String s : listaSamochodow) {
            if (s.length() >= s.replaceAll("[AEYIO]", "").length() + 3) {
                int a = 0;
                final char[] samo = {'A', 'E', 'Y', 'I', 'O'};
                for (int i = 0; i < s.length(); i++) {
                    for (char c : samo) {
                        if (s.charAt(i) == c) {
                            a += (byte) s.charAt(i);
                        }
                    }
                }
                if (a % 2 == 0 & (a / 10) % 2 != 0) {
                    System.out.println("a " + a);
                    System.out.println(s);
                }
            }
        }

        listaSamochodow.stream()
                .filter(auto -> auto.length() >= auto.replaceAll("[AEYIO]", "").length() + 3);
    }
}




