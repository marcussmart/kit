package com.dou.generator.codegen.javamapper.elements;

import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.AbstractJavaMapperMethodGenerator;

import java.util.Set;
import java.util.TreeSet;

/**
 * Created by s on 2017/2/17.
 */
public class SpecialSelectSelectiveMethodGenerator extends AbstractJavaMapperMethodGenerator {



    @Override
    public void addInterfaceElements(Interface interfaze) {

        Method selectOne = getMethod(interfaze, "selectOne", false);
        Method selectSelective = getMethod(interfaze, "selectSelective", true);

        interfaze.addMethod(selectOne);
        interfaze.addMethod(selectSelective);
    }

    private Method getMethod(Interface interfaze, String name, boolean isList) {
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);

        FullyQualifiedJavaType returnType = introspectedTable.getRules()
                .calculateAllFieldsClass();

        //是否是列表
        FullyQualifiedJavaType list;
        if (isList) {
            list = new FullyQualifiedJavaType("List<" + returnType.getShortName() + ">");
        } else {
            list = new FullyQualifiedJavaType(returnType.getShortName());
        }

        method.setReturnType(list);
        importedTypes.add(returnType);

        method.setName(name);

        FullyQualifiedJavaType parameterType;
        parameterType = new FullyQualifiedJavaType(
                introspectedTable.getBaseRecordType());

/*
            parameterType = introspectedTable.getRules()
                    .calculateAllFieldsClass();
*/

        importedTypes.add(parameterType);


        importedTypes.add(parameterType);
        method.addParameter(new Parameter(parameterType, "record")); //$NON-NLS-1$

        context.getCommentGenerator().addGeneralMethodComment(method,
                introspectedTable);


        importedTypes.add(new FullyQualifiedJavaType("java.util.List"));

        interfaze.addImportedTypes(importedTypes);
        return method;
    }


}
