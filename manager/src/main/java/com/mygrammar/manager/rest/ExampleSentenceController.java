package com.mygrammar.manager.rest;

import com.mygrammar.manager.example.ExampleSentence;
import com.mygrammar.manager.example.ExampleSentenceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/examples")
public class ExampleSentenceController {

    public final ExampleSentenceService exampleSentenceService;

    public ExampleSentenceController(ExampleSentenceService exampleSentenceService) {
        this.exampleSentenceService = exampleSentenceService;
    }

    @GetMapping
    public List<ExampleSentence> getAll() {
        return exampleSentenceService.getAllExampleSentence();
    }
}
