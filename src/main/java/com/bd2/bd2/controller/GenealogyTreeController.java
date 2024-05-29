package com.bd2.bd2.controller;

import com.bd2.bd2.model.GenealogyTree;
import com.bd2.bd2.model.XmlToTableResults;
import com.bd2.bd2.service.GenealogyTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GenealogyTreeController {

    @Autowired
    private GenealogyTreeService service;

    @GetMapping("/")
    public String index(Model model) {
        List<GenealogyTree> trees = service.findAll();
        model.addAttribute("trees", trees);
        model.addAttribute("newTree", new GenealogyTree());
        model.addAttribute("personId", Long.valueOf(0));
        model.addAttribute("treeId", Long.valueOf(0));
        return "index";
    }

    @PostMapping("/addTree")
    public String addTree(@ModelAttribute GenealogyTree newTree) {
        service.save(newTree);
        return "redirect:/";
    }

    @PostMapping("/deletePerson")
    public String deletePerson(@RequestParam("personId") Long personId, @RequestParam("treeId") Long treeId) {
        service.deletePerson(personId, treeId);
        return "redirect:/";
    }

    @PostMapping("/selectTree")
    public String selectTree(Model model, @RequestParam("treeIdSelect") Long treeId) {
        List<XmlToTableResults> selectedTree = service.getXmlToTableResults(treeId);
        List<GenealogyTree> trees = service.findAll();
        model.addAttribute("trees", trees);
        model.addAttribute("newTree", new GenealogyTree());
        model.addAttribute("personId", Long.valueOf(0));
        model.addAttribute("treeId", Long.valueOf(0));
        model.addAttribute("xmlToTable", selectedTree);
        return "index";
    }
}
