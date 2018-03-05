package com.dou.generator;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.ken.kit.FileUtil;

/**
 * Created by s on 2017/2/15.
 */
public class Main {

    public static final String conf = "/conf.properties";
    public static final String generatorConfigFileName = "generator_config_file";

    public static void main(String[] args) throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {

        //加载配置文件
        Properties properties = new Properties();
        properties.load(Main.class.getResourceAsStream(conf));


        boolean overwrite = true;
        List<String> warnings = new ArrayList<>();
        ConfigurationParser cp = new ConfigurationParser(warnings);

        String generatorConfigFile = "/" + properties.get(generatorConfigFileName);
        Configuration config = cp.parseConfiguration(Main.class.getResourceAsStream(generatorConfigFile));

        clean(config);

        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);

        if (warnings.size() > 0) {
            System.out.println(System.getProperty("user.dir"));
            System.out.println(warnings);
        } else {
            System.out.println("生成完毕！");
        }

    }


    private static void clean(Configuration config) {
        List<Context> contexts = config.getContexts();
        Context context;
        if (contexts.size() > 0) {
            context = contexts.get(0);

            String modelPath = context.getJavaModelGeneratorConfiguration().getTargetProject();
            String mapPath = context.getSqlMapGeneratorConfiguration().getTargetProject();
            String clientPath = context.getJavaClientGeneratorConfiguration().getTargetProject();

            FileUtil.cleanDirectory(new File(modelPath));
            FileUtil.cleanDirectory(new File(mapPath));
            FileUtil.cleanDirectory(new File(clientPath));
        }
    }
}
