package com.termux.shared.net.socket.local.StringGenerator;

import android.util.Pair;

import com.termux.shared.logger.Logger;
import com.termux.shared.markdown.MarkdownUtils;

public class LocalSocketRunConfigStringGenerator extends StringGenerator{
    private String mTitle;

    public LocalSocketRunConfigStringGenerator(String title) {
        mTitle = title;
    }

    @Override
    public String getLogString() {
        StringBuilder logString = new StringBuilder();

        logString.append(mTitle).append(" Socket Server Run Config:");

        for(Pair<String, Object> logVar: getLogVariableList()) {
            String label = logVar.first;
            Object object = logVar.second;
            logString.append("\n").append(Logger.getSingleLineLogStringEntry(label, object, "-"));
        }

        return logString.toString();
    }

    @Override
    public String getMarkdownString() {
        StringBuilder markdownString = new StringBuilder();

        markdownString.append("## ").append(mTitle).append(" Socket Server Run Config");

        for(Pair<String, Object> logVar: getLogVariableList()) {
            String label = logVar.first;
            Object object = logVar.second;
            markdownString.append("\n").append(MarkdownUtils.getSingleLineMarkdownStringEntry(label, object, "-"));
        }

        return markdownString.toString();
    }
}
