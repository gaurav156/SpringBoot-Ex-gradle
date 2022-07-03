package com.springboot.configdemo.MyApplication.fullstack.emerald;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marklogic.client.document.DocumentManager;
import com.marklogic.client.document.JSONDocumentManager;
import com.marklogic.client.io.JacksonHandle;
import com.springboot.configdemo.MyApplication.MarkLogicConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

@Repository
public class EmeraldRepo {

    @Autowired
    public MarkLogicConfig markLogicConfig;
    private List<EmeraldBooks> bookList = new ArrayList<>();

    private LinkedHashSet<EmeraldBooks> list = new LinkedHashSet<>();
    private final String db = "emerald";

    @PostConstruct
    public void dbConnect() throws IOException {
        JSONDocumentManager docMgr = markLogicConfig.getJSONDocumentManager(db);

        int i = 1;
        while(docMgr.read("/db/book/test/"+i+".json").hasContent()) {
        ObjectMapper mapper = new ObjectMapper();
            String docId = "/db/book/test/"+i+".json";

            this.bookList.add(mapper.readValue(markLogicConfig.getJsonParser(db, docId), new TypeReference<EmeraldBooks>() {
            }));

            i++;
        }

    }

    @PostConstruct
    public void testConnect() throws IOException {
        JSONDocumentManager docMgr = markLogicConfig.getJSONDocumentManager(db);

        int i = 1;
        while(docMgr.read("/db/book/test/"+i+".json").hasContent()) {
            ObjectMapper mapper = new ObjectMapper();
            String docId = "/db/book/test/"+i+".json";

            this.list.add(mapper.readValue(markLogicConfig.getJsonParser(db, docId), new TypeReference<EmeraldBooks>() {
            }));
            i++;
        }
    }

    public LinkedHashSet<EmeraldBooks> getList() throws IOException {
        testConnect();
        return list;
    }

    public List<EmeraldBooks> getBookList(){
        return Collections.unmodifiableList(bookList);
    }

//    public void getTest() throws IOException {
////        DatabaseClient client = DatabaseClientFactory.newClient("localhost", 8383, new DatabaseClientFactory.DigestAuthContext("admin","admin"));
////        JSONDocumentManager docMgr = client.newJSONDocumentManager();
//
//        JSONDocumentManager docMgr = markLogicConfig.getJSONDocumentManager(db);
//
//        int i =1;
//        while(docMgr.read("/db/book/test/"+i+".json").hasContent()) {
//            ObjectMapper mapper = new ObjectMapper();
//            String docId = "/db/book/test/"+i+".json";
////            JsonFactory factory = new JsonFactory();
////            this.bookList = mapper.readValue(markLogicConfig.getJsonParser(db , docId), new TypeReference<>() {
////            });
//
//            this.bookList.add(mapper.readValue(markLogicConfig.getJsonParser(db, docId), new TypeReference<EmeraldBooks>() {
//            }));
//
//
////            JacksonHandle handle = new JacksonHandle();
////            docMgr.read("/db/book/test/"+i+".json", handle);
////            JsonNode doc = handle.get();
////            System.out.println("doc "+i+" : "+doc);
//
//
//
////            docMgr.read("/db/book/test/"+i+".json", handle);
////
////            JsonParser jp = factory.createParser(String.valueOf(handle));
////            bookList = mapper.readValue(jp, new TypeReference<List<Book>>() {
////            });
////            bookList = mapper.readValue(markLogicConfig.getJsonParser("books" , "/db/book/"+i+".json"), new TypeReference<>() {
////            });
////            docMgr.read("/db/book.json", handle);
////            JsonNode doc = handle.get();
//            i++;
//
//        }
//
//        System.out.println(bookList);
//    }

//    public void putTest(){
//
////        DatabaseClient client = DatabaseClientFactory.newClient("localhost", 8383, new DatabaseClientFactory.DigestAuthContext("admin","admin"));
////        DocumentManager manager = client.newDocumentManager();
//
//        DocumentManager manager = markLogicConfig.getDocumentManager(db);
//
//
//        JsonObject obj = new JsonObject();
//        obj.addProperty("bookID", "5");
//        obj.addProperty("bookTitle", "Rushikesh");
//        obj.addProperty("issnNo", "122");
//        obj.addProperty("customerID", "8");
//
////        JsonObject obj2 = new JsonObject();
////        obj2.addProperty("bookID", "5");
////        obj2.addProperty("bookTitle", "Ganesh");
////        obj2.addProperty("issnNo", "12245");
////        obj2.addProperty("customerID", "2");
//
////        JsonObject doc = new JsonObject();
////
////        doc.add("books", obj);
//
//        GSONHandle gsonHandle = new GSONHandle(obj);
//
//        manager.write("/db/book/test/5.json", gsonHandle);
//    }
//
    public void insertBook(EmeraldBooks books) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.valueToTree(books);

        DocumentManager manager = markLogicConfig.getDocumentManager(db);

//        JsonObject obj = new JsonObject();
//        obj.addProperty("bookID", books.getBookID());
//        obj.addProperty("bookTitle", books.getBookTitle());
//        obj.addProperty("issnNo", books.getIssnNo());

//        GSONHandle gsonHandle = new GSONHandle(obj);

//        manager.write("/db/book/test/"+bookID+".json", gsonHandle);


        JacksonHandle handle = new JacksonHandle(node);
//        docMgr.read("/db/book.json", handle);
//        manager.write("/db/book/test/"+bookID+".json", handle);
        manager.write("/db/book/test/"+books.getBookID()+".json", handle);

    }

    public void deleteTest(String id){
        var manager = markLogicConfig.getDocumentManager(db);
        manager.delete("/db/book/test/"+id+".json");
    }
}
