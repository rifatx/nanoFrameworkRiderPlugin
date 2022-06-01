package model.rider

import com.jetbrains.rider.model.nova.ide.SolutionModel
import com.jetbrains.rd.generator.nova.*
import com.jetbrains.rd.generator.nova.PredefinedType.*
import com.jetbrains.rd.generator.nova.csharp.CSharp50Generator
import com.jetbrains.rd.generator.nova.kotlin.Kotlin11Generator

@Suppress("unused")
object NanoFrameworkProtocolModel : Ext(SolutionModel.Solution) {
    init {
        setting(CSharp50Generator.Namespace, "ReSharperPlugin.nanoFrameworkPlugin")
        setting(Kotlin11Generator.Namespace, "com.nanoframeworkplugin.rider.protocol")

        // Properties
        property("someProperty", structdef("customPropType") {
            field("someString", string)
            field("someBool", bool)
            field("someArray", array(string))
        })

//        // Maps
//        map("map", string, string)

        // Remote procedure on backend
        call(
            "deploy",
            structdef("deployData") {
                field("basePath", string)
                field("assemblies", array(string).nullable)
            },
            array(string)

        ).async

        // Remote procedure on frontend
        callback(
            "serialDeviceFound",
            array(structdef("deviceInfo") {
                field("portName", string)
                field("deviceName", string)
            }),
            void
        ).async

//        // Event on backend
//        sink("sink", string).async
//
//        // Event on frontend
//        source("source", string).async
//
//        // Bidirectional event
//        signal("signal", string)
    }
}
