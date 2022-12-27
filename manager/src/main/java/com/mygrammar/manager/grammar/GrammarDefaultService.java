package com.mygrammar.manager.grammar;

import com.mygrammar.manager.share.domain.NameValueList;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GrammarDefaultService implements GrammarService {

    private final GrammarJpaRepository grammarJpaRepository;

    public GrammarDefaultService(GrammarJpaRepository grammarJpaRepository) {
        this.grammarJpaRepository = grammarJpaRepository;
    }

    @Override
    public List<Grammar> getAllGrammar() {
        return grammarJpaRepository.findAll();
    }

    @Override
    public Grammar getOneGrammar(int id) {
        return grammarJpaRepository.findById(id).orElseThrow(
                () -> new RuntimeException("No such grammar id : " + id)
        );
    }

    @Override
    public Grammar addGrammar(Grammar grammar) {
        return grammarJpaRepository.save(grammar);
    }

    @Override
    public Grammar updateGrammar(int id, NameValueList nameValueList) {
        Grammar findGrammar = grammarJpaRepository.findById(id).orElseThrow(
                () -> new RuntimeException("No such grammar id : " + id)
        );
        findGrammar.setValues(nameValueList);
        return grammarJpaRepository.save(findGrammar);
    }

    @Override
    public void deleteGrammar(int id) {
        grammarJpaRepository.deleteById(id);
    }
}
