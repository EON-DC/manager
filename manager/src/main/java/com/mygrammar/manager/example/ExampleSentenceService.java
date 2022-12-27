package com.mygrammar.manager.example;

import com.mygrammar.manager.share.domain.NameValueList;

import java.util.List;

public interface ExampleSentenceService {

    List<ExampleSentence> getAllExampleSentence();

    ExampleSentence getOneExampleSentence(int id);

    ExampleSentence addExampleSentence(ExampleSentence exampleSentence);

    ExampleSentence updateExampleSentence(int id, NameValueList nameValueList);

    void deleteExampleSentence(int id);
}
