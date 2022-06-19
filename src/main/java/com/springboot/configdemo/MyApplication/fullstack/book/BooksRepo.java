package com.springboot.configdemo.MyApplication.fullstack.book;

import com.fasterxml.jackson.databind.JsonNode;
import com.marklogic.client.document.JSONDocumentManager;
import com.marklogic.client.io.JacksonHandle;
import com.springboot.configdemo.MyApplication.BookController;
import com.springboot.configdemo.MyApplication.MarkLogicConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BooksRepo {

    @Autowired
    public MarkLogicConfig markLogicConfig;

    private String db = "books";

    Logger logger = LoggerFactory.getLogger(getClass());

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
        JSONDocumentManager docMgr = markLogicConfig.getJSONDocumentManager(db);
        JacksonHandle handle = new JacksonHandle();
        docMgr.read("/db/book.json", handle);

        logger.debug(String.format("Book Repository - getList method call from %s", getMarkLogicBaseURL()));

        return handle.get();
    }

    public String getMarkLogicBaseURL(){
        logger.debug("Book Repository - getMarkLogicBaseURL method call");
        return markLogicConfig.getMarkLogicBaseURL(db);
    }

}
