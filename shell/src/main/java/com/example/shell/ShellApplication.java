package com.example.shell;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;

@SpringBootApplication
public class ShellApplication {

    public static void main(String[] args) {
        // 禁用 help 内置命令
        //String[] disabledCommands = {"--spring.shell.command.help.enabled=false"};
        //String[] fullArgs = StringUtils.concatenateStringArrays(args, disabledCommands);

        SpringApplication.run(ShellApplication.class, args);
    }

}
