package com.mygrammar.manager.grammar;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mygrammar.manager.exampleHandler.ExampleHandler;
import com.mygrammar.manager.share.domain.NameValue;
import com.mygrammar.manager.share.domain.NameValueList;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Table(name = "grammar")
@SequenceGenerator(
        name = "GRAMMAR_SEQUENCE_GENERATOR",
        sequenceName = "GRAMMAR_SEQUENCE",
        initialValue = 1,
        allocationSize = 200
)
public class Grammar {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GRAMMAR_SEQUENCE_GENERATOR")
    @Column(name = "grammar_id")
    private Integer id;

    private String word;

    private String meaning;

    @OneToMany(mappedBy = "grammar")
    @JsonIgnore
    private List<ExampleHandler> example = new ArrayList<>();

    private String categoryURI;

    protected Grammar(){}

    @Builder
    public Grammar(String word, String meaning, List<ExampleHandler> example, String categoryURI) {
        this();
        this.word = word;
        this.meaning = meaning;
        this.example = example;
        this.categoryURI = categoryURI;
    }

    public List<String> getExampleAsString() {
        return example.stream()
                .map(String::valueOf)
                .collect(Collectors.toList());
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
