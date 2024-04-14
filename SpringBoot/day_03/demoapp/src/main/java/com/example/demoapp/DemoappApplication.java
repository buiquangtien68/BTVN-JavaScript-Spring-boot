package com.example.demoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

// @Bean phải được định nghĩa trong class được chú thích bởi @Configuration
@SpringBootApplication
public class DemoappApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DemoappApplication.class, args);
		List<Integer> numbers = List.of(10, 5, 3, 12, 6, 7, 5, 3);

//		1. Duyệt numbers
		numbers.stream().forEach(System.out::print);

//		2. Tìm các giá trị chẵn trong list
		List<Integer> evenNumber = numbers.stream().filter(number -> number % 2 == 0).toList();

//		3. Tìm các giá trị > 5 trong list
		List<Integer> num1 = numbers.stream().filter(number -> number >5).toList();

//		4. Tìm giá trị max trong list
		int max = numbers.stream().reduce(0, Integer::max);

//		5. Tìm giá trị min trong list
		int min = numbers.stream().reduce(0, Integer::min);

//		6. Tính tổng các phần tử của mảng
		int sum = numbers.stream().reduce(0, Integer::sum);

//		7. Lấy danh sách các phần tử không trùng nhau
		List<Integer> distinctNum = numbers.stream().distinct().toList();

//		8. Lấy 5 phần tử đầu tiên trong mảng
		List<Integer> limitNum = numbers.stream().limit(5).toList();

//		9. Lấy phần tử từ thứ 3 -> thứ 5
		List<Integer> num3 = numbers.stream().limit(5).skip(2).toList();

//		10. Lấy phần tử đầu tiên > 5
		int fistGreaterThanFive = numbers.stream().filter(number -> number > 5).findFirst().get();

//		11. Kiểm tra xem list có phải là list chẵn hay không
		boolean isEvenList = numbers.stream().allMatch(number -> number % 2 == 0);

//		12. Kiểm tra xem list có phần tử > 10 hay không
		boolean isGreaterThanTen = numbers.stream().anyMatch(number -> number > 10);

//		13. Có bao nhiêu phần tử > 5
		int greaterThanFiveSize = numbers.stream().filter(number -> number>5).toList().size();

//		14. Nhân đôi các phần tủ trong list và trả về list mới
		List<Integer> multiplyNumber = numbers.stream().map(number -> number*2).toList();

//		15. Kiểm tra xem list không chứa giá trị nào = 8 hay không
		boolean containsEight = numbers.stream().anyMatch(num -> num != 8);

	}
}