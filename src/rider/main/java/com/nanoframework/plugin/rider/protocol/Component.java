package com.nanoframework.plugin.rider.protocol;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.jetbrains.rd.framework.impl.RpcTimeouts;
import com.jetbrains.rdclient.util.idea.LifetimedProjectComponent;
import com.jetbrains.rider.projectView.SolutionHostExtensionsKt;
import com.nanoframework.plugin.rider.ToolWindowService;
import com.nanoframeworkplugin.rider.protocol.DeployData;
import com.nanoframeworkplugin.rider.protocol.NanoFrameworkProtocolModel;
import com.nanoframeworkplugin.rider.protocol.NanoFrameworkProtocolModel_GeneratedKt;
import kotlin.Unit;
import org.jetbrains.annotations.NotNull;

public final class Component extends LifetimedProjectComponent {
    NanoFrameworkProtocolModel _model;

    public Component(@NotNull Project project) {
        super(project);
        _model = NanoFrameworkProtocolModel_GeneratedKt.getNanoFrameworkProtocolModel(SolutionHostExtensionsKt.getSolution(project));
        initCallbacks();
    }

    public void sendDeployCommand() {
        var v = _model
                .getDeploy()
                .sync(
                        new DeployData("", null),
                        RpcTimeouts.Companion.getDefault()
                );
        var x = ";";
    }

    public void initCallbacks() {
        initSerialPortListenerEvent();
    }

    private void initSerialPortListenerEvent() {
        _model.getSerialDeviceFoundEvent()
                .advise(getComponentLifetime(), deviceInfo2s -> {
                    var tws = ApplicationManager.getApplication()
                            .getService(ToolWindowService.class);
                    tws.getToolWindow()
                            .setDeviceList(deviceInfo2s);
                    return Unit.INSTANCE;
                });
    }
}

