package com.springboot.configdemo.MyApplication.fullstack.book;

import com.fasterxml.jackson.databind.JsonNode;
import com.marklogic.client.document.JSONDocumentManager;
import com.marklogic.client.io.JacksonHandle;
import com.springboot.configdemo.MyApplication.MarkLogicConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BooksRepo {

    @Autowired
    public MarkLogicConfig markLogicConfig;

//    public BooksRepo(){
////        MarkLogicConfig markLogicConfig = new MarkLogicConfig();
//
//        DatabaseClient client = DatabaseClientFactory.newClient(markLogicConfig.getHost(), markLogicConfig.getRestPort(), new DatabaseClientFactory.DigestAuthContext(markLogicConfig.getMlUsername(), markLogicConfig.getMlPassword()));
////        DatabaseClient client = DatabaseClientFactory.newClient(this.host, this.port, new DatabaseClientFactory.DigestAuthContext(this.username,this.password));
//
//        JSONDocumentManager docMgr = client.newJSONDocumentManager();
//        JacksonHandle handle = new JacksonHandle();
//
//        docMgr.read("/db/book.json", handle);
//        this.bookList = handle.get();
//    }

    public JsonNode getList(){
        JSONDocumentManager docMgr = markLogicConfig.getJSONDocumentManager("books");
        JacksonHandle handle = new JacksonHandle();
        docMgr.read("/db/book.json", handle);

        return handle.get();
    }

}
