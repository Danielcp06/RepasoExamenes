package Boletin7_4.Ejercicio5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class Ejercicio5 {
    static void main() {
        Path fichero = Path.of("src/main/Alumnos.txt");
        Path salida = Path.of("src/main/java/Boletin7_4/Ejercicio5/dato.txt");
        Scanner sc = new Scanner(System.in);

        Boolean recorrer = true;
        while(recorrer) {
            String palabra = sc.nextLine();
            if (palabra.equalsIgnoreCase("fin")){
                break;
            }
            try {
                Files.writeString(salida,palabra + System.lineSeparator(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            } catch (IOException e) {
                System.out.println("ERROR: "+e.getMessage());
            }
        }
    }


}
