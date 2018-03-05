package com.dou.generator.plugins.service;

import com.dou.generator.codegen.javamapper.elements.SpecialSelectSelectiveMethodGenerator;
import org.mybatis.generator.api.FullyQualifiedTable;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.codegen.AbstractJavaGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by s on 2017/2/20.
 */
public class SpecialServiceGenerator extends AbstractJavaGenerator {


    @Override
    public List<CompilationUnit> getCompilationUnits() {

        List<CompilationUnit> answer = new LinkedList<>();
//        context
        FullyQualifiedTable fullyQualifiedTable = introspectedTable.getFullyQualifiedTable();
        String domainObjectName = fullyQualifiedTable.getDomainObjectName() + "Service";


        String daoPackage = context.getJavaClientGeneratorConfiguration().getTargetPackage();
        String servicePackage = daoPackage.replace(".dao", ".service");
        String type = servicePackage + "." + domainObjectName;

        //接口
        Interface interfaze = new Interface(type);
        interfaze.setVisibility(JavaVisibility.PUBLIC);
//        interfaze.addImportedType(new FullyQualifiedJavaType("com.yobbo.xyz.exception.BusinessException"));

        addDeleteByPrimaryKeyMethod(interfaze);
        addInsertSelectiveMethod(interfaze);
        addUpdateByPrimaryKeySelectiveMethod(interfaze);
        addSelectByPrimaryKeyMethod(interfaze);
        addSelectSelectiveMethod(interfaze);


        answer.add(interfaze);
        return answer;
    }


    protected void addInsertSelectiveMethod(Interface interfaze) {
        AbstractJavaMapperMethodGenerator methodGenerator = new InsertSelectiveMethodGenerator();
        initializeAndExecuteGenerator(methodGenerator, interfaze);
    }

    protected void addSelectByPrimaryKeyMethod(Interface interfaze) {
        AbstractJavaMapperMethodGenerator methodGenerator = new SelectByPrimaryKeyMethodGenerator(false);
        initializeAndExecuteGenerator(methodGenerator, interfaze);
    }

    private void addSelectSelectiveMethod(Interface interfaze) {
        AbstractJavaMapperMethodGenerator methodGenerator = new SpecialSelectSelectiveMethodGenerator();
        initializeAndExecuteGenerator(methodGenerator, interfaze);
    }

    protected void addUpdateByPrimaryKeySelectiveMethod(Interface interfaze) {
        AbstractJavaMapperMethodGenerator methodGenerator = new UpdateByPrimaryKeySelectiveMethodGenerator();
        initializeAndExecuteGenerator(methodGenerator, interfaze);
    }

    protected void addDeleteByPrimaryKeyMethod(Interface interfaze) {
        AbstractJavaMapperMethodGenerator methodGenerator = new SpecialDeleteByPrimaryKeyMethodGenerator();
        initializeAndExecuteGenerator(methodGenerator, interfaze);
    }

    protected void initializeAndExecuteGenerator(
            AbstractJavaMapperMethodGenerator methodGenerator,
            Interface interfaze) {
        methodGenerator.setContext(context);
        methodGenerator.setIntrospectedTable(introspectedTable);
        methodGenerator.setProgressCallback(progressCallback);
        methodGenerator.setWarnings(warnings);
        methodGenerator.addInterfaceElements(interfaze);
    }


}
