package br.com.desafioviceri.model;

import br.com.desafioviceri.model.utilities.TaskPriority;
import br.com.desafioviceri.model.utilities.TaskStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "tasks")
@Setter
@Getter
@ToString

public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull(message = "Digite um nome para a tarefa.")
    private String title;

    @Column
    @NotNull(message = "Digite uma descrição para a tarefa.")
    private String description;

    @Column
    @NotNull(message = "Digite o prazo para conclusão.")
    private Date deadLine;

    @Column
    @NotNull(message = "Selecione o status para a tarefa")
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Column
    @NotNull(message = "Digite a prioridade ")
    @Enumerated(EnumType.STRING)
    private TaskPriority priority;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    //CORRIGIR
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
}
