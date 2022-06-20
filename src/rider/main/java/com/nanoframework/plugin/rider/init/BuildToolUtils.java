package com.nanoframework.plugin.rider.init;

import com.intellij.openapi.project.Project;
import com.jetbrains.rider.projectView.SolutionHostExtensionsKt;
import com.nanoframework.plugin.rider.util.ZipUtils;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class BuildToolUtils {
    private static final String ZIP_LOCATION = "buildTools/nanoFramework.zip";
    private static final String NF_TOOL_DIRECTORY_NAME = "nanoFramework";

    private final Project _project;
    private final String _msBuildRootPath;

    public BuildToolUtils(@NotNull Project project) {

        _project = project;

        var msBuildPath = SolutionHostExtensionsKt.getSolution(project)
                .getActiveMsBuildPath()
                .getValue();
        _msBuildRootPath = setMsBuildRoot(msBuildPath);
    }

    public boolean toolsExist() {
        var f = new File(_msBuildRootPath);

        if (f.exists() && f.isDirectory() && f.list((dir, name) -> dir.isDirectory() && name.equals(NF_TOOL_DIRECTORY_NAME)).length > 0) {
            return true;
        }

        return false;
    }

    public void install() {
        ZipUtils.unzip(ZIP_LOCATION, _msBuildRootPath);
    }

    private String setMsBuildRoot(String msBuildPath) {
        var f = new File(msBuildPath);

        if (!f.exists()) {
            return null;
        }

        if (!f.isDirectory()) {
            f = f.getParentFile();
        }

        while (f.list((dir, name) -> dir.isDirectory() && name.equals("Current")).length < 1) {
            f = f.getParentFile();
        }

        return f.getAbsolutePath();
    }
}
