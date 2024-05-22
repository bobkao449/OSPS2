function toggleMenu() {
    const menu = document.getElementById('menu');
    menu.classList.toggle('open');
}

function performSearch() {
    const searchBar = document.querySelector('.search-bar');
    const query = searchBar.value;
    const resultsContainer = document.getElementById('search-results');

    // 模擬搜尋結果
    const results = `Results for "${query}"`;
    resultsContainer.textContent = results;

    // 設置結果顯示一秒後清除
    setTimeout(() => {
        resultsContainer.textContent = '';
    }, 1000);
}

document.addEventListener('DOMContentLoaded', function() {
    const text = "Easy Parking.";
    let index = 0;
    const speed = 200; // Typing speed in milliseconds

    function typeWriter() {
        if (index < text.length) {
            document.getElementById("typewriter-text").innerHTML += text.charAt(index);
            index++;
            setTimeout(typeWriter, speed);
        }
    }

    typeWriter();
});