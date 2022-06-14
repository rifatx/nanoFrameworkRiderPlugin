package com.nanoframework.plugin.rider.module;

import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.module.ModuleTypeManager;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class NFModuleType extends ModuleType<NFModuleBuilder> {
    public static final String ID = "NF_MODULE";

    protected NFModuleType() {
        super(ID);
    }

    public static NFModuleType getInstance() {
        return (NFModuleType) ModuleTypeManager.getInstance().findByID(ID);
    }

    @Override
    public @NotNull NFModuleBuilder createModuleBuilder() {
        return new NFModuleBuilder();
    }

    @NotNull
    @Override
    public String getName() {
        return "nanoFramework";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Create new nanoFramework project based on templates.";
    }


    @Override
    public Icon getNodeIcon(@Deprecated boolean b) {
        return null;
    }
}
