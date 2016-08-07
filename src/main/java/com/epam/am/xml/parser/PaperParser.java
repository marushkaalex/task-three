package com.epam.am.xml.parser;

import com.epam.am.xml.model.Paper;

import java.util.List;

public interface PaperParser {
    List<Paper> parse(String fileName) throws ParsingException;
}
