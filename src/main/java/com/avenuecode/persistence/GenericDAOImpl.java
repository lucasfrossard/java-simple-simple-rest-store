package com.avenuecode.persistence;

import java.util.Collection;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class GenericDAOImpl<T> implements GenericDAO<T> {

	// @Setter
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public long insertOrUpdate(T entity) {
		return (long) this.sessionFactory.getCurrentSession().save(entity);
	}

	@Override
	public T findByID(long id, Class<T> clazz) {
		String query = "from " + clazz.getSimpleName() + " item where item.id = :id";
		List<T> list = this.sessionFactory.getCurrentSession()
				.createQuery(query).setParameter("id", id).list();
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public Collection<T> retrieveAll (Class<T> clazz){
		List<T> list = this.sessionFactory.getCurrentSession()
				.createQuery("from " + clazz.getName() + " item").list();
		return list;
		
	}

}
