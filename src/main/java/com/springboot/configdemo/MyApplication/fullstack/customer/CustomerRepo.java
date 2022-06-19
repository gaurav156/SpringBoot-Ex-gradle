package com.springboot.configdemo.MyApplication.fullstack.customer;

import com.fasterxml.jackson.databind.JsonNode;
import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import com.marklogic.client.document.JSONDocumentManager;
import com.marklogic.client.io.JacksonHandle;
import com.springboot.configdemo.MyApplication.MarkLogicConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepo {

    @Autowired
    public MarkLogicConfig markLogicConfig;
    private String db = "customers";

    Logger logger = LoggerFactory.getLogger(getClass());



//    private final JsonNode customerList;

//    public CustomerRepo(){
//        DatabaseClient client = DatabaseClientFactory.newClient("localhost", 8383, new DatabaseClientFactory.DigestAuthContext("admin","admin"));
//
//        JSONDocumentManager docMgr = client.newJSONDocumentManager();
//        JacksonHandle handle = new JacksonHandle();
//
//        docMgr.read("/db/customer.json", handle);
//        this.customerList = handle.get();
//    }

    public JsonNode getList(){
//        MarkLogicConfig markLogicConfig2 = new MarkLogicConfig();
        JSONDocumentManager docMgr = markLogicConfig.getJSONDocumentManager(db);
        JacksonHandle handle = new JacksonHandle();
        docMgr.read("/db/customer.json", handle);

        logger.debug(String.format("Customers Repository - getList method call from %s", getMarkLogicBaseURL()));

        return handle.get();
    }

    public String getMarkLogicBaseURL(){
        logger.debug("Customers Repository - getMarkLogicBaseURL method call");
        return markLogicConfig.getMarkLogicBaseURL(db);
    }
}
