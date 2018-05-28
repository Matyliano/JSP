package todo.repository;


import todo.Application;
import todo.entity.Task;

import javax.persistence.EntityManager;
import java.util.List;

public class TaskRepository {

    private EntityManager entityManager;

    public TaskRepository() {
        this.entityManager = Application.getEntityManager();
    }

    public List<Task> findAll() {
        return entityManager.createQuery("select t from Task t", Task.class).getResultList();
    }

    public Task save(Task task) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(task);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }

        return task;
    }

    public void deleteById(Long id) {
        try {
            entityManager.getTransaction().begin();

            Task task = entityManager.find(Task.class, id);

            if (task == null) {
                throw new IllegalArgumentException("Task does not exist");
            }

            entityManager.remove(task);

            entityManager.getTransaction().commit();
        } catch (IllegalArgumentException e) {
            entityManager.getTransaction().rollback();
            throw e;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }
}
