package com.example.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class WebController {
    List<Integer> numbers = List.of(10, 5, 3, 12, 6, 7, 5, 3);

    @GetMapping("/")
    public String getHome() {
        return "index";
    }
   // 2. Tìm các giá trị chẵn trong list

    @GetMapping("/evenNumber")
    public String evenNumber(Model model) {
        List<Integer> evenNumber = numbers.stream().filter(number -> number % 2 == 0).toList();
        model.addAttribute("evenNumber", evenNumber);
        return "evenNumber";
    }

    //	3. Tìm các giá trị > 5 trong list
    @GetMapping("/greaterThanfive")
    public String greaterThanfive(Model model) {
        List<Integer> greaterThanfive = numbers.stream().filter(number -> number >5).toList();
        model.addAttribute("greaterThanfive", greaterThanfive);
        return "greaterThanfive";
    }

    //	4. Tìm giá trị max trong list
    @GetMapping("/max")
    public String max(Model model) {
        int max = numbers.stream().reduce(0, Integer::max);
        model.addAttribute("max", max);
        return "max";
    }

    //	5. Tìm giá trị min trong list
    @GetMapping("/min")
    public String min(Model model) {
        int min = numbers.stream().reduce(Integer::min).get();
        model.addAttribute("min", min);
        return "min";
    }

    //	6. Tính tổng các phần tử của mảng
    @GetMapping("/sum")
    public String sum(Model model) {
        int sum = numbers.stream().reduce( Integer::sum).get();
        model.addAttribute("sum", sum);
        return "sum";
    }

    //	7. Lấy danh sách các phần tử không trùng nhau
    @GetMapping("/distinctNum")
    public String distinctNum(Model model) {
        List<Integer> distinctNum = numbers.stream().distinct().toList();
        model.addAttribute("distinctNum", distinctNum);
        return "distinctNum";
    }

    //	8. Lấy 5 phần tử đầu tiên trong mảng
    @GetMapping("/limitFive")
    public String limitFive(Model model) {
        List<Integer> limitFive = numbers.stream().limit(5).toList();
        model.addAttribute("limitFive", limitFive);
        return "limitFive";
    }

    //	9. Lấy phần tử từ thứ 3 -> thứ 5
    @GetMapping("/fromThreeToFive")
    public String fromThreeToFive(Model model) {
        List<Integer> fromThreeToFive = numbers.stream().limit(5).skip(2).toList();
        model.addAttribute("fromThreeToFive", fromThreeToFive);
        return "fromThreeToFive";
    }

    //	10. Lấy phần tử đầu tiên > 5
    @GetMapping("/fistGreaterThanFive")
    public String fistGreaterThanFive(Model model) {
        int fistGreaterThanFive = numbers.stream().filter(number -> number > 5).findFirst().get();
        model.addAttribute("fistGreaterThanFive", fistGreaterThanFive);
        return "fistGreaterThanFive";
    }






}
