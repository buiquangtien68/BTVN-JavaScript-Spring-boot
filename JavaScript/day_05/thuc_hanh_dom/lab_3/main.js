const questions = [
    {
        title: "1 + 1 bằng bao nhiêu?",
        choices: ["1", "2", "3", "4"],
        answer: "2"
    },
    {
        title: "Số nào sau đây là số nguyên tố?",
        choices: ["22", "4", "25", "11"],
        answer: "11"
    },
    {
        title: "Căn bậc hai của 81 là bao nhiêu?",
        choices: ["7", "8", "9", "10"],
        answer: "9"
    },
    {
        title: "15% của 100 bằng bao nhiêu?",
        choices: ["10", "15", "20", "25"],
        answer: "15"
    },
    {
        title: "Số nào sau đây chia hết cho 3?",
        choices: ["14", "22", "27", "32"],
        answer: "27"
    }
];

let currentQuestionIndex=0;
let score=0;
let yourAnswer=[];
let correctAnswer=[];
const titleQuestion = document.querySelector("#question p")
const choices = document.querySelector(".choices")

const renderQuestion =(questions) =>{

    //Lấy thông tin câu hỏi
    const currentQuestion = questions[currentQuestionIndex]

    // Hiển thị tiêu đề 
    titleQuestion.innerText = `Câu ${currentQuestionIndex+1}: ${currentQuestion.title}`
    
    // Hiển thị các lựa chọn
    let choiceHtml=""
    currentQuestion.choices.forEach((choice,index)=> {
        choiceHtml+=`
        <div class="choice-item">
            <input type="radio" name="choice" id="${index}"value="${choice}">
            <label for="${index}">${choice}</label>
        </div>`
    });
    choices.innerHTML=choiceHtml;
}
renderQuestion(questions)

const btnFinish = document.getElementById("btn-finish")
const btnNext = document.getElementById("btn-next")
btnNext.addEventListener("click",()=>{
    //Kiểm tra ng dùng đã chọn câu hỏi chưa
    const selectedChoice = document.querySelector("input[name='choice']:checked")
    if(!selectedChoice){
        alert("Bạn chưa chọn câu trả lời")
        return
    }
    //Lưu lại đáp án của người dùng
    if (selectedChoice.value===questions[currentQuestionIndex].answer) {
        score++
    }
    console.log(selectedChoice.value)
    console.log(questions[currentQuestionIndex].answer)
    //Next câu hỏi
    currentQuestionIndex++
    renderQuestion(questions)

    //Khi đến câu hỏi cuối thì ẩn nút Next và hiển thị nút Finish 
    if (currentQuestionIndex==questions.length-1) {
        btnFinish.classList.remove("hide")
        btnNext.classList.add("hide")
    }
})

let scored =false;
btnFinish.addEventListener("click",()=>{
    //Kiểm tra ng dùng đã chọn câu hỏi chưa
    const selectedChoice = document.querySelector("input[name='choice']:checked")
    if(!selectedChoice){
        alert("Bạn chưa chọn câu trả lời")
        return
    }
    //Lưu lại đáp án của người dùng
    if (selectedChoice.value===questions[currentQuestionIndex].answer && scored==false) {
        score++
    }
    scored=true;
    // console.log(selectedChoice.value)
    // console.log(questions[currentQuestionIndex].answer)
    alert(`Điểm của bạn là ${score/questions.length*10} (${score}/${questions.length})`)
})


