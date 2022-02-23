package br.com.desafioviceri.service;

import br.com.desafioviceri.repository.TaskRepository;
import br.com.desafioviceri.model.Task;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {

    private TaskRepository taskRepository;

    //CRIA TAREFA
    public Task createTask (Task task) {
        return taskRepository.save(task);
    }
    //LISTA TODAS TAREFAS
    public List<Task> listAllTasks(){
        return taskRepository.findAll();
    }
    //BUSCA POR ID
    public ResponseEntity<Task> findTaskById(Long id) {
        return taskRepository.findById(id)
                .map(task -> ResponseEntity.ok().body(task))
                .orElse(ResponseEntity.notFound().build());
    }
    //RETORNA POR STATUS
    public List<Task> findByStatus(String status) {
        return taskRepository.findTaskByStatus(status);
    }
    //RETORNA POR PRIORIDADE
    public List<Task> findByPriority(String priority) {
        return taskRepository.findTaskByPriority(priority);
    }
    //ATUALIZA TITULO, DESCRICAO
    public ResponseEntity<Task> updateTaskById(Task task,Long id){
        return taskRepository.findById(id)
                .map(taskToUpdate -> {
                    taskToUpdate.setTitle(task.getTitle());
                    taskToUpdate.setDescription(task.getDescription());
                    Task updated = taskRepository.save((taskToUpdate));
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }
    //ATUALIZA STATUS
    public ResponseEntity<Task> updateTaskStatus(Task task,Long id){
        return taskRepository.findById(id)
                .map(taskToUpdate -> {
                    taskToUpdate.setStatus(task.getStatus());
                    Task updated = taskRepository.save((taskToUpdate));
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }
    //DELETA POR ID
    public ResponseEntity<Object> deleteById (Long id) {
        return taskRepository.findById(id)
                .map(taskDelete -> {
                    taskRepository.deleteById(id);
                    return ResponseEntity.noContent().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
