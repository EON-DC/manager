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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "grammars")
@ToString
public class Grammar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grammar_id")
    private Integer id;

    private String word;

    private String meaning;

    @OneToMany(mappedBy = "grammar")
    private List<Example> examples = new ArrayList<>();


    private String categoryURI;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp createdOnTime;

    protected Grammar() {
    }

    @Builder
    public Grammar(Integer id, String word, String meaning, String categoryURI) {
        this.id = id;
        this.word = word;
        this.meaning = meaning;
        this.categoryURI = categoryURI;
        this.createdOnTime = Timestamp.valueOf(LocalDateTime.now());
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

    public void update(String word, String meaning, String categoryURI, Timestamp createdOnTime) {
        this.word = word;
        this.meaning = meaning;
        this.categoryURI = categoryURI;
        this.createdOnTime = createdOnTime;
    }


}
