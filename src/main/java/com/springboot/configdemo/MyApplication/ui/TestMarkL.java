package com.springboot.configdemo.MyApplication.ui;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import com.marklogic.client.document.DocumentManager;
import com.marklogic.client.document.JSONDocumentManager;
import com.marklogic.client.extra.gson.GSONHandle;
import com.marklogic.client.io.FileHandle;
import com.marklogic.client.io.JacksonHandle;
import com.marklogic.client.io.marker.*;
import com.springboot.configdemo.MyApplication.MarkLogicConfig;
import com.springboot.configdemo.MyApplication.fullstack.book.Book;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TestMarkL {

//    private JsonNode jsonNode;

    @Autowired
    private MarkLogicConfig markLogicConfig;

    private List<Book> bookList = new ArrayList<>();

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
//        this.jsonNode = doc;
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

    public void putTest() throws IOException {

        DatabaseClient client = DatabaseClientFactory.newClient("localhost", 8383, new DatabaseClientFactory.DigestAuthContext("admin","admin"));
//        JSONDocumentManager docMgr = client.newJSONDocumentManager();
//        JacksonHandle handle = new JacksonHandle();
//        JsonNode j = new JsonParser();
//        handle.set();
//        JSONWriteHandle jsonWriteHandle = new FileHandle(new File("C:/Users/gaurav.shukla/Desktop/db-test-books.json"));
//        JSONWriteHandle jsonWriteHandle =  new FileHandle(new File("/db/book.json", "C:/Users/gaurav.shukla/Desktop/db-test-books.json"));
//        DocumentPatchHandle documentPatchHandle = new FileHandle(new File("C:/Users/gaurav.shukla/Desktop/db-test-books.json"));
        DocumentManager manager = client.newDocumentManager();
        JsonObject obj = new JsonObject();
        obj.addProperty("bookID", "7");
        obj.addProperty("bookTitle", "rUSHIKESH");
        obj.addProperty("issnNo", "122");
        obj.addProperty("customerID", "8");

        JsonObject obj2 = new JsonObject();
        obj2.addProperty("bookID", "5");
        obj2.addProperty("bookTitle", "Ganesh");
        obj2.addProperty("issnNo", "12245");
        obj2.addProperty("customerID", "2");

//        JsonObject obj4 = new JsonObject();
//        obj4.addProperty("id", 1);
//        obj4.addProperty("bookID", "12");
//        obj4.addProperty("bookTitle", "SaM");
//        obj4.addProperty("issnNo", "17745");
//        obj4.addProperty("customerID", "8");


        JsonObject doc = new JsonObject();
//        doc.add("books", obj);
//        doc.add("books2", obj2);
        doc.add("books", obj);

//        JSONObject document = new JSONObject();
//        document.put("books", obj);

        GSONHandle gsonHandle = new GSONHandle(doc);

//        manager.setMetadataCategories(DocumentManager.Metadata.valueOf("ALL"));
//        ContentHandle contentHandle = new GSONHandle(doc);
        manager.write("/db/book/test/1.json", gsonHandle);
//        manager.writeAs("/db/book.json", gsonHandle);


//        manager.patchAs("/db/book.json", contentHandle);
//        JacksonHandle h = new Object;
//        docMgr.patch("/db/book.json", h);
//        [
//        {
//            "bookID": "4",
//                "bookTitle": "Rushikesh",
//                "issnNo": "33333",
//                "customerID": ["1"]
//        }
//      ]


//        QueryManager doc = client.newQueryManager();
//        StructureWriteHandle structureWriteHandle = new FileHandle(new File("C:/Users/gaurav.shukla/Desktop/query.txt"));
//        doc.newRawCombinedQueryDefinition(structureWriteHandle);

//        String command = "$ curl --anyauth --user admin:admin -X PUT -d@'C:/Users/gaurav.shukla/Desktop/db-test-books.json' -i \\" +
//                "    -H \"Content-type: application/json\" \\" +
//                "    http://localhost:8060/v1/documents?uri=/db/book.json";
//        Process process = Runtime.getRuntime().exec(command);

//        String command =
//                "curl -X GET https://postman-echo.com/get?foo1=bar1&foo2=bar2";
//        ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
//
//
//        processBuilder.directory(new File("C:/Users/gaurav.shukla/Desktop/db-test-books.json"));
//        Process process = processBuilder.start();

        client.release();

//        ServerEvaluationCall eval = client.newServerEval();
//        String query = "xdmp:feature-metric-increment(xdmp:feature-metric-register(\"datahub.core.install.count\"))";
//        eval.xquery(query).eval().close();

    }

    @PostConstruct
    public void getTest2() throws IOException {
//        List<Book> bookList = new ArrayList<>();
//        DatabaseClient client = DatabaseClientFactory.newClient("localhost", 8060, new DatabaseClientFactory.DigestAuthContext("admin","admin"));
//        JSONDocumentManager docMgr = client.newJSONDocumentManager();
//        System.out.println(docMgr.read("/db/book/test/2.json").hasContent());

//        JacksonHandle handle = new JacksonHandle();
//        docMgr.read("/db/book.json", handle);
//        JsonNode doc = handle.get();
//        System.out.println(doc);


//        MarkLogicConfig markLogicConfig = new MarkLogicConfig();

//        int i =1;
//        while(docMgr.read("/db/book/"+i+".json").hasContent()) {
//            ObjectMapper mapper = new ObjectMapper();
//////            String docId = "/db/book.json";
////            JsonFactory factory = new JsonFactory();
////            JacksonHandle handle = new JacksonHandle();
////            docMgr.read("/db/book/test/"+i+".json", handle);
////
////            JsonParser jp = factory.createParser(String.valueOf(handle));
////            bookList = mapper.readValue(jp, new TypeReference<List<Book>>() {
////            });
//            bookList = mapper.readValue(markLogicConfig.getJsonParser("books" , "/db/book/"+i+".json"), new TypeReference<>() {
//            });
////            docMgr.read("/db/book.json", handle);
////            JsonNode doc = handle.get();
//            i++;
//        }
//
//        System.out.println(bookList);


        ObjectMapper mapper = new ObjectMapper();
        String docId = "/db/book.json";
        String db = "books";
        bookList = mapper.readValue(markLogicConfig.getJsonParser(db , docId), new TypeReference<>() {
        });

        System.out.println(bookList);
    }
}
