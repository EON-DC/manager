package com.mygrammar.manager.rest;

import com.mygrammar.manager.grammar.Grammar;
import com.mygrammar.manager.grammar.GrammarDefaultService;
import com.mygrammar.manager.grammar.GrammarJpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/grammars")
public class GrammarRestController {

    private final GrammarDefaultService grammarDefaultService;
    private final GrammarJpaRepository grammarJpaRepository;

    public GrammarRestController(GrammarDefaultService grammarDefaultService, GrammarJpaRepository grammarJpaRepository) {
        this.grammarDefaultService = grammarDefaultService;
        this.grammarJpaRepository = grammarJpaRepository;
    }

    @GetMapping
    public List<Grammar> getAll() {
        return grammarDefaultService.getAllGrammar();
    }
}
