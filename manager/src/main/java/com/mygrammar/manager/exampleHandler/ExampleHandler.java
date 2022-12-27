package com.mygrammar.manager.exampleHandler;

import com.mygrammar.manager.example.ExampleSentence;
import com.mygrammar.manager.grammar.Grammar;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@SequenceGenerator(
        name = "EXAM_GRAMMAR_HANDLER_SEQUENCE_GENERATOR",
        sequenceName = "HANDLER_SEQUENCE",
        initialValue = 1,
        allocationSize = 110
)
public class ExampleHandler {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXAM_GRAMMAR_HANDLER_SEQUENCE_GENERATOR")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grammar_id")
    private Grammar grammar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "example_id")
    private ExampleSentence exampleSentence;

    protected ExampleHandler() {
    }

    @Builder
    public ExampleHandler(Grammar grammar, ExampleSentence exampleSentence) {
        this.grammar = grammar;
        this.exampleSentence = exampleSentence;
    }

    public ExampleHandler createHandler(Grammar grammar, ExampleSentence exampleSentence) {
        return ExampleHandler.builder().grammar(grammar).exampleSentence(exampleSentence).build();
    }

    @Override
    public String toString() {
        return
                "exampleSentence=" + exampleSentence;
    }
}
