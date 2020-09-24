package com.example.shell.provider;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.context.event.EventListener;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

// bean可以使用内部状态来决定向用户显示什么
//@Component
/*
public class CustomPromptProvider implements PromptProvider {

    private ConnectionDetails connection;

    @Override
    public AttributedString getPrompt() {
        if (connection != null) {
            return new AttributedString(connection.getHost() + ":>",
                    AttributedStyle.DEFAULT.foreground(AttributedStyle.YELLOW));
        }
        else {
            return new AttributedString("server-unknown:>",
                    AttributedStyle.DEFAULT.foreground(AttributedStyle.RED));
        }
    }

    @EventListener
    public void handle(ConnectionUpdatedEvent event) {
        this.connection = event.getConnectionDetails();
    }
}
*/