package com.mygrammar.manager.grammar;

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

}
