 package com.nanoframework.plugin.rider.file;

import com.intellij.openapi.util.NlsContexts;
import com.intellij.openapi.util.NlsSafe;
import com.jetbrains.rider.ideaInterop.fileTypes.msbuild.MsbuildFileType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class NFProjectFileType extends MsbuildFileType {
    @Override
    public @NonNls
    @NotNull String getName() {
        return "nfproj";
    }

    @Override
    public @NlsContexts.Label
    @NotNull String getDescription() {
        return "nanoFramework project file";
    }

    @Override
    public @NlsSafe
    @NotNull String getDefaultExtension() {
        return "nfproj";
    }

    @Override
    public Icon getIcon() {
        return null;
    }
}
