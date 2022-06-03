package com.nanoframework.plugin.rider;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

public class DeployAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        var ps = ApplicationManager.getApplication()
                .getService(ToolWindowService.class);
        var nftw = ps.getToolWindow();

        ps.getComponent()
                .sendDeployCommand();

        Messages.showMessageDialog(
                event.getProject(),
                String.format("action done: %s", nftw.getSelectedDevice()),
                "some title",
                Messages.getInformationIcon());
    }
}
