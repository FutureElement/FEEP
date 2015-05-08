package com.feit.feep.util.resources;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import com.feit.feep.util.FeepUtil;

public class XmlMappingScanner {

    private XmlMappingScanner() {

    }

    public static String[] getAllMappingFilePath(String path) {
        File file = FeepUtil.getClassPathFile(path);
        List<String> pathes = new LinkedList<String>();
        getAllMappingFileByFile(file, pathes);
        return pathes.toArray(new String[pathes.size()]);
    }

    private static void getAllMappingFileByFile(File file, List<String> pathes) {
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                getAllMappingFileByFile(f, pathes);
            } else {
                String filePath = f.getPath();
                if (filePath.endsWith(".xml")) {
                    pathes.add(filePath);
                }
            }
        }
    }

}
