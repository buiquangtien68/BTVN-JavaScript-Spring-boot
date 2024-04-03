//1.Hiển thị danh sách công việc ban đầu, nếu không có công việc nào trong danh sách thì hiển thị Danh sách công việc trống
//Lấy danh sách toDo trên server
let toDoList =[]
const API_URL = "http://localhost:8000/toDo"
const getAllToDoList = async()=>{
    try{
        const res = await axios.get(API_URL)
        console.log(res)
        toDoList=res.data;
        renderToDoList(toDoList)
    }catch(err){
        console.log(err)
    }
}

const ul = document.querySelector("ul")
const renderToDoList = (toDoList)=>{
    ul.innerHTML="";
    if (toDoList.length===0) {
        ul.insertAdjacentHTML("afterbegin","<li>Danh sách công việc trống</li>")
        return;
    }
    let html="";
    toDoList.forEach(toDo => {
        html+=`
        <li>
            <input 
            type="checkbox" 
            ${toDo.status ? "checked" : ""}
            onchange="toggleStatus(${toDo.id})"
            />
            <span class=${toDo.status ? "active" : ""}>${toDo.title}</span>
            <button onclick ="editToDo(${toDo.id})">Edit</button>
            <button onclick ="deleteToDo(${toDo.id})">Delete</button>
        </li>
        `
    })
    ul.innerHTML=html;
}

//2. Thêm công việc mới (nếu title rỗng -> alert “Tên công việc không được để trống”)

const inputToDo = document.getElementById("input-todo");
console.log(inputToDo)
const btnAdd = document.getElementById("btn-add");

btnAdd.addEventListener("click",async()=>{
    //lấy nd trong ô input
    const title = inputToDo.value.trim();

    //kiểm tra nd có rỗng ko
    if(title===""){
        alert("Vui lòng nhập công việc")
        return;
    }
    //Tạo ra to do mới
    const newToDo = {
    title: title,
    status: false
    }

    try {
        const res = await axios.post(API_URL,newToDo)
         //Thêm vao mảng
        toDoList.push(res.data);
          //Render lại giao diện mới
        renderToDoList(toDoList);
        inputToDo.value=""
        inputToDo.focus()
    } catch (error) {
        console.log(error)
    }
})

//3. Xóa công việc (cần confirm trước khi xóa, sử dụng window.confirm)
const deleteToDo = async (id)=>{
    const confirm = window.confirm("Bạn có chắc chắn muốn xóa ko")
    if(confirm){
        try {
            const deleteToDo = await axios.delete(`${API_URL}/${id}`)
            toDoList=toDoList.filter(toDo => toDo.id!==id);
            renderToDoList(toDoList)
        } catch (error) {
            console.log(error)
        }
      
    }
}


//4. Chỉnh sửa tiêu đề công việc (sử dụng window.prompt cho đơn giản)
const editToDo = (id)=>{
    toDoList.forEach(async(toDo) =>{
        if (toDo.id===id) {
        let title= window.prompt("Mời bạn nhập công việc khác",toDo.title)
        if( title== ""){
            alert("Vui lòng nhập công việc")
            return;
        }
        if(title==null){
            return
        }
        const updatedToDo = {
            title: title,
            status: toDo.status
        }
        try {
            const editName = await axios.put(`${API_URL}/${id}`,updatedToDo)
            toDo.title=title;
            renderToDoList(toDoList)
        } catch (error) {
            console.log(error)
        }
        
        }
    })
}

//5. Thay đổi trạng thái công việc (status)
const toggleStatus = (id)=>{
    toDoList.forEach(async (toDo) =>{
        if (toDo.id===id) {
           if(toDo.status==true){
            const updatedToDo = {
                title: toDo.title,
                status: false
            }
            const stausFalse = await axios.put(`${API_URL}/${id}`,updatedToDo)
            toDo.status=false
           }else{
            const updatedToDo = {
                title: toDo.title,
                status: true
            }
            const statusTrue = await axios.put(`${API_URL}/${id}`,updatedToDo)
            toDo.status=true
           }
           renderToDoList(toDoList)
           return;
        }
    })
}
const searchToDo = document.getElementById("search-todo")
const btnSearch = document.getElementById("btn-search")
const ulResul =  document.getElementById("ul-result")

btnSearch.addEventListener("click", async ()=>{
    const value = searchToDo.value.trim();
    if (value==="") {
        alert("Mời bạn nhập tên công việc")
        return;
    }
    let foundToDo1 = await axios.get(`${API_URL}?title_like=${value}`) 
    let html =""
    console.log(foundToDo1)
    if(foundToDo1.data.length==0){
        html+=`<li>Không có công việc này</li>`
    }else{
        foundToDo1.data.forEach(toDo =>{
            html+=`
            <li>
                <input 
                type="checkbox" 
                ${toDo.status ? "checked" : ""}
                onchange="toggleStatus(${toDo.id})"
                />
                <span class=${toDo.status ? "active" : ""}>${toDo.title}</span>
                <button onclick ="editToDo(${toDo.id})">Edit</button>
                <button onclick ="deleteToDo(${toDo.id})">Delete</button>
            </li>
            `
        })
        console.log(html)
    }
    ulResul.innerHTML=html;
    renderToDoList(toDoList);
    searchToDo.value=""
    
})
getAllToDoList();


