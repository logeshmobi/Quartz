package io.gomobi.prvisualizer;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import io.gomobi.prvisualizer.analyzer.ConstructorDependencyAnalyzer;
import io.gomobi.prvisualizer.analyzer.SpringComponentAnalyzer;
import io.gomobi.prvisualizer.model.SpringComponent;
import io.gomobi.prvisualizer.parser.JavaSourceParser;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws Exception {

        if (args.length == 0) {
            System.err.println(
                    "Usage: java -jar pr-visualizer.jar <source-directory>"
            );

            System.exit(1);
        }

        Path sourceDirectory = Path.of(args[0]);

        JavaSourceParser parser =
                new JavaSourceParser();

        SpringComponentAnalyzer componentAnalyzer =
                new SpringComponentAnalyzer();

        ConstructorDependencyAnalyzer dependencyAnalyzer =
                new ConstructorDependencyAnalyzer();

        try (Stream<Path> files =
                     Files.walk(sourceDirectory)) {

            files
                    .filter(Main::isJavaFile)
                    .forEach(javaFile -> {

                        try {

                            CompilationUnit compilationUnit =
                                    parser.parse(javaFile);

                            ClassOrInterfaceDeclaration classDeclaration =
                                    parser.findPrimaryClass(
                                            compilationUnit
                                    );

                            SpringComponent component =
                                    componentAnalyzer.detect(
                                            classDeclaration
                                    );

                            List<String> dependencies =
                                    dependencyAnalyzer.analyze(
                                            classDeclaration
                                    );

                            System.out.println();
                            System.out.println(
                                    "======================================"
                            );

                            System.out.println(
                                    "FILE: " + javaFile
                            );

                            System.out.println(
                                    "CLASS: "
                                            + classDeclaration.getNameAsString()
                            );

                            System.out.println(
                                    "SPRING COMPONENT: "
                                            + component
                            );

                            System.out.println(
                                    "CONSTRUCTOR DEPENDENCIES:"
                            );

                            if (dependencies.isEmpty()) {

                                System.out.println(
                                        "  (none)"
                                );

                            } else {

                                dependencies.forEach(
                                        dependency ->
                                                System.out.println(
                                                        "  -> "
                                                                + dependency
                                                )
                                );
                            }

                        } catch (Exception exception) {

                            System.err.println(
                                    "Failed to parse: "
                                            + javaFile
                            );

                            exception.printStackTrace();
                        }
                    });
        }
    }

    private static boolean isJavaFile(Path path) {

        return Files.isRegularFile(path)
                && path.toString().endsWith(".java");
    }
}