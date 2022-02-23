package br.com.desafioviceri.controller;

import br.com.desafioviceri.model.Task;
import br.com.desafioviceri.service.TaskService;
import io.swagger.annotations.ApiOperation;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/tasks")
@Slf4j
public class TaskController {

    TaskService taskService;
//CRIA UMA NOVA TASK
    @ApiOperation(value = "Criando uma nova tarefa")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Tarefa criada com sucesso"),
            @ApiResponse(code = 500, message = "Houve um erro ao criar a tarefa, verifique as informações")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createdTask(@RequestBody Task task) {
//        log.info("Criando uma nova tarefa com as informaçoes [{}]", task);
//        System.out.print(task);
        return taskService.createTask(task);
    }

    //BUSCA TODAS TASKS
    @ApiOperation(value = "Listando todas as tarefas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tarefas listadas com sucesso"),
            @ApiResponse(code = 500, message = "Houve um erro ao listar as tarefas")
    })
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getAllTasks() {
        log.info("Listando todas as tarefas cadastradas");
        return taskService.listAllTasks();
    }

    //BUSCA POR STATUS
    @ApiOperation(value = "Filtrando tarefas não concluídas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Filtro de tarefas não concluídas"),
            @ApiResponse(code = 404, message = "Não foi encontrada nenhuma tarefa à concluir.")
    })
    @GetMapping("/status")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<Task> findTaskByStatus(@PathVariable (value="status")String status){
        log.info("Listando as tarefas com status [{}]", status);
        return taskService.findByStatus(status);
    }

    //BUSCA POR PRIORIDADE
    @ApiOperation(value = "Buscando uma tarefa por prioridade")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Filtro de tarefas por prioridade"),
            @ApiResponse(code = 404, message = "Não foram encontradas tarefas com a prioridade solicitada")
    })
    @GetMapping("/priority/{priority}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<Task> findTaskByPriority(@PathVariable (value="priority")String priority){
        log.info("Listando as tarefas por prioridade [{}]", priority);
        return taskService.findByPriority(priority);
    }

    //ATUALIZANDO TITULO/DESCRICAO
    @ApiOperation(value = "Atualizando uma tarefa")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tarefa atualizada com sucesso"),
            @ApiResponse(code = 404, message = "Tarefa nao encontrada")
    })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Task> updateTaskById(@PathVariable(value = "id") Long id, @RequestBody Task task) {
        log.info("Atualizando a tarefa por id[{}] as novas informaçoes são: [{}]", id, task);
        return taskService.updateTaskById(task, id);
    }

    //ATUALIZAR STATUS
    @ApiOperation(value = "Atualizando o status de uma tarefa")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Status atualizada com sucesso"),
            @ApiResponse(code = 404, message = "Não atualizado")
    })
    @PutMapping("/status/{status}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Task> updateTaskStatus(@PathVariable(value = "status") Long id, @RequestBody Task task) {
        log.info("Atualizando o status da tarefa [{}]", id, task);
        return taskService.updateTaskStatus(task, id);
    }

    //DELETA TAREFA
    @ApiOperation(value = "Excluindo uma tarefa")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Tarefa excluida com sucesso"),
            @ApiResponse(code = 404, message = "Nao foi possivel excluir a tarefa - tarefa nao encontrada")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> deleteTaskById(@PathVariable(value = "id") Long id) {
        log.info("Excluindo tarefa por Id [{}]", id);
        return taskService.deleteById(id);
    }

}
