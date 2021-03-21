package org.cf.forgot.fastjson.json;


import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONToken;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import org.cf.forgot.fastjson.labels.Labels;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class LabelsDeserializer implements ObjectDeserializer {

    public static final LabelsDeserializer instance = new LabelsDeserializer();

    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object o) {
        JSONLexer lexer = parser.getLexer();
        if (lexer.token() == JSONToken.NULL) {
            lexer.nextToken();
            return null;
        }
        Map<String, String> labels = new HashMap<String, String>();
        for (; ; ) {
            int token = -1;
            // lexer.scanSymbol
            String key = lexer.scanSymbol(parser.getSymbolTable());
            if (key == null) {
                token = lexer.token();
                if (token == JSONToken.RBRACE) {
                    lexer.nextToken(JSONToken.COMMA);
                    break;
                }
                token = lexer.token();
                if (token == JSONToken.COMMA) {
                    if (lexer.isEnabled(Feature.AllowArbitraryCommas)) {
                        continue;
                    }
                }
            }
            lexer.nextTokenWithColon(JSONToken.LITERAL_STRING);
            token = lexer.token();
            if (token == JSONToken.LITERAL_STRING) {
                labels.put(key, lexer.stringVal());
            }

            token = lexer.token();
            if (token == JSONToken.RBRACE) {
                lexer.nextToken(JSONToken.COMMA);
                break;
            }
        }
        return (T) Labels.valueOf(labels);
    }

    @Override
    public int getFastMatchToken() {
        return JSONToken.LBRACE;
    }
}
