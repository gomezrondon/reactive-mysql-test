package com.gomezrondon.reactivemysqltest.config;

import com.github.jasync.r2dbc.mysql.JasyncConnectionFactory;
import com.github.jasync.sql.db.mysql.pool.MySQLConnectionFactory;
import com.github.jasync.sql.db.mysql.util.URLParser;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import java.nio.charset.StandardCharsets;


@Configuration
@EnableR2dbcRepositories(basePackages="com.gomezrondon.reactivemysqltest.repositories")
public class Config extends AbstractR2dbcConfiguration {
    @Override
    public ConnectionFactory connectionFactory() {
        String url = "mysql://root:root@localhost:3306/test";
        return new JasyncConnectionFactory(new MySQLConnectionFactory(
                URLParser.INSTANCE.parseOrDie(url, StandardCharsets.UTF_8)
        ));
    }

}