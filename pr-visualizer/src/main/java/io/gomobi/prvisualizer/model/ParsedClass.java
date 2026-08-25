package io.gomobi.prvisualizer.model;

import java.util.ArrayList;
import java.util.List;

public class ParsedClass {

    private String packageName;

    private String className;

    private SpringComponent component;

    private final List<String> constructorDependencies = new ArrayList<>();

    public ParsedClass(
            String packageName,
            String className,
            SpringComponent component
    ) {
        this.packageName = packageName;
        this.className = className;
        this.component = component;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getClassName() {
        return className;
    }

    public SpringComponent getComponent() {
        return component;
    }

    public List<String> getConstructorDependencies() {
        return constructorDependencies;
    }

    public void addConstructorDependency(String dependency) {
        constructorDependencies.add(dependency);
    }
}