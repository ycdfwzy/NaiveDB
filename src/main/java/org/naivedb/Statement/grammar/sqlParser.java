// Generated from D:/gitRepo/NaiveDB/src/main/java/org/naivedb/Statement/grammar\sql.g4 by ANTLR 4.7.2
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
		T__17=18, T__18=19, SPACES=20, TEXT_STRING=21, K_CREATE=22, K_DATABASE=23, 
		K_DATABASES=24, K_TABLE=25, K_TABLES=26, K_NOT=27, K_NULL=28, K_PRIMARY=29, 
		K_KEY=30, K_DROP=31, K_INSERT=32, K_INTO=33, K_VALUES=34, K_DELETE=35, 
		K_FROM=36, K_WHERE=37, K_UPDATE=38, K_SET=39, K_SELECT=40, K_NATURAL=41, 
		K_LEFT=42, K_RIGHT=43, K_FULL=44, K_OUTER=45, K_INNER=46, K_JOIN=47, K_ON=48, 
		K_USE=49, K_SHOW=50, K_AND=51, K_OR=52, K_INT=53, K_LONG=54, K_FLOAT=55, 
		K_DOUBLE=56, K_STRING=57, IDENTIFIER=58, INTEGER_LITERAL=59, FLOAT_LITERAL=60, 
		UNEXPECTED_CHAR=61;
	public static final int
		RULE_parse = 0, RULE_error = 1, RULE_sql_stmt_list = 2, RULE_sql_stmt = 3, 
		RULE_create_db_stmt = 4, RULE_create_table_stmt = 5, RULE_drop_stmt = 6, 
		RULE_insert_stmt = 7, RULE_delete_stmt = 8, RULE_update_stmt = 9, RULE_select_stmt = 10, 
		RULE_use_stmt = 11, RULE_show_stmt = 12, RULE_select_elements = 13, RULE_asign_clause = 14, 
		RULE_join_range = 15, RULE_join_ranges = 16, RULE_natural_join = 17, RULE_join_on = 18, 
		RULE_outer_join = 19, RULE_inner_join = 20, RULE_single_range = 21, RULE_db_name = 22, 
		RULE_table_name = 23, RULE_attr_name = 24, RULE_type_name = 25, RULE_expr = 26, 
		RULE_numeric_expr = 27, RULE_expr_column = 28, RULE_unary_operator = 29, 
		RULE_pred_expr = 30, RULE_numeric_value = 31;
	private static String[] makeRuleNames() {
		return new String[] {
			"parse", "error", "sql_stmt_list", "sql_stmt", "create_db_stmt", "create_table_stmt", 
			"drop_stmt", "insert_stmt", "delete_stmt", "update_stmt", "select_stmt", 
			"use_stmt", "show_stmt", "select_elements", "asign_clause", "join_range", 
			"join_ranges", "natural_join", "join_on", "outer_join", "inner_join", 
			"single_range", "db_name", "table_name", "attr_name", "type_name", "expr", 
			"numeric_expr", "expr_column", "unary_operator", "pred_expr", "numeric_value"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'('", "','", "')'", "'*'", "'='", "'/'", "'%'", "'+'", 
			"'-'", "'.'", "'~'", "'<'", "'<='", "'>'", "'>='", "'<>'", "'=='", "'!='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, "SPACES", "TEXT_STRING", 
			"K_CREATE", "K_DATABASE", "K_DATABASES", "K_TABLE", "K_TABLES", "K_NOT", 
			"K_NULL", "K_PRIMARY", "K_KEY", "K_DROP", "K_INSERT", "K_INTO", "K_VALUES", 
			"K_DELETE", "K_FROM", "K_WHERE", "K_UPDATE", "K_SET", "K_SELECT", "K_NATURAL", 
			"K_LEFT", "K_RIGHT", "K_FULL", "K_OUTER", "K_INNER", "K_JOIN", "K_ON", 
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
			setState(68);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << K_CREATE) | (1L << K_DROP) | (1L << K_INSERT) | (1L << K_DELETE) | (1L << K_UPDATE) | (1L << K_SELECT) | (1L << K_USE) | (1L << K_SHOW) | (1L << UNEXPECTED_CHAR))) != 0)) {
				{
				setState(66);
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
					setState(64);
					sql_stmt_list();
					}
					break;
				case UNEXPECTED_CHAR:
					{
					setState(65);
					error();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(70);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(71);
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
			setState(73);
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
			setState(76);
			sql_stmt();
			setState(85);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(78); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(77);
						match(T__0);
						}
						}
						setState(80); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==T__0 );
					setState(82);
					sql_stmt();
					}
					} 
				}
				setState(87);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			}
			setState(88);
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
			setState(99);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(90);
				create_db_stmt();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(91);
				create_table_stmt();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(92);
				drop_stmt();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(93);
				insert_stmt();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(94);
				delete_stmt();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(95);
				update_stmt();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(96);
				select_stmt();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(97);
				use_stmt();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(98);
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
			setState(101);
			match(K_CREATE);
			setState(102);
			match(K_DATABASE);
			setState(103);
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
			setState(105);
			match(K_CREATE);
			setState(106);
			match(K_TABLE);
			setState(107);
			table_name();
			{
			setState(108);
			match(T__1);
			setState(109);
			attr_name();
			setState(110);
			type_name();
			setState(113);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_NOT) {
				{
				setState(111);
				match(K_NOT);
				setState(112);
				match(K_NULL);
				}
			}

			setState(124);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(115);
					match(T__2);
					setState(116);
					attr_name();
					setState(117);
					type_name();
					setState(120);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==K_NOT) {
						{
						setState(118);
						match(K_NOT);
						setState(119);
						match(K_NULL);
						}
					}

					}
					} 
				}
				setState(126);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			}
			setState(134);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(127);
				match(T__2);
				setState(128);
				match(K_PRIMARY);
				setState(129);
				match(K_KEY);
				setState(130);
				match(T__1);
				setState(131);
				attr_name();
				setState(132);
				match(T__3);
				}
			}

			setState(136);
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
			setState(138);
			match(K_DROP);
			setState(139);
			_la = _input.LA(1);
			if ( !(_la==K_DATABASE || _la==K_TABLE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(142);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				setState(140);
				table_name();
				}
				break;
			case 2:
				{
				setState(141);
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
			setState(144);
			match(K_INSERT);
			setState(145);
			match(K_INTO);
			setState(146);
			table_name();
			setState(158);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(147);
				match(T__1);
				setState(148);
				attr_name();
				setState(153);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__2) {
					{
					{
					setState(149);
					match(T__2);
					setState(150);
					attr_name();
					}
					}
					setState(155);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(156);
				match(T__3);
				}
			}

			{
			setState(160);
			match(K_VALUES);
			setState(161);
			match(T__1);
			setState(162);
			expr();
			setState(167);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(163);
				match(T__2);
				setState(164);
				expr();
				}
				}
				setState(169);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(170);
			match(T__3);
			setState(185);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(171);
				match(T__2);
				setState(172);
				match(T__1);
				setState(173);
				expr();
				setState(178);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__2) {
					{
					{
					setState(174);
					match(T__2);
					setState(175);
					expr();
					}
					}
					setState(180);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(181);
				match(T__3);
				}
				}
				setState(187);
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
			setState(188);
			match(K_DELETE);
			setState(189);
			match(K_FROM);
			setState(190);
			table_name();
			setState(193);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_WHERE) {
				{
				setState(191);
				match(K_WHERE);
				setState(192);
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
			setState(195);
			match(K_UPDATE);
			setState(196);
			table_name();
			setState(197);
			match(K_SET);
			setState(198);
			asign_clause();
			setState(201);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_WHERE) {
				{
				setState(199);
				match(K_WHERE);
				setState(200);
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
		public Join_rangeContext join_range() {
			return getRuleContext(Join_rangeContext.class,0);
		}
		public TerminalNode K_WHERE() { return getToken(sqlParser.K_WHERE, 0); }
		public Pred_exprContext pred_expr() {
			return getRuleContext(Pred_exprContext.class,0);
		}
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
			setState(203);
			match(K_SELECT);
			setState(204);
			select_elements();
			setState(205);
			match(K_FROM);
			setState(206);
			join_range();
			setState(209);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_WHERE) {
				{
				setState(207);
				match(K_WHERE);
				setState(208);
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
			setState(211);
			match(K_USE);
			setState(212);
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
		public TerminalNode K_DATABASE() { return getToken(sqlParser.K_DATABASE, 0); }
		public Db_nameContext db_name() {
			return getRuleContext(Db_nameContext.class,0);
		}
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
		try {
			setState(222);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(214);
				match(K_SHOW);
				setState(215);
				match(K_DATABASES);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(216);
				match(K_SHOW);
				setState(217);
				match(K_DATABASE);
				setState(218);
				db_name();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(219);
				match(K_SHOW);
				setState(220);
				match(K_TABLE);
				setState(221);
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
			setState(233);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
				enterOuterAlt(_localctx, 1);
				{
				setState(224);
				match(T__4);
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(225);
				expr_column();
				setState(230);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__2) {
					{
					{
					setState(226);
					match(T__2);
					setState(227);
					expr_column();
					}
					}
					setState(232);
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
			setState(235);
			expr_column();
			setState(236);
			match(T__5);
			setState(237);
			expr();
			setState(245);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(238);
				match(T__2);
				setState(239);
				expr_column();
				setState(240);
				match(T__5);
				setState(241);
				expr();
				}
				}
				setState(247);
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

	public static class Join_rangeContext extends ParserRuleContext {
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public Join_rangesContext join_ranges() {
			return getRuleContext(Join_rangesContext.class,0);
		}
		public Join_rangeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_join_range; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).enterJoin_range(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).exitJoin_range(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitJoin_range(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Join_rangeContext join_range() throws RecognitionException {
		Join_rangeContext _localctx = new Join_rangeContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_join_range);
		try {
			setState(250);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(248);
				table_name();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(249);
				join_ranges();
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

	public static class Join_rangesContext extends ParserRuleContext {
		public Single_rangeContext single_range() {
			return getRuleContext(Single_rangeContext.class,0);
		}
		public List<Natural_joinContext> natural_join() {
			return getRuleContexts(Natural_joinContext.class);
		}
		public Natural_joinContext natural_join(int i) {
			return getRuleContext(Natural_joinContext.class,i);
		}
		public List<Join_onContext> join_on() {
			return getRuleContexts(Join_onContext.class);
		}
		public Join_onContext join_on(int i) {
			return getRuleContext(Join_onContext.class,i);
		}
		public Join_rangesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_join_ranges; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).enterJoin_ranges(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).exitJoin_ranges(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitJoin_ranges(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Join_rangesContext join_ranges() throws RecognitionException {
		Join_rangesContext _localctx = new Join_rangesContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_join_ranges);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(252);
			single_range();
			setState(255); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(255);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case K_NATURAL:
					{
					setState(253);
					natural_join();
					}
					break;
				case K_LEFT:
				case K_RIGHT:
				case K_FULL:
				case K_INNER:
				case K_JOIN:
					{
					setState(254);
					join_on();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(257); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << K_NATURAL) | (1L << K_LEFT) | (1L << K_RIGHT) | (1L << K_FULL) | (1L << K_INNER) | (1L << K_JOIN))) != 0) );
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

	public static class Natural_joinContext extends ParserRuleContext {
		public TerminalNode K_NATURAL() { return getToken(sqlParser.K_NATURAL, 0); }
		public Outer_joinContext outer_join() {
			return getRuleContext(Outer_joinContext.class,0);
		}
		public Inner_joinContext inner_join() {
			return getRuleContext(Inner_joinContext.class,0);
		}
		public Natural_joinContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_natural_join; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).enterNatural_join(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).exitNatural_join(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitNatural_join(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Natural_joinContext natural_join() throws RecognitionException {
		Natural_joinContext _localctx = new Natural_joinContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_natural_join);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(259);
			match(K_NATURAL);
			setState(262);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case K_LEFT:
			case K_RIGHT:
			case K_FULL:
				{
				setState(260);
				outer_join();
				}
				break;
			case K_INNER:
			case K_JOIN:
				{
				setState(261);
				inner_join();
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class Join_onContext extends ParserRuleContext {
		public TerminalNode K_ON() { return getToken(sqlParser.K_ON, 0); }
		public Pred_exprContext pred_expr() {
			return getRuleContext(Pred_exprContext.class,0);
		}
		public Outer_joinContext outer_join() {
			return getRuleContext(Outer_joinContext.class,0);
		}
		public Inner_joinContext inner_join() {
			return getRuleContext(Inner_joinContext.class,0);
		}
		public Join_onContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_join_on; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).enterJoin_on(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).exitJoin_on(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitJoin_on(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Join_onContext join_on() throws RecognitionException {
		Join_onContext _localctx = new Join_onContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_join_on);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(266);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case K_LEFT:
			case K_RIGHT:
			case K_FULL:
				{
				setState(264);
				outer_join();
				}
				break;
			case K_INNER:
			case K_JOIN:
				{
				setState(265);
				inner_join();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(268);
			match(K_ON);
			setState(269);
			pred_expr(0);
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

	public static class Outer_joinContext extends ParserRuleContext {
		public TerminalNode K_OUTER() { return getToken(sqlParser.K_OUTER, 0); }
		public TerminalNode K_JOIN() { return getToken(sqlParser.K_JOIN, 0); }
		public Single_rangeContext single_range() {
			return getRuleContext(Single_rangeContext.class,0);
		}
		public TerminalNode K_LEFT() { return getToken(sqlParser.K_LEFT, 0); }
		public TerminalNode K_RIGHT() { return getToken(sqlParser.K_RIGHT, 0); }
		public TerminalNode K_FULL() { return getToken(sqlParser.K_FULL, 0); }
		public Outer_joinContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_outer_join; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).enterOuter_join(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).exitOuter_join(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitOuter_join(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Outer_joinContext outer_join() throws RecognitionException {
		Outer_joinContext _localctx = new Outer_joinContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_outer_join);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(271);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << K_LEFT) | (1L << K_RIGHT) | (1L << K_FULL))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(272);
			match(K_OUTER);
			setState(273);
			match(K_JOIN);
			setState(274);
			single_range();
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

	public static class Inner_joinContext extends ParserRuleContext {
		public TerminalNode K_JOIN() { return getToken(sqlParser.K_JOIN, 0); }
		public Single_rangeContext single_range() {
			return getRuleContext(Single_rangeContext.class,0);
		}
		public TerminalNode K_INNER() { return getToken(sqlParser.K_INNER, 0); }
		public Inner_joinContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inner_join; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).enterInner_join(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).exitInner_join(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitInner_join(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Inner_joinContext inner_join() throws RecognitionException {
		Inner_joinContext _localctx = new Inner_joinContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_inner_join);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(277);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_INNER) {
				{
				setState(276);
				match(K_INNER);
				}
			}

			setState(279);
			match(K_JOIN);
			setState(280);
			single_range();
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

	public static class Single_rangeContext extends ParserRuleContext {
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public Join_rangeContext join_range() {
			return getRuleContext(Join_rangeContext.class,0);
		}
		public Single_rangeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_single_range; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).enterSingle_range(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).exitSingle_range(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitSingle_range(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Single_rangeContext single_range() throws RecognitionException {
		Single_rangeContext _localctx = new Single_rangeContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_single_range);
		try {
			setState(287);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(282);
				table_name();
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 2);
				{
				setState(283);
				match(T__1);
				setState(284);
				join_range();
				setState(285);
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
		enterRule(_localctx, 44, RULE_db_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(289);
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
		enterRule(_localctx, 46, RULE_table_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(291);
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
		enterRule(_localctx, 48, RULE_attr_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(293);
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
		enterRule(_localctx, 50, RULE_type_name);
		try {
			setState(303);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case K_INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(295);
				match(K_INT);
				}
				break;
			case K_LONG:
				enterOuterAlt(_localctx, 2);
				{
				setState(296);
				match(K_LONG);
				}
				break;
			case K_FLOAT:
				enterOuterAlt(_localctx, 3);
				{
				setState(297);
				match(K_FLOAT);
				}
				break;
			case K_DOUBLE:
				enterOuterAlt(_localctx, 4);
				{
				setState(298);
				match(K_DOUBLE);
				}
				break;
			case K_STRING:
				enterOuterAlt(_localctx, 5);
				{
				setState(299);
				match(K_STRING);
				setState(300);
				match(T__1);
				setState(301);
				match(INTEGER_LITERAL);
				setState(302);
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
		public TerminalNode TEXT_STRING() { return getToken(sqlParser.TEXT_STRING, 0); }
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
		enterRule(_localctx, 52, RULE_expr);
		try {
			setState(307);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
			case T__8:
			case T__9:
			case T__11:
			case K_NOT:
			case IDENTIFIER:
			case INTEGER_LITERAL:
			case FLOAT_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(305);
				numeric_expr(0);
				}
				break;
			case TEXT_STRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(306);
				match(TEXT_STRING);
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
		int _startState = 54;
		enterRecursionRule(_localctx, 54, RULE_numeric_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(319);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INTEGER_LITERAL:
			case FLOAT_LITERAL:
				{
				setState(310);
				numeric_value();
				}
				break;
			case IDENTIFIER:
				{
				setState(311);
				expr_column();
				}
				break;
			case T__8:
			case T__9:
			case T__11:
			case K_NOT:
				{
				setState(312);
				unary_operator();
				setState(313);
				numeric_expr(4);
				}
				break;
			case T__1:
				{
				setState(315);
				match(T__1);
				setState(316);
				numeric_expr(0);
				setState(317);
				match(T__3);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(329);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(327);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
					case 1:
						{
						_localctx = new Numeric_exprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_numeric_expr);
						setState(321);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(322);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__6) | (1L << T__7))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(323);
						numeric_expr(4);
						}
						break;
					case 2:
						{
						_localctx = new Numeric_exprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_numeric_expr);
						setState(324);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(325);
						_la = _input.LA(1);
						if ( !(_la==T__8 || _la==T__9) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(326);
						numeric_expr(3);
						}
						break;
					}
					} 
				}
				setState(331);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
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
		enterRule(_localctx, 56, RULE_expr_column);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(335);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
			case 1:
				{
				setState(332);
				table_name();
				setState(333);
				match(T__10);
				}
				break;
			}
			setState(337);
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
		enterRule(_localctx, 58, RULE_unary_operator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(339);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__8) | (1L << T__9) | (1L << T__11) | (1L << K_NOT))) != 0)) ) {
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
		int _startState = 60;
		enterRecursionRule(_localctx, 60, RULE_pred_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(342);
			expr();
			setState(343);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(344);
			expr();
			}
			_ctx.stop = _input.LT(-1);
			setState(351);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,35,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Pred_exprContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_pred_expr);
					setState(346);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(347);
					_la = _input.LA(1);
					if ( !(_la==K_AND || _la==K_OR) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(348);
					pred_expr(2);
					}
					} 
				}
				setState(353);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,35,_ctx);
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
		enterRule(_localctx, 62, RULE_numeric_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(354);
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
		case 27:
			return numeric_expr_sempred((Numeric_exprContext)_localctx, predIndex);
		case 30:
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3?\u0167\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\3\2\3\2\7\2E\n\2\f\2\16\2H\13\2\3\2\3\2\3\3\3\3\3\3\3\4\3\4\6\4Q\n"+
		"\4\r\4\16\4R\3\4\7\4V\n\4\f\4\16\4Y\13\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\5\5f\n\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\5\7t\n\7\3\7\3\7\3\7\3\7\3\7\5\7{\n\7\7\7}\n\7\f\7\16\7\u0080\13\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7\u0089\n\7\3\7\3\7\3\b\3\b\3\b\3\b\5\b"+
		"\u0091\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\7\t\u009a\n\t\f\t\16\t\u009d\13"+
		"\t\3\t\3\t\5\t\u00a1\n\t\3\t\3\t\3\t\3\t\3\t\7\t\u00a8\n\t\f\t\16\t\u00ab"+
		"\13\t\3\t\3\t\3\t\3\t\3\t\3\t\7\t\u00b3\n\t\f\t\16\t\u00b6\13\t\3\t\3"+
		"\t\7\t\u00ba\n\t\f\t\16\t\u00bd\13\t\3\n\3\n\3\n\3\n\3\n\5\n\u00c4\n\n"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u00cc\n\13\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\5\f\u00d4\n\f\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16"+
		"\u00e1\n\16\3\17\3\17\3\17\3\17\7\17\u00e7\n\17\f\17\16\17\u00ea\13\17"+
		"\5\17\u00ec\n\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\7\20\u00f6\n"+
		"\20\f\20\16\20\u00f9\13\20\3\21\3\21\5\21\u00fd\n\21\3\22\3\22\3\22\6"+
		"\22\u0102\n\22\r\22\16\22\u0103\3\23\3\23\3\23\5\23\u0109\n\23\3\24\3"+
		"\24\5\24\u010d\n\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\26\5\26"+
		"\u0118\n\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\5\27\u0122\n\27\3"+
		"\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\5"+
		"\33\u0132\n\33\3\34\3\34\5\34\u0136\n\34\3\35\3\35\3\35\3\35\3\35\3\35"+
		"\3\35\3\35\3\35\3\35\5\35\u0142\n\35\3\35\3\35\3\35\3\35\3\35\3\35\7\35"+
		"\u014a\n\35\f\35\16\35\u014d\13\35\3\36\3\36\3\36\5\36\u0152\n\36\3\36"+
		"\3\36\3\37\3\37\3 \3 \3 \3 \3 \3 \3 \3 \7 \u0160\n \f \16 \u0163\13 \3"+
		"!\3!\3!\2\48>\"\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62"+
		"\64\668:<>@\2\n\4\2\31\31\33\33\3\2,.\4\2\7\7\t\n\3\2\13\f\5\2\13\f\16"+
		"\16\35\35\3\2\17\25\3\2\65\66\3\2=>\2\u0177\2F\3\2\2\2\4K\3\2\2\2\6N\3"+
		"\2\2\2\be\3\2\2\2\ng\3\2\2\2\fk\3\2\2\2\16\u008c\3\2\2\2\20\u0092\3\2"+
		"\2\2\22\u00be\3\2\2\2\24\u00c5\3\2\2\2\26\u00cd\3\2\2\2\30\u00d5\3\2\2"+
		"\2\32\u00e0\3\2\2\2\34\u00eb\3\2\2\2\36\u00ed\3\2\2\2 \u00fc\3\2\2\2\""+
		"\u00fe\3\2\2\2$\u0105\3\2\2\2&\u010c\3\2\2\2(\u0111\3\2\2\2*\u0117\3\2"+
		"\2\2,\u0121\3\2\2\2.\u0123\3\2\2\2\60\u0125\3\2\2\2\62\u0127\3\2\2\2\64"+
		"\u0131\3\2\2\2\66\u0135\3\2\2\28\u0141\3\2\2\2:\u0151\3\2\2\2<\u0155\3"+
		"\2\2\2>\u0157\3\2\2\2@\u0164\3\2\2\2BE\5\6\4\2CE\5\4\3\2DB\3\2\2\2DC\3"+
		"\2\2\2EH\3\2\2\2FD\3\2\2\2FG\3\2\2\2GI\3\2\2\2HF\3\2\2\2IJ\7\2\2\3J\3"+
		"\3\2\2\2KL\7?\2\2LM\b\3\1\2M\5\3\2\2\2NW\5\b\5\2OQ\7\3\2\2PO\3\2\2\2Q"+
		"R\3\2\2\2RP\3\2\2\2RS\3\2\2\2ST\3\2\2\2TV\5\b\5\2UP\3\2\2\2VY\3\2\2\2"+
		"WU\3\2\2\2WX\3\2\2\2XZ\3\2\2\2YW\3\2\2\2Z[\7\3\2\2[\7\3\2\2\2\\f\5\n\6"+
		"\2]f\5\f\7\2^f\5\16\b\2_f\5\20\t\2`f\5\22\n\2af\5\24\13\2bf\5\26\f\2c"+
		"f\5\30\r\2df\5\32\16\2e\\\3\2\2\2e]\3\2\2\2e^\3\2\2\2e_\3\2\2\2e`\3\2"+
		"\2\2ea\3\2\2\2eb\3\2\2\2ec\3\2\2\2ed\3\2\2\2f\t\3\2\2\2gh\7\30\2\2hi\7"+
		"\31\2\2ij\5.\30\2j\13\3\2\2\2kl\7\30\2\2lm\7\33\2\2mn\5\60\31\2no\7\4"+
		"\2\2op\5\62\32\2ps\5\64\33\2qr\7\35\2\2rt\7\36\2\2sq\3\2\2\2st\3\2\2\2"+
		"t~\3\2\2\2uv\7\5\2\2vw\5\62\32\2wz\5\64\33\2xy\7\35\2\2y{\7\36\2\2zx\3"+
		"\2\2\2z{\3\2\2\2{}\3\2\2\2|u\3\2\2\2}\u0080\3\2\2\2~|\3\2\2\2~\177\3\2"+
		"\2\2\177\u0088\3\2\2\2\u0080~\3\2\2\2\u0081\u0082\7\5\2\2\u0082\u0083"+
		"\7\37\2\2\u0083\u0084\7 \2\2\u0084\u0085\7\4\2\2\u0085\u0086\5\62\32\2"+
		"\u0086\u0087\7\6\2\2\u0087\u0089\3\2\2\2\u0088\u0081\3\2\2\2\u0088\u0089"+
		"\3\2\2\2\u0089\u008a\3\2\2\2\u008a\u008b\7\6\2\2\u008b\r\3\2\2\2\u008c"+
		"\u008d\7!\2\2\u008d\u0090\t\2\2\2\u008e\u0091\5\60\31\2\u008f\u0091\5"+
		".\30\2\u0090\u008e\3\2\2\2\u0090\u008f\3\2\2\2\u0091\17\3\2\2\2\u0092"+
		"\u0093\7\"\2\2\u0093\u0094\7#\2\2\u0094\u00a0\5\60\31\2\u0095\u0096\7"+
		"\4\2\2\u0096\u009b\5\62\32\2\u0097\u0098\7\5\2\2\u0098\u009a\5\62\32\2"+
		"\u0099\u0097\3\2\2\2\u009a\u009d\3\2\2\2\u009b\u0099\3\2\2\2\u009b\u009c"+
		"\3\2\2\2\u009c\u009e\3\2\2\2\u009d\u009b\3\2\2\2\u009e\u009f\7\6\2\2\u009f"+
		"\u00a1\3\2\2\2\u00a0\u0095\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00a2\3\2"+
		"\2\2\u00a2\u00a3\7$\2\2\u00a3\u00a4\7\4\2\2\u00a4\u00a9\5\66\34\2\u00a5"+
		"\u00a6\7\5\2\2\u00a6\u00a8\5\66\34\2\u00a7\u00a5\3\2\2\2\u00a8\u00ab\3"+
		"\2\2\2\u00a9\u00a7\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00ac\3\2\2\2\u00ab"+
		"\u00a9\3\2\2\2\u00ac\u00bb\7\6\2\2\u00ad\u00ae\7\5\2\2\u00ae\u00af\7\4"+
		"\2\2\u00af\u00b4\5\66\34\2\u00b0\u00b1\7\5\2\2\u00b1\u00b3\5\66\34\2\u00b2"+
		"\u00b0\3\2\2\2\u00b3\u00b6\3\2\2\2\u00b4\u00b2\3\2\2\2\u00b4\u00b5\3\2"+
		"\2\2\u00b5\u00b7\3\2\2\2\u00b6\u00b4\3\2\2\2\u00b7\u00b8\7\6\2\2\u00b8"+
		"\u00ba\3\2\2\2\u00b9\u00ad\3\2\2\2\u00ba\u00bd\3\2\2\2\u00bb\u00b9\3\2"+
		"\2\2\u00bb\u00bc\3\2\2\2\u00bc\21\3\2\2\2\u00bd\u00bb\3\2\2\2\u00be\u00bf"+
		"\7%\2\2\u00bf\u00c0\7&\2\2\u00c0\u00c3\5\60\31\2\u00c1\u00c2\7\'\2\2\u00c2"+
		"\u00c4\5> \2\u00c3\u00c1\3\2\2\2\u00c3\u00c4\3\2\2\2\u00c4\23\3\2\2\2"+
		"\u00c5\u00c6\7(\2\2\u00c6\u00c7\5\60\31\2\u00c7\u00c8\7)\2\2\u00c8\u00cb"+
		"\5\36\20\2\u00c9\u00ca\7\'\2\2\u00ca\u00cc\5> \2\u00cb\u00c9\3\2\2\2\u00cb"+
		"\u00cc\3\2\2\2\u00cc\25\3\2\2\2\u00cd\u00ce\7*\2\2\u00ce\u00cf\5\34\17"+
		"\2\u00cf\u00d0\7&\2\2\u00d0\u00d3\5 \21\2\u00d1\u00d2\7\'\2\2\u00d2\u00d4"+
		"\5> \2\u00d3\u00d1\3\2\2\2\u00d3\u00d4\3\2\2\2\u00d4\27\3\2\2\2\u00d5"+
		"\u00d6\7\63\2\2\u00d6\u00d7\5.\30\2\u00d7\31\3\2\2\2\u00d8\u00d9\7\64"+
		"\2\2\u00d9\u00e1\7\32\2\2\u00da\u00db\7\64\2\2\u00db\u00dc\7\31\2\2\u00dc"+
		"\u00e1\5.\30\2\u00dd\u00de\7\64\2\2\u00de\u00df\7\33\2\2\u00df\u00e1\5"+
		"\60\31\2\u00e0\u00d8\3\2\2\2\u00e0\u00da\3\2\2\2\u00e0\u00dd\3\2\2\2\u00e1"+
		"\33\3\2\2\2\u00e2\u00ec\7\7\2\2\u00e3\u00e8\5:\36\2\u00e4\u00e5\7\5\2"+
		"\2\u00e5\u00e7\5:\36\2\u00e6\u00e4\3\2\2\2\u00e7\u00ea\3\2\2\2\u00e8\u00e6"+
		"\3\2\2\2\u00e8\u00e9\3\2\2\2\u00e9\u00ec\3\2\2\2\u00ea\u00e8\3\2\2\2\u00eb"+
		"\u00e2\3\2\2\2\u00eb\u00e3\3\2\2\2\u00ec\35\3\2\2\2\u00ed\u00ee\5:\36"+
		"\2\u00ee\u00ef\7\b\2\2\u00ef\u00f7\5\66\34\2\u00f0\u00f1\7\5\2\2\u00f1"+
		"\u00f2\5:\36\2\u00f2\u00f3\7\b\2\2\u00f3\u00f4\5\66\34\2\u00f4\u00f6\3"+
		"\2\2\2\u00f5\u00f0\3\2\2\2\u00f6\u00f9\3\2\2\2\u00f7\u00f5\3\2\2\2\u00f7"+
		"\u00f8\3\2\2\2\u00f8\37\3\2\2\2\u00f9\u00f7\3\2\2\2\u00fa\u00fd\5\60\31"+
		"\2\u00fb\u00fd\5\"\22\2\u00fc\u00fa\3\2\2\2\u00fc\u00fb\3\2\2\2\u00fd"+
		"!\3\2\2\2\u00fe\u0101\5,\27\2\u00ff\u0102\5$\23\2\u0100\u0102\5&\24\2"+
		"\u0101\u00ff\3\2\2\2\u0101\u0100\3\2\2\2\u0102\u0103\3\2\2\2\u0103\u0101"+
		"\3\2\2\2\u0103\u0104\3\2\2\2\u0104#\3\2\2\2\u0105\u0108\7+\2\2\u0106\u0109"+
		"\5(\25\2\u0107\u0109\5*\26\2\u0108\u0106\3\2\2\2\u0108\u0107\3\2\2\2\u0109"+
		"%\3\2\2\2\u010a\u010d\5(\25\2\u010b\u010d\5*\26\2\u010c\u010a\3\2\2\2"+
		"\u010c\u010b\3\2\2\2\u010d\u010e\3\2\2\2\u010e\u010f\7\62\2\2\u010f\u0110"+
		"\5> \2\u0110\'\3\2\2\2\u0111\u0112\t\3\2\2\u0112\u0113\7/\2\2\u0113\u0114"+
		"\7\61\2\2\u0114\u0115\5,\27\2\u0115)\3\2\2\2\u0116\u0118\7\60\2\2\u0117"+
		"\u0116\3\2\2\2\u0117\u0118\3\2\2\2\u0118\u0119\3\2\2\2\u0119\u011a\7\61"+
		"\2\2\u011a\u011b\5,\27\2\u011b+\3\2\2\2\u011c\u0122\5\60\31\2\u011d\u011e"+
		"\7\4\2\2\u011e\u011f\5 \21\2\u011f\u0120\7\6\2\2\u0120\u0122\3\2\2\2\u0121"+
		"\u011c\3\2\2\2\u0121\u011d\3\2\2\2\u0122-\3\2\2\2\u0123\u0124\7<\2\2\u0124"+
		"/\3\2\2\2\u0125\u0126\7<\2\2\u0126\61\3\2\2\2\u0127\u0128\7<\2\2\u0128"+
		"\63\3\2\2\2\u0129\u0132\7\67\2\2\u012a\u0132\78\2\2\u012b\u0132\79\2\2"+
		"\u012c\u0132\7:\2\2\u012d\u012e\7;\2\2\u012e\u012f\7\4\2\2\u012f\u0130"+
		"\7=\2\2\u0130\u0132\7\6\2\2\u0131\u0129\3\2\2\2\u0131\u012a\3\2\2\2\u0131"+
		"\u012b\3\2\2\2\u0131\u012c\3\2\2\2\u0131\u012d\3\2\2\2\u0132\65\3\2\2"+
		"\2\u0133\u0136\58\35\2\u0134\u0136\7\27\2\2\u0135\u0133\3\2\2\2\u0135"+
		"\u0134\3\2\2\2\u0136\67\3\2\2\2\u0137\u0138\b\35\1\2\u0138\u0142\5@!\2"+
		"\u0139\u0142\5:\36\2\u013a\u013b\5<\37\2\u013b\u013c\58\35\6\u013c\u0142"+
		"\3\2\2\2\u013d\u013e\7\4\2\2\u013e\u013f\58\35\2\u013f\u0140\7\6\2\2\u0140"+
		"\u0142\3\2\2\2\u0141\u0137\3\2\2\2\u0141\u0139\3\2\2\2\u0141\u013a\3\2"+
		"\2\2\u0141\u013d\3\2\2\2\u0142\u014b\3\2\2\2\u0143\u0144\f\5\2\2\u0144"+
		"\u0145\t\4\2\2\u0145\u014a\58\35\6\u0146\u0147\f\4\2\2\u0147\u0148\t\5"+
		"\2\2\u0148\u014a\58\35\5\u0149\u0143\3\2\2\2\u0149\u0146\3\2\2\2\u014a"+
		"\u014d\3\2\2\2\u014b\u0149\3\2\2\2\u014b\u014c\3\2\2\2\u014c9\3\2\2\2"+
		"\u014d\u014b\3\2\2\2\u014e\u014f\5\60\31\2\u014f\u0150\7\r\2\2\u0150\u0152"+
		"\3\2\2\2\u0151\u014e\3\2\2\2\u0151\u0152\3\2\2\2\u0152\u0153\3\2\2\2\u0153"+
		"\u0154\5\62\32\2\u0154;\3\2\2\2\u0155\u0156\t\6\2\2\u0156=\3\2\2\2\u0157"+
		"\u0158\b \1\2\u0158\u0159\5\66\34\2\u0159\u015a\t\7\2\2\u015a\u015b\5"+
		"\66\34\2\u015b\u0161\3\2\2\2\u015c\u015d\f\3\2\2\u015d\u015e\t\b\2\2\u015e"+
		"\u0160\5> \4\u015f\u015c\3\2\2\2\u0160\u0163\3\2\2\2\u0161\u015f\3\2\2"+
		"\2\u0161\u0162\3\2\2\2\u0162?\3\2\2\2\u0163\u0161\3\2\2\2\u0164\u0165"+
		"\t\t\2\2\u0165A\3\2\2\2&DFRWesz~\u0088\u0090\u009b\u00a0\u00a9\u00b4\u00bb"+
		"\u00c3\u00cb\u00d3\u00e0\u00e8\u00eb\u00f7\u00fc\u0101\u0103\u0108\u010c"+
		"\u0117\u0121\u0131\u0135\u0141\u0149\u014b\u0151\u0161";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}