// Generated from D:/gitRepo/NaiveDB/src/org/naivedb/Statement/grammar\sql.g4 by ANTLR 4.7.2
package org.naivedb.Statement.grammar;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class sqlParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, SPACES=21, K_CREATE=22, K_DATABASE=23, K_DATABASES=24, 
		K_TABLE=25, K_TABLES=26, K_NOT=27, K_NULL=28, K_PRIMARY=29, K_KEY=30, 
		K_DROP=31, K_INSERT=32, K_INTO=33, K_VALUES=34, K_DELETE=35, K_FROM=36, 
		K_WHERE=37, K_UPDATE=38, K_SET=39, K_SELECT=40, K_JOIN=41, K_ON=42, K_USE=43, 
		K_SHOW=44, K_AND=45, K_OR=46, K_INT=47, K_LONG=48, K_FLOAT=49, K_DOUBLE=50, 
		K_STRING=51, IDENTIFIER=52, INTEGER_LITERAL=53, FLOAT_LITERAL=54, UNEXPECTED_CHAR=55;
	public static final int
		RULE_parse = 0, RULE_error = 1, RULE_sql_stmt_list = 2, RULE_sql_stmt = 3, 
		RULE_create_db_stmt = 4, RULE_create_table_stmt = 5, RULE_drop_stmt = 6, 
		RULE_insert_stmt = 7, RULE_delete_stmt = 8, RULE_update_stmt = 9, RULE_select_stmt = 10, 
		RULE_use_stmt = 11, RULE_show_stmt = 12, RULE_select_elements = 13, RULE_asign_clause = 14, 
		RULE_db_name = 15, RULE_table_name = 16, RULE_attr_name = 17, RULE_type_name = 18, 
		RULE_expr = 19, RULE_numeric_expr = 20, RULE_expr_column = 21, RULE_unary_operator = 22, 
		RULE_pred_expr = 23, RULE_numeric_value = 24;
	private static String[] makeRuleNames() {
		return new String[] {
			"parse", "error", "sql_stmt_list", "sql_stmt", "create_db_stmt", "create_table_stmt", 
			"drop_stmt", "insert_stmt", "delete_stmt", "update_stmt", "select_stmt", 
			"use_stmt", "show_stmt", "select_elements", "asign_clause", "db_name", 
			"table_name", "attr_name", "type_name", "expr", "numeric_expr", "expr_column", 
			"unary_operator", "pred_expr", "numeric_value"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'('", "','", "')'", "'*'", "'='", "'\"'", "'/'", "'%'", 
			"'+'", "'-'", "'.'", "'~'", "'<'", "'<='", "'>'", "'>='", "'<>'", "'=='", 
			"'!='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, "SPACES", "K_CREATE", 
			"K_DATABASE", "K_DATABASES", "K_TABLE", "K_TABLES", "K_NOT", "K_NULL", 
			"K_PRIMARY", "K_KEY", "K_DROP", "K_INSERT", "K_INTO", "K_VALUES", "K_DELETE", 
			"K_FROM", "K_WHERE", "K_UPDATE", "K_SET", "K_SELECT", "K_JOIN", "K_ON", 
			"K_USE", "K_SHOW", "K_AND", "K_OR", "K_INT", "K_LONG", "K_FLOAT", "K_DOUBLE", 
			"K_STRING", "IDENTIFIER", "INTEGER_LITERAL", "FLOAT_LITERAL", "UNEXPECTED_CHAR"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "sql.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public sqlParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ParseContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(sqlParser.EOF, 0); }
		public List<Sql_stmt_listContext> sql_stmt_list() {
			return getRuleContexts(Sql_stmt_listContext.class);
		}
		public Sql_stmt_listContext sql_stmt_list(int i) {
			return getRuleContext(Sql_stmt_listContext.class,i);
		}
		public List<ErrorContext> error() {
			return getRuleContexts(ErrorContext.class);
		}
		public ErrorContext error(int i) {
			return getRuleContext(ErrorContext.class,i);
		}
		public ParseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).enterParse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).exitParse(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitParse(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParseContext parse() throws RecognitionException {
		ParseContext _localctx = new ParseContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_parse);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << K_CREATE) | (1L << K_DROP) | (1L << K_INSERT) | (1L << K_DELETE) | (1L << K_UPDATE) | (1L << K_SELECT) | (1L << K_USE) | (1L << K_SHOW) | (1L << UNEXPECTED_CHAR))) != 0)) {
				{
				setState(52);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case K_CREATE:
				case K_DROP:
				case K_INSERT:
				case K_DELETE:
				case K_UPDATE:
				case K_SELECT:
				case K_USE:
				case K_SHOW:
					{
					setState(50);
					sql_stmt_list();
					}
					break;
				case UNEXPECTED_CHAR:
					{
					setState(51);
					error();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(56);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(57);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ErrorContext extends ParserRuleContext {
		public Token UNEXPECTED_CHAR;
		public TerminalNode UNEXPECTED_CHAR() { return getToken(sqlParser.UNEXPECTED_CHAR, 0); }
		public ErrorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_error; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).enterError(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).exitError(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitError(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ErrorContext error() throws RecognitionException {
		ErrorContext _localctx = new ErrorContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_error);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59);
			((ErrorContext)_localctx).UNEXPECTED_CHAR = match(UNEXPECTED_CHAR);

			      throw new RuntimeException("UNEXPECTED_CHAR=" + (((ErrorContext)_localctx).UNEXPECTED_CHAR!=null?((ErrorContext)_localctx).UNEXPECTED_CHAR.getText():null) );
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Sql_stmt_listContext extends ParserRuleContext {
		public List<Sql_stmtContext> sql_stmt() {
			return getRuleContexts(Sql_stmtContext.class);
		}
		public Sql_stmtContext sql_stmt(int i) {
			return getRuleContext(Sql_stmtContext.class,i);
		}
		public Sql_stmt_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sql_stmt_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).enterSql_stmt_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).exitSql_stmt_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitSql_stmt_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Sql_stmt_listContext sql_stmt_list() throws RecognitionException {
		Sql_stmt_listContext _localctx = new Sql_stmt_listContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_sql_stmt_list);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
			sql_stmt();
			setState(71);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(64); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(63);
						match(T__0);
						}
						}
						setState(66); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==T__0 );
					setState(68);
					sql_stmt();
					}
					} 
				}
				setState(73);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			}
			setState(74);
			match(T__0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Sql_stmtContext extends ParserRuleContext {
		public Create_db_stmtContext create_db_stmt() {
			return getRuleContext(Create_db_stmtContext.class,0);
		}
		public Create_table_stmtContext create_table_stmt() {
			return getRuleContext(Create_table_stmtContext.class,0);
		}
		public Drop_stmtContext drop_stmt() {
			return getRuleContext(Drop_stmtContext.class,0);
		}
		public Insert_stmtContext insert_stmt() {
			return getRuleContext(Insert_stmtContext.class,0);
		}
		public Delete_stmtContext delete_stmt() {
			return getRuleContext(Delete_stmtContext.class,0);
		}
		public Update_stmtContext update_stmt() {
			return getRuleContext(Update_stmtContext.class,0);
		}
		public Select_stmtContext select_stmt() {
			return getRuleContext(Select_stmtContext.class,0);
		}
		public Use_stmtContext use_stmt() {
			return getRuleContext(Use_stmtContext.class,0);
		}
		public Show_stmtContext show_stmt() {
			return getRuleContext(Show_stmtContext.class,0);
		}
		public Sql_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sql_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).enterSql_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).exitSql_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitSql_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Sql_stmtContext sql_stmt() throws RecognitionException {
		Sql_stmtContext _localctx = new Sql_stmtContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_sql_stmt);
		try {
			setState(85);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(76);
				create_db_stmt();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(77);
				create_table_stmt();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(78);
				drop_stmt();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(79);
				insert_stmt();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(80);
				delete_stmt();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(81);
				update_stmt();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(82);
				select_stmt();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(83);
				use_stmt();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(84);
				show_stmt();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Create_db_stmtContext extends ParserRuleContext {
		public TerminalNode K_CREATE() { return getToken(sqlParser.K_CREATE, 0); }
		public TerminalNode K_DATABASE() { return getToken(sqlParser.K_DATABASE, 0); }
		public Db_nameContext db_name() {
			return getRuleContext(Db_nameContext.class,0);
		}
		public Create_db_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_create_db_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).enterCreate_db_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).exitCreate_db_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitCreate_db_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Create_db_stmtContext create_db_stmt() throws RecognitionException {
		Create_db_stmtContext _localctx = new Create_db_stmtContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_create_db_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			match(K_CREATE);
			setState(88);
			match(K_DATABASE);
			setState(89);
			db_name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Create_table_stmtContext extends ParserRuleContext {
		public TerminalNode K_CREATE() { return getToken(sqlParser.K_CREATE, 0); }
		public TerminalNode K_TABLE() { return getToken(sqlParser.K_TABLE, 0); }
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public List<Attr_nameContext> attr_name() {
			return getRuleContexts(Attr_nameContext.class);
		}
		public Attr_nameContext attr_name(int i) {
			return getRuleContext(Attr_nameContext.class,i);
		}
		public List<Type_nameContext> type_name() {
			return getRuleContexts(Type_nameContext.class);
		}
		public Type_nameContext type_name(int i) {
			return getRuleContext(Type_nameContext.class,i);
		}
		public List<TerminalNode> K_NOT() { return getTokens(sqlParser.K_NOT); }
		public TerminalNode K_NOT(int i) {
			return getToken(sqlParser.K_NOT, i);
		}
		public List<TerminalNode> K_NULL() { return getTokens(sqlParser.K_NULL); }
		public TerminalNode K_NULL(int i) {
			return getToken(sqlParser.K_NULL, i);
		}
		public TerminalNode K_PRIMARY() { return getToken(sqlParser.K_PRIMARY, 0); }
		public TerminalNode K_KEY() { return getToken(sqlParser.K_KEY, 0); }
		public Create_table_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_create_table_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).enterCreate_table_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).exitCreate_table_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitCreate_table_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Create_table_stmtContext create_table_stmt() throws RecognitionException {
		Create_table_stmtContext _localctx = new Create_table_stmtContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_create_table_stmt);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
			match(K_CREATE);
			setState(92);
			match(K_TABLE);
			setState(93);
			table_name();
			{
			setState(94);
			match(T__1);
			setState(95);
			attr_name();
			setState(96);
			type_name();
			setState(99);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_NOT) {
				{
				setState(97);
				match(K_NOT);
				setState(98);
				match(K_NULL);
				}
			}

			setState(110);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(101);
					match(T__2);
					setState(102);
					attr_name();
					setState(103);
					type_name();
					setState(106);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==K_NOT) {
						{
						setState(104);
						match(K_NOT);
						setState(105);
						match(K_NULL);
						}
					}

					}
					} 
				}
				setState(112);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			}
			setState(120);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(113);
				match(T__2);
				setState(114);
				match(K_PRIMARY);
				setState(115);
				match(K_KEY);
				setState(116);
				match(T__1);
				setState(117);
				attr_name();
				setState(118);
				match(T__3);
				}
			}

			setState(122);
			match(T__3);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Drop_stmtContext extends ParserRuleContext {
		public TerminalNode K_DROP() { return getToken(sqlParser.K_DROP, 0); }
		public TerminalNode K_TABLE() { return getToken(sqlParser.K_TABLE, 0); }
		public TerminalNode K_DATABASE() { return getToken(sqlParser.K_DATABASE, 0); }
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public Db_nameContext db_name() {
			return getRuleContext(Db_nameContext.class,0);
		}
		public Drop_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_drop_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).enterDrop_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).exitDrop_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitDrop_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Drop_stmtContext drop_stmt() throws RecognitionException {
		Drop_stmtContext _localctx = new Drop_stmtContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_drop_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
			match(K_DROP);
			setState(125);
			_la = _input.LA(1);
			if ( !(_la==K_DATABASE || _la==K_TABLE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(128);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				setState(126);
				table_name();
				}
				break;
			case 2:
				{
				setState(127);
				db_name();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Insert_stmtContext extends ParserRuleContext {
		public TerminalNode K_INSERT() { return getToken(sqlParser.K_INSERT, 0); }
		public TerminalNode K_INTO() { return getToken(sqlParser.K_INTO, 0); }
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public TerminalNode K_VALUES() { return getToken(sqlParser.K_VALUES, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<Attr_nameContext> attr_name() {
			return getRuleContexts(Attr_nameContext.class);
		}
		public Attr_nameContext attr_name(int i) {
			return getRuleContext(Attr_nameContext.class,i);
		}
		public Insert_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_insert_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).enterInsert_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).exitInsert_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitInsert_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Insert_stmtContext insert_stmt() throws RecognitionException {
		Insert_stmtContext _localctx = new Insert_stmtContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_insert_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130);
			match(K_INSERT);
			setState(131);
			match(K_INTO);
			setState(132);
			table_name();
			setState(144);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(133);
				match(T__1);
				setState(134);
				attr_name();
				setState(139);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__2) {
					{
					{
					setState(135);
					match(T__2);
					setState(136);
					attr_name();
					}
					}
					setState(141);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(142);
				match(T__3);
				}
			}

			{
			setState(146);
			match(K_VALUES);
			setState(147);
			match(T__1);
			setState(148);
			expr();
			setState(153);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(149);
				match(T__2);
				setState(150);
				expr();
				}
				}
				setState(155);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(156);
			match(T__3);
			setState(171);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(157);
				match(T__2);
				setState(158);
				match(T__1);
				setState(159);
				expr();
				setState(164);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__2) {
					{
					{
					setState(160);
					match(T__2);
					setState(161);
					expr();
					}
					}
					setState(166);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(167);
				match(T__3);
				}
				}
				setState(173);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Delete_stmtContext extends ParserRuleContext {
		public TerminalNode K_DELETE() { return getToken(sqlParser.K_DELETE, 0); }
		public TerminalNode K_FROM() { return getToken(sqlParser.K_FROM, 0); }
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public TerminalNode K_WHERE() { return getToken(sqlParser.K_WHERE, 0); }
		public Pred_exprContext pred_expr() {
			return getRuleContext(Pred_exprContext.class,0);
		}
		public Delete_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_delete_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).enterDelete_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).exitDelete_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitDelete_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Delete_stmtContext delete_stmt() throws RecognitionException {
		Delete_stmtContext _localctx = new Delete_stmtContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_delete_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(174);
			match(K_DELETE);
			setState(175);
			match(K_FROM);
			setState(176);
			table_name();
			setState(179);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_WHERE) {
				{
				setState(177);
				match(K_WHERE);
				setState(178);
				pred_expr(0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Update_stmtContext extends ParserRuleContext {
		public TerminalNode K_UPDATE() { return getToken(sqlParser.K_UPDATE, 0); }
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public TerminalNode K_SET() { return getToken(sqlParser.K_SET, 0); }
		public Asign_clauseContext asign_clause() {
			return getRuleContext(Asign_clauseContext.class,0);
		}
		public TerminalNode K_WHERE() { return getToken(sqlParser.K_WHERE, 0); }
		public Pred_exprContext pred_expr() {
			return getRuleContext(Pred_exprContext.class,0);
		}
		public Update_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_update_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).enterUpdate_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).exitUpdate_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitUpdate_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Update_stmtContext update_stmt() throws RecognitionException {
		Update_stmtContext _localctx = new Update_stmtContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_update_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(181);
			match(K_UPDATE);
			setState(182);
			table_name();
			setState(183);
			match(K_SET);
			setState(184);
			asign_clause();
			setState(187);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_WHERE) {
				{
				setState(185);
				match(K_WHERE);
				setState(186);
				pred_expr(0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Select_stmtContext extends ParserRuleContext {
		public TerminalNode K_SELECT() { return getToken(sqlParser.K_SELECT, 0); }
		public Select_elementsContext select_elements() {
			return getRuleContext(Select_elementsContext.class,0);
		}
		public TerminalNode K_FROM() { return getToken(sqlParser.K_FROM, 0); }
		public List<Table_nameContext> table_name() {
			return getRuleContexts(Table_nameContext.class);
		}
		public Table_nameContext table_name(int i) {
			return getRuleContext(Table_nameContext.class,i);
		}
		public TerminalNode K_JOIN() { return getToken(sqlParser.K_JOIN, 0); }
		public TerminalNode K_ON() { return getToken(sqlParser.K_ON, 0); }
		public List<Pred_exprContext> pred_expr() {
			return getRuleContexts(Pred_exprContext.class);
		}
		public Pred_exprContext pred_expr(int i) {
			return getRuleContext(Pred_exprContext.class,i);
		}
		public TerminalNode K_WHERE() { return getToken(sqlParser.K_WHERE, 0); }
		public Select_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_select_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).enterSelect_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).exitSelect_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitSelect_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Select_stmtContext select_stmt() throws RecognitionException {
		Select_stmtContext _localctx = new Select_stmtContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_select_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(189);
			match(K_SELECT);
			setState(190);
			select_elements();
			setState(191);
			match(K_FROM);
			setState(192);
			table_name();
			setState(198);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_JOIN) {
				{
				setState(193);
				match(K_JOIN);
				setState(194);
				table_name();
				setState(195);
				match(K_ON);
				setState(196);
				pred_expr(0);
				}
			}

			setState(202);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_WHERE) {
				{
				setState(200);
				match(K_WHERE);
				setState(201);
				pred_expr(0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Use_stmtContext extends ParserRuleContext {
		public TerminalNode K_USE() { return getToken(sqlParser.K_USE, 0); }
		public Db_nameContext db_name() {
			return getRuleContext(Db_nameContext.class,0);
		}
		public Use_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_use_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).enterUse_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).exitUse_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitUse_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Use_stmtContext use_stmt() throws RecognitionException {
		Use_stmtContext _localctx = new Use_stmtContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_use_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(204);
			match(K_USE);
			setState(205);
			db_name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Show_stmtContext extends ParserRuleContext {
		public TerminalNode K_SHOW() { return getToken(sqlParser.K_SHOW, 0); }
		public TerminalNode K_DATABASES() { return getToken(sqlParser.K_DATABASES, 0); }
		public TerminalNode K_TABLES() { return getToken(sqlParser.K_TABLES, 0); }
		public TerminalNode K_TABLE() { return getToken(sqlParser.K_TABLE, 0); }
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public Show_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_show_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).enterShow_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).exitShow_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitShow_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Show_stmtContext show_stmt() throws RecognitionException {
		Show_stmtContext _localctx = new Show_stmtContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_show_stmt);
		int _la;
		try {
			setState(212);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(207);
				match(K_SHOW);
				setState(208);
				_la = _input.LA(1);
				if ( !(_la==K_DATABASES || _la==K_TABLES) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(209);
				match(K_SHOW);
				setState(210);
				match(K_TABLE);
				setState(211);
				table_name();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Select_elementsContext extends ParserRuleContext {
		public List<Expr_columnContext> expr_column() {
			return getRuleContexts(Expr_columnContext.class);
		}
		public Expr_columnContext expr_column(int i) {
			return getRuleContext(Expr_columnContext.class,i);
		}
		public Select_elementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_select_elements; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).enterSelect_elements(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).exitSelect_elements(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitSelect_elements(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Select_elementsContext select_elements() throws RecognitionException {
		Select_elementsContext _localctx = new Select_elementsContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_select_elements);
		int _la;
		try {
			setState(223);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
				enterOuterAlt(_localctx, 1);
				{
				setState(214);
				match(T__4);
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(215);
				expr_column();
				setState(220);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__2) {
					{
					{
					setState(216);
					match(T__2);
					setState(217);
					expr_column();
					}
					}
					setState(222);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Asign_clauseContext extends ParserRuleContext {
		public List<Expr_columnContext> expr_column() {
			return getRuleContexts(Expr_columnContext.class);
		}
		public Expr_columnContext expr_column(int i) {
			return getRuleContext(Expr_columnContext.class,i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public Asign_clauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_asign_clause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).enterAsign_clause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).exitAsign_clause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitAsign_clause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Asign_clauseContext asign_clause() throws RecognitionException {
		Asign_clauseContext _localctx = new Asign_clauseContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_asign_clause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(225);
			expr_column();
			setState(226);
			match(T__5);
			setState(227);
			expr();
			setState(235);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(228);
				match(T__2);
				setState(229);
				expr_column();
				setState(230);
				match(T__5);
				setState(231);
				expr();
				}
				}
				setState(237);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Db_nameContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(sqlParser.IDENTIFIER, 0); }
		public Db_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_db_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).enterDb_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).exitDb_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitDb_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Db_nameContext db_name() throws RecognitionException {
		Db_nameContext _localctx = new Db_nameContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_db_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(238);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Table_nameContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(sqlParser.IDENTIFIER, 0); }
		public Table_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).enterTable_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).exitTable_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitTable_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Table_nameContext table_name() throws RecognitionException {
		Table_nameContext _localctx = new Table_nameContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_table_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(240);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Attr_nameContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(sqlParser.IDENTIFIER, 0); }
		public Attr_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attr_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).enterAttr_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).exitAttr_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitAttr_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Attr_nameContext attr_name() throws RecognitionException {
		Attr_nameContext _localctx = new Attr_nameContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_attr_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(242);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Type_nameContext extends ParserRuleContext {
		public TerminalNode K_INT() { return getToken(sqlParser.K_INT, 0); }
		public TerminalNode K_LONG() { return getToken(sqlParser.K_LONG, 0); }
		public TerminalNode K_FLOAT() { return getToken(sqlParser.K_FLOAT, 0); }
		public TerminalNode K_DOUBLE() { return getToken(sqlParser.K_DOUBLE, 0); }
		public TerminalNode K_STRING() { return getToken(sqlParser.K_STRING, 0); }
		public TerminalNode INTEGER_LITERAL() { return getToken(sqlParser.INTEGER_LITERAL, 0); }
		public Type_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).enterType_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).exitType_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitType_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Type_nameContext type_name() throws RecognitionException {
		Type_nameContext _localctx = new Type_nameContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_type_name);
		try {
			setState(252);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case K_INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(244);
				match(K_INT);
				}
				break;
			case K_LONG:
				enterOuterAlt(_localctx, 2);
				{
				setState(245);
				match(K_LONG);
				}
				break;
			case K_FLOAT:
				enterOuterAlt(_localctx, 3);
				{
				setState(246);
				match(K_FLOAT);
				}
				break;
			case K_DOUBLE:
				enterOuterAlt(_localctx, 4);
				{
				setState(247);
				match(K_DOUBLE);
				}
				break;
			case K_STRING:
				enterOuterAlt(_localctx, 5);
				{
				setState(248);
				match(K_STRING);
				setState(249);
				match(T__1);
				setState(250);
				match(INTEGER_LITERAL);
				setState(251);
				match(T__3);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public Numeric_exprContext numeric_expr() {
			return getRuleContext(Numeric_exprContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).exitExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_expr);
		try {
			int _alt;
			setState(262);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
			case T__9:
			case T__10:
			case T__12:
			case K_NOT:
			case IDENTIFIER:
			case INTEGER_LITERAL:
			case FLOAT_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(254);
				numeric_expr(0);
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 2);
				{
				setState(255);
				match(T__6);
				setState(257); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(256);
						matchWildcard();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(259); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(261);
				match(T__6);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Numeric_exprContext extends ParserRuleContext {
		public Numeric_valueContext numeric_value() {
			return getRuleContext(Numeric_valueContext.class,0);
		}
		public Expr_columnContext expr_column() {
			return getRuleContext(Expr_columnContext.class,0);
		}
		public Unary_operatorContext unary_operator() {
			return getRuleContext(Unary_operatorContext.class,0);
		}
		public List<Numeric_exprContext> numeric_expr() {
			return getRuleContexts(Numeric_exprContext.class);
		}
		public Numeric_exprContext numeric_expr(int i) {
			return getRuleContext(Numeric_exprContext.class,i);
		}
		public Numeric_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numeric_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).enterNumeric_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).exitNumeric_expr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitNumeric_expr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Numeric_exprContext numeric_expr() throws RecognitionException {
		return numeric_expr(0);
	}

	private Numeric_exprContext numeric_expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Numeric_exprContext _localctx = new Numeric_exprContext(_ctx, _parentState);
		Numeric_exprContext _prevctx = _localctx;
		int _startState = 40;
		enterRecursionRule(_localctx, 40, RULE_numeric_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(274);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INTEGER_LITERAL:
			case FLOAT_LITERAL:
				{
				setState(265);
				numeric_value();
				}
				break;
			case IDENTIFIER:
				{
				setState(266);
				expr_column();
				}
				break;
			case T__9:
			case T__10:
			case T__12:
			case K_NOT:
				{
				setState(267);
				unary_operator();
				setState(268);
				numeric_expr(4);
				}
				break;
			case T__1:
				{
				setState(270);
				match(T__1);
				setState(271);
				numeric_expr(0);
				setState(272);
				match(T__3);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(284);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(282);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
					case 1:
						{
						_localctx = new Numeric_exprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_numeric_expr);
						setState(276);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(277);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__7) | (1L << T__8))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(278);
						numeric_expr(4);
						}
						break;
					case 2:
						{
						_localctx = new Numeric_exprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_numeric_expr);
						setState(279);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(280);
						_la = _input.LA(1);
						if ( !(_la==T__9 || _la==T__10) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(281);
						numeric_expr(3);
						}
						break;
					}
					} 
				}
				setState(286);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Expr_columnContext extends ParserRuleContext {
		public Attr_nameContext attr_name() {
			return getRuleContext(Attr_nameContext.class,0);
		}
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public Expr_columnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr_column; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).enterExpr_column(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).exitExpr_column(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitExpr_column(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expr_columnContext expr_column() throws RecognitionException {
		Expr_columnContext _localctx = new Expr_columnContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_expr_column);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(290);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				{
				setState(287);
				table_name();
				setState(288);
				match(T__11);
				}
				break;
			}
			setState(292);
			attr_name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Unary_operatorContext extends ParserRuleContext {
		public TerminalNode K_NOT() { return getToken(sqlParser.K_NOT, 0); }
		public Unary_operatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unary_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).enterUnary_operator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).exitUnary_operator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitUnary_operator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Unary_operatorContext unary_operator() throws RecognitionException {
		Unary_operatorContext _localctx = new Unary_operatorContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_unary_operator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(294);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__9) | (1L << T__10) | (1L << T__12) | (1L << K_NOT))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Pred_exprContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<Pred_exprContext> pred_expr() {
			return getRuleContexts(Pred_exprContext.class);
		}
		public Pred_exprContext pred_expr(int i) {
			return getRuleContext(Pred_exprContext.class,i);
		}
		public TerminalNode K_AND() { return getToken(sqlParser.K_AND, 0); }
		public TerminalNode K_OR() { return getToken(sqlParser.K_OR, 0); }
		public Pred_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pred_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).enterPred_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).exitPred_expr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitPred_expr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Pred_exprContext pred_expr() throws RecognitionException {
		return pred_expr(0);
	}

	private Pred_exprContext pred_expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Pred_exprContext _localctx = new Pred_exprContext(_ctx, _parentState);
		Pred_exprContext _prevctx = _localctx;
		int _startState = 46;
		enterRecursionRule(_localctx, 46, RULE_pred_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(297);
			expr();
			setState(298);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(299);
			expr();
			}
			_ctx.stop = _input.LT(-1);
			setState(306);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Pred_exprContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_pred_expr);
					setState(301);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(302);
					_la = _input.LA(1);
					if ( !(_la==K_AND || _la==K_OR) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(303);
					pred_expr(2);
					}
					} 
				}
				setState(308);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Numeric_valueContext extends ParserRuleContext {
		public TerminalNode INTEGER_LITERAL() { return getToken(sqlParser.INTEGER_LITERAL, 0); }
		public TerminalNode FLOAT_LITERAL() { return getToken(sqlParser.FLOAT_LITERAL, 0); }
		public Numeric_valueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numeric_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).enterNumeric_value(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).exitNumeric_value(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitNumeric_value(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Numeric_valueContext numeric_value() throws RecognitionException {
		Numeric_valueContext _localctx = new Numeric_valueContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_numeric_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(309);
			_la = _input.LA(1);
			if ( !(_la==INTEGER_LITERAL || _la==FLOAT_LITERAL) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 20:
			return numeric_expr_sempred((Numeric_exprContext)_localctx, predIndex);
		case 23:
			return pred_expr_sempred((Pred_exprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean numeric_expr_sempred(Numeric_exprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 3);
		case 1:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean pred_expr_sempred(Pred_exprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\39\u013a\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\3\2\3\2\7\2\67\n\2\f\2\16\2:\13\2\3\2\3\2\3\3\3\3\3\3\3\4\3"+
		"\4\6\4C\n\4\r\4\16\4D\3\4\7\4H\n\4\f\4\16\4K\13\4\3\4\3\4\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\5\5X\n\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\5\7f\n\7\3\7\3\7\3\7\3\7\3\7\5\7m\n\7\7\7o\n\7\f\7\16\7r"+
		"\13\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7{\n\7\3\7\3\7\3\b\3\b\3\b\3\b\5\b"+
		"\u0083\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\7\t\u008c\n\t\f\t\16\t\u008f\13"+
		"\t\3\t\3\t\5\t\u0093\n\t\3\t\3\t\3\t\3\t\3\t\7\t\u009a\n\t\f\t\16\t\u009d"+
		"\13\t\3\t\3\t\3\t\3\t\3\t\3\t\7\t\u00a5\n\t\f\t\16\t\u00a8\13\t\3\t\3"+
		"\t\7\t\u00ac\n\t\f\t\16\t\u00af\13\t\3\n\3\n\3\n\3\n\3\n\5\n\u00b6\n\n"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u00be\n\13\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\5\f\u00c9\n\f\3\f\3\f\5\f\u00cd\n\f\3\r\3\r\3\r\3\16\3\16"+
		"\3\16\3\16\3\16\5\16\u00d7\n\16\3\17\3\17\3\17\3\17\7\17\u00dd\n\17\f"+
		"\17\16\17\u00e0\13\17\5\17\u00e2\n\17\3\20\3\20\3\20\3\20\3\20\3\20\3"+
		"\20\3\20\7\20\u00ec\n\20\f\20\16\20\u00ef\13\20\3\21\3\21\3\22\3\22\3"+
		"\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\5\24\u00ff\n\24\3\25"+
		"\3\25\3\25\6\25\u0104\n\25\r\25\16\25\u0105\3\25\5\25\u0109\n\25\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\5\26\u0115\n\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\7\26\u011d\n\26\f\26\16\26\u0120\13\26\3\27\3\27"+
		"\3\27\5\27\u0125\n\27\3\27\3\27\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31"+
		"\3\31\3\31\7\31\u0133\n\31\f\31\16\31\u0136\13\31\3\32\3\32\3\32\2\4*"+
		"\60\33\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\2\n\4\2\31"+
		"\31\33\33\4\2\32\32\34\34\4\2\7\7\n\13\3\2\f\r\5\2\f\r\17\17\35\35\3\2"+
		"\20\26\3\2/\60\3\2\678\2\u014b\28\3\2\2\2\4=\3\2\2\2\6@\3\2\2\2\bW\3\2"+
		"\2\2\nY\3\2\2\2\f]\3\2\2\2\16~\3\2\2\2\20\u0084\3\2\2\2\22\u00b0\3\2\2"+
		"\2\24\u00b7\3\2\2\2\26\u00bf\3\2\2\2\30\u00ce\3\2\2\2\32\u00d6\3\2\2\2"+
		"\34\u00e1\3\2\2\2\36\u00e3\3\2\2\2 \u00f0\3\2\2\2\"\u00f2\3\2\2\2$\u00f4"+
		"\3\2\2\2&\u00fe\3\2\2\2(\u0108\3\2\2\2*\u0114\3\2\2\2,\u0124\3\2\2\2."+
		"\u0128\3\2\2\2\60\u012a\3\2\2\2\62\u0137\3\2\2\2\64\67\5\6\4\2\65\67\5"+
		"\4\3\2\66\64\3\2\2\2\66\65\3\2\2\2\67:\3\2\2\28\66\3\2\2\289\3\2\2\29"+
		";\3\2\2\2:8\3\2\2\2;<\7\2\2\3<\3\3\2\2\2=>\79\2\2>?\b\3\1\2?\5\3\2\2\2"+
		"@I\5\b\5\2AC\7\3\2\2BA\3\2\2\2CD\3\2\2\2DB\3\2\2\2DE\3\2\2\2EF\3\2\2\2"+
		"FH\5\b\5\2GB\3\2\2\2HK\3\2\2\2IG\3\2\2\2IJ\3\2\2\2JL\3\2\2\2KI\3\2\2\2"+
		"LM\7\3\2\2M\7\3\2\2\2NX\5\n\6\2OX\5\f\7\2PX\5\16\b\2QX\5\20\t\2RX\5\22"+
		"\n\2SX\5\24\13\2TX\5\26\f\2UX\5\30\r\2VX\5\32\16\2WN\3\2\2\2WO\3\2\2\2"+
		"WP\3\2\2\2WQ\3\2\2\2WR\3\2\2\2WS\3\2\2\2WT\3\2\2\2WU\3\2\2\2WV\3\2\2\2"+
		"X\t\3\2\2\2YZ\7\30\2\2Z[\7\31\2\2[\\\5 \21\2\\\13\3\2\2\2]^\7\30\2\2^"+
		"_\7\33\2\2_`\5\"\22\2`a\7\4\2\2ab\5$\23\2be\5&\24\2cd\7\35\2\2df\7\36"+
		"\2\2ec\3\2\2\2ef\3\2\2\2fp\3\2\2\2gh\7\5\2\2hi\5$\23\2il\5&\24\2jk\7\35"+
		"\2\2km\7\36\2\2lj\3\2\2\2lm\3\2\2\2mo\3\2\2\2ng\3\2\2\2or\3\2\2\2pn\3"+
		"\2\2\2pq\3\2\2\2qz\3\2\2\2rp\3\2\2\2st\7\5\2\2tu\7\37\2\2uv\7 \2\2vw\7"+
		"\4\2\2wx\5$\23\2xy\7\6\2\2y{\3\2\2\2zs\3\2\2\2z{\3\2\2\2{|\3\2\2\2|}\7"+
		"\6\2\2}\r\3\2\2\2~\177\7!\2\2\177\u0082\t\2\2\2\u0080\u0083\5\"\22\2\u0081"+
		"\u0083\5 \21\2\u0082\u0080\3\2\2\2\u0082\u0081\3\2\2\2\u0083\17\3\2\2"+
		"\2\u0084\u0085\7\"\2\2\u0085\u0086\7#\2\2\u0086\u0092\5\"\22\2\u0087\u0088"+
		"\7\4\2\2\u0088\u008d\5$\23\2\u0089\u008a\7\5\2\2\u008a\u008c\5$\23\2\u008b"+
		"\u0089\3\2\2\2\u008c\u008f\3\2\2\2\u008d\u008b\3\2\2\2\u008d\u008e\3\2"+
		"\2\2\u008e\u0090\3\2\2\2\u008f\u008d\3\2\2\2\u0090\u0091\7\6\2\2\u0091"+
		"\u0093\3\2\2\2\u0092\u0087\3\2\2\2\u0092\u0093\3\2\2\2\u0093\u0094\3\2"+
		"\2\2\u0094\u0095\7$\2\2\u0095\u0096\7\4\2\2\u0096\u009b\5(\25\2\u0097"+
		"\u0098\7\5\2\2\u0098\u009a\5(\25\2\u0099\u0097\3\2\2\2\u009a\u009d\3\2"+
		"\2\2\u009b\u0099\3\2\2\2\u009b\u009c\3\2\2\2\u009c\u009e\3\2\2\2\u009d"+
		"\u009b\3\2\2\2\u009e\u00ad\7\6\2\2\u009f\u00a0\7\5\2\2\u00a0\u00a1\7\4"+
		"\2\2\u00a1\u00a6\5(\25\2\u00a2\u00a3\7\5\2\2\u00a3\u00a5\5(\25\2\u00a4"+
		"\u00a2\3\2\2\2\u00a5\u00a8\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a6\u00a7\3\2"+
		"\2\2\u00a7\u00a9\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a9\u00aa\7\6\2\2\u00aa"+
		"\u00ac\3\2\2\2\u00ab\u009f\3\2\2\2\u00ac\u00af\3\2\2\2\u00ad\u00ab\3\2"+
		"\2\2\u00ad\u00ae\3\2\2\2\u00ae\21\3\2\2\2\u00af\u00ad\3\2\2\2\u00b0\u00b1"+
		"\7%\2\2\u00b1\u00b2\7&\2\2\u00b2\u00b5\5\"\22\2\u00b3\u00b4\7\'\2\2\u00b4"+
		"\u00b6\5\60\31\2\u00b5\u00b3\3\2\2\2\u00b5\u00b6\3\2\2\2\u00b6\23\3\2"+
		"\2\2\u00b7\u00b8\7(\2\2\u00b8\u00b9\5\"\22\2\u00b9\u00ba\7)\2\2\u00ba"+
		"\u00bd\5\36\20\2\u00bb\u00bc\7\'\2\2\u00bc\u00be\5\60\31\2\u00bd\u00bb"+
		"\3\2\2\2\u00bd\u00be\3\2\2\2\u00be\25\3\2\2\2\u00bf\u00c0\7*\2\2\u00c0"+
		"\u00c1\5\34\17\2\u00c1\u00c2\7&\2\2\u00c2\u00c8\5\"\22\2\u00c3\u00c4\7"+
		"+\2\2\u00c4\u00c5\5\"\22\2\u00c5\u00c6\7,\2\2\u00c6\u00c7\5\60\31\2\u00c7"+
		"\u00c9\3\2\2\2\u00c8\u00c3\3\2\2\2\u00c8\u00c9\3\2\2\2\u00c9\u00cc\3\2"+
		"\2\2\u00ca\u00cb\7\'\2\2\u00cb\u00cd\5\60\31\2\u00cc\u00ca\3\2\2\2\u00cc"+
		"\u00cd\3\2\2\2\u00cd\27\3\2\2\2\u00ce\u00cf\7-\2\2\u00cf\u00d0\5 \21\2"+
		"\u00d0\31\3\2\2\2\u00d1\u00d2\7.\2\2\u00d2\u00d7\t\3\2\2\u00d3\u00d4\7"+
		".\2\2\u00d4\u00d5\7\33\2\2\u00d5\u00d7\5\"\22\2\u00d6\u00d1\3\2\2\2\u00d6"+
		"\u00d3\3\2\2\2\u00d7\33\3\2\2\2\u00d8\u00e2\7\7\2\2\u00d9\u00de\5,\27"+
		"\2\u00da\u00db\7\5\2\2\u00db\u00dd\5,\27\2\u00dc\u00da\3\2\2\2\u00dd\u00e0"+
		"\3\2\2\2\u00de\u00dc\3\2\2\2\u00de\u00df\3\2\2\2\u00df\u00e2\3\2\2\2\u00e0"+
		"\u00de\3\2\2\2\u00e1\u00d8\3\2\2\2\u00e1\u00d9\3\2\2\2\u00e2\35\3\2\2"+
		"\2\u00e3\u00e4\5,\27\2\u00e4\u00e5\7\b\2\2\u00e5\u00ed\5(\25\2\u00e6\u00e7"+
		"\7\5\2\2\u00e7\u00e8\5,\27\2\u00e8\u00e9\7\b\2\2\u00e9\u00ea\5(\25\2\u00ea"+
		"\u00ec\3\2\2\2\u00eb\u00e6\3\2\2\2\u00ec\u00ef\3\2\2\2\u00ed\u00eb\3\2"+
		"\2\2\u00ed\u00ee\3\2\2\2\u00ee\37\3\2\2\2\u00ef\u00ed\3\2\2\2\u00f0\u00f1"+
		"\7\66\2\2\u00f1!\3\2\2\2\u00f2\u00f3\7\66\2\2\u00f3#\3\2\2\2\u00f4\u00f5"+
		"\7\66\2\2\u00f5%\3\2\2\2\u00f6\u00ff\7\61\2\2\u00f7\u00ff\7\62\2\2\u00f8"+
		"\u00ff\7\63\2\2\u00f9\u00ff\7\64\2\2\u00fa\u00fb\7\65\2\2\u00fb\u00fc"+
		"\7\4\2\2\u00fc\u00fd\7\67\2\2\u00fd\u00ff\7\6\2\2\u00fe\u00f6\3\2\2\2"+
		"\u00fe\u00f7\3\2\2\2\u00fe\u00f8\3\2\2\2\u00fe\u00f9\3\2\2\2\u00fe\u00fa"+
		"\3\2\2\2\u00ff\'\3\2\2\2\u0100\u0109\5*\26\2\u0101\u0103\7\t\2\2\u0102"+
		"\u0104\13\2\2\2\u0103\u0102\3\2\2\2\u0104\u0105\3\2\2\2\u0105\u0103\3"+
		"\2\2\2\u0105\u0106\3\2\2\2\u0106\u0107\3\2\2\2\u0107\u0109\7\t\2\2\u0108"+
		"\u0100\3\2\2\2\u0108\u0101\3\2\2\2\u0109)\3\2\2\2\u010a\u010b\b\26\1\2"+
		"\u010b\u0115\5\62\32\2\u010c\u0115\5,\27\2\u010d\u010e\5.\30\2\u010e\u010f"+
		"\5*\26\6\u010f\u0115\3\2\2\2\u0110\u0111\7\4\2\2\u0111\u0112\5*\26\2\u0112"+
		"\u0113\7\6\2\2\u0113\u0115\3\2\2\2\u0114\u010a\3\2\2\2\u0114\u010c\3\2"+
		"\2\2\u0114\u010d\3\2\2\2\u0114\u0110\3\2\2\2\u0115\u011e\3\2\2\2\u0116"+
		"\u0117\f\5\2\2\u0117\u0118\t\4\2\2\u0118\u011d\5*\26\6\u0119\u011a\f\4"+
		"\2\2\u011a\u011b\t\5\2\2\u011b\u011d\5*\26\5\u011c\u0116\3\2\2\2\u011c"+
		"\u0119\3\2\2\2\u011d\u0120\3\2\2\2\u011e\u011c\3\2\2\2\u011e\u011f\3\2"+
		"\2\2\u011f+\3\2\2\2\u0120\u011e\3\2\2\2\u0121\u0122\5\"\22\2\u0122\u0123"+
		"\7\16\2\2\u0123\u0125\3\2\2\2\u0124\u0121\3\2\2\2\u0124\u0125\3\2\2\2"+
		"\u0125\u0126\3\2\2\2\u0126\u0127\5$\23\2\u0127-\3\2\2\2\u0128\u0129\t"+
		"\6\2\2\u0129/\3\2\2\2\u012a\u012b\b\31\1\2\u012b\u012c\5(\25\2\u012c\u012d"+
		"\t\7\2\2\u012d\u012e\5(\25\2\u012e\u0134\3\2\2\2\u012f\u0130\f\3\2\2\u0130"+
		"\u0131\t\b\2\2\u0131\u0133\5\60\31\4\u0132\u012f\3\2\2\2\u0133\u0136\3"+
		"\2\2\2\u0134\u0132\3\2\2\2\u0134\u0135\3\2\2\2\u0135\61\3\2\2\2\u0136"+
		"\u0134\3\2\2\2\u0137\u0138\t\t\2\2\u0138\63\3\2\2\2!\668DIWelpz\u0082"+
		"\u008d\u0092\u009b\u00a6\u00ad\u00b5\u00bd\u00c8\u00cc\u00d6\u00de\u00e1"+
		"\u00ed\u00fe\u0105\u0108\u0114\u011c\u011e\u0124\u0134";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}