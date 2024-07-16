package com.gerimedica.assignment.util;

import com.gerimedica.assignment.entity.Item;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CSVParser{

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static List<Item> parseCSVFile(InputStreamReader inputStreamReader) throws Exception {
        List<Item> items = new ArrayList<>();
        BufferedReader br = new BufferedReader(inputStreamReader);
        String line;
        br.readLine(); // Skip the header
        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            Item item = new Item();
            item.setSource(data[0].trim().replace("\"", ""));
            item.setCodeListCode(data[1].trim().replace("\"", ""));
            item.setCode(data[2].trim().replace("\"", ""));
            item.setDisplayValue(data[3].trim().replace("\"", ""));
            item.setLongDescription(data[4].trim().replace("\"", ""));
            item.setFromDate(parseDate(data[5].trim().replace("\"", ""))); // Use parseDate method
            item.setToDate(parseDate(data[6].trim().replace("\"", "")));   // Use parseDate method
            item.setSortingPriority(parseInteger(data[7].trim().replace("\"", ""))); // Use parseInteger method
            items.add(item);
        }
        return items;
    }

    // New method to handle date parsing
    private static LocalDate parseDate(String date) {
        if (date.isEmpty()) {
            return null;
        }
        return LocalDate.parse(date, DATE_FORMATTER);
    }

    // New method to handle integer parsing
    private static Integer parseInteger(String number) {
        if (number.isEmpty()) {
            return null;
        }
        return Integer.parseInt(number);
    }}
