package com.dou.template;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.FileTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by s on 2017/2/5.
 */
public class Template {

    private TemplateEngine engine;
    private boolean initialized = false;


    private final void initialize() {
        if (!initialized) {
            synchronized (this) {

            ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
            templateResolver.setPrefix("template/");
            templateResolver.setSuffix(".tpl");
            templateResolver.setCharacterEncoding("UTF-8");
            templateResolver.setTemplateMode("TEXT");

            engine = new TemplateEngine();
            engine.setTemplateResolver(templateResolver);
            }
        }
    }

    public void process(String templateName, Map arguments, Writer writer) {
        initialize();

        Context ctx = new Context();
        ctx.setVariables(arguments);
        engine.process(templateName, ctx, writer);
    }

    public static void main(String[] args) throws UnsupportedEncodingException {

        Template template = new Template();

        Map arguments = new HashMap();
        arguments.put("name", "dou");

        Writer writer = new OutputStreamWriter(System.out, "UTF-8");

        template.process("dou", arguments, writer);
    }

}
