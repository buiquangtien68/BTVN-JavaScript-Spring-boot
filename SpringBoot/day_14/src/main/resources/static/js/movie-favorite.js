
const toggleFavorite = async ()=>{
    if (favorite!==null) {
        await deleteFavorite(favorite.id);
    } else {
        await createFavorite();
    }
}

const createFavorite = async () =>{
    const data = {
        userId: user.id,
        movieId: currentMovie.id
    }
    try {
        let res = await axios.post("/api/favorites", data);
        toastr.success("Đã thêm vào danh sách yêu thích thành công")
        document.getElementById("favoriteButton").innerText = "Bỏ yêu thích";

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
            document.getElementById("favoriteButton").innerText = "Yêu thích";
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


