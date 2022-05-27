package com.nanoframework.plugin.rider;

public class ToolWindowService {

    private NFToolWindow _toolWindow;

    public NFToolWindow getToolWindow() {
        return _toolWindow;
    }

    void setToolWindow(NFToolWindow toolWindow) {
        this._toolWindow = toolWindow;
    }
}