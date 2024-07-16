package com.gerimedica.assignment.controller;


import com.gerimedica.assignment.entity.Item;
import com.gerimedica.assignment.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/items")
public class ItemController {
    @Autowired
    private ItemService service;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");


    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            service.uploadFile(file);
            return "File uploaded successfully!";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @GetMapping
    public List<Item> getAllItems() {
        return service.findAll();
    }

    @GetMapping("/{code}")
    public Item getItemByCode(@PathVariable String code) {
        Optional<Item> item = service.findByCode(code);
        return item.orElse(null);
    }

    @DeleteMapping
    public String deleteAllItems() {
        service.deleteAll();
        return "All items deleted successfully!";
    }
}
