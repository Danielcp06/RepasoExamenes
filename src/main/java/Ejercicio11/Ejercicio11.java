package Ejercicio11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.regex.Pattern;
import java.util.stream.Stream;

//Se dispone de un fichero de texto con los nombres de los alumnos de una clase. Cada
//línea contiene en mayúscula el nombre y los dos apellidos de los alumnos, así como su
//curso separado por un espacio. Se desea montar una estructura de directorios de forma que
//se cree una carpeta por cada curso y dentro de ella una carpeta con cada alumno con el
//nombre Apelllido1Apellido2Nombre.Si se detecta algún error en el fichero se parará el
//proceso.
public class Ejercicio11 {
    static void main() {
        Path fichero = Path.of("src/main/Alumnos.txt");
        Path salida = Path.of("src/main/alumnoSalida");
        Pattern pattern = Pattern.compile("(?<nombre>[A-Z]+)\\s(?<apellido1>[A-Z]+)\\s(?<apellido2>[A-Z]+)\\s(?<curso>\\d[A-Z]+)");

        try(Stream<String> lineas = Files.lines(fichero)){
            lineas.map(l -> pattern.matcher(l))
                    .filter(m -> m.find())
                    .map(m ->{
                        String nombre = m.group("nombre");
                        String apellido1 = m.group("apellido1");
                        String apellido2 = m.group("apellido2");
                        String curso = m.group("curso");
                        return new AlumnoDTO(nombre,apellido1,apellido2,curso);
                    })
                    .forEach(alumno -> {
                        try {
                            Path rutaCompleta = Path.of("src/main/alumnoSalida", alumno.curso(), alumno.apellido1() + alumno.apellido2() + alumno.nombre());

                            Files.createDirectories(salida);
                            Files.createDirectories(rutaCompleta);
                            Files.createFile(rutaCompleta.resolve(String.valueOf(alumno)));
                        } catch (IOException e) {
                            throw new RuntimeException("Error al crear directorio: " + e.getMessage());
                        }
                    });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
