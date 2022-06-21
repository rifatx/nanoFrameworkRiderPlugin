package com.nanoframework.plugin.rider.init;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupActivity;
import com.nanoframework.plugin.rider.util.ProjectUtils;
import org.jetbrains.annotations.NotNull;

public class BuildToolInstallerActivity implements StartupActivity {

    @Override
    public void runActivity(@NotNull Project project) {
        var btu = project.getService(BuildToolUtils.class);

        if (btu.msBuildFolderFound()
                && !btu.toolsExist()
                && btu.install()) {
            ProjectUtils.reloadProjects(project);
        }
    }
}
