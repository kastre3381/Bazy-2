package com.bd2.bd2.model;

import java.io.Serializable;

public class GenealogyTree implements Serializable {

    private Long id;
    private String xmlData;

    public GenealogyTree() {
    }

    public GenealogyTree(Long id, String xmlData) {
        this.id = id;
        this.xmlData = xmlData;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getXmlData() {
        return xmlData;
    }

    public void setXmlData(String xmlData) {
        this.xmlData = xmlData;
    }

    @Override
    public String toString()
    {
        return "GenealogyTree{" +
                "id=" + id +
                ", xmlData='" + xmlData + '\'' +
                '}';
    }

}
