package kr.or.connect.todo.persistence;

public class TodoSqls {
	static final String SELECT_ALL = "SELECT * FROM todo ORDER BY id DESC";
	static final String SELECT_BY_ID = "SELECT * FROM todo WHERE id=:id";
	static final String COUNT_TODO = "SELECT COUNT(*) FROM todo";
	static final String UPDATE_COMPLETED = "UPDATE todo SET completed=:completed WHERE id= :id";
	static final String DELETE_BY_ID ="DELETE FROM todo WHERE id= :id";
	static final String DELETE_BY_COMPLETED ="DELETE FROM todo WHERE completed=1";
}
