package com.company.taskmanagement.screen.task;

import com.company.taskmanagement.entity.Project;
import com.company.taskmanagement.entity.User;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.core.usersubstitution.CurrentUserSubstitution;
import io.jmix.ui.component.HasValue;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import com.company.taskmanagement.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("tm_Task.edit")
@UiDescriptor("task-edit.xml")
@EditedEntityContainer("taskDc")
public class TaskEdit extends StandardEditor<Task> {

    @Subscribe("projectField")
    public void onProjectFieldValueChange(final HasValue.ValueChangeEvent<Project> event) {
        if(event.isUserOriginated()) {
            Project newProject = event.getValue();
            if(newProject != null){
                getEditedEntity().setPriority(newProject.getDefaultTasksPriority());
            }
        }
    }

//    @Subscribe(id = "taskDc", target = Target.DATA_CONTAINER)
//    public void onTaskDcItemPropertyChange(final InstanceContainer.ItemPropertyChangeEvent<Task> event) {
//        if("project".equals(event.getProperty())) {
//            Project newProject = (Project) event.getValue();
//            if(newProject != null){
//                event.getItem().setPriority(newProject.getDefaultTasksPriority());
//            }
//
//        }
//    }
    
    

    @Autowired
    private CurrentUserSubstitution currentUserSubstitution;

    @Subscribe
    public void onInitEntity(final InitEntityEvent<Task> event) {
        final User user = (User) currentUserSubstitution.getEffectiveUser();
        event.getEntity().setAssignee(user);
    }
}