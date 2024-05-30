package com.bd2.bd2.controller;

import com.bd2.bd2.model.GenealogyTree;
import com.bd2.bd2.model.XmlToTableResults;
import com.bd2.bd2.service.GenealogyTreeService;
import com.bd2.bd2.tools.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.sql.SQLException;
import java.util.Date;
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

    @PostMapping("/addPerson")
    public String addPerson(Model model,
                            @RequestParam("treeIdAdd") Long id,
                            @RequestParam("nameAdd") String name,
                            @RequestParam("surnameAdd") String surname,
                            @RequestParam("birthDateAdd") String bdate,
                            @RequestParam(value = "deathDateAdd", required = false) String ddate,
                            @RequestParam(value = "fatherIdAdd", required = false) Long fid,
                            @RequestParam(value = "motherIdAdd", required = false) Long mid,
                            @RequestParam(value = "partnerIdAdd", required = false) Long pid,
                            @RequestParam("plecAdd") String plec) {
        List<GenealogyTree> trees = service.findAll();
        model.addAttribute("trees", trees);
        model.addAttribute("newTree", new GenealogyTree());
        model.addAttribute("personId", Long.valueOf(0));
        model.addAttribute("treeId", Long.valueOf(0));
        try {
            service.save(id, name, surname, bdate, ddate, fid, mid, pid, plec);
        } catch (Exception e) {
            String s = Tools.extractBetweenOccurrences(e.toString(), "System.ArgumentException:");
            model.addAttribute("error", s);
        }
            return "index";

    }


    @PostMapping("/deletePerson")
    public String deletePerson(Model model, @RequestParam("personId") Long personId, @RequestParam("treeId") Long treeId) {
        List<GenealogyTree> trees = service.findAll();
        model.addAttribute("trees", trees);
        model.addAttribute("newTree", new GenealogyTree());
        model.addAttribute("personId", Long.valueOf(0));
        model.addAttribute("treeId", Long.valueOf(0));
        try
        {
            service.deletePerson(personId, treeId);
        } catch (Exception e)
        {
            String s = Tools.extractBetweenOccurrences(e.toString(), "System.ArgumentException:");
            model.addAttribute("error", s);
        }
        finally {
            return "index";
        }
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
        try
        {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(selectedTree);
            model.addAttribute("jsonData", json);
        } catch (JsonProcessingException e)
        {
            model.addAttribute("error", e.getMessage());
        }

        return "index";
    }
}
