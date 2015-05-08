package com.feit.feep.util.io;


public class FileContext {
    /*protected static final int    type_like             = 0;
    protected static final int    type_likeright        = 1;
    protected static final int    type_likeleft         = 2;
    protected static final int    type_equals           = 3;
    protected static final int    type_smaller          = 4;
    protected static final int    type_smaller_equals   = 5;
    protected static final int    type_larger           = 6;
    protected static final int    type_larger_equals    = 7;
    protected static final int    type_in               = 8;
    protected static final int    type_notequals        = 9;

    protected static final int    relation_and          = 1;
    protected static final int    relation_or           = 2;

    protected final static String configXmlPath         = "WebRoot\\WEB-INF\\FEEP.xml";
    protected final static String InitCONFPath          = "xxx";
    protected final static String UpdateCONFPath        = "xxx";
    protected final static int    XML                   = 1;
    protected final static int    CONF                  = 2;

    protected final static String dbconfigNodeName      = "db-connections";
    protected final static String initConfigNodeName    = "init";
    protected final static String contextConfigNodeName = "context";

    public static boolean isNull(String str) {
        if (null == str || str.trim().equals("")) {
            return true;
        }
        return false;
    }

    public static int toInt(Object o) {
        if (null == o || o.toString().trim().equals("")) {
            return 0;
        } else {
            if (o.toString().contains(".")) {
                return Integer.parseInt(o.toString().substring(0, o.toString().indexOf(".")));
            } else
                return Integer.parseInt(o.toString());

        }
    }

    protected static final String CHARSET             = "UTF-8";
    protected static final String PACKAGE_STARTNAME   = "FEEP.";
    protected static final String PACKAGE_LIB_NAME    = ".BLL";
    protected static final String PACKAGE_CLIENT_NAME = ".Client";
    protected static final String TMP_Path            = "WebRoot/WEB-INF/FEEP/TMP";
    protected static final String LIB_Path            = "WebRoot/WEB-INF/lib";
    protected static final String CLIENT_Path         = "WebRoot/WEB-INF/clientLib";

    public static String getWEBROOT_FULLPATH() {
        return "/FEEP";
    }

    public static void closeInputStream(InputStream is) {
        try {
            if (null != is) {
                is.close();
            }
        }
        catch (IOException e) {
            Global.getInstance().logError("close InputStream error", e);
        }
    }

    public static void closeOutputStream(OutputStream os) {
        try {
            if (null != os) {
                os.close();
            }
        }
        catch (IOException e) {
            Global.getInstance().logError("close OutputStream error", e);
        }
    }

    public static void closeZipInputStream(ZipInputStream zis) {
        try {
            if (null != zis) {
                zis.close();
            }
        }
        catch (IOException e) {
            Global.getInstance().logError("close ZipInputStream error", e);
        }
    }

    public static void closeZipOutputStream(ZipOutputStream zos) {
        try {
            if (null != zos) {
                zos.close();
            }
        }
        catch (IOException e) {
            Global.getInstance().logError("close ZipOutputStream error", e);
        }
    }

    public static void closeBufferedReader(BufferedReader reader) {
        try {
            if (null != reader) {
                reader.close();
            }
        }
        catch (IOException e) {
            Global.getInstance().logError("close BufferedReader error", e);
        }
    }

    public static void closeServletOutputStream(ServletOutputStream sos) {
        try {
            if (null != sos) {
                sos.close();
            }
        }
        catch (IOException e) {
            Global.getInstance().logError("close ServletOutputStream error", e);
        }
    }*/
}