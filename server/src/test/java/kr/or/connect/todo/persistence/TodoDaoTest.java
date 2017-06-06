package kr.or.connect.todo.persistence;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.todo.domain.Todo;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TodoDaoTest {

	@Autowired
	private TodoDao dao;

	@Test
	public void shouldSelectAll() {
		List<Todo> allTodos = dao.selectAll();
		assertThat(allTodos, is(notNullValue()));
	}

	@Test
	public void shouldInsertAndSelect() {
		// given
		Todo todo = new Todo("오늘 할일", 0, new Date());

		// when
		Integer id = dao.insert(todo);

		// then
		Todo selected = dao.selectById(id);
		assertThat(selected.getTodo(), is("오늘 할 일"));
	}

	@Test
	public void shouldDelete() {
		// given
		Todo todo = new Todo("내일 할 일", 0, new Date());
		Integer id = dao.insert(todo);

		// when
		int affected = dao.deleteById(id);

		// Then
		assertThat(affected, is(1));
	}

	@Test
	public void shouldUpdate() {
		// Given
		Todo todo = new Todo("내일 할 일", 0, new Date());
		Integer id = dao.insert(todo);

		// When
		todo.setId(id);
		todo.setTodo("내일 할 일2");
		int affected = dao.update(todo);

		// Then
		assertThat(affected, is(1));
		Todo updated = dao.selectById(id);
		assertThat(updated.getTodo(), is("내일 할 일2"));
	}

}
