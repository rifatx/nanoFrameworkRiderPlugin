package com.nanoframework.plugin.rider.module;

import com.intellij.ide.util.projectWizard.ModuleBuilder;
import com.intellij.openapi.module.ModuleType;

public class NFModuleBuilder extends ModuleBuilder {
    @Override
    public ModuleType<?> getModuleType() {
        return NFModuleType.getInstance();
    }
}
