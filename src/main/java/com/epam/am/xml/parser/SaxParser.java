package com.epam.am.xml.parser;

import com.epam.am.xml.Runner;
import com.epam.am.xml.model.Paper;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SaxParser implements PaperParser {
    @Override
    public List<Paper> parse(String fileName) throws ParsingException {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            SaxHandler handler = new SaxHandler();
            parser.parse(Runner.class.getClassLoader().getResourceAsStream("paper.xml"), handler);

            return handler.paperList;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new ParsingException(e);
        }
    }

    private static class SaxHandler extends DefaultHandler {
        private List<Paper> paperList;
        private Paper tmpPaper;
        private String currentElement;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            switch (qName) {
                case "papers":
                    paperList = new ArrayList<>();
                    break;
                case "paper":
                    tmpPaper = new Paper();
                    break;
                case "chars":
                    tmpPaper.setChars(new Paper.Chars());
                    break;
                default:
                    currentElement = qName;
                    break;
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            if (currentElement == null) return;
            String str = new String(ch, start, length);
            switch (currentElement) {
                case "title":
                    tmpPaper.setTitle(str);
                    break;
                case "type":
                    tmpPaper.setType(Paper.Type.valueOf(str.toUpperCase()));
                    break;
                case "monthly":
                    tmpPaper.setMonthly(Boolean.valueOf(str));
                    break;
                case "colored":
                    tmpPaper.getChars().setColored(Boolean.valueOf(str));
                    break;
                case "pageCount":
                    tmpPaper.getChars().setPageCount(Integer.parseInt(str));
                    break;
                case "index":
                    tmpPaper.getChars().setIndex(str);
                    break;
                case "glossy":
                    tmpPaper.getChars().setGlossy(Boolean.valueOf(str));
                    break;
            }
            currentElement = null;
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            switch (qName) {
                case "paper":
                    paperList.add(tmpPaper);
                    tmpPaper = null;
                    break;
            }
        }
    }
}
