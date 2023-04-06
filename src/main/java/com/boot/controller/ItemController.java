package com.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.boot.model.Item;
import com.boot.repo.ItemRepository;

import java.util.Map;

@RestController
public class ItemController {
    @Autowired
    ItemRepository itemRepo;

    @GetMapping("/getAllItems")
    @ResponseBody
    public ResponseEntity<Map<Integer, Item>> getAllItems(){
        Map<Integer, Item> items =  itemRepo.getAllItems();
        return new ResponseEntity<Map<Integer, Item>>(items, HttpStatus.OK);
    }

    @GetMapping("/item/{itemId}")
    @ResponseBody
    public ResponseEntity<Item> getItem(@PathVariable int itemId){
        Item item = itemRepo.getItem(itemId);
        return new ResponseEntity<Item>(item, HttpStatus.OK);
    }

    @PostMapping(value = "/addItem", consumes = {"application/json"}, produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<Item> addItem(@RequestBody Item item){
        itemRepo.addItem(item);
        return new ResponseEntity<Item>(HttpStatus.CREATED);
    }

    @PutMapping("/updateItem")
    @ResponseBody
    public ResponseEntity<Item> updateItem(@RequestBody Item item){
        if(item != null){
            itemRepo.updateItem(item);
        }
        return new ResponseEntity<Item>(item, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteItem(@PathVariable int id){
        itemRepo.deleteItem(id);
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }
}
