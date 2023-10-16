package com.company.taskmanagement.security;

import com.company.taskmanagement.entity.Project;
import com.company.taskmanagement.entity.Task;
import com.company.taskmanagement.entity.User;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.securityui.role.annotation.MenuPolicy;
import io.jmix.securityui.role.annotation.ScreenPolicy;

@ResourceRole(name = "TaskCreator", code = TaskCreatorRole.CODE)
public interface TaskCreatorRole {
    String CODE = "task-creator";

    @MenuPolicy(menuIds = {"tm_Project.browse", "tm_Task.browse", "tm_User.browse"})
    @ScreenPolicy(screenIds = {"tm_Project.browse", "tm_Task.browse", "tm_Task.edit", "tm_User.browse"})
    void screens();

    @EntityAttributePolicy(entityClass = Project.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Project.class, actions = EntityPolicyAction.READ)
    void project();

    @EntityAttributePolicy(entityClass = Task.class, attributes = "dueDate", action = EntityAttributePolicyAction.VIEW)
    @EntityAttributePolicy(entityClass = Task.class, attributes = {"id", "name", "assignee", "project", "priority", "subtasks"}, action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Task.class, actions = EntityPolicyAction.ALL)
    void task();

    @EntityAttributePolicy(entityClass = User.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = User.class, actions = EntityPolicyAction.READ)
    void user();
}