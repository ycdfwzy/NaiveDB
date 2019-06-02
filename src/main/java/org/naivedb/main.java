// import BPlusTree.*;
package org.naivedb;

import org.antlr.v4.runtime.*;
import org.naivedb.Database.Database;
import org.naivedb.Persistence.PersistenceData;
import org.naivedb.Statement.*;
import org.naivedb.Statement.grammar.ThrowingErrorListener;
import org.naivedb.Statement.grammar.myVisitor;
import org.naivedb.Statement.grammar.sqlLexer;
import org.naivedb.Statement.grammar.sqlParser;
import org.naivedb.utils.NumberUtils;
import org.naivedb.utils.MyLogger;
import org.naivedb.Type.Type;
import java.util.logging.*;
import org.naivedb.Table.TempTable;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;
import org.naivedb.Database.DatabaseManager;
import javafx.util.Pair;

public class main {

    public static void main(String[] args)
        throws Exception {
        Database curDatabase = null;
        DatabaseManager.initial();
        while (true) {
            byte[] bytes = new byte[1024];
            System.in.read(bytes, 0, 1024);
            CharStream input = CharStreams.fromString(new String(bytes).trim());
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
                for (Object o: res) {
                    if (o instanceof StatementCreateDatabase) {
                        StatementCreateDatabase stm = (StatementCreateDatabase) o;
                        System.out.println(stm.exec());
                    }
                    if (o instanceof StatementUse) {
                        StatementUse stm = (StatementUse) o;
                        curDatabase = DatabaseManager.get(stm.getDBName());
                    }
                    if (o instanceof StatementCreateTable) {
                        StatementCreateTable stm = (StatementCreateTable) o;
                        System.out.println(stm.exec(curDatabase));
                    }
                    if (o instanceof StatementInsert) {
                        StatementInsert stm = (StatementInsert) o;
                        System.out.println(stm.exec(curDatabase));
                    }
                }
            } catch (Exception e)
            {
                System.out.println(e.getMessage());
                e.printStackTrace();
                DatabaseManager.close();
                break;
            }
        }

    }
}
