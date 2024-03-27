let colors = [
    '#3498db',
    '#9b59b6',
    '#e74c3c',
    '#2c3e50',
    '#d35400',
]


const boxes = document.querySelector(".boxes");
const points = document.querySelector(".points")


const btn = document.getElementById("btn")
btn.addEventListener("click",()=>{
    colors.map(c => {
        const box = document.createElement("div")
        box.classList.add("box")
        box.style.backgroundColor = `${c}`
        boxes.insertAdjacentElement("beforeend",box)
    })
    points.innerText=Number(points.innerText)+colors.length;
    const box = document.querySelectorAll(".box")
    Array.from(box).map(b=> {
        b.addEventListener("click",()=>{
        boxes.removeChild(b)
        points.innerText=Number(points.innerText)-1;
    })
    })
})
console.log(btn.click())

