package br.com.desafioviceri.repository;

import br.com.desafioviceri.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TaskRepository  extends JpaRepository<Task, Long> {

    @Query(value = "SELECT * FROM TASKS WHERE NOT (STATUS = 'DONE')", nativeQuery = true)
    public List<Task> findTaskByStatus (String status);

    @Query(value = "SELECT * FROM TASKS WHERE PRIORITY = ?", nativeQuery = true)
    public List<Task> findTaskByPriority (String priority);

}
