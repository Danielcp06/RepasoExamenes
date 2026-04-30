package Ejercicio9;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Ejercicio9 {

    static void main() {

        Path fichero = Path.of("src/main/Matriculas.txt");
        Path salida = Path.of("src/main/SalidaMatricula.json");
        Pattern patron = Pattern.compile("(?<numeros>[0-9]{4})-(?<letras>[A-Z && [^AEIOU]]{3})");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (Stream<String> lineas = Files.lines(fichero)){
            List<MatriculaDTO> matriculasValidas = lineas.map(l -> patron.matcher(l))
                    .filter(m -> m.find())
                    .map(m ->{
                        String numeros = m.group("numeros");
                        String letras = m.group("letras");
                        return new MatriculaDTO(numeros,letras);
                   })
                    .toList();
            String json = gson.toJson(matriculasValidas);
            Files.writeString(salida,json, StandardOpenOption.CREATE);

        } catch (IOException e) {
            System.out.println("error");
        }
    }

}
