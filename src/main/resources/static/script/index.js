function toggleDrawer() {
    const drawer=$("#drawer1")
    const mask=$("#drawerMask")
    if (drawer.hasClass("hidden")){
        drawer.removeClass("hidden")
        mask.show()
    }else{
        drawer.addClass("hidden")
        mask.hide()
    }
}