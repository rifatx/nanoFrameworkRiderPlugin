package com.nanoFramework.plugin.rider;

import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBList;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class NFToolWindow extends SimpleToolWindowPanel {
    private final JBList<Pair<String, String>> _deviceList;

    public NFToolWindow(ToolWindow toolWindow) {
        super(false, true);

        final var actionManager = ActionManager.getInstance();
        var actionGroup = new DefaultActionGroup("ACTION_GROUP", false);
        actionGroup.add(ActionManager.getInstance().getAction("com.nanoFramework.plugin.rider.DeployAction"));
        var actionToolbar = actionManager.createActionToolbar("ACTION_TOOLBAR", actionGroup, true);
        actionToolbar.setOrientation(SwingConstants.HORIZONTAL);
//        this.setToolbar(actionToolbar.getComponent());

        var toolWindowContent = new JPanel();
        toolWindowContent.setLayout(new BorderLayout());

        this.setLayout(new BorderLayout());
        this.add(actionToolbar.getComponent(), BorderLayout.PAGE_START);
        this.add(toolWindowContent, BorderLayout.CENTER);

        var topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        toolWindowContent.add(topPanel, BorderLayout.PAGE_START);

        var contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        toolWindowContent.add(contentPanel, BorderLayout.CENTER);

        var b1 = new JButton();
        b1.setText("Button1");
        b1.addActionListener(e -> {
        });
        topPanel.add(b1);

        var b2 = new JButton();
        b2.setText("Button2");
        b2.addActionListener(e -> {
        });
        topPanel.add(b2);

        var b3 = new JButton();
        b3.setText("Button3");
        b3.addActionListener(e -> {
        });
        topPanel.add(b3);

        ArrayList<Pair<String, String>> data = new ArrayList<>();
        data.add(new Pair<>("key1", "val1"));
        data.add(new Pair<>("key2", "val2"));
        data.add(new Pair<>("key3", "val3"));

        _deviceList = new JBList<>(data);
        _deviceList.setCellRenderer((list, value, index, isSelected, cellHasFocus) -> new JBLabel(value.first + "." + value.second));

        contentPanel.add(_deviceList, BorderLayout.PAGE_START);
    }

    public JBList<Pair<String, String>> getDeviceList() {
        return _deviceList;
    }

    public String getSelectedDevice() {
        var sp = getDeviceList().getSelectedValue();
        return String.format("%s.%s", sp.first, sp.second);
    }
}
