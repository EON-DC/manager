package com.mygrammar.manager.dto;

import com.mygrammar.manager.grammar.Example;
import com.mygrammar.manager.grammar.Grammar;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GrammarDto {

    private Integer id;
    private String word;
    private String meaning;
    private String categoryURI;
    private Timestamp createdOnTime;

    public GrammarDto(Grammar grammar) {
        this.id = grammar.getId();
        this.word = grammar.getWord();
        this.meaning = grammar.getMeaning();
        this.categoryURI = grammar.getCategoryURI();
        this.createdOnTime = grammar.getCreatedOnTime();
    }

    public Grammar toDomain() {
        Grammar grammar = new Grammar(id, word, meaning, categoryURI);
        if (createdOnTime == null) {
            createdOnTime = Timestamp.valueOf(LocalDateTime.now());
        }
        return grammar;
    }

}
