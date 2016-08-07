package com.epam.am.xml.parser;

import com.epam.am.xml.model.Paper;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import java.util.ArrayList;
import java.util.List;

public class StaxParser implements PaperParser {
    private List<Paper> paperList;
    private Paper tmpPaper;
    private String currentElement;

    @Override
    public List<Paper> parse(String fileName) throws ParsingException {
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader =
                    factory.createXMLEventReader(
                            StaxParser.class.getClassLoader().getResourceAsStream("paper.xml")
                    );

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                switch (event.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT:
                        handleStartElement(event);
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        handleCharacters(event);
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        handleEndElement(event);
                        break;
                }
            }
        } catch (XMLStreamException e) {
            throw new ParsingException(e);
        }

        return paperList;
    }

    private void handleStartElement(XMLEvent event) {
        String qName = event.asStartElement().getName().getLocalPart();
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

    private void handleEndElement(XMLEvent event) {
        String qName = event.asEndElement().getName().getLocalPart();
        switch (qName) {
            case "paper":
                paperList.add(tmpPaper);
                tmpPaper = null;
                break;
        }
    }

    private void handleCharacters(XMLEvent event) {

        if (currentElement == null) return;
        String str = event.asCharacters().getData();
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
}
