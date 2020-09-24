package com.example.shell.command;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import javax.validation.constraints.Size;

@ShellComponent
public class MyCommands {

    // key指定命令名称可接收多个值 默认使用方法名 名称参数不可重复  $ sum 1 2  亦可--arg value
    // 想提供一个包含空格的参数值需要使用单引号或双引号 \为转义 当不使用封闭引号时，也可以转义空格字符
    @ShellMethod(value = "Add two integers together.", key = "sum")
    public int add(int a, int b) {
        return a + b;
    }

    // -a --third
    @ShellMethod(value = "Display stuff.", prefix="-")
    public String echo(String a, @ShellOption("--third") String b, String c){
        return String.format("You said a=%d, b=%d,c=%d", a, b, c);
    }

    // 互斥 -C or --command
    /*
    @ShellMethod("Describe a command.")
    public String help(@ShellOption({"-C", "--command"}) String command) {
        return "HELLO THANK YOU！";
    }
    */
    
    @ShellMethod("Say hello.")
    public String greet(@ShellOption(defaultValue="World") String who) {
        return "THE WORLD!"+who;
    }

    // add (--number) 1 2 3.3
    @ShellMethod("Add Numbers.")
    public float add(@ShellOption(arity=3) float[] numbers){
        return numbers[0] + numbers[1] + numbers[2];
    }

    // false true  $ shutdown  $ shutdown --force
    @ShellMethod("Terminate the system.")
    public String shutdown(boolean force) {
        return "You said " + force;
    }

    // 参数限制 不合法时会自动提示
    @ShellMethod("Change password.")
    public String changePassword(@Size(min = 8, max = 40) String password) {
        return "Password successfully set to " + password;
    }
}
