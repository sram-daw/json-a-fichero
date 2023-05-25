import java.sql.Array;
import java.util.ArrayList;

public class Animal {
    private String nombre;
    private String especie;
    private int edad;
    private String color;
    ArrayList<String> patologias = new ArrayList<>();

    /**
     * Constructor por defecto con datos
     */
    public Animal() {
        this.nombre = "Calcetines";
        this.especie = "Gato";
        this.edad = 2;
        this.color = "Negro";
        this.patologias.add("Asma");
        this.patologias.add("Dermatitis");
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public ArrayList<String> getPatologias() {
        return patologias;
    }

    public void setPatologias(ArrayList<String> patologias) {
        this.patologias = patologias;
    }

    /**
     * Para poder comprobar los datos
     *
     * @return informacion del animal
     */
    @Override
    public String toString() {
        StringBuilder info;
        info = new StringBuilder(nombre + " es un " + especie + " de " + edad + " años. " + "Tiene las siguientes enfermedades:");
        for (String p : patologias)
            info.append(" ").append(p);
        return info.toString();
    }
}
