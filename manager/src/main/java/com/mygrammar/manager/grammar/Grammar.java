package com.mygrammar.manager.grammar;

import com.mygrammar.manager.dto.GrammarDto;
import com.mygrammar.manager.share.domain.NameValue;
import com.mygrammar.manager.share.domain.NameValueList;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "grammars")
@SequenceGenerator(
        name = "GRAMMAR_SEQUENCE_GENERATOR",
        sequenceName = "GRAMMAR_SEQUENCE",
        initialValue = 1,
        allocationSize = 200
)
@ToString
public class Grammar {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GRAMMAR_SEQUENCE_GENERATOR")
    @Column(name = "grammar_id")
    private Integer id;

    private String word;

    private String meaning;

    @ElementCollection
    private List<String> examples = new ArrayList<>();


    private String categoryURI;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp createdOnTime;

    protected Grammar() {
    }

    @Builder
    public Grammar(String word, String meaning, List<String> example, String categoryURI, Timestamp createdOnTime) {
        this.word = word;
        this.meaning = meaning;
        this.examples = example;
        this.categoryURI = categoryURI;
        this.createdOnTime = createdOnTime;
    }

    public void addExample(String exampleSentence) {
        examples.add(exampleSentence);
    }

    public void setValues(NameValueList nameValueList) {
        for (NameValue nameValue : nameValueList.getNameValueList()) {
            String name = nameValue.getName();
            String value = nameValue.getValue();
            switch (name) {
                case "word":
                    word = value;
                    break;
                case "meaning":
                    meaning = value;
                    break;
                case "categoryURI":
                    categoryURI = value;
                    break;
                default:
                    throw new RuntimeException("No such field : " + name);
            }
        }
    }


}
