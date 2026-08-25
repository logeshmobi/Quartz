package io.gomobi.prvisualizer.parser;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

import java.nio.file.Path;

public class JavaSourceParser {

    public CompilationUnit parse(Path javaFile) throws Exception {

        return StaticJavaParser.parse(javaFile);
    }

    public ClassOrInterfaceDeclaration findPrimaryClass(
            CompilationUnit compilationUnit
    ) {

        return compilationUnit
                .findFirst(ClassOrInterfaceDeclaration.class)
                .orElseThrow(() ->
                        new IllegalStateException(
                                "No class found in source file"
                        )
                );
    }
}