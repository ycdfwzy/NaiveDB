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
		RULE_join_range = 15, RULE_join_ranges = 16, RULE_product_range = 17, 
		RULE_natural_join = 18, RULE_join_on = 19, RULE_outer_join = 20, RULE_inner_join = 21, 
		RULE_single_range = 22, RULE_db_name = 23, RULE_table_name = 24, RULE_attr_name = 25, 
		RULE_type_name = 26, RULE_expr = 27, RULE_numeric_expr = 28, RULE_expr_column = 29, 
		RULE_unary_operator = 30, RULE_pred_expr = 31, RULE_numeric_value = 32;
	private static String[] makeRuleNames() {
		return new String[] {
			"parse", "error", "sql_stmt_list", "sql_stmt", "create_db_stmt", "create_table_stmt", 
			"drop_stmt", "insert_stmt", "delete_stmt", "update_stmt", "select_stmt", 
			"use_stmt", "show_stmt", "select_elements", "asign_clause", "join_range", 
			"join_ranges", "product_range", "natural_join", "join_on", "outer_join", 
			"inner_join", "single_range", "db_name", "table_name", "attr_name", "type_name", 
			"expr", "numeric_expr", "expr_column", "unary_operator", "pred_expr", 
			"numeric_value"
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
			setState(70);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << K_CREATE) | (1L << K_DROP) | (1L << K_INSERT) | (1L << K_DELETE) | (1L << K_UPDATE) | (1L << K_SELECT) | (1L << K_USE) | (1L << K_SHOW) | (1L << UNEXPECTED_CHAR))) != 0)) {
				{
				setState(68);
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
					setState(66);
					sql_stmt_list();
					}
					break;
				case UNEXPECTED_CHAR:
					{
					setState(67);
					error();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(72);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(73);
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
			setState(75);
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
			setState(78);
			sql_stmt();
			setState(87);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(80); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(79);
						match(T__0);
						}
						}
						setState(82); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==T__0 );
					setState(84);
					sql_stmt();
					}
					} 
				}
				setState(89);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			}
			setState(90);
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
			setState(101);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(92);
				create_db_stmt();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(93);
				create_table_stmt();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(94);
				drop_stmt();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(95);
				insert_stmt();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(96);
				delete_stmt();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(97);
				update_stmt();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(98);
				select_stmt();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(99);
				use_stmt();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(100);
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
			setState(103);
			match(K_CREATE);
			setState(104);
			match(K_DATABASE);
			setState(105);
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
			setState(107);
			match(K_CREATE);
			setState(108);
			match(K_TABLE);
			setState(109);
			table_name();
			{
			setState(110);
			match(T__1);
			setState(111);
			attr_name();
			setState(112);
			type_name();
			setState(115);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_NOT) {
				{
				setState(113);
				match(K_NOT);
				setState(114);
				match(K_NULL);
				}
			}

			setState(126);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(117);
					match(T__2);
					setState(118);
					attr_name();
					setState(119);
					type_name();
					setState(122);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==K_NOT) {
						{
						setState(120);
						match(K_NOT);
						setState(121);
						match(K_NULL);
						}
					}

					}
					} 
				}
				setState(128);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			}
			setState(136);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(129);
				match(T__2);
				setState(130);
				match(K_PRIMARY);
				setState(131);
				match(K_KEY);
				setState(132);
				match(T__1);
				setState(133);
				attr_name();
				setState(134);
				match(T__3);
				}
			}

			setState(138);
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
			setState(140);
			match(K_DROP);
			setState(141);
			_la = _input.LA(1);
			if ( !(_la==K_DATABASE || _la==K_TABLE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(144);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				setState(142);
				table_name();
				}
				break;
			case 2:
				{
				setState(143);
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
			setState(146);
			match(K_INSERT);
			setState(147);
			match(K_INTO);
			setState(148);
			table_name();
			setState(160);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(149);
				match(T__1);
				setState(150);
				attr_name();
				setState(155);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__2) {
					{
					{
					setState(151);
					match(T__2);
					setState(152);
					attr_name();
					}
					}
					setState(157);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(158);
				match(T__3);
				}
			}

			{
			setState(162);
			match(K_VALUES);
			setState(163);
			match(T__1);
			setState(164);
			expr();
			setState(169);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(165);
				match(T__2);
				setState(166);
				expr();
				}
				}
				setState(171);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(172);
			match(T__3);
			setState(187);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(173);
				match(T__2);
				setState(174);
				match(T__1);
				setState(175);
				expr();
				setState(180);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__2) {
					{
					{
					setState(176);
					match(T__2);
					setState(177);
					expr();
					}
					}
					setState(182);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(183);
				match(T__3);
				}
				}
				setState(189);
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
			setState(190);
			match(K_DELETE);
			setState(191);
			match(K_FROM);
			setState(192);
			table_name();
			setState(195);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_WHERE) {
				{
				setState(193);
				match(K_WHERE);
				setState(194);
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
			setState(197);
			match(K_UPDATE);
			setState(198);
			table_name();
			setState(199);
			match(K_SET);
			setState(200);
			asign_clause();
			setState(203);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_WHERE) {
				{
				setState(201);
				match(K_WHERE);
				setState(202);
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
			setState(205);
			match(K_SELECT);
			setState(206);
			select_elements();
			setState(207);
			match(K_FROM);
			setState(208);
			join_range();
			setState(211);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_WHERE) {
				{
				setState(209);
				match(K_WHERE);
				setState(210);
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
			setState(213);
			match(K_USE);
			setState(214);
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
			setState(224);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(216);
				match(K_SHOW);
				setState(217);
				match(K_DATABASES);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(218);
				match(K_SHOW);
				setState(219);
				match(K_DATABASE);
				setState(220);
				db_name();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(221);
				match(K_SHOW);
				setState(222);
				match(K_TABLE);
				setState(223);
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
			setState(235);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
				enterOuterAlt(_localctx, 1);
				{
				setState(226);
				match(T__4);
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(227);
				expr_column();
				setState(232);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__2) {
					{
					{
					setState(228);
					match(T__2);
					setState(229);
					expr_column();
					}
					}
					setState(234);
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
			setState(237);
			expr_column();
			setState(238);
			match(T__5);
			setState(239);
			expr();
			setState(247);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(240);
				match(T__2);
				setState(241);
				expr_column();
				setState(242);
				match(T__5);
				setState(243);
				expr();
				}
				}
				setState(249);
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
		public Join_rangeContext join_range() {
			return getRuleContext(Join_rangeContext.class,0);
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
			setState(256);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(250);
				table_name();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(251);
				join_ranges();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(252);
				match(T__1);
				setState(253);
				join_range();
				setState(254);
				match(T__3);
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
		public List<Product_rangeContext> product_range() {
			return getRuleContexts(Product_rangeContext.class);
		}
		public Product_rangeContext product_range(int i) {
			return getRuleContext(Product_rangeContext.class,i);
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
		try {
			int _alt;
			setState(271);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(258);
				single_range();
				setState(260); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(259);
						product_range();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(262); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(264);
				single_range();
				setState(267); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						setState(267);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
						case K_NATURAL:
							{
							setState(265);
							natural_join();
							}
							break;
						case K_LEFT:
						case K_RIGHT:
						case K_FULL:
						case K_INNER:
						case K_JOIN:
							{
							setState(266);
							join_on();
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(269); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class Product_rangeContext extends ParserRuleContext {
		public Single_rangeContext single_range() {
			return getRuleContext(Single_rangeContext.class,0);
		}
		public Join_rangeContext join_range() {
			return getRuleContext(Join_rangeContext.class,0);
		}
		public Product_rangeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_product_range; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).enterProduct_range(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).exitProduct_range(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitProduct_range(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Product_rangeContext product_range() throws RecognitionException {
		Product_rangeContext _localctx = new Product_rangeContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_product_range);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(273);
			match(T__2);
			setState(276);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				{
				setState(274);
				single_range();
				}
				break;
			case 2:
				{
				setState(275);
				join_range();
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
		enterRule(_localctx, 36, RULE_natural_join);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(278);
			match(K_NATURAL);
			setState(281);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case K_LEFT:
			case K_RIGHT:
			case K_FULL:
				{
				setState(279);
				outer_join();
				}
				break;
			case K_INNER:
			case K_JOIN:
				{
				setState(280);
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
		enterRule(_localctx, 38, RULE_join_on);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(285);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case K_LEFT:
			case K_RIGHT:
			case K_FULL:
				{
				setState(283);
				outer_join();
				}
				break;
			case K_INNER:
			case K_JOIN:
				{
				setState(284);
				inner_join();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(287);
			match(K_ON);
			setState(288);
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
		public TerminalNode K_LEFT() { return getToken(sqlParser.K_LEFT, 0); }
		public TerminalNode K_RIGHT() { return getToken(sqlParser.K_RIGHT, 0); }
		public TerminalNode K_FULL() { return getToken(sqlParser.K_FULL, 0); }
		public Single_rangeContext single_range() {
			return getRuleContext(Single_rangeContext.class,0);
		}
		public Join_rangeContext join_range() {
			return getRuleContext(Join_rangeContext.class,0);
		}
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
		enterRule(_localctx, 40, RULE_outer_join);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(290);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << K_LEFT) | (1L << K_RIGHT) | (1L << K_FULL))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(291);
			match(K_OUTER);
			setState(292);
			match(K_JOIN);
			setState(295);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
			case 1:
				{
				setState(293);
				single_range();
				}
				break;
			case 2:
				{
				setState(294);
				join_range();
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

	public static class Inner_joinContext extends ParserRuleContext {
		public TerminalNode K_JOIN() { return getToken(sqlParser.K_JOIN, 0); }
		public Single_rangeContext single_range() {
			return getRuleContext(Single_rangeContext.class,0);
		}
		public Join_rangeContext join_range() {
			return getRuleContext(Join_rangeContext.class,0);
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
		enterRule(_localctx, 42, RULE_inner_join);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(298);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_INNER) {
				{
				setState(297);
				match(K_INNER);
				}
			}

			setState(300);
			match(K_JOIN);
			setState(303);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
			case 1:
				{
				setState(301);
				single_range();
				}
				break;
			case 2:
				{
				setState(302);
				join_range();
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
		enterRule(_localctx, 44, RULE_single_range);
		try {
			setState(310);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(305);
				table_name();
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 2);
				{
				setState(306);
				match(T__1);
				setState(307);
				join_range();
				setState(308);
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
		enterRule(_localctx, 46, RULE_db_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(312);
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
		enterRule(_localctx, 48, RULE_table_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(314);
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
		enterRule(_localctx, 50, RULE_attr_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(316);
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
		enterRule(_localctx, 52, RULE_type_name);
		try {
			setState(326);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case K_INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(318);
				match(K_INT);
				}
				break;
			case K_LONG:
				enterOuterAlt(_localctx, 2);
				{
				setState(319);
				match(K_LONG);
				}
				break;
			case K_FLOAT:
				enterOuterAlt(_localctx, 3);
				{
				setState(320);
				match(K_FLOAT);
				}
				break;
			case K_DOUBLE:
				enterOuterAlt(_localctx, 4);
				{
				setState(321);
				match(K_DOUBLE);
				}
				break;
			case K_STRING:
				enterOuterAlt(_localctx, 5);
				{
				setState(322);
				match(K_STRING);
				setState(323);
				match(T__1);
				setState(324);
				match(INTEGER_LITERAL);
				setState(325);
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
		enterRule(_localctx, 54, RULE_expr);
		try {
			setState(330);
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
				setState(328);
				numeric_expr(0);
				}
				break;
			case TEXT_STRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(329);
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
		int _startState = 56;
		enterRecursionRule(_localctx, 56, RULE_numeric_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(342);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INTEGER_LITERAL:
			case FLOAT_LITERAL:
				{
				setState(333);
				numeric_value();
				}
				break;
			case IDENTIFIER:
				{
				setState(334);
				expr_column();
				}
				break;
			case T__8:
			case T__9:
			case T__11:
			case K_NOT:
				{
				setState(335);
				unary_operator();
				setState(336);
				numeric_expr(4);
				}
				break;
			case T__1:
				{
				setState(338);
				match(T__1);
				setState(339);
				numeric_expr(0);
				setState(340);
				match(T__3);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(352);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,38,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(350);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
					case 1:
						{
						_localctx = new Numeric_exprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_numeric_expr);
						setState(344);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(345);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__6) | (1L << T__7))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(346);
						numeric_expr(4);
						}
						break;
					case 2:
						{
						_localctx = new Numeric_exprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_numeric_expr);
						setState(347);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(348);
						_la = _input.LA(1);
						if ( !(_la==T__8 || _la==T__9) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(349);
						numeric_expr(3);
						}
						break;
					}
					} 
				}
				setState(354);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,38,_ctx);
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
		enterRule(_localctx, 58, RULE_expr_column);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(358);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
			case 1:
				{
				setState(355);
				table_name();
				setState(356);
				match(T__10);
				}
				break;
			}
			setState(360);
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
		enterRule(_localctx, 60, RULE_unary_operator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(362);
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
		int _startState = 62;
		enterRecursionRule(_localctx, 62, RULE_pred_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(373);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
			case 1:
				{
				setState(365);
				expr();
				setState(366);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(367);
				expr();
				}
				break;
			case 2:
				{
				setState(369);
				match(T__1);
				setState(370);
				pred_expr(0);
				setState(371);
				match(T__3);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(380);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,41,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Pred_exprContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_pred_expr);
					setState(375);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(376);
					_la = _input.LA(1);
					if ( !(_la==K_AND || _la==K_OR) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(377);
					pred_expr(3);
					}
					} 
				}
				setState(382);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,41,_ctx);
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
		enterRule(_localctx, 64, RULE_numeric_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(383);
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
		case 28:
			return numeric_expr_sempred((Numeric_exprContext)_localctx, predIndex);
		case 31:
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
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3?\u0184\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\3\2\3\2\7\2G\n\2\f\2\16\2J\13\2\3\2\3\2\3\3\3\3\3\3\3\4\3"+
		"\4\6\4S\n\4\r\4\16\4T\3\4\7\4X\n\4\f\4\16\4[\13\4\3\4\3\4\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\5\5h\n\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\5\7v\n\7\3\7\3\7\3\7\3\7\3\7\5\7}\n\7\7\7\177\n\7\f\7\16"+
		"\7\u0082\13\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7\u008b\n\7\3\7\3\7\3\b\3"+
		"\b\3\b\3\b\5\b\u0093\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\7\t\u009c\n\t\f\t"+
		"\16\t\u009f\13\t\3\t\3\t\5\t\u00a3\n\t\3\t\3\t\3\t\3\t\3\t\7\t\u00aa\n"+
		"\t\f\t\16\t\u00ad\13\t\3\t\3\t\3\t\3\t\3\t\3\t\7\t\u00b5\n\t\f\t\16\t"+
		"\u00b8\13\t\3\t\3\t\7\t\u00bc\n\t\f\t\16\t\u00bf\13\t\3\n\3\n\3\n\3\n"+
		"\3\n\5\n\u00c6\n\n\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u00ce\n\13\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\5\f\u00d6\n\f\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\5\16\u00e3\n\16\3\17\3\17\3\17\3\17\7\17\u00e9\n\17\f"+
		"\17\16\17\u00ec\13\17\5\17\u00ee\n\17\3\20\3\20\3\20\3\20\3\20\3\20\3"+
		"\20\3\20\7\20\u00f8\n\20\f\20\16\20\u00fb\13\20\3\21\3\21\3\21\3\21\3"+
		"\21\3\21\5\21\u0103\n\21\3\22\3\22\6\22\u0107\n\22\r\22\16\22\u0108\3"+
		"\22\3\22\3\22\6\22\u010e\n\22\r\22\16\22\u010f\5\22\u0112\n\22\3\23\3"+
		"\23\3\23\5\23\u0117\n\23\3\24\3\24\3\24\5\24\u011c\n\24\3\25\3\25\5\25"+
		"\u0120\n\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\5\26\u012a\n\26\3"+
		"\27\5\27\u012d\n\27\3\27\3\27\3\27\5\27\u0132\n\27\3\30\3\30\3\30\3\30"+
		"\3\30\5\30\u0139\n\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\34\3\34"+
		"\3\34\3\34\3\34\3\34\5\34\u0149\n\34\3\35\3\35\5\35\u014d\n\35\3\36\3"+
		"\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\5\36\u0159\n\36\3\36\3\36"+
		"\3\36\3\36\3\36\3\36\7\36\u0161\n\36\f\36\16\36\u0164\13\36\3\37\3\37"+
		"\3\37\5\37\u0169\n\37\3\37\3\37\3 \3 \3!\3!\3!\3!\3!\3!\3!\3!\3!\5!\u0178"+
		"\n!\3!\3!\3!\7!\u017d\n!\f!\16!\u0180\13!\3\"\3\"\3\"\2\4:@#\2\4\6\b\n"+
		"\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@B\2\n\4\2\31\31"+
		"\33\33\3\2,.\4\2\7\7\t\n\3\2\13\f\5\2\13\f\16\16\35\35\4\2\b\b\17\25\3"+
		"\2\65\66\3\2=>\2\u019a\2H\3\2\2\2\4M\3\2\2\2\6P\3\2\2\2\bg\3\2\2\2\ni"+
		"\3\2\2\2\fm\3\2\2\2\16\u008e\3\2\2\2\20\u0094\3\2\2\2\22\u00c0\3\2\2\2"+
		"\24\u00c7\3\2\2\2\26\u00cf\3\2\2\2\30\u00d7\3\2\2\2\32\u00e2\3\2\2\2\34"+
		"\u00ed\3\2\2\2\36\u00ef\3\2\2\2 \u0102\3\2\2\2\"\u0111\3\2\2\2$\u0113"+
		"\3\2\2\2&\u0118\3\2\2\2(\u011f\3\2\2\2*\u0124\3\2\2\2,\u012c\3\2\2\2."+
		"\u0138\3\2\2\2\60\u013a\3\2\2\2\62\u013c\3\2\2\2\64\u013e\3\2\2\2\66\u0148"+
		"\3\2\2\28\u014c\3\2\2\2:\u0158\3\2\2\2<\u0168\3\2\2\2>\u016c\3\2\2\2@"+
		"\u0177\3\2\2\2B\u0181\3\2\2\2DG\5\6\4\2EG\5\4\3\2FD\3\2\2\2FE\3\2\2\2"+
		"GJ\3\2\2\2HF\3\2\2\2HI\3\2\2\2IK\3\2\2\2JH\3\2\2\2KL\7\2\2\3L\3\3\2\2"+
		"\2MN\7?\2\2NO\b\3\1\2O\5\3\2\2\2PY\5\b\5\2QS\7\3\2\2RQ\3\2\2\2ST\3\2\2"+
		"\2TR\3\2\2\2TU\3\2\2\2UV\3\2\2\2VX\5\b\5\2WR\3\2\2\2X[\3\2\2\2YW\3\2\2"+
		"\2YZ\3\2\2\2Z\\\3\2\2\2[Y\3\2\2\2\\]\7\3\2\2]\7\3\2\2\2^h\5\n\6\2_h\5"+
		"\f\7\2`h\5\16\b\2ah\5\20\t\2bh\5\22\n\2ch\5\24\13\2dh\5\26\f\2eh\5\30"+
		"\r\2fh\5\32\16\2g^\3\2\2\2g_\3\2\2\2g`\3\2\2\2ga\3\2\2\2gb\3\2\2\2gc\3"+
		"\2\2\2gd\3\2\2\2ge\3\2\2\2gf\3\2\2\2h\t\3\2\2\2ij\7\30\2\2jk\7\31\2\2"+
		"kl\5\60\31\2l\13\3\2\2\2mn\7\30\2\2no\7\33\2\2op\5\62\32\2pq\7\4\2\2q"+
		"r\5\64\33\2ru\5\66\34\2st\7\35\2\2tv\7\36\2\2us\3\2\2\2uv\3\2\2\2v\u0080"+
		"\3\2\2\2wx\7\5\2\2xy\5\64\33\2y|\5\66\34\2z{\7\35\2\2{}\7\36\2\2|z\3\2"+
		"\2\2|}\3\2\2\2}\177\3\2\2\2~w\3\2\2\2\177\u0082\3\2\2\2\u0080~\3\2\2\2"+
		"\u0080\u0081\3\2\2\2\u0081\u008a\3\2\2\2\u0082\u0080\3\2\2\2\u0083\u0084"+
		"\7\5\2\2\u0084\u0085\7\37\2\2\u0085\u0086\7 \2\2\u0086\u0087\7\4\2\2\u0087"+
		"\u0088\5\64\33\2\u0088\u0089\7\6\2\2\u0089\u008b\3\2\2\2\u008a\u0083\3"+
		"\2\2\2\u008a\u008b\3\2\2\2\u008b\u008c\3\2\2\2\u008c\u008d\7\6\2\2\u008d"+
		"\r\3\2\2\2\u008e\u008f\7!\2\2\u008f\u0092\t\2\2\2\u0090\u0093\5\62\32"+
		"\2\u0091\u0093\5\60\31\2\u0092\u0090\3\2\2\2\u0092\u0091\3\2\2\2\u0093"+
		"\17\3\2\2\2\u0094\u0095\7\"\2\2\u0095\u0096\7#\2\2\u0096\u00a2\5\62\32"+
		"\2\u0097\u0098\7\4\2\2\u0098\u009d\5\64\33\2\u0099\u009a\7\5\2\2\u009a"+
		"\u009c\5\64\33\2\u009b\u0099\3\2\2\2\u009c\u009f\3\2\2\2\u009d\u009b\3"+
		"\2\2\2\u009d\u009e\3\2\2\2\u009e\u00a0\3\2\2\2\u009f\u009d\3\2\2\2\u00a0"+
		"\u00a1\7\6\2\2\u00a1\u00a3\3\2\2\2\u00a2\u0097\3\2\2\2\u00a2\u00a3\3\2"+
		"\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00a5\7$\2\2\u00a5\u00a6\7\4\2\2\u00a6"+
		"\u00ab\58\35\2\u00a7\u00a8\7\5\2\2\u00a8\u00aa\58\35\2\u00a9\u00a7\3\2"+
		"\2\2\u00aa\u00ad\3\2\2\2\u00ab\u00a9\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac"+
		"\u00ae\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ae\u00bd\7\6\2\2\u00af\u00b0\7\5"+
		"\2\2\u00b0\u00b1\7\4\2\2\u00b1\u00b6\58\35\2\u00b2\u00b3\7\5\2\2\u00b3"+
		"\u00b5\58\35\2\u00b4\u00b2\3\2\2\2\u00b5\u00b8\3\2\2\2\u00b6\u00b4\3\2"+
		"\2\2\u00b6\u00b7\3\2\2\2\u00b7\u00b9\3\2\2\2\u00b8\u00b6\3\2\2\2\u00b9"+
		"\u00ba\7\6\2\2\u00ba\u00bc\3\2\2\2\u00bb\u00af\3\2\2\2\u00bc\u00bf\3\2"+
		"\2\2\u00bd\u00bb\3\2\2\2\u00bd\u00be\3\2\2\2\u00be\21\3\2\2\2\u00bf\u00bd"+
		"\3\2\2\2\u00c0\u00c1\7%\2\2\u00c1\u00c2\7&\2\2\u00c2\u00c5\5\62\32\2\u00c3"+
		"\u00c4\7\'\2\2\u00c4\u00c6\5@!\2\u00c5\u00c3\3\2\2\2\u00c5\u00c6\3\2\2"+
		"\2\u00c6\23\3\2\2\2\u00c7\u00c8\7(\2\2\u00c8\u00c9\5\62\32\2\u00c9\u00ca"+
		"\7)\2\2\u00ca\u00cd\5\36\20\2\u00cb\u00cc\7\'\2\2\u00cc\u00ce\5@!\2\u00cd"+
		"\u00cb\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce\25\3\2\2\2\u00cf\u00d0\7*\2\2"+
		"\u00d0\u00d1\5\34\17\2\u00d1\u00d2\7&\2\2\u00d2\u00d5\5 \21\2\u00d3\u00d4"+
		"\7\'\2\2\u00d4\u00d6\5@!\2\u00d5\u00d3\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6"+
		"\27\3\2\2\2\u00d7\u00d8\7\63\2\2\u00d8\u00d9\5\60\31\2\u00d9\31\3\2\2"+
		"\2\u00da\u00db\7\64\2\2\u00db\u00e3\7\32\2\2\u00dc\u00dd\7\64\2\2\u00dd"+
		"\u00de\7\31\2\2\u00de\u00e3\5\60\31\2\u00df\u00e0\7\64\2\2\u00e0\u00e1"+
		"\7\33\2\2\u00e1\u00e3\5\62\32\2\u00e2\u00da\3\2\2\2\u00e2\u00dc\3\2\2"+
		"\2\u00e2\u00df\3\2\2\2\u00e3\33\3\2\2\2\u00e4\u00ee\7\7\2\2\u00e5\u00ea"+
		"\5<\37\2\u00e6\u00e7\7\5\2\2\u00e7\u00e9\5<\37\2\u00e8\u00e6\3\2\2\2\u00e9"+
		"\u00ec\3\2\2\2\u00ea\u00e8\3\2\2\2\u00ea\u00eb\3\2\2\2\u00eb\u00ee\3\2"+
		"\2\2\u00ec\u00ea\3\2\2\2\u00ed\u00e4\3\2\2\2\u00ed\u00e5\3\2\2\2\u00ee"+
		"\35\3\2\2\2\u00ef\u00f0\5<\37\2\u00f0\u00f1\7\b\2\2\u00f1\u00f9\58\35"+
		"\2\u00f2\u00f3\7\5\2\2\u00f3\u00f4\5<\37\2\u00f4\u00f5\7\b\2\2\u00f5\u00f6"+
		"\58\35\2\u00f6\u00f8\3\2\2\2\u00f7\u00f2\3\2\2\2\u00f8\u00fb\3\2\2\2\u00f9"+
		"\u00f7\3\2\2\2\u00f9\u00fa\3\2\2\2\u00fa\37\3\2\2\2\u00fb\u00f9\3\2\2"+
		"\2\u00fc\u0103\5\62\32\2\u00fd\u0103\5\"\22\2\u00fe\u00ff\7\4\2\2\u00ff"+
		"\u0100\5 \21\2\u0100\u0101\7\6\2\2\u0101\u0103\3\2\2\2\u0102\u00fc\3\2"+
		"\2\2\u0102\u00fd\3\2\2\2\u0102\u00fe\3\2\2\2\u0103!\3\2\2\2\u0104\u0106"+
		"\5.\30\2\u0105\u0107\5$\23\2\u0106\u0105\3\2\2\2\u0107\u0108\3\2\2\2\u0108"+
		"\u0106\3\2\2\2\u0108\u0109\3\2\2\2\u0109\u0112\3\2\2\2\u010a\u010d\5."+
		"\30\2\u010b\u010e\5&\24\2\u010c\u010e\5(\25\2\u010d\u010b\3\2\2\2\u010d"+
		"\u010c\3\2\2\2\u010e\u010f\3\2\2\2\u010f\u010d\3\2\2\2\u010f\u0110\3\2"+
		"\2\2\u0110\u0112\3\2\2\2\u0111\u0104\3\2\2\2\u0111\u010a\3\2\2\2\u0112"+
		"#\3\2\2\2\u0113\u0116\7\5\2\2\u0114\u0117\5.\30\2\u0115\u0117\5 \21\2"+
		"\u0116\u0114\3\2\2\2\u0116\u0115\3\2\2\2\u0117%\3\2\2\2\u0118\u011b\7"+
		"+\2\2\u0119\u011c\5*\26\2\u011a\u011c\5,\27\2\u011b\u0119\3\2\2\2\u011b"+
		"\u011a\3\2\2\2\u011c\'\3\2\2\2\u011d\u0120\5*\26\2\u011e\u0120\5,\27\2"+
		"\u011f\u011d\3\2\2\2\u011f\u011e\3\2\2\2\u0120\u0121\3\2\2\2\u0121\u0122"+
		"\7\62\2\2\u0122\u0123\5@!\2\u0123)\3\2\2\2\u0124\u0125\t\3\2\2\u0125\u0126"+
		"\7/\2\2\u0126\u0129\7\61\2\2\u0127\u012a\5.\30\2\u0128\u012a\5 \21\2\u0129"+
		"\u0127\3\2\2\2\u0129\u0128\3\2\2\2\u012a+\3\2\2\2\u012b\u012d\7\60\2\2"+
		"\u012c\u012b\3\2\2\2\u012c\u012d\3\2\2\2\u012d\u012e\3\2\2\2\u012e\u0131"+
		"\7\61\2\2\u012f\u0132\5.\30\2\u0130\u0132\5 \21\2\u0131\u012f\3\2\2\2"+
		"\u0131\u0130\3\2\2\2\u0132-\3\2\2\2\u0133\u0139\5\62\32\2\u0134\u0135"+
		"\7\4\2\2\u0135\u0136\5 \21\2\u0136\u0137\7\6\2\2\u0137\u0139\3\2\2\2\u0138"+
		"\u0133\3\2\2\2\u0138\u0134\3\2\2\2\u0139/\3\2\2\2\u013a\u013b\7<\2\2\u013b"+
		"\61\3\2\2\2\u013c\u013d\7<\2\2\u013d\63\3\2\2\2\u013e\u013f\7<\2\2\u013f"+
		"\65\3\2\2\2\u0140\u0149\7\67\2\2\u0141\u0149\78\2\2\u0142\u0149\79\2\2"+
		"\u0143\u0149\7:\2\2\u0144\u0145\7;\2\2\u0145\u0146\7\4\2\2\u0146\u0147"+
		"\7=\2\2\u0147\u0149\7\6\2\2\u0148\u0140\3\2\2\2\u0148\u0141\3\2\2\2\u0148"+
		"\u0142\3\2\2\2\u0148\u0143\3\2\2\2\u0148\u0144\3\2\2\2\u0149\67\3\2\2"+
		"\2\u014a\u014d\5:\36\2\u014b\u014d\7\27\2\2\u014c\u014a\3\2\2\2\u014c"+
		"\u014b\3\2\2\2\u014d9\3\2\2\2\u014e\u014f\b\36\1\2\u014f\u0159\5B\"\2"+
		"\u0150\u0159\5<\37\2\u0151\u0152\5> \2\u0152\u0153\5:\36\6\u0153\u0159"+
		"\3\2\2\2\u0154\u0155\7\4\2\2\u0155\u0156\5:\36\2\u0156\u0157\7\6\2\2\u0157"+
		"\u0159\3\2\2\2\u0158\u014e\3\2\2\2\u0158\u0150\3\2\2\2\u0158\u0151\3\2"+
		"\2\2\u0158\u0154\3\2\2\2\u0159\u0162\3\2\2\2\u015a\u015b\f\5\2\2\u015b"+
		"\u015c\t\4\2\2\u015c\u0161\5:\36\6\u015d\u015e\f\4\2\2\u015e\u015f\t\5"+
		"\2\2\u015f\u0161\5:\36\5\u0160\u015a\3\2\2\2\u0160\u015d\3\2\2\2\u0161"+
		"\u0164\3\2\2\2\u0162\u0160\3\2\2\2\u0162\u0163\3\2\2\2\u0163;\3\2\2\2"+
		"\u0164\u0162\3\2\2\2\u0165\u0166\5\62\32\2\u0166\u0167\7\r\2\2\u0167\u0169"+
		"\3\2\2\2\u0168\u0165\3\2\2\2\u0168\u0169\3\2\2\2\u0169\u016a\3\2\2\2\u016a"+
		"\u016b\5\64\33\2\u016b=\3\2\2\2\u016c\u016d\t\6\2\2\u016d?\3\2\2\2\u016e"+
		"\u016f\b!\1\2\u016f\u0170\58\35\2\u0170\u0171\t\7\2\2\u0171\u0172\58\35"+
		"\2\u0172\u0178\3\2\2\2\u0173\u0174\7\4\2\2\u0174\u0175\5@!\2\u0175\u0176"+
		"\7\6\2\2\u0176\u0178\3\2\2\2\u0177\u016e\3\2\2\2\u0177\u0173\3\2\2\2\u0178"+
		"\u017e\3\2\2\2\u0179\u017a\f\4\2\2\u017a\u017b\t\b\2\2\u017b\u017d\5@"+
		"!\5\u017c\u0179\3\2\2\2\u017d\u0180\3\2\2\2\u017e\u017c\3\2\2\2\u017e"+
		"\u017f\3\2\2\2\u017fA\3\2\2\2\u0180\u017e\3\2\2\2\u0181\u0182\t\t\2\2"+
		"\u0182C\3\2\2\2,FHTYgu|\u0080\u008a\u0092\u009d\u00a2\u00ab\u00b6\u00bd"+
		"\u00c5\u00cd\u00d5\u00e2\u00ea\u00ed\u00f9\u0102\u0108\u010d\u010f\u0111"+
		"\u0116\u011b\u011f\u0129\u012c\u0131\u0138\u0148\u014c\u0158\u0160\u0162"+
		"\u0168\u0177\u017e";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}