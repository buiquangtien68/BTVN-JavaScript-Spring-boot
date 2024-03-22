// Đề Bài
// Danh sách các sản phẩm có trong giỏ hàng
let products = [
    {
        name: "Iphone 13 Pro Max", // Tên sản phẩm
        price: 3000000, // Giá mỗi sản phẩm
        brand: "Apple", // Thương hiệu
        count: 2, // Số lượng sản phẩm trong giỏ hàng
    },
    {
        name: "Samsung Galaxy Z Fold3",
        price: 41000000,
        brand: "Samsung",
        count: 1,
    },
    {
        name: "IPhone 11",
        price: 15500000,
        brand: "Apple",
        count: 1,
    },
    {
        name: "OPPO Find X3 Pro",
        price: 19500000,
        brand: "OPPO",
        count: 3,
    },
]

// 1. In ra thông tin các sản phẩm trong giỏ hàng theo cấu trúc sau
// Tên - Giá - Thương Hiệu - Số lượng
// Ví dụ : OPPO Find X3 Pro - 19500000 - OPPO - 3
const printProduct = (products)=>{
    products.forEach(p => {
        console.log(`${p.name}-${p.price}-${p.brand}-${p.count}`);
    });
}
printProduct(products);

// 2. Tính tổng tiền tất cả sản phẩm trong giỏ hàng
// Tổng tiền mỗi sản phẩm = price * count
const totalPrice=(products)=>{
    return products.reduce((total,p)=>total+p.price*p.count,0);
}
console.log(totalPrice(products));

// 3. Tìm các sản phẩm của thuơng hiệu "Apple"
const findBrand=(products,brand)=>{
    return products.filter(p=>p.brand===brand);
}
console.log(findBrand(products,"Apple"));

// 4. Tìm các sản phẩm có giá > 20000000
const findByPrice  = (products,price)=>{
    return products.filter(p=>p.price>price);
}
console.log(findByPrice(products,20000000));

// 5. Tìm các sản phẩm có chữ "pro" trong tên (Không phân biệt hoa thường)
const findByKeyword = (products,keyword)=>{
    return products.filter(p=>p.name.toLowerCase().includes(keyword));
}
console.log(findByKeyword(products,"pro"))

// 6. Thêm 1 sản phẩm bất kỳ vào trong mảng product
products.push(
    {
        name: "Iphone 12",
        price: 25000000,
        brand: "Apple",
        count:2,
    }
)
console.log(products);
// 7. Xóa tất cả sản phẩm của thương hiệu "Samsung" trong giỏ hàng
const deleteByBrand =(products,brand)=>{
    products=products.filter(p=>p.brand!==brand);
    return products;
}
products=deleteByBrand(products,"Samsung");
console.log(products);

// 8. Sắp xếp giỏ hàng theo price tăng dần
products.sort((a,b)=>a.price-b.price)

// 9. Sắp xếp giỏ hàng theo count giảm dần
products.sort((a,b)=>b.count-a.count)

// 10. Lấy ra 2 sản phẩm bất kỳ trong giỏ hàng
const randomProducts = (products,countOfProduct)=>{
    let randomProducts =[];
    for (let index = 0; index < countOfProduct; index++) {
        randomProducts.push(products[Math.floor(Math.random()*products.length)]);
    }
    return randomProducts;
}
console.log(randomProducts(products,2));
console.log(randomProducts(products,2));