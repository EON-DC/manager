package com.mygrammar.manager.grammar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExampleJpaRepository extends JpaRepository<Example, Integer> {

    @Query(value = "select e from Example e where e.grammar.id =:grammarId")
    List<Example> findExamplesByGrammarId(@Param("grammarId") Integer id);
}
