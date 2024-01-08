package com.mluetech.springbootbookstoreinventory.service;


import com.mluetech.springbootbookstoreinventory.entity.MyBookList;
import com.mluetech.springbootbookstoreinventory.repository.MyBookListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyBookListService {
    @Autowired
    private MyBookListRepository myBookListRepository;
    public void saveMyBooks(MyBookList myBookList){
        myBookListRepository.save(myBookList);
    }

    public List<MyBookList> getAllMyBooks(){
        return myBookListRepository.findAll();
    }

    public void deleteById(long id){
        myBookListRepository.deleteById(id);
    }
}
