package org.bitforlife.Todo;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class TodoService {

    private final TodoRepository repository;

    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    public Todo create(Todo todo) {

        LocalDateTime now = LocalDateTime.now();
        todo.setCreatedAt(now);
        todo.setUpdatedAt(now);
        return this.repository.save(todo);
    }

    public List<Todo> listAll() {
        return this.repository.findAll();
    }

    public Todo update(int id, Todo todo) throws Exception {
        Optional<Todo> existingTodo = this.repository.findById(id);
        if (existingTodo.isPresent()) {
            Todo _todo = existingTodo.get();
            _todo.setTitle(todo.getTitle());
            _todo.setCompleted(todo.isCompleted());

            return this.repository.save(_todo);
        }

        return null;

    }

    public boolean deleteById(int id) {
        if (!this.repository.existsById(id)) {
            return false;
        }

        this.repository.deleteById(id);
        return true;
    }


}
