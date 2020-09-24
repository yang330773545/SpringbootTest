package com.example.shell.command;

import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.commands.Clear;

// 覆盖特定命令 禁用后 实现该<Command>.Command接口
public class MyClearCommand implements Clear.Command {

    @ShellMethod("Clear the screen, only better.")
    public void clear() {
        // ...
    }

}
