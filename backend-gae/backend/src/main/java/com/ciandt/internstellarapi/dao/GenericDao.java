package com.ciandt.internstellarapi.dao;

import com.google.appengine.api.datastore.Query.Filter;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.Query;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import static com.ciandt.internstellarapi.util.OfyService.ofy;

/**
 * Created by rodrigosclosa on 29/08/16.
 */
public class GenericDao<T> implements IGenericDao<T> {

    protected Class<T> clazz;

    @SuppressWarnings("unchecked")
    public GenericDao() {
        clazz = ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    @Override
    public Key<T> save(T entity) {
        return ofy().save().entity(entity).now();
    }

    @Override
    public void insert(T entity) {
        save(entity);
    }

    @Override
    public void delete(T entity) {
        ofy().delete().entity(entity).now();
    }

    @Override
    public void update(T entity) {
        save(entity);
    }

    @Override
    public List<T> listAll() {
        Query<T> query = ofy().load().type(clazz);
        return query.list();
    }

    @Override
    public int countAll() {
        Query<T> query = ofy().load().type(clazz);
        return query.count();
    }

    @Override
    public List<T> listAll(Integer limit) {
        Query<T> query = ofy().load().type(clazz).limit(limit);
        return query.list();
    }

    @Override
    public T getByProperty(String propName, Object propValue) {
        return ofy().load().type(clazz).filter(propName, propValue).first().now();
    }

    @Override
    public T getByFilter(Filter filtro) {
        return ofy().load().type(clazz).filter(filtro).first().now();
    }

    @Override
    public T getById(Long id) {
        return ofy().load().type(clazz).id(id).now();
    }

    @Override
    public T getByKey(Long id) {
        return ofy().load().key(Key.create(clazz, id)).now();
    }

    @Override
    public List<T> listByProperty(String propName, Object propValue) {
        return ofy().load().type(clazz).filter(propName, propValue).list();
    }

    @Override
    public List<T> listByProperty(String propName, Object propValue, Integer limit) {
        return ofy().load().type(clazz).filter(propName, propValue).limit(limit).list();
    }

    @Override
    public List<T> listByFilter(Filter filtro) {
        return ofy().load().type(clazz).filter(filtro).list();
    }

    @Override
    public List<T> listByFilter(Filter filtro, Integer limit) {
        return ofy().load().type(clazz).filter(filtro).limit(limit).list();
    }

    @Override
    public int countByFilter(Filter filtro) {
        return ofy().load().type(clazz).filter(filtro).count();
    }

    public List<T> listByStartWith(String field, String search) {
        Query query = ofy().load().type(clazz).filter(field + " >=", search);
        return query.filter(field + " <", search+"\ufffd").list();
    }
}
