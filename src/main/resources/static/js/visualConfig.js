function buttons(id)
{
    document.getElementById("addPersonForm").style.display = "none";
    document.getElementById("deletePersonForm").style.display = "none";
    document.getElementById("chooseTreeForm").style.display = "none";
    document.getElementById(id).style.display = "block";
    document.getElementById("table").style.display = "none";
}

function resizeCanvas()
{
    var canvas = document.getElementById("canvas");
    var vw = window.innerWidth;
    var vh = window.innerHeight;
    canvas.width = 0.5 * vw;
    canvas.height = 0.8 * vh;
}

window.addEventListener('load', resizeCanvas);
window.addEventListener('resize', resizeCanvas);

// Przykładowe użycie danych na stronie