package com.mygrammar.manager.example;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExampleSentenceJpaRepository extends JpaRepository<ExampleSentence, Integer> {
}
