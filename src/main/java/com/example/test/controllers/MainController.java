package com.example.test.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

@Controller
public class MainController {
    @GetMapping("/")
    public String Home() {
        return "home";
    }

    @GetMapping("/post")
    public String PostForm() {
        return "post";
    }

    @PostMapping("/post")
    public String PostSubmit(@RequestParam("value") String val, Model model) throws Exception {
        String pyth_file = System.getProperty("user.dir") + "/src/main/java/com/example/test/Property/eval.py";

        String command = String.format("python %s %s", pyth_file, val.replace(" ", ""));

        System.out.println("\nExecuting python script file now ......");
        Process pcs = Runtime.getRuntime().exec(command);
        pcs.waitFor();

        BufferedInputStream in = new BufferedInputStream(pcs.getInputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        String lineStr = null;
        String result = null;

        while ((lineStr = br.readLine()) != null) {
            result = lineStr;
        }

        br.close();
        in.close();

        if (result == null)
            model.addAttribute("answer", "Некорректное выражение");
        else
            model.addAttribute("answer", "Ваш ответ: " + result);

        return "post";
    }

    @GetMapping("/get")
    public String GetSubmit(@RequestParam(value = "value", required = false) String val, Model model) throws Exception {
        if (val == null) {
            return "get";
        }

        String pyth_file = System.getProperty("user.dir") + "/src/main/java/com/example/test/Property/eval.py";

        String command = String.format("python %s %s", pyth_file, val.replace(" ", ""));

        System.out.println("\nExecuting python script file now ......");
        Process pcs = Runtime.getRuntime().exec(command);
        pcs.waitFor();

        BufferedInputStream in = new BufferedInputStream(pcs.getInputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        String lineStr = null;
        String result = null;

        while ((lineStr = br.readLine()) != null) {
            result = lineStr;
        }

        br.close();
        in.close();

        if (result == null)
            model.addAttribute("answer", "Некорректное выражение");
        else
            model.addAttribute("answer", "Ваш ответ: " + result);

        return "get";
    }
}
