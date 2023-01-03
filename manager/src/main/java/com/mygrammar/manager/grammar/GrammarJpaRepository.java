package com.mygrammar.manager.grammar;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GrammarJpaRepository extends JpaRepository<Grammar, Integer> {

    @Query(value = "select g from Grammar g order by g.id desc")
    List<Grammar> getLast10AddedGrammar(Pageable pageable);
}
