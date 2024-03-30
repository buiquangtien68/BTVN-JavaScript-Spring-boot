const btn = document.getElementById('btn');
const image = document.getElementById('image');
const select = document.getElementById('breed-list');

const getBreedsList = async ()=>{
    const breedsList = await axios.get("https://dog.ceo/api/breeds/list/all");
    renderBreedsList(breedsList.data.message)
}
 const renderBreedsList = (breedsList)=>{
    let optionHtml =""
    for (const key in breedsList){
        optionHtml+=`
        <option value=${key}>${key}</option>;
        `
    }
    select.innerHTML=optionHtml;
 }
 getBreedsList();

 btn.addEventListener("click", async ()=>{
    try{
        const res = await axios.get(`https://dog.ceo/api/breed/${select.value}/images/random`)
        console.log(res.data.message)
        image.src = res.data.message
    }catch(err){
        console.log(err)
    }
 })

