package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class User {
    private String name;
    private Integer age;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public void toObjectList() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        File file = new File("file.txt");
        FileInputStream fis = new FileInputStream(file);
        List<User> list = new ArrayList<>();
        Scanner scanner = new Scanner(fis);
        String line = null;
        while (scanner.hasNext()) {
            line = scanner.nextLine();
            String[] parts = line.split(" ");
            if (parts.length == 2) {
                String name = parts[0];
                int age = Integer.parseInt(parts[1]);
                list.add(new User(name, age));
            }
        }
        FileOutputStream fos = new FileOutputStream("user.json");
        int i = list.size()-1;
        fos.write('[');
        for(User userOne:list){
            fos.write(gson.toJson(userOne).getBytes());
            if (i>0) {
                fos.write(',');
                i--;
            }
            fos.write('\n');
        }
        fos.write(']');
    }
}