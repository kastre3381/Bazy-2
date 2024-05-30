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
    const canvas = document.getElementById("canvas");
    const vw = window.innerWidth;
    const vh = window.innerHeight;
    canvas.width = 0.5 * vw;
    canvas.height = 0.8 * vh;
}

window.addEventListener('load', resizeCanvas);
window.addEventListener('resize', resizeCanvas);
// resizeCanvas();
// Przykładowe użycie danych na stronie