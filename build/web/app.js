// Mở gói cước (hiển thị overlay)
const cartIcon = document.querySelector(".cart-icon");
const packageOverlay = document.querySelector(".package-overlay");
const closeIcon = document.querySelector(".close-icon");

if (cartIcon && packageOverlay && closeIcon) {
    cartIcon.addEventListener("click", function() {
        packageOverlay.style.opacity = "1";
        packageOverlay.style.visibility = "visible";
    });

    closeIcon.addEventListener("click", function() {
        packageOverlay.style.opacity = "0";
        packageOverlay.style.visibility = "hidden";
    });
}