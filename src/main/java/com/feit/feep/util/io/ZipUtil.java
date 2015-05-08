package com.feit.feep.util.io;


public class ZipUtil extends FileContext {

    /*public static void unZip(File file) throws IOException {
        ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(file), Charset.forName(CHARSET));
        ZipEntry zipEntry = null;
        ZipFile zipFile = null;
        String folderName = TMP_Path + "/" + file.getName().split("[.]")[1];
        while ((zipEntry = zipInputStream.getNextEntry()) != null) {
            newFile(folderName);
            String tempfileName = zipEntry.getName();
            File tempfile = newFile(folderName + "/" + tempfileName);
            OutputStream os = new FileOutputStream(tempfile);
            zipFile = new ZipFile(file);
            InputStream is = zipFile.getInputStream(zipEntry);
            int len = 0;
            while ((len = is.read()) != -1)
                os.write(len);
            closeOutputStream(os);
            closeInputStream(is);
        }
        closeZipInputStream(zipInputStream);
    }

    public static File newFile(String path) {
        File file = null;
        try {
            file = new File(path);
            if (!file.getParentFile().exists())
                file.getParentFile().mkdirs();
            if (file.isDirectory()) {
                if (file.exists())
                    file.delete();
                file.mkdir();
            }
        }
        catch (Exception e) {
            Global.getInstance().logError("create file error，PATH:" + path, e);
            throw new RuntimeException(e);
        }
        return file;
    }

    public static void test1() throws IOException {
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream("D:\\testZip.zip"), Charset.forName(CHARSET));
        // 实例化一个名称为ab.txt的ZipEntry对象
        ZipEntry entry = new ZipEntry("ab.txt");
        // 设置注释
        zos.setComment("zip测试for单个文件");
        // 把生成的ZipEntry对象加入到压缩文件中，而之后往压缩文件中写入的内容都会放在这个ZipEntry对象里面
        zos.putNextEntry(entry);
        InputStream is = new FileInputStream("D:\\ab.txt");
        int len = 0;
        while ((len = is.read()) != -1)
            zos.write(len);
        is.close();
        zos.close();
    }

    public static void test2() throws IOException {
        File inFile = new File("D:\\test");
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream("D:\\test.zip"), Charset.forName(CHARSET));
        zos.setComment("多文件处理");
        zipFile(inFile, zos, "");
        zos.close();
    }

    public static void zipFile(File inFile, ZipOutputStream zos, String dir) throws IOException {
        if (inFile.isDirectory()) {
            File[] files = inFile.listFiles();
            for (File file : files)
                zipFile(file, zos, dir + "\\" + inFile.getName());
        } else {
            String entryName = null;
            if (!"".equals(dir))
                entryName = dir + "\\" + inFile.getName();
            else
                entryName = inFile.getName();
            ZipEntry entry = new ZipEntry(entryName);
            zos.putNextEntry(entry);
            InputStream is = new FileInputStream(inFile);
            int len = 0;
            while ((len = is.read()) != -1)
                zos.write(len);
            is.close();
        }
    }*/

}