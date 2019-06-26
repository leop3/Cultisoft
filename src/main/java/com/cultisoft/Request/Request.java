package com.cultisoft.Request;

import java.util.ArrayList;
import java.util.List;

public class Request<T> {

	List<T> list = new ArrayList<>();

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

}
