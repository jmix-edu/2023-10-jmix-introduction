package com.company.taskmanagement.screen.subtask;

import io.jmix.ui.screen.*;
import com.company.taskmanagement.entity.Subtask;

@UiController("tm_Subtask.browse")
@UiDescriptor("subtask-browse.xml")
@LookupComponent("subtasksTable")
public class SubtaskBrowse extends StandardLookup<Subtask> {
}