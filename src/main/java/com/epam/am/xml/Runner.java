package com.epam.am.xml;

import com.epam.am.xml.model.Paper;
import com.epam.am.xml.parser.PaperParser;
import com.epam.am.xml.parser.ParsingException;
import com.epam.am.xml.parser.SaxParser;

import java.util.List;

public class Runner {
    public static void main(String[] args) {
        PaperParser parser = new SaxParser();
        try {
            List<Paper> paperList = parser.parse("paper.xml");
            System.out.println(paperList);
        } catch (ParsingException e) {
            e.printStackTrace();
        }
    }
}
