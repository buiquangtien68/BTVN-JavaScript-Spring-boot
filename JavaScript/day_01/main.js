//Bài 1:
let calculateFactorial = (num) =>{
    let ans =1;
    for (let index = num; index >0; index--) {
        ans *=index;
    }
    return ans;
}
console.log(calculateFactorial(5));

//Bài 2:
let reverseString = (str) =>{
    let reverseStr="";
    for (let index = str.length-1; index >=0 ; index --) {
         reverseStr=reverseStr + str.charAt(index);
    }
    return reverseStr;
}
console.log(reverseString("hello"));

//Bài 3:
let translate =(nation)=>{
    switch (nation) {
        case "VN":
            console.log("Xin chào");
            break;
        case "EN":
            console.log("Hello")
            break;
        case "JP":
            console.log("Konnichiwa");
            break;
        case "IT":
            console.log("Ciao");
            break;
        case "KR":
            console.log("Annyeonghaseyo");
            break;
        case "FR":
            console.log("Bonjour");
            break;
        case "DE":
            console.log("Hallo");
            break;
        default:
            break;
    }
}
let nation ="FR"
translate(nation);

//Bài 4:
let subString=(str)=>{
    let subStr="";
    for (let index = 0; index < 10; index++) {
        subStr=subStr+str.charAt(index);
    }
    return subStr+"...";
}
console.log(subString("xinchaocacbandenvoiTechmaster"));


