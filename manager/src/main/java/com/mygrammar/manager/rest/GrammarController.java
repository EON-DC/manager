package com.mygrammar.manager.rest;

import com.mygrammar.manager.grammar.Grammar;
import com.mygrammar.manager.grammar.GrammarDefaultService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/grammars")
public class GrammarController {

    private final GrammarDefaultService grammarDefaultService;

    public GrammarController(GrammarDefaultService grammarDefaultService) {
        this.grammarDefaultService = grammarDefaultService;
    }

    @GetMapping
    public List<Grammar> getAll() {
        return grammarDefaultService.getAllGrammar();
    }
}
