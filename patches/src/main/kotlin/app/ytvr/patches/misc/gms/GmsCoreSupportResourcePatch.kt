/*
 * Adapted from MorpheApp/morphe-patches:
 * https://github.com/MorpheApp/morphe-patches/blob/main/patches/src/main/kotlin/app/morphe/patches/shared/misc/gms/GmsCoreSupportPatch.kt
 * (GPLv3)
 */

package app.ytvr.patches.misc.gms

import app.morphe.patcher.patch.resourcePatch
import org.w3c.dom.Element
import org.w3c.dom.Node

private const val GMS_CORE_VENDOR_GROUP_ID = "app.revanced"
private const val YOUTUBE_VR_PACKAGE_NAME = "com.google.android.apps.youtube.vr.oculus"

// SHA-1 of the certificate the original YouTube VR APK is signed with.
private const val YOUTUBE_VR_SPOOFED_SIGNATURE = "db3415c8886becda9ac0a5ca2cdf35bf735bea47"

/**
 * Resource patch that updates the Android manifest to support GmsCore.
 */
@Suppress("unused")
val gmsCoreSupportResourcePatch = resourcePatch {
    execute {
        // Add metadata to the manifest to support spoofing the package name
        // and signature of GmsCore.
        document("AndroidManifest.xml").use { document ->
            fun Node.adoptChild(
                tagName: String,
                block: Element.() -> Unit
            ) {
                val child = ownerDocument.createElement(tagName)
                child.block()
                appendChild(child)
            }

            val applicationNode = document
                .getElementsByTagName("application")
                .item(0)

            // Spoof package name and signature.
            applicationNode.adoptChild("meta-data") {
                setAttribute(
                    "android:name",
                    "$GMS_CORE_VENDOR_GROUP_ID.android.gms.SPOOFED_PACKAGE_NAME"
                )
                setAttribute("android:value", YOUTUBE_VR_PACKAGE_NAME)
            }

            applicationNode.adoptChild("meta-data") {
                setAttribute(
                    "android:name",
                    "$GMS_CORE_VENDOR_GROUP_ID.android.gms.SPOOFED_PACKAGE_SIGNATURE"
                )
                setAttribute("android:value", YOUTUBE_VR_SPOOFED_SIGNATURE)
            }

            // GmsCore presence detection in extension.
            applicationNode.adoptChild("meta-data") {
                setAttribute("android:name", "app.revanced.MICROG_PACKAGE_NAME")
                setAttribute("android:value", "$GMS_CORE_VENDOR_GROUP_ID.android.gms")
            }
        }

        // Text transformations on the decoded manifest.
        val transformations = mapOf(
            "com.google.android.c2dm" to "$GMS_CORE_VENDOR_GROUP_ID.android.c2dm",
            "com.google.android.providers.gsf" to "$GMS_CORE_VENDOR_GROUP_ID.android.providers.gsf",
            "com.google.android.gms.version" to "$GMS_CORE_VENDOR_GROUP_ID.android.gms.version",
            "com.google.android.gms.phenotype" to "$GMS_CORE_VENDOR_GROUP_ID.android.gms.phenotype",
            "</queries>" to "<package android:name=\"$GMS_CORE_VENDOR_GROUP_ID.android.gms\"/></queries>",
        )

        val manifest = get("AndroidManifest.xml")
        manifest.writeText(
            transformations.entries.fold(manifest.readText()) { acc, (from, to) ->
                acc.replace(
                    from,
                    to,
                )
            }
        )
    }
}
