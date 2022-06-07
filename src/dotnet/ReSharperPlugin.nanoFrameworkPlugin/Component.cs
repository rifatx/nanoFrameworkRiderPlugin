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
    private readonly PortSerialManager _serialPortManager;
    private readonly NanoFrameworkProtocolModel _nfProtocolModel;

    public Component(Lifetime lifetime, ISolution solution)
    {
        _serialPortManager = (PortSerialManager) PortBase.CreateInstanceForSerial();
        _nfProtocolModel = solution.GetProtocolSolution().GetNanoFrameworkProtocolModel();

        SetDeployHandler();
        StartModel();
    }

    private void StartModel()
    {
        _serialPortManager.NanoFrameworkDevices.CollectionChanged += (sender, args) =>
        {
            _nfProtocolModel.SerialDeviceFoundEvent
                .Fire(
                    _serialPortManager
                        .NanoFrameworkDevices
                        .Where(d => d is NanoDevice<NanoSerialDevice>)
                        .Cast<NanoDevice<NanoSerialDevice>>()
                        .Select(d => new DeviceInfo(d.DeviceId, d.TargetName))
                        .ToArray());
        };

        _serialPortManager.StartDeviceWatchers();
    }

    internal void SetDeployHandler()
    {
        _nfProtocolModel.Deploy.Set((_, s) =>
            RdTask<string[]>.Successful(new[]
                {$"OK: {s.BasePath}, [{string.Join(", ", s.Assemblies ?? new string[] { })}]"}));
    }
}