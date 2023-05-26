# Ejercicio JSON
Para serializar más de un objeto java, es necesario guardarlos previamente en un array para que así también se almacenen
en el fichero JSON como tal, de lo contrario daría problemas de lectura:

```
       //Se almacenan los dos objetos animal en un arraylist para que el writer lo almacene también como un array en json
        ArrayList<Animal> animales = new ArrayList<>();
        animales.add(animal_original);
        animales.add(animal_original_2);
        String json = gson.toJson(animales);

       //Creamos un fichero y guardamos los objetos java originales como array
        FileWriter writer = new FileWriter("output.json");
        writer.write(json);
        writer.close();
```

Al realizar la deserialización también debemos crear un array en Java en el que almacenar 
el contenido del fichero json, transformándolo en objetos Java. Además, es importante tener en cuenta que debemos indicar con el objeto Type qué tipo de estructura
se va a deserializar. Posteriormente, se pasará como segundo parámetro a la función fromJson:
```
//se usa el objeto Type para especificar el tipo de objeto que va a ser deserializado, en este caso, un arraylist de objetos de tipo Animal
Type tipoLista = new TypeToken<ArrayList<Animal>>() {
}.getType();
//se debe crear un array de animales para que corresponda con el array en json
ArrayList<Animal> animalesAObjeto = gson.fromJson(jsonString, tipoLista);
```