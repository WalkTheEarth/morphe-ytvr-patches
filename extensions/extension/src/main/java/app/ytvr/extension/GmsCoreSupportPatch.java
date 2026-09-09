package app.ytvr.extension;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.util.Log;

/**
 * Minimal GmsCore (microG) support for YouTube VR.
 *
 * Adapted from:
 * https://github.com/MorpheApp/morphe-patches/blob/main/extensions/shared-youtube/library/src/main/java/app/morphe/extension/shared/patches/GmsCoreSupportPatch.java
 * (GPLv3)
 */
@SuppressWarnings("unused")
public class GmsCoreSupportPatch {

    private static final String TAG = "GmsCoreSupportPatch";

    private static String getGmsCoreVendorGroupId() {
        return null; // Modified during patching.
    }

    private static String getOriginalPackageName() {
        return null; // Modified during patching.
    }

    /**
     * Checks that GmsCore is installed so the patched app does not silently fail
     * when GmsCore is missing. Called from the main activity onCreate.
     */
    public static void checkGmsCore(Activity activity) {
        String gmsCorePackageName = getGmsCoreVendorGroupId() + ".android.gms";

        try {
            activity.getPackageManager().getPackageInfo(gmsCorePackageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            Log.w(TAG, "GmsCore is not installed: " + gmsCorePackageName);

            new AlertDialog.Builder(activity)
                    .setTitle("GmsCore")
                    .setMessage("GmsCore is not installed. Install GmsCore to sign in with a Google account.")
                    .setPositiveButton(android.R.string.ok, null)
                    .show();
        }
    }
}
