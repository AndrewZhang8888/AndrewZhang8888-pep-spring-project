package com.example.controller;

import com.example.entity.*;
import java.util.*;
import org.springframework.web.bind.annotation.*;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    //User Story 1 POST;localhost_8080/register
    //Request Body
    @PostMapping("/register")
    @ResponseBody
    public Account registerAccount(@RequestBody Account acct){
        Account reg_acct = new Account();
        return reg_acct;
    }

    //User Story 2 POST;localhost:8080/login
    //Request Body 
    @PostMapping("/login")
    @ResponseBody
    public Account loginAccount(@RequestBody Account acct){
        Account login_acct = new Account();
        return login_acct;
    } 
    
    //User Story 3 POST;localhost:8080/messages 
    //Request Body
    @PostMapping("/messages")
    @ResponseBody
    public Message createMessage(@RequestBody Message msg){
        Message created_msg = new Message();
        return created_msg;
    }

    //User Story 4 GET;localhost:8080/messages
    @PostMapping("/messages")
    @ResponseBody
    public List<Message> getAllMessages(){
        List<Message> msg_list = new ArrayList<Message>();
        return msg_list;
    }

    //User Story 5 GET;localhost:8080/messages/{messageId}
    //PathVariable
    @PostMapping("/messages/{messageId}")
    @ResponseBody
    public Message getMessageById(@PathVariable int messageId){
        Message msg = new Message();
        return msg;
    }

    //User Story 6 DELETE;localhost:8080/messages/{messageId}
    //PathVariable
    @DeleteMapping("/messages/{messageId}")
    @ResponseBody
    public int deleteMessageById(@PathVariable int messageId){
        return 1;
    }

    //User Story 7 PATCH;localhost:8080/messages/{messageId}
    //PathVariable, RequestBody
    @PatchMapping("/messages/{messageId}")
    @ResponseBody 
    public int updateMessageById(@PathVariable int messageId, @RequestBody Map<String, String> msg_text){
        return 1;
    }

    //User Story 8 GET;localhost:8080/accounts/{accountId}/messages
    //PathVariable
    @GetMapping("/accounts/{accountId}/messages")
    @ResponseBody
    public List<Message> getMessagesByAccount(@PathVariable int accountId){
        List<Message> msg_list = new ArrayList<Message>();
        return msg_list;
    }
}
