package com.epam.am.xml;

import com.epam.am.xml.model.Paper;
import com.epam.am.xml.parser.*;

import java.util.List;

public class Runner {
    public static void main(String[] args) {
        try {
            PaperParser parser = new DomParser();
            List<Paper> domPaperList = parser.parse("paper.xml");

            parser = new SaxParser();
            List<Paper> saxPaperList = parser.parse("paper.xml");

            parser = new StaxParser();
            List<Paper> staxPaperList = parser.parse("paper.xml");

            System.out.println(domPaperList.equals(saxPaperList) && saxPaperList.equals(staxPaperList));
        } catch (ParsingException e) {
            e.printStackTrace();
        }
    }
}
