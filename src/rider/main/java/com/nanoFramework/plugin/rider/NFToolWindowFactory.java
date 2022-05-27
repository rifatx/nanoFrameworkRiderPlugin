package com.nanoFramework.plugin.rider;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import org.jetbrains.annotations.NotNull;

public class NFToolWindowFactory implements ToolWindowFactory {
    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        var nftw=new NFToolWindow(toolWindow);
        var ps=ApplicationManager.getApplication()
                .getService(ProjectService.class);
        ps.setToolWindow(nftw);
        var component = toolWindow.getComponent();
        component.getParent().add(nftw.getComponent());
    }
}
