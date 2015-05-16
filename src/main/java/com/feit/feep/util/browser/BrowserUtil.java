package com.feit.feep.util.browser;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import com.feit.feep.core.Global;

public class BrowserUtil {

    private BrowserUtil() {

    }

    private static final String WINDOWS = "WINDOWS";
    private static final String LINUX   = "LINUX";

    public static void openBrowser() {
        String serverPath = "http://localhost:8080/";
        openBrowser(serverPath + Global.getInstance().getFeepConfig().getContextPath() + "/feep_login/link.feep");
    }

    public static void openBrowser(String url) {
        try {
            Global.getInstance().logInfo("open browser url: " + url);
            String osName = System.getProperty("os.name");
            if ((osName.toUpperCase()).indexOf(WINDOWS) != -1) {
                gotoUrlWindows(url);
            } else if ((osName.toUpperCase()).indexOf(LINUX) != -1) {
                gotoUrlLinux(url);
            }
        } catch (Exception e) {
            Global.getInstance().logError("open browser error", e);
        }
    }

    private static void gotoUrlWindows(String url) throws IOException {
        String cmd = "rundll32 url.dll,FileProtocolHandler " + url;
        Runtime.getRuntime().exec(cmd);
    }

    private static void gotoUrlLinux(String url) throws URISyntaxException, IOException {
        URI uri = new URI(url);
        java.awt.Desktop.getDesktop().browse(uri);
    }
}