package com.nanoframework.plugin.rider;

import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.openapi.wm.ex.ToolWindowManagerListener;
import org.jetbrains.annotations.NotNull;

public class NFToolWindowListener implements ToolWindowManagerListener {
    private final NFToolWindow _nfToolWindow;

    public NFToolWindowListener(@NotNull ToolWindowManager toolWindowManager, NFToolWindow nfToolWindow) {
        var twId = toolWindowManager.getActiveToolWindowId();
        if (twId != null && !twId.equals(NFToolWindow.NF_TOOL_WINDOW_ID)) {
            _nfToolWindow = null;
            return;
        }
        _nfToolWindow = nfToolWindow;
    }

    @Override
    public void stateChanged(@NotNull ToolWindowManager toolWindowManager) {
        if (_nfToolWindow == null) {
            return;
        }

        if (toolWindowManager.getToolWindow(NFToolWindow.NF_TOOL_WINDOW_ID).isVisible()) {
            _nfToolWindow.startBackgroundThreads();
        } else {
            _nfToolWindow.stopBackgroundThreads();
        }
    }
}
