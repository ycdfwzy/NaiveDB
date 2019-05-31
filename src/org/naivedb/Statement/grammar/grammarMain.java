package org.naivedb.Statement.grammar;

import org.antlr.v4.runtime.*;

import java.util.ArrayList;

public class grammarMain {
    public static void main(String[] args) {
        CharStream input = CharStreams.fromString("create table mydb (a int, b string(124) not null, primary key (a));drop database mydb;");
        sqlLexer lexer = new sqlLexer(input);
        lexer.removeErrorListeners();
        lexer.addErrorListener(ThrowingErrorListener.INSTANCE);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        sqlParser parser = new sqlParser(tokenStream);
        parser.removeErrorListeners();
        parser.addErrorListener(ThrowingErrorListener.INSTANCE);

        try {
            myVisitor visitor = new myVisitor();
            ArrayList res = (ArrayList) visitor.visit(parser.parse());
            System.out.print(res);
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
