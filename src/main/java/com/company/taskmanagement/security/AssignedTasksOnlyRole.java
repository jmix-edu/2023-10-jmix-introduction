package com.company.taskmanagement.security;

import com.company.taskmanagement.entity.Task;
import com.company.taskmanagement.entity.TaskPriority;
import io.jmix.security.model.RowLevelBiPredicate;
import io.jmix.security.model.RowLevelPolicyAction;
import io.jmix.security.role.annotation.JpqlRowLevelPolicy;
import io.jmix.security.role.annotation.PredicateRowLevelPolicy;
import io.jmix.security.role.annotation.RowLevelRole;
import org.springframework.context.ApplicationContext;

@RowLevelRole(name = "AssignedTasksOnly", code = AssignedTasksOnlyRole.CODE)
public interface AssignedTasksOnlyRole {
    String CODE = "assigned-tasks-only";

    @JpqlRowLevelPolicy(entityClass = Task.class, where = "{E}.assignee.id = :current_user_id")
    void task();

    @PredicateRowLevelPolicy(entityClass = Task.class,
            actions = RowLevelPolicyAction.CREATE)
    default RowLevelBiPredicate<Task, ApplicationContext> createNoHighPriorityTasks() {
            return (task, applicationContext) -> task.getPriority() != TaskPriority.HIGH;
    }
}