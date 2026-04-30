package Ejercicio10;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Ejercicio10 {
    static void main() {
        Path fichero = Path.of("src/main/ficheros.txt");
        Path salida = Path.of("src/main/ficherosSalida");
        Pattern pattern = Pattern.compile("F\\s[A-Za-z]{3,}.[A-Za-z]{3}");

        try(Stream<String> lineas = Files.lines(fichero)){
            lineas.filter(l ->{
                if (l.matches("F\\s[A-Za-z]{3,}.[A-Za-z]{3}")){
                    return true;
                }else {
                    System.out.println("No se ha podido crear");
                    return false;
                }
                    })
                    .forEach(l -> {
                        try {
                            Files.createDirectories(salida);
                           if (Files.exists(salida.resolve(Path.of(l)))){
                               System.out.println("Ya esta creado");
                           }else {
                               Files.createFile(salida.resolve(Path.of(l)));
                               System.out.println("Se ha creado");
                           }
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                    });


        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
