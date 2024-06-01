
const toggleFavorite = async (id)=>{
    if (favorites.some(favorite => favorite.movie.id === currentMovie.id)) {
        await deleteFavorite(id)
    } else {
        await createFavorite()
    }
}
const btnFavoriteGroup =document.getElementById("btnFavoriteGroup")

const renderFavorites = favorites =>{
    let html = "";
    if (favorites.some(favorite => favorite.movie.id === currentMovie.id)) {
        html = `
            <button id="favoriteButton" class="favorite-btn" onclick="toggleFavorite(currentMovie.id)" style="background-color: green; color: white; padding: 10px 20px; border: none; border-radius: 5px; cursor: pointer;">Bỏ yêu thích</button>
        `;
    } else {
        html = `
            <button id="favoriteButton" class="favorite-btn" onclick="toggleFavorite()" style="background-color: green; color: white; padding: 10px 20px; border: none; border-radius: 5px; cursor: pointer;">Yêu thích</button>
        `;
    }
    btnFavoriteGroup.innerHTML = html;
}

const createFavorite = async () =>{
    const data = {
        userId: user.id,
        movieId: currentMovie.id
    }
    try {
        let res = await axios.put("/api/favorites", data);
        toastr.success("Đã thêm vào danh sách yêu thích thành công")
        favorites.unshift(res.data)
        console.log(favorites)
        renderFavorites(favorites)

    } catch (e) {
        console.log(e)
    }

}

const deleteFavorite = async (id) =>{
    const confirm = window.confirm("Bạn có chắc chắn muốn xóa ko")
    if(confirm){
        try {
            const deleteFavor = await axios.delete(`/api/favorites/${id}`)
            console.log("Sự kiện xóa Favor")
            favorites=favorites.filter(f=>f.movie.id !==id)
            console.log(favorites)
            renderFavorites(favorites)
            toastr.success("Xóa khỏi danh sách yêu thích thành công")
        } catch (error) {
            console.log(error)
        }
    }
}
const btnFavoriteAnnotation=document.getElementById("favoriteButtonAnnotation");
if (btnFavoriteAnnotation){
    btnFavoriteAnnotation.addEventListener("click", ()=>{
        toastr.error("Cần phải đăng nhập mới thêm được vào danh sách yêu thích")
    })
}


