package Boletin7_4.Ejercicio3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class Ejercicio3 {
    static void main() {
        Path path = Path.of("src/main/Alumnos.txt");
        String palabra = "JUAN";
        try (Stream<String> lineas = Files.lines(path)) {
            lineas.filter(l -> l.contains(palabra))
                    .forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

}
