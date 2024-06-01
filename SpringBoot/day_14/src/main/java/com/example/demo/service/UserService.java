package com.example.demo.service;

import com.example.demo.entities.Movie;
import com.example.demo.entities.User;
import com.example.demo.model.dataChart.MovieData;
import com.example.demo.model.dataChart.UserData;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getUserInCurrentMonth() {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = LocalDate.now().withDayOfMonth(1);
        return userRepository.findByCreatedAtBetween(startDate, endDate);
    }

    public List<UserData> getUserDataInFiveMonthsNearly() {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = LocalDate.now().minusMonths(4).withDayOfMonth(1);
        // Khởi tạo Map để lưu trữ dữ liệu của các tháng
        Map<YearMonth, Integer> userCountByMonth = new HashMap<>();

        // Khởi tạo tất cả các tháng trong khoảng thời gian với số lượng phim ban đầu là 0
        YearMonth currentMonth = YearMonth.from(startDate);
        while (!currentMonth.isAfter(YearMonth.from(endDate))) {
            userCountByMonth.put(currentMonth, 0);
            currentMonth = currentMonth.plusMonths(1);
        }

        // Lấy danh sách các bộ phim trong khoảng thời gian đã cho
        userRepository.findByCreatedAtBetween(startDate, endDate).forEach(user -> {
            YearMonth userYearMonth = YearMonth.from(user.getCreatedAt());

            // Kiểm tra xem tháng này đã có trong Map chưa, nếu chưa thì thêm vào và đếm là 1
            userCountByMonth.put(userYearMonth, userCountByMonth.getOrDefault(userYearMonth, 0) + 1);
        });

        // Chuyển dữ liệu từ Map sang danh sách MovieData
        List<UserData> userDataList = new ArrayList<>();
        userCountByMonth.forEach((yearMonth, userCount) -> {
            userDataList.add(UserData.builder()
                    .year(yearMonth.getYear())
                    .month(yearMonth.getMonthValue())
                    .userCount(userCount)
                    .build());
        });
        // Sắp xếp theo ngày tăng dần
        userDataList.sort(Comparator.comparing(userData -> YearMonth.of(userData.getYear(), userData.getMonth())));

        return userDataList;
    }


    public List<User> getAllUsers() {
        return userRepository.findAll(Sort.by("createdAt").descending());
    }
}
