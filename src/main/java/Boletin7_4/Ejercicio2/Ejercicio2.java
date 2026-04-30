package Boletin7_4.Ejercicio2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class Ejercicio2 {
    public static void main(String[] args) {
        Path path = Path.of("src/main/Alumnos.txt");
        String palabraABuscar = "JUAN"; // Cambia esto por la palabra que quieras

        try (Stream<String> lineas = Files.lines(path)) {

            long total = lineas
                    .flatMap(linea -> Stream.of(linea.split("\\s+"))) // Convertimos cada línea en un flujo de palabras
                    .filter(palabra -> palabra.equalsIgnoreCase(palabraABuscar)) // Comparamos ignorando mayúsculas/minúsculas
                    .count(); // Contamos cuántas veces pasó el filtro

            System.out.println("La palabra '" + palabraABuscar + "' aparece " + total + " veces.");

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }

    }
}
