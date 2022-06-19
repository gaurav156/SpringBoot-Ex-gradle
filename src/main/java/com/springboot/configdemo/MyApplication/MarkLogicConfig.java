package com.springboot.configdemo.MyApplication;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import com.marklogic.client.document.JSONDocumentManager;
import com.marklogic.client.document.XMLDocumentManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableAutoConfiguration
//@ComponentScan
public class MarkLogicConfig {

    @Value("${ml.host}")
    private String host;

    @Value("${ml.port.emerald}")
    private int mlEmeraldPort;

    @Value("${ml.port.books}")
    private int mlBooksPort;

    @Value("${ml.port.customers}")
    private int mlCustomersPort;

    @Value("${ml.username}")
    private String mlUsername;

    @Value("${ml.password}")
    private String mlPassword;

//    @Bean
    public DatabaseClient getDatabaseClient(String db) {
        if(db.equals("books")) {
            return DatabaseClientFactory.newClient(host, mlBooksPort, new DatabaseClientFactory.DigestAuthContext(mlUsername, mlPassword));
        } else if (db.equals("customers")) {
            return DatabaseClientFactory.newClient(host, mlCustomersPort, new DatabaseClientFactory.DigestAuthContext(mlUsername, mlPassword));
        } else {
            return DatabaseClientFactory.newClient(host, mlEmeraldPort, new DatabaseClientFactory.DigestAuthContext(mlUsername, mlPassword));
        }
    }

//    @Bean
//    public QueryManager getQueryManager() {
//        return getDatabaseClient().newQueryManager();
//    }

//    @Bean
    public XMLDocumentManager getXMLDocumentManager(String db) {
        return getDatabaseClient(db).newXMLDocumentManager();
    }

//    @Bean
    public JSONDocumentManager getJSONDocumentManager(String db) {
        return getDatabaseClient(db).newJSONDocumentManager();
    }

//    @Bean
    public String getMarkLogicBaseURL(String db) {
        if (db.equals("books")) {
            return String.format("http://%s:%d", host, mlBooksPort);
        }
        else if (db.equals("customers")) {
            return String.format("http://%s:%d", host, mlCustomersPort);
        }
        else {
            return String.format("http://%s:%d", host, mlEmeraldPort);
        }
    }

    public String getHost() {
        return host;
    }

    public int getMlEmeraldPort() {
        return mlEmeraldPort;
    }

    public int getMlBooksPort() {
        return mlBooksPort;
    }

    public int getMlCustomersPort() {
        return mlCustomersPort;
    }

    public String getMlUsername() {
        return mlUsername;
    }

    public String getMlPassword() {
        return mlPassword;
    }
}
