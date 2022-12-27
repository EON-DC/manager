package com.mygrammar.manager.example;

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

@Entity
@Getter
@Setter
@Table(name = "example")
@SequenceGenerator(
        name = "EXAMPLE_SEQUENCE_GENERATOR",
        sequenceName = "EXAMPLE_SEQUENCE",
        initialValue = 1,
        allocationSize = 300
)
public class ExampleSentence {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXAMPLE_SEQUENCE_GENERATOR")
    @Column(name = "example_id")
    private Integer id;

    private String sentence;

    @OneToMany(mappedBy = "exampleSentence")
    @JsonIgnore
    private List<ExampleHandler> answerWord = new ArrayList<>();

    protected ExampleSentence() {
    }

    @Builder
    public ExampleSentence(String sentence, List<ExampleHandler> answerWord) {
        this();
        this.sentence = sentence;
        this.answerWord = answerWord;
    }

    @Override
    public String toString() {
        return "ExampleSentence{" +
                "sentence='" + sentence + '\'' +
                '}';
    }

    public void setValues(NameValueList nameValueList) {
        for (NameValue nameValue : nameValueList.getNameValueList()) {
            String name = nameValue.getName();
            String value = nameValue.getValue();
            switch (name) {
                case "sentence":
                    sentence = value;
                    break;
                default:
                    throw new RuntimeException("No such field : " + name);
            }
        }
    }


}
