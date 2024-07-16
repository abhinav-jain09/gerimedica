package com.gerimedica.assignment.service;



import com.gerimedica.assignment.entity.Item;
import com.gerimedica.assignment.repository.ItemRepository;
import com.gerimedica.assignment.util.CSVParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    private ItemRepository repository;

    public List<Item> findAll() {
        return repository.findAll();
    }

    public Optional<Item> findByCode(String code) {
        return repository.findById(code);
    }

    public void saveAll(List<Item> items) {
        repository.saveAll(items);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public void uploadFile(MultipartFile file) throws Exception {
        List<Item> items = CSVParser.parseCSVFile(new InputStreamReader(file.getInputStream()));
        saveAll(items);
    }
}
