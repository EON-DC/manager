package com.mygrammar.manager.dto;

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
    private List<String> examples;
    private String categoryURI;
    private Timestamp createdOnTime;

    public GrammarDto(Grammar grammar) {
        BeanUtils.copyProperties(grammar, this);
    }

    public Grammar toDomain() {
        this.setCreatedOnTime(Timestamp.valueOf(LocalDateTime.now()));
        Grammar grammar = Grammar.builder().build();
        BeanUtils.copyProperties(this, grammar);
        if (this.examples != null) {
            Iterator<String> iterator = examples.iterator();
            while (!iterator.hasNext()) {
                grammar.getExamples().add(iterator.next());
            }
        }
        return grammar;
    }

}
