package com.springboot.configdemo.MyApplication.ui;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import com.marklogic.client.document.JSONDocumentManager;
import com.marklogic.client.io.JacksonHandle;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class TestMarkL {

    private JsonNode jsonNode;

    public void getTest(){
//        DatabaseClient client = DatabaseClientFactory.newClient("localhost", 8060, new DatabaseClientFactory.BasicAuthContext("admin","admin"));
        DatabaseClient client = DatabaseClientFactory.newClient("localhost", 8060, new DatabaseClientFactory.DigestAuthContext("admin","admin"));

//        DatabaseClient client =
//                DatabaseClientFactory.newClient("localhost", 8060, "admin", "admin",
//                        DatabaseClientFactory.Authentication.DIGEST);
        JSONDocumentManager docMgr = client.newJSONDocumentManager();
        JacksonHandle handle = new JacksonHandle();
        docMgr.read("/db/book.json", handle);
        JsonNode doc = handle.get();
//        System.out.println(doc.get("bookTitle").asText());

        System.out.println();
        doc.forEach(p -> {
            if(p.get("bookID").asText().equals("2")){
                System.out.println(p);
            }
        });

//        JsonNode locatedNode = doc.path("bookID");
//        System.out.println(locatedNode);


        client.release();
    }
}
