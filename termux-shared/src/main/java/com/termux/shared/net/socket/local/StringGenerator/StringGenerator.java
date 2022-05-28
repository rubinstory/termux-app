package com.termux.shared.net.socket.local.StringGenerator;

import android.util.Pair;

import java.util.List;

// Singleton 적용하기
public abstract class StringGenerator {
    private List<Pair<String, Object>> logVariableList = null;

    public void setLogVariableList(List<Pair<String, Object>> logVariableList) {
        this.logVariableList = logVariableList;
    };

    public List<Pair<String, Object>> getLogVariableList() {
        return logVariableList;
    };

    public abstract String getLogString();
    public abstract String getMarkdownString();
}
