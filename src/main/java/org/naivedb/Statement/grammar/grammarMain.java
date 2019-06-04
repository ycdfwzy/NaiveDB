package org.naivedb.Statement.grammar;

import org.antlr.v4.runtime.*;

import java.util.ArrayList;

public class grammarMain {
    public static void main(String[] args) {
        CharStream input = CharStreams.fromString("use db;" +
                            "create table mydb (a int, b string(124) not null, primary key (a));" +
                            "insert into mydb (b, a) values (\"2342\", 1), (\"78964\", 2);" +
                            "update mydb set b = \"1234\" where a == 1;" +
                            "delete from mydb where a <> 1;" +
                            "show databases;" +
                            "show database db;" +
                            "drop database mydb;" +
                            "select a from t1 join t2 on t1.a==t2.b natural join t3 where t3.x>0;");
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
