package com.yang.gzcloud.view;

import com.yang.gzcloud.entity.MyComputerUsage;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class StreamController {
    @PatchMapping("/mycomputer")
    public String demo(){
        List<MyComputerUsage> list = new ArrayList<>();
        list.stream().filter(m -> m.getCpuUse() > 1.0d).mapToDouble(m -> m.getCpuUse()).sum();
        list.stream().filter(m -> m.getCpuUse() > 1.0d).collect(Collectors.toList());
        return null;
    }
}
