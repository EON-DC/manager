package com.mygrammar.manager;

import com.mygrammar.manager.example.ExampleSentence;
import com.mygrammar.manager.example.ExampleSentenceJpaRepository;
import com.mygrammar.manager.example.ExampleSentenceService;
import com.mygrammar.manager.exampleHandler.ExampleHandler;
import com.mygrammar.manager.exampleHandler.ExampleHandlerRepository;
import com.mygrammar.manager.grammar.Grammar;
import com.mygrammar.manager.grammar.GrammarJpaRepository;
import com.mygrammar.manager.grammar.GrammarService;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.IntStream;

@Component
public class ApplicationRunner implements  CommandLineRunner{

    private final ExampleSentenceJpaRepository exampleSentenceJpaRepository;
    private final GrammarJpaRepository grammarJpaRepository;

    private final ExampleHandlerRepository exampleHandlerRepository;

    private final Faker faker;


    public ApplicationRunner(ExampleSentenceJpaRepository exampleSentenceJpaRepository,
                             GrammarJpaRepository grammarJpaRepository,
                             ExampleHandlerRepository exampleHandlerRepository) {
        this.exampleSentenceJpaRepository = exampleSentenceJpaRepository;
        this.grammarJpaRepository = grammarJpaRepository;

        this.exampleHandlerRepository = exampleHandlerRepository;
        this.faker = new Faker();
    }

    @Override
    public void run(String... args) throws Exception {
        commandLineRunner(exampleSentenceJpaRepository, grammarJpaRepository, exampleHandlerRepository);
    }

    void commandLineRunner(ExampleSentenceJpaRepository exampleSentenceJpaRepository,
                           GrammarJpaRepository grammarJpaRepository,
                           ExampleHandlerRepository exampleHandlerRepository) {
        Grammar myGrammar = Grammar.builder().word("park").meaning("producer's name").categoryURI("arsdf2").build();
        ExampleSentence exampleSentence = ExampleSentence.builder().sentence("my name is ___").build();

        ExampleHandler firstHandler = ExampleHandler.builder().grammar(myGrammar).exampleSentence(exampleSentence).build();
        exampleSentenceJpaRepository.save(exampleSentence);
        grammarJpaRepository.save(myGrammar);


        exampleHandlerRepository.save(firstHandler);

        List<Grammar> grammars = IntStream.rangeClosed(1, 100)
                .mapToObj(i -> Grammar.builder()
                        .word(faker.verb().base())
                        .categoryURI(faker.lorem().word())
                        .meaning(faker.company().name()).build())
                .toList();

        List<ExampleSentence> exampleSentences = IntStream.rangeClosed(1, 200)
                .mapToObj(i -> ExampleSentence.builder()
                        .sentence(faker.food().dish().concat("___"))
                        .build())
                .toList();

        exampleSentenceJpaRepository.saveAll(exampleSentences);
        grammarJpaRepository.saveAll(grammars);

        for (int i = 0; i < 100; i++) {
            ExampleHandler build1 = ExampleHandler.builder()
                    .grammar(grammars.get(i))
                    .exampleSentence(exampleSentences.get(2 * i))
                    .build();
            ExampleHandler build2 = ExampleHandler.builder()
                    .grammar(grammars.get(i))
                    .exampleSentence(exampleSentences.get(2 * i + 1))
                    .build();
            exampleHandlerRepository.save(build1);
            exampleHandlerRepository.save(build2);
        }


    }
}
