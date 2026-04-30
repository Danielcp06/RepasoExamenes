package Boletin7_4.Ejercicio9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Ejercicio9 {
    static void main() {
        Path fichero = Path.of("src/main/notas.txt");
        Scanner sc = new Scanner(System.in);
        while (true) {
            String palabra = sc.nextLine();
            if (palabra.equalsIgnoreCase("fin")) break;

            try {
                List<String> lineas;

                if (Files.exists(fichero)) {
                    // Si el archivo existe, lo leemos
                    lineas = Files.readAllLines(fichero);
                } else {
                    // Si no existe, creamos una lista vacía para no tener errores
                    lineas = new ArrayList<>();
                }
                lineas.add(palabra);
                Collections.sort(lineas); // Orden alfabético
                Files.write(fichero, lineas);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }


}
