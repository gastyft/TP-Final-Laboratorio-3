package com.tpFinal.repository;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Repository<T> implements IRepository<T> {

        private final File archivo;


        private final ObjectMapper mapper = new ObjectMapper();
        private List<T> listaArchivos;
        private final Class<T> typeParameterClass;

        public Repository(Class<T> typeParameterClass ) {
            this.typeParameterClass = typeParameterClass;
            this.archivo = new File("src/main/java/com/tpFinal/archivos/"+typeParameterClass.getSimpleName()+".json");
        }

        @Override
        public void cargar() {
            try {
                CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(List.class, typeParameterClass);
                this.listaArchivos = mapper.readValue(archivo, collectionType);
            } catch (IOException e) {
                this.listaArchivos = new ArrayList<>();
            }
        }

        @Override
        public void guardar() {
            try {
                mapper.writerWithDefaultPrettyPrinter().writeValue(archivo, this.listaArchivos);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public List<T> listar() {
            cargar();
            return this.listaArchivos;
        }

        @Override
        public void agregar(T... objeto) {
            cargar();
            this.listaArchivos.addAll(List.of(objeto));
            guardar();
        }

        @Override
        public void eliminar(T objeto) {
            cargar();
            this.listaArchivos.remove(objeto);
            guardar();
        }

        @Override
        public void modificar(T objetoOriginal, int index) { //Recibe el original o se puede
            //modificar para que reciba el index. Y el objeto nuevo modificado
            cargar();
            //System.out.println(index);
            if (index != -1) {
                this.listaArchivos.set(index, objetoOriginal);
                System.out.println("Se modifico correctamente "+ objetoOriginal);
            } else {
                // Si el objeto no está en la lista, puedes manejarlo según sea necesario,
                // por ejemplo, lanzar una excepción, agregarlo, etc.
                System.out.println("El objeto no existe en la lista.");
            }
            guardar();
        }
    public int lastId() {
        cargar();
        if (this.listaArchivos.isEmpty()) {
            return -1; // O cualquier otro valor predeterminado que desees
        }
        else {
            return this.listaArchivos.size() - 1;
        }
    }

}
