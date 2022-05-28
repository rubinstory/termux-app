package com.termux.shared.net.socket.local.StringGenerator;

import android.util.Pair;

import com.termux.shared.logger.Logger;
import com.termux.shared.markdown.MarkdownUtils;

import java.util.List;

public class PeerCredStringGenerator extends StringGenerator{

    @Override
    public String getLogString() {
        StringBuilder logString = new StringBuilder();

        logString.append("Peer Cred:");

        for (Pair<String, Object> logVar: getLogVariableList()) {
            String label = logVar.first;
            Object object = logVar.second;
            switch(label) {
                case "Cmdline":
                    if (label != null) logString.append("\n").append(Logger.getMultiLineLogStringEntry(label, object, "-"));
                    break;
                default:
                    logString.append("\n").append(Logger.getSingleLineLogStringEntry(label, object, "-"));
            }
        }

        return logString.toString();
    }

    @Override
    public String getMarkdownString() {
        StringBuilder markdownString = new StringBuilder();

        markdownString.append("## ").append("Peer Cred");

        for (Pair<String, Object> logVar: getLogVariableList()) {
            String label = logVar.first;
            Object object = logVar.second;
            switch(label) {
                case "Cmdline":
                    if (label != null) markdownString.append("\n").append(MarkdownUtils.getMultiLineMarkdownStringEntry(label, object, "-"));
                    break;
                default:
                    markdownString.append("\n").append(MarkdownUtils.getSingleLineMarkdownStringEntry(label, object, "-"));
            }
        }

        return markdownString.toString();
    }
}
