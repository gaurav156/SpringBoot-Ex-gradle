package com.springboot.configdemo.MyApplication.fullstack.book;

import com.fasterxml.jackson.databind.JsonNode;
import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import com.marklogic.client.document.JSONDocumentManager;
import com.marklogic.client.io.JacksonHandle;
import org.springframework.stereotype.Repository;

@Repository
public class BooksRepo {

    private final JsonNode bookList;

    public BooksRepo(){
        DatabaseClient client = DatabaseClientFactory.newClient("localhost", 8060, new DatabaseClientFactory.DigestAuthContext("admin","admin"));

        JSONDocumentManager docMgr = client.newJSONDocumentManager();
        JacksonHandle handle = new JacksonHandle();

        docMgr.read("/db/book.json", handle);
        this.bookList = handle.get();
    }

    public JsonNode getList(){
        return bookList;
    }

}
