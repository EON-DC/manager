package com.mygrammar.manager.controller;

import com.mygrammar.manager.dto.GrammarDto;
import com.mygrammar.manager.dto.RowResponse;
import com.mygrammar.manager.dto.SimpleGrammarDto;
import com.mygrammar.manager.grammar.Grammar;
import com.mygrammar.manager.grammar.GrammarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/grammars")
@RequiredArgsConstructor
@Slf4j
public class GrammarController {

    private final GrammarService grammarService;

    @GetMapping(value = "/addForm")
    public String addForm(Model model) {
        List<SimpleGrammarDto> latestGrammar = grammarService.getLatestAddedGrammar();
        GrammarDto grammarDto = new GrammarDto();
        model.addAttribute("grammars", latestGrammar);
        model.addAttribute("grammarDto", grammarDto);
        return "/grammars/addGrammarForm";
    }

    @PostMapping("/add")
    public String addGrammar(Model model, GrammarDto grammarDto) {
        // todo : 오류 분석하고 있으면 그대로 정보 담아 되돌리고, 아니면 PRG static으로 addGrammarForm 돌리기
        //  grammarDto 를 domain 으로 변경하기, service 를 통해 등록하기
        log.info(grammarDto.toString());
        Grammar grammar = grammarDto.toDomain();
        grammarService.addGrammar(grammar);

        return "redirect:/grammars/addForm";
    }

    @GetMapping(value = "/edit/{id}")
    public String editFormGrammar(Model model, @PathVariable(value = "id") Integer grammarId) {
        // todo : service에서 grammarId에 따라 조회한 값을 dto로 전환하여 모델에 담기
        //  뷰에서는 보여주기만 할 예정
        Grammar oneGrammar = grammarService.getOneGrammar(grammarId);

        GrammarDto grammarDto = new GrammarDto(oneGrammar);
        List<String> examples = new ArrayList<>(grammarDto.getExamples());
        model.addAttribute("grammarDto", grammarDto);
        model.addAttribute("examples", examples);

        return "/grammars/editGrammarForm";
    }

    @PostMapping(value = "/edit/{id}", params = {"addRow"})
    public String addRow(Model model, @PathVariable(value = "id") Integer grammarId,
                         GrammarDto grammarDto, ArrayList<String> examples) {
        // todo :
        Grammar grammar = grammarDto.toDomain();
        grammar.setExamples(examples);
        System.out.println("examples = " + examples.toString());
        System.out.println("grammarDto = " + grammarDto);
        System.out.println("grammar = " + grammar);
        grammar.getExamples().add("");
        grammarService.addGrammar(grammar);
        return "redirect:/grammars/edit/" + grammarId;
    }

    @PostMapping(value = "/edit/{id}", params = {"removeRow"})
    public String deleteRow(Model model, @PathVariable(value = "id") Integer grammarId,
                            GrammarDto grammarDto) {
        // todo :
        Grammar grammar = grammarDto.toDomain();
        int lastIndex = grammar.getExamples().size();
        grammar.getExamples().remove(lastIndex);
        grammarService.addGrammar(grammar);
        return "redirect:/grammars/edit/" + grammarId;

    }

    @PostMapping(value = "/edit/{id}")
    public String editGrammar(Model model, @PathVariable(value = "id") Integer grammarId,
                            GrammarDto grammarDto, ArrayList<String> examples) {
        // todo :
        Grammar grammar = grammarDto.toDomain();
        grammar.setExamples(examples);
        System.out.println("examples = " + examples.toString());
        System.out.println("grammarDto = " + grammarDto);
        System.out.println("grammar = " + grammar);

        grammarService.addGrammar(grammar);
        return "redirect:/grammars/edit/" + grammarId;

    }
}
