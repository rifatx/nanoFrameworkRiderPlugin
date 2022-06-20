package com.nanoframework.plugin.rider.util;

import com.intellij.openapi.project.Project;
import com.intellij.workspaceModel.ide.WorkspaceModel;
import com.jetbrains.rd.framework.impl.RpcTimeouts;
import com.jetbrains.rider.model.ProjectModelTasks_PregeneratedKt;
import com.jetbrains.rider.model.ReloadCommand;
import com.jetbrains.rider.model.UnloadCommand;
import com.jetbrains.rider.projectView.SolutionHostExtensionsKt;
import com.jetbrains.rider.projectView.workspace.WorkspaceModelExtensionsKt;
import com.jetbrains.rider.test.framework.ProjectModelExtensionsKt;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Collectors;

public class ProjectUtils {
    private static final int LOAD_RELOAD_WARN_TIMEOUT = 5000;
    private static final int LOAD_RELOAD_ERR_TIMEOUT = 30000;

    public static void reloadProjects(@NotNull Project project) {
        var loadedProjectIds = ProjectModelExtensionsKt
                .getLoadedProjects(project)
                .stream()
                .map(p ->
                        WorkspaceModelExtensionsKt
                                .getProjectModelId(WorkspaceModel.getInstance(project), p))
                .collect(Collectors.toList());

        var solution = SolutionHostExtensionsKt.getSolution(project);

        ProjectModelTasks_PregeneratedKt.getProjectModelTasks(solution)
                .getUnloadProjects()
                .sync(new UnloadCommand(loadedProjectIds), new RpcTimeouts(LOAD_RELOAD_WARN_TIMEOUT, LOAD_RELOAD_ERR_TIMEOUT));

        var unloadedProjectIds = ProjectModelExtensionsKt
                .getLoadedProjects(project)
                .stream()
                .map(p ->
                        WorkspaceModelExtensionsKt
                                .getProjectModelId(WorkspaceModel.getInstance(project), p))
                .collect(Collectors.toList());

        ProjectModelTasks_PregeneratedKt.getProjectModelTasks(solution)
                .getReloadProjects()
                .sync(new ReloadCommand(unloadedProjectIds), new RpcTimeouts(LOAD_RELOAD_WARN_TIMEOUT, LOAD_RELOAD_ERR_TIMEOUT));
    }
}
