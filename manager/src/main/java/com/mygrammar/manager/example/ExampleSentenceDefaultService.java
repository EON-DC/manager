package com.mygrammar.manager.example;

import com.mygrammar.manager.share.domain.NameValueList;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExampleSentenceDefaultService implements ExampleSentenceService{

    private final ExampleSentenceJpaRepository sentenceJpaRepository;

    public ExampleSentenceDefaultService(ExampleSentenceJpaRepository sentenceJpaRepository) {
        this.sentenceJpaRepository = sentenceJpaRepository;
    }

    @Override
    public List<ExampleSentence> getAllExampleSentence() {
        return sentenceJpaRepository.findAll();
    }

    @Override
    public ExampleSentence getOneExampleSentence(int id) {
        return sentenceJpaRepository.findById(id).orElseThrow(
                () -> new RuntimeException("No such grammar id : " + id)
        );
    }

    @Override
    public ExampleSentence addExampleSentence(ExampleSentence exampleSentence) {
        return sentenceJpaRepository.save(exampleSentence);
    }

    @Override
    public ExampleSentence updateExampleSentence(int id, NameValueList nameValueList) {
        ExampleSentence findExample = sentenceJpaRepository.findById(id).orElseThrow(
                () -> new RuntimeException("No such grammar id : " + id)
        );
        findExample.setValues(nameValueList);
        return sentenceJpaRepository.save(findExample);
    }

    @Override
    public void deleteExampleSentence(int id) {
        sentenceJpaRepository.deleteById(id);
    }
}
