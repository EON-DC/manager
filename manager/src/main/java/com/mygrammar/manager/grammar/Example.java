package com.mygrammar.manager.grammar;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "example")
public class Example {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "example_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "grammar_id")
    private Grammar grammar;
    private String sentence;


    public static Example createExample(Grammar grammar, String sentence) {
        Example example = new Example();
        example.setGrammar(grammar);
        example.setSentence(sentence);
        return example;
    }


}
