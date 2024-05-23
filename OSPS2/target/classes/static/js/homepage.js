function toggleMenu() {
    const menu = document.getElementById('menu');
    menu.classList.toggle('open');
}

function performSearch() {
    var selectElement = document.getElementById('location-select');
    var selectedValue = selectElement.value;

    if (selectedValue) {
        window.location.href = '/' + selectedValue;
    } else {
        alert('請選擇一個地點');
    }
}

document.addEventListener('DOMContentLoaded', function() {
    const text = "Easy Parking.";
    let index = 0;
    const speed = 150; // Typing speed in milliseconds

    function typeWriter() {
        if (index < text.length) {
            document.getElementById("typewriter-text").innerHTML += text.charAt(index);
            index++;
            setTimeout(typeWriter, speed);
        }
    }

    typeWriter();
});