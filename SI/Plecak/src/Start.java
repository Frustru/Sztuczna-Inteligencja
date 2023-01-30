/*
    Wyjasnienie
     Do plecaka wrzucamy przedmioty, ktorych rozmiar podajemy na poczatku - jak w poleceniu, najmniejsza waga - najwieksza wartosc
     
     size - pojemnosc plecaka, maksymalna waga

     n - liczba dostepnych rzeczy ktore mozna wybrać

     weights - tablica z wagami przedmioitow

     values - tablica z wartosciami przedmiotow

     id - tablica z numerami id */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;



class Plecak {
    public Plecak(int size, int n, int[] weights, int[] values, int[] id) {

        // tablica-tabela
        int[][] array = new int[n + 1][size + 1];

        // tablica w ktorej przechowujemy produkty
        int[][] produkty = new int[n + 1][size + 1];

        for (int i = 0; i <= size; i++) {
            array[0][i] = 0;   }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= size; j++) {
                int item;
                if (weights[i] <= j) {
                    item = array[i - 1][j - weights[i]];
                } else item = 0;

                if ((weights[i] <= j) && (values[i] + item) > array[i - 1][j]) {
                    array[i][j] = item + values[i];
                    produkty[i][j] = 1;
                } else {
                    array[i][j] = array[i - 1][j];
                    produkty[i][j] = 0;
                }
            }
        }

        // Lista z produktami, które należy włożyć do plecaka
        ArrayList<Integer> zawartoscPlecaka = new ArrayList<>();
        int tW = size;
        for (int i = n; i >= 0; i--) {
            if (produkty[i][tW] == 1) {
                tW -= weights[i];
                zawartoscPlecaka.add(i);
            }
        }

        // Sortowanie listy
        Collections.sort(zawartoscPlecaka);

        // Wyświetlenie rozwiązania
        System.out.println("\nWybrane przedmioty:");
        int totalWeight = 0;
        int totalValue = 0;
        for (int i : zawartoscPlecaka) {
            totalWeight += weights[i];
            totalValue += values[i];
            System.out.println(">> Produkt: [id] " + id[i]);
        }
        if (totalWeight==0 || totalValue==0){
            System.out.println("Nic nie zabrales");
        }
        else {
            System.out.print("\n>> zabrales przedmioty o wadze" + totalWeight);
            System.out.println(" ich laczna wartosc to: " + totalValue + " zl << ");
        }
    }
}

public class Start {
    protected Plecak plecak;
    public Start() {

        String plik = "dane.csv";
        BufferedReader fileReader = null;

        final String SEPARATOR = ";";
        String line = "";

        ArrayList<Integer> listIds = new ArrayList<>();
        ArrayList<Integer> listWeights = new ArrayList<>();
        ArrayList<Integer> listValues = new ArrayList<>();

        try {

            // Create file reader
            fileReader = new BufferedReader(new FileReader(plik));

            fileReader.readLine();

            while ((line = fileReader.readLine()) != null) {

                // Wczytaj dane rozdzielone separatorem ;
                String[] dane = line.split(SEPARATOR);

                int id = Integer.parseInt(dane[0]);
                int weight = Integer.parseInt(dane[1]);
                int value = Integer.parseInt(dane[2]);

                // Add data to lists
                listIds.add(id);
                listWeights.add(weight);
                listValues.add(value);
            }

            //brak pliku lub blad odczytu
        } catch (FileNotFoundException e) {
            System.out.println("Nie można odczytać pliku...");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // Zamyka plik
                if (fileReader != null) fileReader.close();
            } catch (IOException e) {
                System.out.println("Nie mozna zamknąć pliku...");
            }
        }
      
        int[] id = new int[listIds.size()];
        for (int i = 0; i < listIds.size(); i++) {
            id[i] = listIds.get(i);
        }
        int[] weights = new int[listWeights.size()];
        for (int i = 0; i < listWeights.size(); i++) {
            weights[i] = listWeights.get(i);
        }
        int[] values = new int[listValues.size()];
        for (int i = 0; i < listValues.size(); i++) {
            values[i] = listValues.get(i);
        }
        int n = id.length;
        String g = "*******************";
        System.out.print("   "+g+g+"\n   ** Dane zostały poprawnie  wczytane **\n   "+g+g);

        Scanner in = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n\n"+g+" MENU "+g+"\n\tWybierz opcję:\n[1] >>> Problem plecakowy\n[2] >>> Zakończ");
            System.out.print("[?] >>> ");
            choice = in.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("    >>> Podaj pojemność plecaka: ");
                    int size = in.nextInt();

                    plecak = new Plecak(size, n, weights, values, id);
                    break;
                case 2:
                    System.out.println("Koniec.");
                    System.exit(0);
                default:
                    System.out.println("\n    !!! Wybierz prawidłową opcję !!! ");
                    break;
            }
        } while (true);
    }
    public static void main(String[] args) {
        Start obj = new Start();
    }
}