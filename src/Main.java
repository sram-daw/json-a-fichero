import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
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
        //creación del primer objeto
        Animal animal_original = new Animal();
        System.out.println("Animal original 1 (java object): " + animal_original);
        //creación del segundo objeto
        Animal animal_original_2 = new Animal();
        System.out.println("Animal original 2 (java object): " + animal_original_2);
        ArrayList<String> enfermedades = new ArrayList<>();
        enfermedades.add("Leishmania");
        enfermedades.add("Rabia");
        animal_original_2.setNombre("Toby");
        animal_original_2.setEdad(7);
        animal_original_2.setEspecie("Perro");
        animal_original_2.setPatologias(enfermedades);

        /*Serialización*/

        //pasamos los objetos java animal_original a json
        json_original = gson.toJson(animal_original, Animal.class);
        System.out.println("Animal 1 en json: " + json_original);
        json_original2 = gson.toJson(animal_original_2, Animal.class);
        System.out.println("Animal 2 en json: " + json_original2);
        //se almacenan los dos objetos animal en un arraylist para que el writer lo almacene también como un array en json
        ArrayList<Animal> animales = new ArrayList<>();
        animales.add(animal_original);
        animales.add(animal_original_2);
        String json = gson.toJson(animales);

        //Creamos un fichero y guardamos los objetos java originales como array
        FileWriter writer = new FileWriter("output.json");
        writer.write(json);
        writer.close();

        /*Deserialización*/

        //Leemos el fichero json
        FileReader reader = new FileReader("output.json");
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuilder builder = new StringBuilder();
        int numCharsRead;

        while ((numCharsRead = bufferedReader.read()) != -1) {
            builder.append((char) numCharsRead);
        }

        reader.close();
        //y creamos el string con el contenido del fichero output.json
        String jsonString = builder.toString();
        //se usa el objeto Type para especificar el tipo de objeto que va a ser deserializado, en este caso, un arraylist de objetos de tipo Animal
        Type tipoLista = new TypeToken<ArrayList<Animal>>() {
        }.getType();
        //se debe crear un array de animales para que corresponda con el array en json
        ArrayList<Animal> animalesAObjeto = gson.fromJson(jsonString, tipoLista);

        for (Animal animal : animalesAObjeto) {
            System.out.println("Deserialización de json a objetos java: " + animal.toString());
        }

    }
}