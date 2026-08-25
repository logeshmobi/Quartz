package io.gomobi.prvisualizer.analyzer;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;
import io.gomobi.prvisualizer.model.SpringComponent;

public class SpringComponentAnalyzer {

    public SpringComponent detect(
            ClassOrInterfaceDeclaration classDeclaration
    ) {

        if (hasAnnotation(classDeclaration, "RestController")) {
            return SpringComponent.REST_CONTROLLER;
        }

        if (hasAnnotation(classDeclaration, "Controller")) {
            return SpringComponent.CONTROLLER;
        }

        if (hasAnnotation(classDeclaration, "Service")) {
            return SpringComponent.SERVICE;
        }

        if (hasAnnotation(classDeclaration, "Repository")) {
            return SpringComponent.REPOSITORY;
        }

        if (hasAnnotation(classDeclaration, "Component")) {
            return SpringComponent.COMPONENT;
        }

        if (hasAnnotation(classDeclaration, "Configuration")) {
            return SpringComponent.CONFIGURATION;
        }

        if (hasAnnotation(classDeclaration, "EventListener")) {
            return SpringComponent.EVENT_LISTENER;
        }

        /*
         * We intentionally don't classify every class
         * as a Spring component.
         */
        return SpringComponent.UNKNOWN;
    }

    private boolean hasAnnotation(
            ClassOrInterfaceDeclaration classDeclaration,
            String annotationName
    ) {

        return classDeclaration
                .getAnnotations()
                .stream()
                .map(AnnotationExpr::getNameAsString)
                .anyMatch(annotationName::equals);
    }
}