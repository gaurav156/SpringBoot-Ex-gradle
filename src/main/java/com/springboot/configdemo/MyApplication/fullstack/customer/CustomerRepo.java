package com.springboot.configdemo.MyApplication.fullstack.customer;

import com.fasterxml.jackson.databind.JsonNode;
import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import com.marklogic.client.document.JSONDocumentManager;
import com.marklogic.client.io.JacksonHandle;
import com.springboot.configdemo.MyApplication.MarkLogicConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepo {

    @Autowired
    public MarkLogicConfig markLogicConfig;

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
        JSONDocumentManager docMgr = markLogicConfig.getJSONDocumentManager("customers");
        JacksonHandle handle = new JacksonHandle();
        docMgr.read("/db/customer.json", handle);

        return handle.get();
    }
}
