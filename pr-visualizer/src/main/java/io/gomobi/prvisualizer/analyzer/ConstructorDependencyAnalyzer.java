package io.gomobi.prvisualizer.analyzer;

import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

import java.util.List;

public class ConstructorDependencyAnalyzer {

    public List<String> analyze(
            ClassOrInterfaceDeclaration classDeclaration
    ) {

        return classDeclaration
                .getConstructors()
                .stream()
                .findFirst()
                .map(ConstructorDeclaration::getParameters)
                .stream()
                .flatMap(List::stream)
                .map(Parameter::getTypeAsString)
                .toList();
    }
}