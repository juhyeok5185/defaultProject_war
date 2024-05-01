package com.ein.common.config;

import org.hibernate.dialect.MariaDB10Dialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StandardBasicTypes;

public class CustomMariaDBDialect extends MariaDB10Dialect {

    public CustomMariaDBDialect() {
        super();
        registerFunction("group_concat", new StandardSQLFunction("group_concat", StandardBasicTypes.STRING));
    }

}
