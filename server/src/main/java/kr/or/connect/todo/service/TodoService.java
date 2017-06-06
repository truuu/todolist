package kr.or.connect.todo.service;

import java.util.Collection;
import java.util.Date;

import org.springframework.stereotype.Service;

import kr.or.connect.todo.domain.Todo;
import kr.or.connect.todo.persistence.TodoDao;

@Service
public class TodoService {

	private TodoDao dao;

	public TodoService(TodoDao dao){
		this.dao=dao;
	}

	public Collection<Todo> findAll(){
		return dao.selectAll();
	}

	public Todo create(Todo todo) {
		todo.setCompleted(0);
		todo.setDate(new Date());
		Integer id = dao.insert(todo);
		todo.setId(id);
		return todo;
	}

	public boolean update(Integer id){
		Todo updateTodo = dao.selectById(id);
		updateTodo.setCompleted(1);
		int affected = dao.update(updateTodo);
		return affected == 1;
	}

	public boolean delete(Integer id) {
		int affected = dao.deleteById(id);
		return affected == 1;
	}

	public boolean deleteCompleted(){
		int affected = dao.deleteCompleted();
		return affected ==1;
	}


}
