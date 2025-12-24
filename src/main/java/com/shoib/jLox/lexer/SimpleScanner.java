package com.shoib.jLox.lexer;

import java.util.ArrayList;
import java.util.List;

import com.shoib.jLox.lexer.Token;


public class SimpleScanner {
    private final String source;
    private final List<Token> tokens = new ArrayList<>();

    private int start = 0;
    private int current = 0;
    private int line = 1;

    SimpleScanner(String source) {
        if (source == null) throw new IllegalArgumentException("source code should not be null!");

        this.source = source;
    }

    List<Token> scanTokens() {
        while(!isAtEnd()) {
            // We are at the beginning of th next lexeme
            start = current;
            scanToken();
        }

        tokens.add(new Token(Token.Type.EOF, "", null, line));
        return tokens;
    }

    private void scanToken() {
        var nextChar = advance();

        switch (nextChar) {
            case '(' -> addToken(Token.Type.LEFT_PAREN);
            case ')' -> addToken(Token.Type.RIGHT_PAREN);
            case '{' -> addToken(Token.Type.LEFT_BRACE);
            case '}' -> addToken(Token.Type.RIGHT_BRACE);
            case ',' -> addToken(Token.Type.COMMA);
            case '.' -> addToken(Token.Type.DOT);
            case '-' -> addToken(Token.Type.MINUS);
            case '+' -> addToken(Token.Type.PLUS);
            case ';' -> addToken(Token.Type.SEMICOLON);
            case '*' -> addToken(Token.Type.STAR);
        }
    }

    private boolean isAtEnd() {
        return current >= source.length();
    }

    private char advance() {
        current++;
        return source.charAt(current - 1);
    }

    private void addToken(Token.Type type) {
        addToken(type, null);
    }

    private void addToken(Token.Type type, Object literal) {
        String text = source.substring(start, current);
        tokens.add(new Token(type, text, literal, line));
    }
}
