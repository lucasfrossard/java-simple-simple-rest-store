package com.avenuecode.persistence;

import java.util.Collection;

public interface GenericDAO<T> {

	public long insertOrUpdate(T entity);

	public T findByID(long id, Class<T> clazz);

	public Collection<T> retrieveAll(Class<T> clazz);
}
