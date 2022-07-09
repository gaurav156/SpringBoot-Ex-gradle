package com.springboot.configdemo.MyApplication.fullstack.user;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marklogic.client.document.JSONDocumentManager;
import com.marklogic.client.io.JacksonHandle;
import com.springboot.configdemo.MyApplication.MarkLogicConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class UsersRepo {

    @Autowired
    public MarkLogicConfig markLogicConfig;

    private final String db = "user";

    Logger logger = LoggerFactory.getLogger(getClass());

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

        logger.debug("User Repository - getUserList method call");
        return Collections.unmodifiableList(userList);
    }

    public User userFilter(String id) throws IOException {
        logger.debug(String.format("User Repository - userFilter method call for userID : %s", id));

        User user = new User();
        for(User i : getUserList()){
            if(i.getId().equals(id)){
                user = i;
            }
        }
//        return getUserList().stream().filter(p -> p.getId().equals(id)).collect(Collectors.toList());
        return user;
    }

    public User addUser(User user) throws IOException {
        user.setId(generateID());
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.valueToTree(user);

        var manager = markLogicConfig.getDocumentManager(db);

        JacksonHandle handle = new JacksonHandle(node);
        manager.write("/db/user/"+user.getId()+".json", handle);

        logger.debug(String.format("User Repository - addUser method call for userID : %s", user.getId()));
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

        logger.debug("User Repository - authenticateUser method call");
        return result;
    }

    public String generateID() throws IOException {
        String id = String.valueOf(getUserList().size()+1);

        logger.debug("User Repository - generateID method call");
        return id;
    }
}
