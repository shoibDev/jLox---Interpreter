package com.shoib.jLox.lexer;

import java.util.List;

public interface Scanner {
    static SimpleScanner createDefault(String sourceCode) {
        return new SimpleScanner(sourceCode);
    }
    List<Token> scanTokens();
}
