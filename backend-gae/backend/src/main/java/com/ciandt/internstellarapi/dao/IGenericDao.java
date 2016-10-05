package com.ciandt.internstellarapi.dao;

import com.google.appengine.api.datastore.Query;
import com.googlecode.objectify.Key;

import java.util.List;

/**
 * Created by rodrigosclosa on 29/08/16.
 */
public interface IGenericDao<T> {

    Key<T> save(T entity);
    void insert(T entity);
    void delete(T entity);
    void update(T entity);
    List<T> listAll();
    int countAll();
    List<T> listAll(Integer limit);
    T getByProperty(String propName, Object propValue);
    T getByFilter(Query.Filter filtro);
    T getById(Long id);
    T getByKey(Long id);
    List<T> listByProperty(String propName, Object propValue);
    List<T> listByProperty(String propName, Object propValue, Integer limit);
    List<T> listByFilter(Query.Filter filtro);
    List<T> listByFilter(Query.Filter filtro, Integer limit);
    int countByFilter(Query.Filter filtro);
    List<T> listByStartWith(String field, String search);
}
