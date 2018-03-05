package com.dou.generator.plugins;

import com.dou.generator.plugins.service.SpecialServiceGenerator;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.codegen.AbstractJavaGenerator;
import org.mybatis.generator.config.PropertyRegistry;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by s on 2017/2/20.
 */
public class SpecialServicePlugin extends PluginAdapter{



    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }


    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
        List<GeneratedJavaFile> answer = new LinkedList<>();

        AbstractJavaGenerator javaGenerator = new SpecialServiceGenerator();
        LinkedList<String> warnings = new LinkedList<>();

        initJavaGenerator(introspectedTable, javaGenerator, warnings);

        List<CompilationUnit> compilationUnits = javaGenerator.getCompilationUnits();
        for (CompilationUnit compilationUnit : compilationUnits) {
            GeneratedJavaFile gjf = new GeneratedJavaFile(compilationUnit,
                    context.getJavaModelGeneratorConfiguration()
                            .getTargetProject(),
                    context.getProperty(PropertyRegistry.CONTEXT_JAVA_FILE_ENCODING),
                    context.getJavaFormatter());
            answer.add(gjf);
        }
        return answer;
    }

    private void initJavaGenerator(IntrospectedTable introspectedTable, AbstractJavaGenerator javaGenerator, LinkedList<String> warnings) {
        javaGenerator.setContext(context);
        javaGenerator.setIntrospectedTable(introspectedTable);
        javaGenerator.setProgressCallback(null);
        javaGenerator.setWarnings(warnings);
    }
}
