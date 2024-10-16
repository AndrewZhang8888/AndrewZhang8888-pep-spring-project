package com.example.service;

import com.example.entity.*;
import com.example.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MessageService {
    MessageRepository msgRep;
    AccountRepository accRep;

    @Autowired
    public MessageService(MessageRepository msgRep, AccountRepository accRep){
        this.msgRep = msgRep;
        this.accRep = accRep;
    }

    //User Story 3
    public Message createMessage(Message msg){
        // Message retMessage = msgRep.save(msg);
        // return retMessage;
        // if (!msg.getMessageText().isEmpty() && msg.getMessageText().length()<=255 && accRep.findById(msg.getPostedBy()).isPresent()){
        Optional<Account> optAcc = accRep.findById(msg.getPostedBy());
        if (!msg.getMessageText().isEmpty() && msg.getMessageText().length()<=255 && optAcc.isPresent()){
            Message retMessage = msgRep.save(msg);
            return retMessage;
        } else{                                                                                                                                                                         
            return null;
        }
    }

    //User Story 4
    public List<Message> getAllMessages(){
        List<Message> msgList = new ArrayList<Message>();
        msgList = msgRep.findAll();
        return msgList;
    }

    //User Story 5 
    // public Message getMessageById(int id){
    public Message getMessageById(int id){
        Optional<Message> optionalMsg = msgRep.findById(id);
        if (optionalMsg.isPresent()){
            Message retMessage = optionalMsg.get();
            return retMessage;
        } else{
            return null;
        }        
    }
    
    //User Story 6 
    public int deleteMessageById(int id){
        int originalSize = msgRep.findAll().size();
        try {
            msgRep.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
        }
        int newSize = msgRep.findAll().size();    
        if(newSize!=originalSize){
            return 1;
        } else{
            return 0;
        }
    }

    //User Story 7 
    public Integer updateMessageById(int id, String msgText){
        Optional<Message> optionalMsg = msgRep.findById(id);
        if (optionalMsg.isPresent()){
            if (msgText.length()<=255 && !msgText.isEmpty()){
                Message retMessage = optionalMsg.get();
                retMessage.setMessageText(msgText);
                msgRep.save(retMessage);
                return 1;
            }
            return null;
        } else{
            return null;
        }    
    }

    //User Story 8 
    public List<Message> getMessagesByPostedId(int accId){
        List<Message> msgList = new ArrayList<Message>();
        msgList = msgRep.findByPostedBy(accId);
        return msgList;
    }
}
