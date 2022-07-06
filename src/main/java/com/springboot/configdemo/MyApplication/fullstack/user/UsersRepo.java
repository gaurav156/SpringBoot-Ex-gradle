package com.springboot.configdemo.MyApplication.fullstack.user;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marklogic.client.document.JSONDocumentManager;
import com.marklogic.client.io.JacksonHandle;
import com.springboot.configdemo.MyApplication.MarkLogicConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UsersRepo {

    @Autowired
    public MarkLogicConfig markLogicConfig;

    private final String db = "user";

    public List<User> getUserList() throws IOException {
        List<User> userList = new ArrayList<>();
        JSONDocumentManager docMgr = markLogicConfig.getJSONDocumentManager(db);
        int i = 1;
        while(docMgr.read("/db/user/"+i+".json").hasContent()) {
            ObjectMapper mapper = new ObjectMapper();
            String docId = "/db/user/"+i+".json";
            userList.add(mapper.readValue(markLogicConfig.getJsonParser(db, docId), new TypeReference<User>() {
            }));
            i++;
        }

        return Collections.unmodifiableList(userList);
    }

    public List<User> userFilter(String id) throws IOException {

        return getUserList().stream().filter(p -> p.getId().equals(id)).collect(Collectors.toList());
    }

    public User addUser(User user) throws IOException {
        user.setId(generateID());
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.valueToTree(user);

        var manager = markLogicConfig.getDocumentManager(db);

        JacksonHandle handle = new JacksonHandle(node);
        manager.write("/db/user/"+user.getId()+".json", handle);

        return user;
    }

    public User authenticateUser(String email , String password) throws IOException {
        User result = null;
            for (User i : getUserList()) {
                if (i.getEmail().equals(email) & i.getPassword().equals(password)) {
                    result = i;
                    break;
                }
            }

        return result;
    }

    public String generateID() throws IOException {
        String id = String.valueOf(getUserList().size()+1);
        return id;
    }
}
