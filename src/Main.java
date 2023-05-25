import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

/*
 Utilización de la libreria para parseo de JSON
 https://github.com/google/gson
 Volcamos a un fichero los datos y recuperamos de otro fichero
*/
public class Main {
    public static void main(String[] args) throws IOException {
        Gson gson = new Gson();
        String json_original;
        String json_original2;

        Animal animal_original = new Animal();
        System.out.println(animal_original);
        //segundo objeto
        Animal animal_original_2 = new Animal();
        ArrayList<String> enfermedades = new ArrayList<>();
        enfermedades.add("Leishmania");
        enfermedades.add("Rabia");
        animal_original_2.setNombre("Toby");
        animal_original_2.setEdad(7);
        animal_original_2.setEspecie("Perro");
        animal_original_2.setPatologias(enfermedades);

        //pasamos los objeto animal_original a json
        json_original = gson.toJson(animal_original, Animal.class);
        System.out.println(json_original);

        json_original2 = gson.toJson(animal_original_2, Animal.class);
        System.out.println(json_original2);
        //se almacenan los dos objetos animal en un arraylist para que el writer lo almacene también como un array en json
        ArrayList<Animal> animales = new ArrayList<>();
        animales.add(animal_original);
        animales.add(animal_original_2);
        String json = gson.toJson(animales);

        // Creamos un fichero y guardamos los json originales
        FileWriter writer = new FileWriter("output.json");
        writer.write(json);
        writer.close();

        //leemos el fichero json y pasamos lo pasamos a nuevos objetos animal
        FileReader reader = new FileReader("output.json");
        char[] buffer = new char[1024];
        int numCharsRead = reader.read(buffer);
        reader.close();
        String jsonString = new String(buffer, 0, numCharsRead);
        //se usa el objeto Type para especificar el tipo de objeto que va a ser deserializado, en este caso, un arraylist de objetos de tipo Animal
        Type tipoLista = new TypeToken<ArrayList<Animal>>() {
        }.getType();
        //se debe crear un array de animales para que corresponda con el array en json
        ArrayList<Animal> animalesAObjeto = gson.fromJson(jsonString, tipoLista);

        for (Animal animal : animalesAObjeto) {
            System.out.println(animal.toString());
        }

    }
}