package com.dou.generator.special;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.internal.rules.FlatModelRules;

/**
 * Created by s on 2017/2/17.
 */
public class SpecialRule extends FlatModelRules {


    public SpecialRule(IntrospectedTable introspectedTable) {
        super(introspectedTable);
    }


    @Override
    public boolean generateUpdateByPrimaryKeyWithoutBLOBs() {
        return false;
    }

    @Override
    public boolean generateUpdateByPrimaryKeyWithBLOBs() {
        return false;
    }

    @Override
    public boolean generateInsert() {
        return false;
    }

/*
    @Override
    public boolean generateSelectByPrimaryKey() {
        return false;
    }
*/
}
