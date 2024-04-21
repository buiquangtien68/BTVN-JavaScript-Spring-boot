package com.example.demo.response.impl;

import com.example.demo.response.PageResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageResponseImpl<T> implements PageResponse<T> {
    List<T> data;
    int currentPage;
    int pageSize;

    @Override
    public int getCurrentPage() {
        return currentPage;
    }

    @Override
    public List<T> getContent() {
        return data.subList((currentPage-1)*pageSize,Math.min(currentPage * pageSize,data.size()));
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public int getTotalPages() {
        return (int) Math.ceil((double) data.size() / (double) pageSize);
    }

    @Override
    public int getTotalElements() {
        return data.size();
    }
}
