package com.company.jmixpm.view.task;

import com.company.jmixpm.app.TaskService;
import com.company.jmixpm.entity.Task;

import com.company.jmixpm.entity.User;
import com.company.jmixpm.view.main.MainView;

import com.company.jmixpm.view.project.ProjectListView;
import com.vaadin.flow.router.Route;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.core.usersubstitution.CurrentUserSubstitution;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "tasks/:id", layout = MainView.class)
@ViewController("Task_.detail")
@ViewDescriptor("task-detail-view.xml")
@EditedEntityContainer("taskDc")
public class TaskDetailView extends StandardDetailView<Task> {
    @Autowired
    private TaskService taskService;
    @Autowired
    private CurrentAuthentication currentAuthentication;
    @Autowired
    private CurrentUserSubstitution currentUserSubstitution;

    @Subscribe
    public void onInitEntity(InitEntityEvent<Task> event) {
        event.getEntity().setAssignee(taskService.findLeastBusyUser());
    }

    @Install(to = "projectField.entityLookup", subject = "viewConfigurer")
    private void projectFieldEntityLookupViewConfigurer(final ProjectListView view) {
        view.setHideArchived(true);
    }
    
}