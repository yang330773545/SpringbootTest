package com.example.shell.command;

import org.springframework.core.convert.converter.Converter;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.stereotype.Component;

// 参数转换
@ShellComponent
class ConversionCommands {
    @ShellMethod("Shows conversion using Spring converter")
    public Class<? extends DomainObject> conversionExample(DomainObject object) {
        return object.getClass();
    }
}

class DomainObject {
    private final String value;

    DomainObject(String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }
}

@Component
class CustomDomainConverter implements Converter<String, DomainObject> {

    @Override
    public DomainObject convert(String source) {
        return new DomainObject(source);
    }
}
