package com.legomin.controller;

import com.legomin.model.Token;
import com.legomin.model.User;
import com.legomin.model.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Виталий on 10.02.2016.
 */
@Controller
public class MainController {

    private int _tokenLifeInSeconds = 5 * 60; // 5 minutes
    private Map<User,Token> _activeUsers = new HashMap<>();

    @Autowired
    private Dao _Dao;

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public WSMessage message(WSMessage message) throws Exception {

        User user  = _Dao.getByEmailAndPassword(message.getData().get("email"), message.getData().get("password"));

        WSMessage wsMessage = null;
        if (user == null)
        {
            wsMessage = generateErrorMessage(message);
        }else
        {
            wsMessage = generateSuccessMessage(message, user);
        }

        return wsMessage;
    }

    private WSMessage generateSuccessMessage(WSMessage message, User user) {
        WSMessage wsMessage = new WSMessage();
        wsMessage.setType(TypeMessage.CUSTOMER_API_TOKEN);
        wsMessage.setSeqence_id(message.getSeqence_id());

        Token token = new Token();
        Date expDate = new Date();
        expDate.setTime(expDate.getTime() +  + _tokenLifeInSeconds * 1000);
        token.setExpDate(expDate);
        token.setUuid(_Dao.getNewUUID());

        _Dao.saveToken(token); // to  token's history
        _activeUsers.put(user, token); // to active tokens

        Map data =  wsMessage.getData();
        data.put("api_token", token.getUuid());
        data.put("api_token_expiration_date", token.getExpDate().toString());

        return wsMessage;
    }

    private WSMessage generateErrorMessage(WSMessage message) {
        WSMessage wsMessage = new WSMessage();
        wsMessage.setType(TypeMessage.CUSTOMER_ERROR);
        wsMessage.setSeqence_id(message.getSeqence_id());

        Map data =  wsMessage.getData();
        data.put("error_description","Customer not found");
        data.put("error_code","customer.notFound");

        return wsMessage;
    }

}
