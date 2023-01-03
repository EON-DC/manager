package com.mygrammar.manager.grammar;

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

    @Override
    public List<SimpleGrammarDto> getLatestAddedGrammar() {
        List<Grammar> last10AddedGrammar = grammarJpaRepository.getLast10AddedGrammar(PageRequest.of(0, 10));
        List<SimpleGrammarDto> simpleGrammarDtoList = new ArrayList<>();
        for (Grammar g : last10AddedGrammar) {
            simpleGrammarDtoList.add(new SimpleGrammarDto(g.getId(), g.getWord()));
        }
        return simpleGrammarDtoList;
    }

}
