package com.nanoframework.plugin.rider;

import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.openapi.wm.ex.ToolWindowManagerEx;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBList;
import com.nanoframework.plugin.rider.serial.SerialPortWrapper;
import com.nanoframeworkplugin.rider.protocol.DeviceInfo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class NFToolWindow extends SimpleToolWindowPanel {
    static final String NF_TOOL_WINDOW_ID = "NFToolWindow";

    private final JBList<Pair<String, String>> _deviceList;
    private Thread _portMonitorThread;

    public NFToolWindow(ToolWindow toolWindow) {
        super(false, true);

//        ToolWindowManager manager = ToolWindowManager.getInstance(toolWindow.getProject());
//        if (manager instanceof ToolWindowManagerEx) {
//            ToolWindowManagerEx managerEx = ((ToolWindowManagerEx) manager);
//            managerEx.addToolWindowManagerListener(new NFToolWindowListener(manager, this));
//        }

        final var actionManager = ActionManager.getInstance();
        var actionGroup = new DefaultActionGroup("ACTION_GROUP", false);
        actionGroup.add(ActionManager.getInstance().getAction("DeployAction"));
        var actionToolbar = actionManager.createActionToolbar("ACTION_TOOLBAR", actionGroup, true);
        actionToolbar.setOrientation(SwingConstants.HORIZONTAL);

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

        _deviceList = new JBList<>();
        _deviceList.setCellRenderer((list, value, index, isSelected, cellHasFocus) -> new JBLabel(value.first + "." + value.second));

        contentPanel.add(_deviceList, BorderLayout.PAGE_START);
    }

    private void startPortMonitorThread() {
        if (_portMonitorThread != null && _portMonitorThread.isAlive()) {
            return;
        }

        _portMonitorThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                var data = new ArrayList<Pair<String, String>>();
                for (var portName : SerialPortWrapper.getSerialPorts()) {
                    data.add(new Pair<>(portName, "DeviceType"));
                }

                _deviceList.setListData(new Vector<>(data));

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    return;
                }
            }
        });

        _portMonitorThread.start();
    }

    void startBackgroundThreads() {
        startPortMonitorThread();
    }

    void stopBackgroundThreads() {
        _portMonitorThread.interrupt();
    }

    public void cleanUp() {
        stopBackgroundThreads();
    }

    public void setDeviceList(DeviceInfo[] devices) {
        var data = new ArrayList<Pair<String, String>>();
        for (var d : devices) {
            data.add(new Pair<>(d.getPortName(), d.getDeviceName()));
        }

        _deviceList.setListData(new Vector<>(data));
    }

    public JBList<Pair<String, String>> getDeviceList() {
        return _deviceList;
    }

    public String getSelectedDevice() {
        var sp = getDeviceList().getSelectedValue();

        if (sp == null) {
            return "<No device selected>";
        }
        return String.format("%s (%s)", sp.first, sp.second);
    }
}
