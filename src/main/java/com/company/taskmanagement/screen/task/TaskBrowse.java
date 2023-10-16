package com.company.taskmanagement.screen.task;

import com.company.taskmanagement.app.TaskImportService;
import io.jmix.ui.Notifications;
import io.jmix.ui.action.Action;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.screen.*;
import com.company.taskmanagement.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("tm_Task.browse")
@UiDescriptor("task-browse.xml")
@LookupComponent("tasksTable")
public class TaskBrowse extends StandardLookup<Task> {

    @Autowired
    private TaskImportService taskImportService;
    @Autowired
    private CollectionLoader<Task> tasksDl;
    @Autowired
    private Notifications notifications;

    @Subscribe("tasksTable.importTasks")
    public void onTasksTableImportTasks(final Action.ActionPerformedEvent event) {
        int importResult = taskImportService.importTasks();
        tasksDl.load();
        notifications.create()
                .withType(Notifications.NotificationType.TRAY)
                .withCaption(importResult + " Tasks were imported")
                .show();
    }
}