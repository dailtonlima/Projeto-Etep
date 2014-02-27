/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.etep.common;

import java.util.List;

/**
 *
 * @author Dailton
 */
public interface IGenericDAO<T> {
    
    public T read(Integer codigo);
    
    public List<T> listAll();
    
    public T create(T object);
    
    public T update(T object);
    
    public boolean delete(T object);
}
