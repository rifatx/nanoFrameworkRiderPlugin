using System.Threading;
using JetBrains.Application.BuildScript.Application.Zones;
using JetBrains.ReSharper.Feature.Services;
using JetBrains.ReSharper.Psi.CSharp;
using JetBrains.ReSharper.TestFramework;
using JetBrains.TestFramework;
using JetBrains.TestFramework.Application.Zones;
using NUnit.Framework;

[assembly: Apartment(ApartmentState.STA)]

namespace ReSharperPlugin.nanoFrameworkPlugin.Tests
{
    [ZoneDefinition]
    public class nanoFrameworkPluginTestEnvironmentZone : ITestsEnvZone, IRequire<PsiFeatureTestZone>, IRequire<InanoFrameworkPluginZone> { }

    [ZoneMarker]
    public class ZoneMarker : IRequire<ICodeEditingZone>, IRequire<ILanguageCSharpZone>, IRequire<nanoFrameworkPluginTestEnvironmentZone> { }

    [SetUpFixture]
    public class nanoFrameworkPluginTestsAssembly : ExtensionTestEnvironmentAssembly<nanoFrameworkPluginTestEnvironmentZone> { }
}
