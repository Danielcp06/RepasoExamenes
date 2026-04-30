package Boletin7_4.Ejercicio1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class Ejercicio1 {
    public static void main(String[] args) {
        // Ruta al archivo
        Path path = Path.of("src/main/Alumnos.txt");

        try (Stream<String> lineas = Files.lines(path)) {

            long totalPalabras = lineas
                    .filter(linea -> !linea.isBlank()) // Ignoramos líneas vacías
                    .mapToLong(linea -> linea.trim().split("\\s+").length) // Dividimos por espacios y contamos
                    .sum(); // Sumamos los resultados de cada línea

            System.out.println("El archivo tiene " + totalPalabras + " palabras.");

        } catch (IOException e) {
            System.err.println("No se pudo leer el archivo: " + e.getMessage());
        }

    }
}
