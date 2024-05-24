toastr.options = {
    "closeButton": false,
    "debug": false,
    "newestOnTop": false,
    "progressBar": false,
    "positionClass": "toast-top-right",
    "preventDuplicates": false,
    "onclick": null,
    "showDuration": "300",
    "hideDuration": "1000",
    "timeOut": "5000",
    "extendedTimeOut": "1000",
    "showEasing": "swing",
    "hideEasing": "linear",
    "showMethod": "fadeIn",
    "hideMethod": "fadeOut"
}
const logout = async ()=>{
    console.log("swuj kieenj aans nuts")
    try {
        let res = await axios.post("/api/auth/logout");
        toastr.success("Đăng xuất thành công")
        setTimeout(()=>{
            window.location.href='/'
        },1000)
    } catch (e) {
        console.log(e)
        toastr.error(e.response.data.message);
    }
}
