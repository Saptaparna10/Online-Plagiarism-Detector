package com.team113.plagiarismdetector.ast;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.Map;

/**
 * This class is used to provide AST of given file in particular data structure format
 */
public class DataProvider {

    static final Logger logger = Logger.getLogger(DataProvider.class);

    /**
     * This private constructor allows the access to static methods of class
     */
    private DataProvider(){

    }

    /**
     * This method is used to fetch category map for given python file
     * @param source python file for which category hash is to be fetched
     * @return category map for given python file
     */
    public static Map<Integer,Integer> cateHash(String source) {

        logger.log(Level.INFO, "Received request to create cateHash");

        PythonParser listener = new PythonParser();

        Python3Parser parser = build(source);

        ParseTreeWalker.DEFAULT.walk(listener, parser.file_input());

        return listener.getCateMap();
    }

    /**
     * This method is used to fetch LCS map for given python file
     * @param source python file for which LCS hash is to be fetched
     * @return LCS map for given python file
     */
    public static Map<Integer,Map<String,String>> lcsHash(String source) {

        logger.log(Level.INFO, "Received request to create lcsHash");

        PythonParser listener = new PythonParser();

        Python3Parser parser = build(source);

        ParseTreeWalker.DEFAULT.walk(listener, parser.file_input());

        return listener.getLcsMap();
    }

    /**
     * This method is used to parse given string input file
     * @param file the input file in string format
     * @return parsed file containing variables, expressions, types, etc
     */
    private static Python3Parser build(String file) {

        Python3Lexer lexer = new Python3Lexer(CharStreams.fromString(file));
        lexer.removeErrorListeners();
        Python3Parser python3Parser = new Python3Parser(new CommonTokenStream(lexer));
        python3Parser.removeErrorListeners();

        return python3Parser;
    }
}
