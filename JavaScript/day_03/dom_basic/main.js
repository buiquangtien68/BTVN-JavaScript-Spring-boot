//Bài 1:
//Tạo 1 thẻ p có id=“text”, có nội dung bất kỳ (có thể tạo bằng HTML hay Javascript - tùy chọn). Yêu cầu
//Đặt màu văn bản thành #777
const text = document.getElementById("text");
text.style.color="#777";

//Đặt kích thước phông chữ thành 2rem
text.style.fontSize="2rem";

//Đặt nội dung HTML thành Tôi có thể làm <em> bất cứ điều gì </em> tôi muốn với JavaScript.
text.innerHTML="Tôi có thể làm <em> bất cứ điều gì </em> tôi muốn với JavaScript."


//Bài 2:
//Sử dụng vòng lặp để đặt màu chữ cho tất cả thẻ li thành màu blue (tạo thẻ ul-li bằng html)
const ul = document.querySelector("ul")
ul.classList.add("bai-2")
const liList =document.querySelectorAll(".bai-2 li");
Array.from(liList).forEach(item=>{
    item.style.color="blue";
})


//Bài 3:
//1. Thêm 3 thẻ <li> có nội dung Item 8, Item 9, Item 10 vào cuối danh sách
const list= document.getElementById("list");
for (let index = 8; index < 11; index++) {
    const li = document.createElement("li");
    li.innerText=`Item ${index}`;
    list.insertAdjacentElement("beforeend",li);
}

//2. Sửa nội dung cho thẻ <li> 1 thành màu đỏ (color)
const li1 =document.querySelector("#list li")
li1.style.color="red"

//3. Sửa background cho thẻ <li> 3 thành màu xanh (background-color)
const li3 = document.querySelector("#list li:nth-child(3)")
li3.style.backgroundColor="green"

//4. Remove thẻ <li> 4
const li4 = document.querySelector("#list li:nth-child(4)")
li4.parentNode.removeChild(li4)

//5. Thêm thẻ <li> mới thay thế cho thẻ <li> 4 bị xóa ở bước trước, thẻ <li> mới có nội dung bất kỳ
const liNew = document.createElement("li")
liNew.innerText="New item"
li3.insertAdjacentElement("afterend",liNew);
