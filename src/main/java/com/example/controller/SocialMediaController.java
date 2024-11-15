package com.example.controller;

import com.example.entity.*;
import com.example.service.*;
import com.example.exception.*;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

@RestController
public class SocialMediaController {
    MessageService msgServ;
    AccountService accServ;

    @Autowired
    public SocialMediaController(MessageService msgServ, AccountService accServ){
        this.msgServ = msgServ;
        this.accServ = accServ;
    }

    //User Story 1 POST;localhost_8080/register
    //Request Body
    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<Account> registerAccount(@RequestBody Account acct){
        try {
            Account registeredAccount = accServ.registerUser(acct.getUsername(), acct.getPassword());
            return ResponseEntity.status(200).body(registeredAccount);
        } catch (DuplicateUsernameException e) {
            return ResponseEntity.status(409).build();
        } catch (InvalidRegistrationDataException e) {
            return ResponseEntity.status(400).build();
        }
    }

    //User Story 2 POST;localhost:8080/login
    //Request Body 
    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<Account> loginAccount(@RequestBody Account acct){
        Account acc = accServ.findAccountByUsernameAndPass(acct.getUsername(), acct.getPassword());
        if(acc!=null){
            return ResponseEntity.status(200).body(acc);
        } else{
            return ResponseEntity.status(401).build();      
        }
    } 
    
    //User Story 3 POST;localhost:8080/messages 
    //Request Body
    @PostMapping("/messages")
    @ResponseBody
    public ResponseEntity<Message> createMessage(@RequestBody Message msg){
        Message retMsg = msgServ.createMessage(msg);
        if (retMsg!=null){
            return ResponseEntity.status(200).body(retMsg);
        } else{
            return ResponseEntity.status(400).build();
        }
    }

    //User Story 4 GET;localhost:8080/messages
    @GetMapping("/messages")
    @ResponseBody
    public ResponseEntity<List<Message>> getAllMessages(){
        List<Message> msg_list = new ArrayList<Message>();
        msg_list = msgServ.getAllMessages();
        return ResponseEntity.status(200).body(msg_list);
    }

    //User Story 5 GET;localhost:8080/messages/{messageId}
    //PathVariable
    @GetMapping("/messages/{messageId}")
    @ResponseBody
    public ResponseEntity<Message> getMessageById(@PathVariable int messageId){
        Message msg = msgServ.getMessageById(messageId);
        if(msg!=null){
            return ResponseEntity.status(200).body(msg);
        } else{
            return ResponseEntity.status(200).build();        
        }
    }

    //User Story 6 DELETE;localhost:8080/messages/{messageId}
    //PathVariable
    @DeleteMapping("/messages/{messageId}")
    @ResponseBody
    public ResponseEntity<Integer> deleteMessageById(@PathVariable int messageId){
        int delRows = msgServ.deleteMessageById(messageId);
        if (delRows==1){
            return ResponseEntity.status(200).body(1);
        } else{
            return ResponseEntity.status(200).build();
        }
    }

    //User Story 7 PATCH;localhost:8080/messages/{messageId}
    //PathVariable, RequestBody
    @PatchMapping("/messages/{messageId}")
    @ResponseBody 
    public ResponseEntity<Integer> updateMessageById(@PathVariable int messageId, @RequestBody Map<String, String> msg_text){
        Integer updatedRows = msgServ.updateMessageById(messageId, msg_text.get("messageText"));
        if(updatedRows==null){
            return ResponseEntity.status(400).build();
        } else{
            return ResponseEntity.status(200).body(updatedRows);           
        }
    }

    //User Story 8 GET;localhost:8080/accounts/{accountId}/messages
    //PathVariable
    @GetMapping("/accounts/{accountId}/messages")
    @ResponseBody
    public ResponseEntity<List<Message>> getMessagesByAccount(@PathVariable int accountId){
        List<Message> msg_list = new ArrayList<Message>();
        msg_list = msgServ.getMessagesByPostedId(accountId);
        return ResponseEntity.status(200).body(msg_list);
    }
}
