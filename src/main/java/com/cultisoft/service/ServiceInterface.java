package com.cultisoft.service;

import java.util.List;

public interface ServiceInterface<T> {

	public abstract List<T> listAll();

	public abstract T add(T tipo);

	public abstract void remove(String id);

	public abstract T update(T tipo);

	public abstract T findById(String id);

	public abstract List<T> findByIdTipo(String id);

}
