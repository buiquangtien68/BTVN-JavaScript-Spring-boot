# BTVN:
- Trong phần main.js

# Lab Buổi 1:
## Lab2:
- L2 - Bài 1:
``` js
let checkMark = (mark)=>{
    if(mark>=85){
        return "A";
    }else if(70 <= mark && mark < 85){
        return "B";
    }else if(40 <= mark && mark <70){
        return "C";
    }else{
        return "D";
    }
}
console.log(checkMark(68));
```

- L2 - Bài 2:
```js
let max=(a,b)=>{
    if(a>b){
        console.log(`Số lớn hơn là ${a}`);
    }else if(a<b){
        console.log(`Số lớn hơn là ${b}`);
    }else{
        console.log("Hai số bằng nhau")
    }
}
max(6,8);
```
- L2 - Bài 3:
```js
let ans=(num)=>{
    if(num<0){
        console.log("Số âm");
    }else if(num>0){
        console.log("Số dương");
    }else{
        console.log("Đây là số 0")
    }
}
ans(-6);
```

- L2 - Bài 4:
```js
let ans2=(num)=>{
    if(num%2==0){
        console.log("Số chẵn");
    }else{
        console.log("Số lẻ")
    }
}
ans2(13);
```

- L2 - Bài 5:
```js
let ans3=(num)=>{
    if(num%3==0 && num%5==0){
        return true;
    }else{
        return false;
    }
}
console.log(ans3(30));
```
- L2 - Bài 6:
```js
let ans4=(a,b,c)=>{
    if(a+b==c){
        return true;
    }else{
        return false;
    }
}
console.log(ans4(1,2,3));
```

## Lab 3
- L3 - Bài 1:
```js
let day =4;
switch (day) {
    case 0:
        console.log("Hôm nay là chủ nhật");
        break;
    case 1:
        console.log("Hôm nay là thứ 2");
        break;
    case 2:
        console.log("Hôm nay là thứ 3");
        break;
    case 3:
        console.log("Hôm nay là thứ 4");
        break;
    case 4:
        console.log("Hôm nay là thứ 5");
        break;
    case 5:
        console.log("Hôm nay là thứ 6");
        break;
    case 6:
        console.log("Hôm nay là thứ 7");
        break;
    default:
        break;
}
```

- L3 - Bài 2:
```js
let month =4;
switch (month) {
    case 1:
    case 2:
    case 3:
        console.log("Mùa Xuân");
        break;
    case 4:
    case 5:
    case 6:
        console.log("Mùa Hè");
        break;
    case 7:
    case 8:
    case 9:
        console.log("Mùa Thu");
        break;
    case 10:
    case 11:
    case 12:
        console.log("Mùa Đông");
        break;
    default:
        break;
}
```

## Lab 4
- L4 - Bài 1:
```js
let repeatString = (str)=>{
    let repeatStr="";
    for (let index = 0; index < 10; index++) {
            repeatStr+=str;
        }
    return repeatStr;
    }
console.log(repeatString("a"));
```

- L4 - Bài 2:
```js
let repeatString = (str)=>{
    let repeatStr="";
    for (let index = 0; index < 10; index++) {
        if(index!=9){
            repeatStr+=str+"-";
        }else{
            repeatStr+=str;
        }
        
    }
    return repeatStr;
}
console.log(repeatString("a"));
```

- L4 - Bài 3:
```js
let repeatString = (str,num)=>{
    let repeatStr="";
    for (let index = 0; index < num; index++) {
        if(index!=num-1){
            repeatStr+=str+"-";
        }else{
            repeatStr+=str;
        }
        
    }
    return repeatStr;
}
console.log(repeatString("a",5));
```

- L4 - Bài 4:
```js
let sum5=0;
for (let index = 0; index <= 100; index++) {
    if(index%5==0){
        sum5+=index;
    }
}
console.log(sum5);
```

- L4 - Bài 5:
```js
const PI = 3.14;
let vSpherical =(r)=>{
    return (4/3)*PI*Math.pow(r, 3);
}
console.log(vSpherical(2));
```

- L4 - Bài 6:
```js
let sum =(a,b)=>{
    let sum=0;
    if(a>b){
        for (let index = b+1; index < a; index++) {
            sum+=index;
        }
    }else{
        for (let index = a+1; index < b; index++) {
            sum+=index;
        }
    }
    return sum;
}
console.log(sum(8,3))
```

- L4 - Bài 7:
```js
let isPrime=(num)=> {
  if (num <= 1) {
    return false;
  }
  for (let i = 2; i <= Math.sqrt(num); i++) {
    if (num % i == 0) {
      return false;
    }
  }
  return true;
}
console.log(isPrime(43));
```

- L4 - Bài 8:
```js
let sumOfPrimes=(num)=>{
    let sum = 0;
    for (let i = 2; i <= num; i++) {
      if (isPrime(i)) {
        sum += i;
      }
    }
    return sum;
  }
  let isPrime=(num)=> {
    if (num <= 1) {
      return false;
    }
    for (let i = 2; i <= Math.sqrt(num); i++) {
      if (num % i == 0) {
        return false;
      }
    }
    return true;
  }
  console.log(sumOfPrimes(5))
```

- L4 - Bài 9:
```js
let sum = (num)=>{
    let sum=0;
    for (let index = 0; index < num; index++) {
        if(num%index==0){
            sum+=index;
        }
    }
    return sum;
}
console.log(sum(4));
```


