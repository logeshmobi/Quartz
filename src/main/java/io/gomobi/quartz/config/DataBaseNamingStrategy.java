package io.gomobi.quartz.config;


import io.gomobi.quartz.common.util.StringUtil;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class DataBaseNamingStrategy extends PhysicalNamingStrategyStandardImpl {

    @Override
    public Identifier toPhysicalTableName(Identifier logicalName, JdbcEnvironment context) {
        return toUpperCaseSnakeNaming(logicalName);
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier logicalName, JdbcEnvironment context) {
        return toUpperCaseSnakeNaming(logicalName);
    }

    @Override
    public Identifier toPhysicalTypeName(Identifier logicalName, JdbcEnvironment jdbcEnvironment) {
        return toUpperCaseSnakeNaming(logicalName);
    }

    private static Identifier toUpperCaseSnakeNaming(Identifier logicalName){
        return Identifier.toIdentifier(StringUtil.camelToUpperSnake(logicalName.getText()));
    }
}
