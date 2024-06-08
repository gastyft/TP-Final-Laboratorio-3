package com.tpFinal.repository;

import java.util.List;

public interface IRepository<T> {
    public void cargar();
    public void guardar();
    public List<T> listar();
    public void agregar(T... objeto);
    public void modificar(T objetoOriginal,int index);
    public void eliminar(T objeto);
    public int lastId();
}
