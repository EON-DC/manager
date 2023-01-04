package com.mygrammar.manager.grammar;

import com.mygrammar.manager.dto.GrammarDto;
import com.mygrammar.manager.dto.SimpleGrammarDto;
import com.mygrammar.manager.share.domain.NameValueList;
import org.springframework.data.domain.Page;

import java.util.List;

public interface GrammarService {

    List<Grammar> getAllGrammar();

    Grammar getOneGrammar(int id);

    Grammar addGrammar(Grammar grammar);

    Grammar updateGrammar(int id, NameValueList nameValueList);

    void deleteGrammar(int id);

    List<SimpleGrammarDto> getLatestAddedGrammar();

    Grammar updateGrammar(GrammarDto grammarDto);

    Example createExample(Grammar grammar, String sentence);

    Example saveExample(Grammar grammar, String examples);

    List<Example> getExamples(Grammar grammar);

    void createRow(Grammar grammar, int index);
    void createRow(Grammar grammar);

    void deleteRow(Grammar grammar, int index);
    void deleteRow(Grammar grammar);

}
