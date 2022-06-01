using System.Linq;
using JetBrains.Lifetimes;
using JetBrains.ProjectModel;
using JetBrains.Rd.Tasks;
using JetBrains.RdBackend.Common.Features;
using nanoFramework.Tools.Debugger;
using nanoFramework.Tools.Debugger.PortSerial;

namespace ReSharperPlugin.nanoFrameworkPlugin;

[SolutionComponent]
public class Component
{
    private readonly NanoFrameworkProtocolModel _nfProtocolModel;
    private readonly PortSerialManager _serialPortManager;

    public Component(Lifetime lifetime, ISolution solution)
    {
        _nfProtocolModel = solution.GetProtocolSolution().GetNanoFrameworkProtocolModel();
        _serialPortManager = (PortSerialManager) PortBase.CreateInstanceForSerial();

        StartModel();
    }

    private void StartModel()
    {
        _serialPortManager.DeviceEnumerationCompleted += (o, ea) =>
        {
            if (o is PortSerialManager psm)
            {
                _nfProtocolModel
                    .SerialDeviceFound
                    .Sync(psm
                        .NanoFrameworkDevices
                        .Where(d => d is NanoDevice<NanoSerialDevice>)
                        .Cast<NanoDevice<NanoSerialDevice>>()
                        .Select(d => new DeviceInfo(d.DeviceId, d.TargetName))
                        .ToArray());
            }
        };
    }

    internal void Deploy()
    {
        _nfProtocolModel.Deploy.Set((_, s) =>
            RdTask<string[]>.Successful(new[]
                {$"OK: {s.BasePath}, [{string.Join(", ", s.Assemblies ?? new string[] { })}]"}));
    }
}