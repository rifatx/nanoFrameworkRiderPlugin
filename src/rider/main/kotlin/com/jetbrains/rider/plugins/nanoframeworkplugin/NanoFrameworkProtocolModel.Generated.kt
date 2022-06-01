@file:Suppress("EXPERIMENTAL_API_USAGE","EXPERIMENTAL_UNSIGNED_LITERALS","PackageDirectoryMismatch","UnusedImport","unused","LocalVariableName","CanBeVal","PropertyName","EnumEntryName","ClassName","ObjectPropertyName","UnnecessaryVariable","SpellCheckingInspection")
package com.nanoframeworkplugin.rider.protocol

import com.jetbrains.rd.framework.*
import com.jetbrains.rd.framework.base.*
import com.jetbrains.rd.framework.impl.*

import com.jetbrains.rd.util.lifetime.*
import com.jetbrains.rd.util.reactive.*
import com.jetbrains.rd.util.string.*
import com.jetbrains.rd.util.*
import kotlin.reflect.KClass
import kotlin.jvm.JvmStatic



/**
 * #### Generated from [NanoFrameworkProtocolModel.kt:10]
 */
class NanoFrameworkProtocolModel private constructor(
    private val _someProperty: RdOptionalProperty<CustomPropType>,
    private val _deploy: RdCall<DeployData, Array<String>>,
    private val _serialDeviceFound: RdCall<Array<DeviceInfo>, Unit>
) : RdExtBase() {
    //companion
    
    companion object : ISerializersOwner {
        
        override fun registerSerializersCore(serializers: ISerializers)  {
            serializers.register(CustomPropType)
            serializers.register(DeployData)
            serializers.register(DeviceInfo)
        }
        
        
        
        
        private val __StringArraySerializer = FrameworkMarshallers.String.array()
        private val __DeviceInfoArraySerializer = DeviceInfo.array()
        
        const val serializationHash = 5757355806742605723L
        
    }
    override val serializersOwner: ISerializersOwner get() = NanoFrameworkProtocolModel
    override val serializationHash: Long get() = NanoFrameworkProtocolModel.serializationHash
    
    //fields
    val someProperty: IOptProperty<CustomPropType> get() = _someProperty
    val deploy: IRdCall<DeployData, Array<String>> get() = _deploy
    val serialDeviceFound: IRdEndpoint<Array<DeviceInfo>, Unit> get() = _serialDeviceFound
    //methods
    //initializer
    init {
        _someProperty.optimizeNested = true
    }
    
    init {
        _deploy.async = true
        _serialDeviceFound.async = true
    }
    
    init {
        bindableChildren.add("someProperty" to _someProperty)
        bindableChildren.add("deploy" to _deploy)
        bindableChildren.add("serialDeviceFound" to _serialDeviceFound)
    }
    
    //secondary constructor
    internal constructor(
    ) : this(
        RdOptionalProperty<CustomPropType>(CustomPropType),
        RdCall<DeployData, Array<String>>(DeployData, __StringArraySerializer),
        RdCall<Array<DeviceInfo>, Unit>(__DeviceInfoArraySerializer, FrameworkMarshallers.Void)
    )
    
    //equals trait
    //hash code trait
    //pretty print
    override fun print(printer: PrettyPrinter)  {
        printer.println("NanoFrameworkProtocolModel (")
        printer.indent {
            print("someProperty = "); _someProperty.print(printer); println()
            print("deploy = "); _deploy.print(printer); println()
            print("serialDeviceFound = "); _serialDeviceFound.print(printer); println()
        }
        printer.print(")")
    }
    //deepClone
    override fun deepClone(): NanoFrameworkProtocolModel   {
        return NanoFrameworkProtocolModel(
            _someProperty.deepClonePolymorphic(),
            _deploy.deepClonePolymorphic(),
            _serialDeviceFound.deepClonePolymorphic()
        )
    }
    //contexts
}
val com.jetbrains.rd.ide.model.Solution.nanoFrameworkProtocolModel get() = getOrCreateExtension("nanoFrameworkProtocolModel", ::NanoFrameworkProtocolModel)



/**
 * #### Generated from [NanoFrameworkProtocolModel.kt:16]
 */
