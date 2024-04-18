package com.example.demo.controller;

import com.example.demo.model.Question;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WebController {
    List<Integer> numbers = List.of(10, 5, 3, 12, 6, 7, 5, 3);
    private List<Question> questions = new ArrayList<>(
            List.of(
                    new Question(2,"Tìm các giá trị chẵn trong list",evenNumber(numbers)),
                    new Question(3,"Tìm các giá trị > 5 trong list",ListGreaterThanFive(numbers)),
                    new Question(4,"Tìm giá trị max trong list",max(numbers)),
                    new Question(5,"Tìm giá trị min trong list",min(numbers)),
                    new Question(6,"Tính tổng các phần tử của mảng",sum(numbers)),
                    new Question(7,"Lấy danh sách các phần tử không trùng nhau",distinctList(numbers)),
                    new Question(8,"Lấy 5 phần tử đầu tiên trong mảng",fiveFirst(numbers)),
                    new Question(9,"Lấy phần tử từ thứ 3 -> thứ 5",fromThreeToFive(numbers)),
                    new Question(10,"Lấy phần tử đầu tiên > 5",fistGreaterThanFive(numbers))
            )
    );
    //2. Tìm các giá trị chẵn trong list
    public  String evenNumber (List<Integer> numbers) {
        return numbers.stream().filter(number -> number % 2 == 0).toList().toString();
    }

    //		3. Tìm các giá trị > 5 trong list
    public  String ListGreaterThanFive (List<Integer> numbers) {
        return numbers.stream().filter(number -> number >5).toList().toString();
    }
    //		4. Tìm giá trị max trong list
    public String max (List<Integer> numbers) { return numbers.stream().reduce(Integer::max).get().toString();}

    //		5. Tìm giá trị min trong list
    public String min (List<Integer> numbers) { return numbers.stream().reduce( Integer::min).get().toString();}

    //		6. Tính tổng các phần tử của mảng
    public String sum (List<Integer> numbers) { return numbers.stream().reduce(0, Integer::sum).toString();}

    //		7. Lấy danh sách các phần tử không trùng nhau
    public String distinctList (List<Integer> numbers) {return numbers.stream().distinct().toList().toString();}

    //		8. Lấy 5 phần tử đầu tiên trong mảng
    public  String fiveFirst (List<Integer> numbers) {return numbers.stream().limit(5).toList().toString();}

    //		9. Lấy phần tử từ thứ 3 -> thứ 5
    public  String fromThreeToFive (List<Integer> numbers) {return numbers.stream().skip(2).limit(3).toList().toString();}

    //		10. Lấy phần tử đầu tiên > 5
    public String fistGreaterThanFive (List<Integer> numbers) {return numbers.stream().filter(number -> number > 5).findFirst().get().toString();}

    @GetMapping("/")
    public String getHome(Model model) {
        model.addAttribute("questions", questions);
        return "index";
    }
    @GetMapping("/questions/{id}")
    public String getUserDetail(@PathVariable int id, Model model) {
        Question question = questions.stream().filter(u -> u.getId() == id).findFirst().orElse(null);
        model.addAttribute("question", question);
        return "question-detail";
    }



}
