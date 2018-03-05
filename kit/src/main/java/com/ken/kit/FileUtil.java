package com.ken.kit;

import java.io.*;
import java.nio.channels.FileChannel;


public class FileUtil {

    public static void transfer(File source, File target) throws IOException {
        if (!source.isFile()) return;

        createDirectory(target.getParentFile());
        FileChannel sourceChannel = null;
        FileChannel targetChannel = null;
        try {
            sourceChannel = new FileInputStream(source).getChannel();
            targetChannel = new FileOutputStream(target).getChannel();
            sourceChannel.transferTo(0, source.length(), targetChannel);
        } finally {
            try {
                if (null != sourceChannel) {
                    sourceChannel.close();
                }
                if (null != targetChannel) {
                    targetChannel.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void transferNoClose(InputStream in, OutputStream out)throws IOException {
            int count;
            byte[] buff = new byte[2048];
            while ((count = in.read(buff)) != -1) {
                out.write(buff, 0, count);
            }
    }

    public static void transferCloseIn(InputStream in, OutputStream out)throws IOException {
        try {
            int count;
            byte[] buff = new byte[2048];
            while ((count = in.read(buff)) != -1) {
                out.write(buff, 0, count);
            }
        } finally {
            if (null != in)
                try {
                    in.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
        }
    }

    public static void transferCloseOut(InputStream in, OutputStream out)throws IOException {
        try {
            int count;
            byte[] buff = new byte[2048];
            while ((count = in.read(buff)) != -1) {
                out.write(buff, 0, count);
            }
        } finally {
            if (null != out) {
                try {
                    out.close();
                } catch (IOException ioe){
                    ioe.printStackTrace();
                }

            }
        }
    }

    public static void transferCloseAll(InputStream in, OutputStream out) throws IOException {
        try {
            int count;
            byte[] buff = new byte[2048];
            while ((count = in.read(buff)) != -1) {
                out.write(buff, 0, count);
            }
        } finally {
            try {
                if (null != in) in.close();
                if (null != out) out.close();
            }
            catch (IOException ioe){
                ioe.printStackTrace();
            }
        }
    }

    public static void createDirectory(File dir) throws IOException {
        if (null != dir && !dir.exists()) {
            if (!dir.mkdirs()){
                throw new IOException("fail to create directory" + dir.getCanonicalPath());
            }
        }
    }

    /**
     * 删除文件，把指定文件夹/文件删掉，文件夹下的子文件也全部删掉
     * @param root
     */
    public static void deleteFile(File root) {
        if (root.isDirectory()) {
            for (File file: root.listFiles()) {
                deleteFile(file);
            }
            root.delete();
        } else if (root.isFile()){
            root.delete();
        }
    }

    /**
     * 删除文件，如果输入是文件夹，只把文件夹下的子文件删掉，文件夹本身不删除
     * @param root
     */
    public static void cleanDirectory(File root)  {
        if (root.isDirectory()) {
            for (File file: root.listFiles()) {
                deleteFile(file);
            }
        } else if (root.isFile()){
            root.delete();
        }
    }

}
