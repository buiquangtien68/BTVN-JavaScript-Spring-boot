package com.example.demoapp.utils;

import com.example.demoapp.model.Book;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Component
public class ExcelFileReader implements IFileReader{
    @Override
    public List<Book> readFile(String path) throws IOException {
        System.out.println("Đọc file Excel");//sử dụng ApaChe POI
        List<Book> books = new ArrayList<>();

        try {
            InputStream inputStream = new FileInputStream(path);
            Workbook workbook = WorkbookFactory.create(inputStream);

            Sheet sheet = workbook.getSheetAt(0); // Lấy sheet đầu tiên
            Iterator<Row> rowIterator = sheet.iterator();

            // Bỏ qua dòng tiêu đề nếu cần
            if (rowIterator.hasNext()) {
                rowIterator.next(); // Bỏ qua dòng tiêu đề
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                Book book = new Book();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    int columnIndex = cell.getColumnIndex();
                    switch (columnIndex) {
                        case 0:
                            book.setId((int) cell.getNumericCellValue());
                            break;
                        case 1:
                            book.setTitle(cell.getStringCellValue());
                            break;
                        case 3:
                            book.setAuthor(cell.getStringCellValue());
                            break;
                        case 2:
                            book.setYear((int) cell.getNumericCellValue());
                            break;
                        default:
                            // Handle if there are more columns in your Excel than expected
                            break;
                    }
                }
                books.add(book);
            }

            workbook.close();
            inputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions appropriately
        }

        return books;
    }
}
