package com.nanoframework.plugin.rider.protocol;

import com.intellij.openapi.project.Project;
import com.jetbrains.rd.framework.impl.RpcTimeouts;
import com.jetbrains.rdclient.util.idea.LifetimedProjectComponent;
import com.jetbrains.rider.projectView.SolutionHostExtensionsKt;
import com.nanoframeworkplugin.rider.protocol.DeployData;
import com.nanoframeworkplugin.rider.protocol.NanoFrameworkProtocolModel;
import com.nanoframeworkplugin.rider.protocol.NanoFrameworkProtocolModel_GeneratedKt;
import org.jetbrains.annotations.NotNull;

public final class Component extends LifetimedProjectComponent {
    public Component(@NotNull Project project) {
        super(project);
        NanoFrameworkProtocolModel model = NanoFrameworkProtocolModel_GeneratedKt.getNanoFrameworkProtocolModel(SolutionHostExtensionsKt.getSolution(project));


//        var v = model
//                .getDeploy()
//                .sync(
//                        new DeployData("", null),
//                        RpcTimeouts.Companion.getDefault()
//                );
    }
}
