package com.nanoframework.plugin.rider.serial;

import com.fazecast.jSerialComm.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SerialPortWrapper {
    public static List<String> getSerialPorts() {
        return Arrays.stream(SerialPort.getCommPorts())
                .map(SerialPort::getSystemPortName)
                .collect(Collectors.toList());
    }
}
