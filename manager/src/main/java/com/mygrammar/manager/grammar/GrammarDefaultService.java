package com.mygrammar.manager.grammar;

import com.mygrammar.manager.dto.GrammarDto;
import com.mygrammar.manager.dto.SimpleGrammarDto;
import com.mygrammar.manager.share.domain.NameValueList;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class GrammarDefaultService implements GrammarService {

    private final GrammarJpaRepository grammarJpaRepository;
    private final ExampleJpaRepository exampleJpaRepository;

    public GrammarDefaultService(GrammarJpaRepository grammarJpaRepository, ExampleJpaRepository exampleJpaRepository) {
        this.grammarJpaRepository = grammarJpaRepository;
        this.exampleJpaRepository = exampleJpaRepository;
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

    public Grammar updateGrammar(GrammarDto grammarDto) {
        Grammar oneGrammar = getOneGrammar(grammarDto.getId());
        oneGrammar.update(grammarDto.getWord(),
                grammarDto.getMeaning(),
                grammarDto.getCategoryURI(),
                grammarDto.getCreatedOnTime());
        return oneGrammar;
    }

    @Override
    public Example saveExample(Grammar grammar, String exampleSentence) {
        return exampleJpaRepository.save(Example.createExample(grammar, exampleSentence));
    }

    @Override
    public List<Example> getExamples(Grammar grammar) {
        return exampleJpaRepository.findExamplesByGrammarId(grammar.getId());
    }

    @Override
    public void deleteGrammar(int id) {
        grammarJpaRepository.deleteById(id);
    }

    @Override
    public List<SimpleGrammarDto> getLatestAddedGrammar() {
        List<Grammar> last10AddedGrammar = grammarJpaRepository.getLast10AddedGrammar(PageRequest.of(0, 10));
        List<SimpleGrammarDto> simpleGrammarDtoList = new ArrayList<>();
        for (Grammar g : last10AddedGrammar) {
            simpleGrammarDtoList.add(new SimpleGrammarDto(g.getId(), g.getWord()));
        }
        return simpleGrammarDtoList;
    }

    public Example createExample(Grammar grammar, String sentence) {
        nullCheck(grammar);
        Example example = Example.createExample(grammar, sentence);
        exampleJpaRepository.save(example);
        return example;
    }

    public void createRow(Grammar grammar) {
        nullCheck(grammar);
        grammar.getExamples().add(createExample(grammar, ""));
    }

    public void createRow(Grammar grammar, int index) {
        nullCheck(grammar);
        grammar.getExamples().add(index, createExample(grammar, ""));
    }

    public void deleteRow(Grammar grammar) {
        nullCheck(grammar);
        List<Example> examples = grammar.getExamples();

        if (examples.size() == 0) {
            return;
        }
        examples.remove(examples.size() - 1);
    }

    public void deleteRow(Grammar grammar, int index) {
        nullCheck(grammar);

        List<Example> examples = grammar.getExamples();

        if (examples.size() <= index) {
            return;
        }
        examples.remove(index);
    }

    public void nullCheck(Grammar grammar) {
        if (grammar.getId() == null) {
            throw new RuntimeException("No such grammar id : " + grammar.getId());
        }
    }
}
