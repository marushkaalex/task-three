package com.epam.am.xml.parser;

import com.epam.am.xml.model.Paper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DomParser implements PaperParser {
    @Override
    public List<Paper> parse(String fileName) throws ParsingException {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(DomParser.class.getClassLoader().getResourceAsStream("paper.xml"));

            doc.getDocumentElement().normalize();

            NodeList paperList = doc.getElementsByTagName("paper");
            List<Paper> resList = new ArrayList<>(paperList.getLength());

            for (int i = 0; i < paperList.getLength(); i++) {

                Node tmpNode = paperList.item(i);
                if (tmpNode.getNodeType() != Node.ELEMENT_NODE) continue;

                Element element = (Element) tmpNode;
                Paper paper = new Paper();

                paper.setTitle(getInnerElementValue(element, "title"));
                paper.setType(Paper.Type.valueOf(getInnerElementValue(element, "type").toUpperCase()));
                paper.setMonthly(getInnerElementBoolean(element, "monthly"));

                Paper.Chars chars = new Paper.Chars();
                chars.setColored(getInnerElementBoolean(element, "monthly"));
                chars.setIndex(getInnerElementValue(element, "index"));
                chars.setPageCount(Integer.parseInt(getInnerElementValue(element, "pageCount")));
                chars.setGlossy(getInnerElementBoolean(element, "glossy"));

                paper.setChars(chars);

                resList.add(paper);
            }
            return resList;
        } catch (SAXException | ParserConfigurationException | IOException e) {
            throw new ParsingException(e);
        }
    }

    private String getInnerElementValue(Element element, String tagName) {
        return element.getElementsByTagName(tagName).item(0).getTextContent();
    }

    private boolean getInnerElementBoolean(Element element, String tagName) {
        return Boolean.parseBoolean(getInnerElementValue(element, tagName));
    }
}
