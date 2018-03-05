package com.ken.kit;


import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtil {

    public static void compress(File root) throws IOException {
        if (null == root)
            throw new IOException("The compress file is null");

        ZipOutputStream out = null;
        try {
            out = new ZipOutputStream(new FileOutputStream(root.getCanonicalPath() + ".zip"));
            compress0(root, out, root.getParentFile().getCanonicalPath());
        } finally {
            if (null != out) {
                out.close();
            }
        }
    }

    private static void compress0(File root, ZipOutputStream out, final String parent) throws IOException {
        File[] files = root.listFiles();
        for (File file: files){
            if (file.isFile()) {
                ZipEntry entry = new ZipEntry(file.getCanonicalPath().replace(parent,"").substring(1));
                out.putNextEntry(entry);
                FileUtil.transferCloseIn(new FileInputStream(file), out);
            } else if (file.isDirectory()){
                compress0(file, out, parent);
            }
        }
    }


}