data class CustomPropType (
    val someString: String,
    val someBool: Boolean,
    val someArray: Array<String>
) : IPrintable {
    //companion
    
    companion object : IMarshaller<CustomPropType> {
        override val _type: KClass<CustomPropType> = CustomPropType::class
        
        @Suppress("UNCHECKED_CAST")
        override fun read(ctx: SerializationCtx, buffer: AbstractBuffer): CustomPropType  {
            val someString = buffer.readString()
            val someBool = buffer.readBool()
            val someArray = buffer.readArray {buffer.readString()}
            return CustomPropType(someString, someBool, someArray)
        }
        
        override fun write(ctx: SerializationCtx, buffer: AbstractBuffer, value: CustomPropType)  {
            buffer.writeString(value.someString)
            buffer.writeBool(value.someBool)
            buffer.writeArray(value.someArray) { buffer.writeString(it) }
        }
        
        
    }
    //fields
    //methods
    //initializer
    //secondary constructor
    //equals trait
    override fun equals(other: Any?): Boolean  {
        if (this === other) return true
        if (other == null || other::class != this::class) return false
        
        other as CustomPropType
        
        if (someString != other.someString) return false
        if (someBool != other.someBool) return false
        if (!(someArray contentDeepEquals other.someArray)) return false
        
        return true
    }
    //hash code trait
    override fun hashCode(): Int  {
        var __r = 0
        __r = __r*31 + someString.hashCode()
        __r = __r*31 + someBool.hashCode()
        __r = __r*31 + someArray.contentDeepHashCode()
        return __r
    }
    //pretty print
    override fun print(printer: PrettyPrinter)  {
        printer.println("CustomPropType (")
        printer.indent {
            print("someString = "); someString.print(printer); println()
            print("someBool = "); someBool.print(printer); println()
            print("someArray = "); someArray.print(printer); println()
        }
        printer.print(")")
    }
    //deepClone
    //contexts
}


/**
 * #### Generated from [NanoFrameworkProtocolModel.kt:28]
 */
data class DeployData (
    val basePath: String,
    val assemblies: Array<String>?
) : IPrintable {
    //companion
    
    companion object : IMarshaller<DeployData> {
        override val _type: KClass<DeployData> = DeployData::class
        
        @Suppress("UNCHECKED_CAST")
        override fun read(ctx: SerializationCtx, buffer: AbstractBuffer): DeployData  {
            val basePath = buffer.readString()
            val assemblies = buffer.readNullable { buffer.readArray {buffer.readString()} }
            return DeployData(basePath, assemblies)
        }
        
        override fun write(ctx: SerializationCtx, buffer: AbstractBuffer, value: DeployData)  {
            buffer.writeString(value.basePath)
            buffer.writeNullable(value.assemblies) { buffer.writeArray(it) { buffer.writeString(it) } }
        }
        
        
    }
    //fields
    //methods
    //initializer
    //secondary constructor
    //equals trait
    override fun equals(other: Any?): Boolean  {
        if (this === other) return true
        if (other == null || other::class != this::class) return false
        
        other as DeployData
        
        if (basePath != other.basePath) return false
        if (assemblies != other.assemblies) return false
        
        return true
    }
    //hash code trait
    override fun hashCode(): Int  {
        var __r = 0
        __r = __r*31 + basePath.hashCode()
        __r = __r*31 + if (assemblies != null) assemblies.contentDeepHashCode() else 0
        return __r
    }
    //pretty print
    override fun print(printer: PrettyPrinter)  {
        printer.println("DeployData (")
        printer.indent {
            print("basePath = "); basePath.print(printer); println()
            print("assemblies = "); assemblies.print(printer); println()
        }
        printer.print(")")
    }
    //deepClone
    //contexts
}


/**
 * #### Generated from [NanoFrameworkProtocolModel.kt:39]
 */
data class DeviceInfo (
    val portName: String,
    val deviceName: String
) : IPrintable {
    //companion
    
    companion object : IMarshaller<DeviceInfo> {
        override val _type: KClass<DeviceInfo> = DeviceInfo::class
        
        @Suppress("UNCHECKED_CAST")
        override fun read(ctx: SerializationCtx, buffer: AbstractBuffer): DeviceInfo  {
            val portName = buffer.readString()
            val deviceName = buffer.readString()
            return DeviceInfo(portName, deviceName)
        }
        
        override fun write(ctx: SerializationCtx, buffer: AbstractBuffer, value: DeviceInfo)  {
            buffer.writeString(value.portName)
            buffer.writeString(value.deviceName)
        }
        
        
    }
    //fields
    //methods
    //initializer
    //secondary constructor
    //equals trait
    override fun equals(other: Any?): Boolean  {
        if (this === other) return true
        if (other == null || other::class != this::class) return false
        
        other as DeviceInfo
        
        if (portName != other.portName) return false
        if (deviceName != other.deviceName) return false
        
        return true
    }
    //hash code trait
    override fun hashCode(): Int  {
        var __r = 0
        __r = __r*31 + portName.hashCode()
        __r = __r*31 + deviceName.hashCode()
        return __r
    }
    //pretty print
    override fun print(printer: PrettyPrinter)  {
        printer.println("DeviceInfo (")
        printer.indent {
            print("portName = "); portName.print(printer); println()
            print("deviceName = "); deviceName.print(printer); println()
        }
        printer.print(")")
    }
    //deepClone
    //contexts
}
