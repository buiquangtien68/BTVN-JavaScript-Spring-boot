const btn = document.getElementById('btn');
const image = document.getElementById('image');
const select = document.getElementById('breed-list');
const ul = document.querySelector("ul");
const result = document.querySelector(".result")

const getBreedsList = async ()=>{
    const breedsList = await axios.get("https://dog.ceo/api/breeds/list/all");
    renderBreedsList(breedsList.data.message)
}

 const renderBreedsList = (breedsList) => {
    let optionHtml = ""
    for (const key in breedsList){
        optionHtml+=`
        <option value=${key}>${key}</option>;
        `
    }
    select.innerHTML=optionHtml;
 }
 getBreedsList();

 btn.addEventListener("click",async()=>{
    console.log(select.value)
    const subBreedsList = await axios.get(`https://dog.ceo/api/breed/${select.value}/list`);
    renderSubBreedsList(subBreedsList.data.message)
})
const renderSubBreedsList = (subBreedsList) => {
    let liHtml = ""
    console.log(subBreedsList.length)
    if (subBreedsList.length==0) {
        liHtml = "<li>Không có sub breed</li>"
    }else{
        subBreedsList.forEach(subBreed => {
            liHtml+=`
            <li><a href="#">${subBreed}</a></li>
            `
        });
    }
    ul.innerHTML=liHtml;
    renderImage()
 }
 const renderImage = ()=>{
    const a = document.querySelectorAll("a")
    Array.from(a).forEach(a=>{
   
     a.addEventListener("click",async()=>{
        try{
            const res = await axios.get(`https://dog.ceo/api/breed/${select.value}/${a.innerText}/images/random`)
            console.log(res.data.message)
            image.src = res.data.message
            result.classList.remove("hide");
        }catch(err){
            console.log(err)
        }
     })
 })
 }
 





