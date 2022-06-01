package com.nanoframework.plugin.rider;

import com.intellij.openapi.project.ProjectManager;
import com.nanoframework.plugin.rider.protocol.Component;

public class ToolWindowService {
    private NFToolWindow _toolWindow;

    public ToolWindowService() {
        var c = new Component(ProjectManager.getInstance().getOpenProjects()[0]);
    }

    public NFToolWindow getToolWindow() {
        return _toolWindow;
    }

    void setToolWindow(NFToolWindow toolWindow) {
        this._toolWindow = toolWindow;
    }
}