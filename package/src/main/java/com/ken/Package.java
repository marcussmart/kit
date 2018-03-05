package com.ken;

import com.ken.kit.FileUtil;
import com.ken.kit.StringUtil;
import com.ken.kit.ZipUtil;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 * package
 */
public class Package {


    private File sourceDir;
    private File targetDir;

    private final String projectDirKey = "projectDir";
    private final String projectNameKey = "projectName";
    private final String javaPathKey = "javaPath";
    private final String resourcePathKey = "resourcePath";
    private final String webAppPathKey = "webAppPath";
    private final String[] keys = {projectDirKey, projectNameKey, javaPathKey, resourcePathKey, webAppPathKey};

    private Properties config;

    public static void main(String args[]) throws IOException {
        Package aPackage = new Package();
        aPackage.initConfig();
        aPackage.copy(aPackage.loadPatch());
        aPackage.compress();
    }

    private void compress() throws IOException {
        ZipUtil.compress(targetDir);
    }

    private void initConfig() throws IOException {
        final String configFileName = "config.properties";

        Properties properties = new Properties();
        File configFile = new File(System.getProperty("user.dir"), configFileName);
        if (!configFile.exists()) {
            throw new RuntimeException("config.properties is not exist, please put" +
                    " config.properties to the dir which package.jar in");
        }
        properties.load(new FileReader(configFile));
        for (String key : keys) {
            String value = properties.getProperty(key);
            if (null == value) {
                throw new IllegalArgumentException(key + " in config.properties isn't pointed");
            }
        }
        config = properties;
        sourceDir = new File(config.getProperty(projectDirKey));
        targetDir = new File(System.getProperty("user.dir"), config.getProperty(projectNameKey));
        FileUtil.deleteFile(targetDir);
    }

    private List<String> loadPatch() throws IOException {
        final String patchFileName = "file.properties";
        File file = new File(System.getProperty("user.dir"), patchFileName);
        if (!file.exists()) {
            throw new RuntimeException("file.properties is not exist, " +
                    "please put file.properties to the dir which package.jar in");
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-16LE"));
        String line;
        List<String> lines = new LinkedList<String>();
        while (null != (line = reader.readLine())) {
            if (!StringUtil.isEmpty(line)) {
                lines.add(line);
            }
        }
        return lines;
    }

    private void copy(List<String> files) throws IOException {
        for (String file : files) {
            if (file.contains(config.getProperty(webAppPathKey))) {
                copySourceFile(file);
            } else if (file.contains(config.getProperty(javaPathKey)) ||
                       file.contains(config.getProperty(resourcePathKey))) {
                copyClassFile(file);
            }
        }
    }

    private void copySourceFile(String file) throws IOException {
        String webAppPath = config.getProperty(webAppPathKey);
        String relativePath = file.substring(file.indexOf(webAppPath) + webAppPath.length());
        FileUtil.transfer(new File(sourceDir, relativePath),
                new File(targetDir, relativePath));
    }

    private void copyClassFile(String file) throws IOException {
        String resourcePath = config.getProperty(resourcePathKey);
        String javaPath = config.getProperty(javaPathKey);
        int index = file.contains(resourcePath)
                ? file.indexOf(resourcePath) + resourcePath.length()
                : file.indexOf(javaPath) + javaPath.length();
        String relativePath = file.substring(index).replaceAll("\\.java$", ".class");
        File source = new File(sourceDir, "WEB-INF/classes/" + relativePath);
        File target = new File(targetDir, "WEB-INF/classes/" + relativePath);
        FileUtil.transfer(source, target);

        final String fileName = source.getName().replaceAll("\\.class$", "");
        File[] files = source.getParentFile().listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.matches(fileName + "\\$.*");
            }
        });
        for (File item: files) {
            String path = item.getCanonicalPath().substring(item.getCanonicalPath().indexOf(sourceDir.getCanonicalPath()) + sourceDir.getCanonicalPath().length());
            FileUtil.transfer(item, new File(targetDir, path));
        }
    }


}
