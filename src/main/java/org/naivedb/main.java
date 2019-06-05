// import BPlusTree.*;
package org.naivedb;

import org.antlr.v4.runtime.*;
import org.naivedb.Database.Database;
import org.naivedb.Statement.*;
import org.naivedb.Statement.grammar.ThrowingErrorListener;
import org.naivedb.Statement.grammar.myVisitor;
import org.naivedb.Statement.grammar.sqlLexer;
import org.naivedb.Statement.grammar.sqlParser;

import java.util.ArrayList;

import org.naivedb.Database.DatabaseManager;

public class main {

    public static void main(String[] args)
        throws Exception {
        Database curDatabase = null;
        DatabaseManager.initial();
        while (true) {
            byte[] bytes = new byte[1024];
            System.in.read(bytes, 0, 1024);
            if (new String(bytes).trim().toUpperCase().compareTo("EXIT") == 0) {
                if (curDatabase != null)
                    curDatabase.close();
                DatabaseManager.close();
                break;
            }
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
                        stm.exec().show();
                    }
                    if (o instanceof StatementShow) {
                        StatementShow stm = (StatementShow) o;
                        stm.exec(curDatabase).show();
                    }
                    if (o instanceof StatementDropDatabase) {
                        StatementDropDatabase stm = (StatementDropDatabase) o;
                        stm.exec().show();
                        if (stm.getDbName().compareTo(curDatabase.getName()) == 0) {
                            curDatabase = null;
                        }
                    }
                    if (o instanceof StatementUse) {
                        if (curDatabase != null)
                            curDatabase.close();
                        StatementUse stm = (StatementUse) o;
                        curDatabase = DatabaseManager.get(stm.getDBName());
                    }
                    if (o instanceof StatementCreateTable) {
                        StatementCreateTable stm = (StatementCreateTable) o;
                        stm.exec(curDatabase).show();
                    }
                    if (o instanceof StatementDropTable) {
                        StatementDropTable stm = (StatementDropTable) o;
                        stm.exec(curDatabase).show();
                    }
                    if (o instanceof StatementInsert) {
                        StatementInsert stm = (StatementInsert) o;
                        stm.exec(curDatabase).show();
                    }
                    if (o instanceof StatementDelete) {
                        StatementDelete stm = (StatementDelete) o;
                        stm.exec(curDatabase).show();
                    }
                    if (o instanceof StatementUpdate) {
                        StatementUpdate stm = (StatementUpdate) o;
                        stm.exec(curDatabase).show();
                    }
                    if (o instanceof StatementSelect) {
                        StatementSelect stm = (StatementSelect) o;
                        stm.exec(curDatabase).show();
                    }
                }
            } catch (Exception e)
            {
                System.out.println(e.getMessage());
                e.printStackTrace();
                if (curDatabase != null)
                    curDatabase.close();
                DatabaseManager.close();
                break;
            }
        }

    }
}
