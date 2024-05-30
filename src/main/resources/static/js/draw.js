function draw()
{
    const canvas = document.getElementById("canvas");
    const width = canvas.width;
    const height = canvas.height;
    let dpth = maxDepth(familyTreeRoot);
    let distY = (height)*0.8 /(dpth - 1);
    let startY = (height)*0.1/2.0;
    let visited = [];
    let pos = [];
    let circles = []
    let cId = 0;

    for(let depth = 0; depth < dpth; depth++)
    {
        let num = numbers_of_people_on_lvl[depth];
        let distX;
        if(num > 1) distX = (width)*0.8/(num-1);
        else distX = width/2;
        let startX = (width)*0.2/2.0;
        let id = 0;

        for(let i in indexes) visited[i] = -1;

        for(let i in indexes)
        {
            if(indexes[i][1] === depth && indexes[i][0] == null && visited[i] == -1)
            {
                circles[i] = [startX + (id++)*distX, startY + depth*distY, null];
                visited[i] = 1;
            }
            else if(indexes[i][1] === depth && indexes[i][0] != null && (visited[i] == -1 || visited[indexes[i][0]] == -1))
            {
                circles[i] = [startX + (id++)*distX, startY + depth*distY, indexes[i][0]];
                circles[indexes[i][0]] = [startX + (id++)*distX, startY + depth*distY, i];
                visited[i] = 1;
                visited[indexes[i][0]] = 1;
            }

        }

    }

    const ctx = canvas.getContext('2d');
    ctx.clearRect(0, 0, canvas.width, canvas.height);


    const imgMan = new Image();
    imgMan.src = '/images/man.webp';
    const imgWoman = new Image();
    imgWoman.src = '/images/woman.webp';

    let already_drawn = [];

    for(let i in circles) already_drawn[i] = -1;

    for(let i in circles)
    {
        const [x, y, partner] = circles[i];
        let scale = 0.1;
        if(partner != null && already_drawn[i] == -1 && already_drawn[partner] == -1)
        {
            ctx.beginPath();
            ctx.moveTo(x, y);
            ctx.lineTo(circles[partner][0], circles[partner][1]);
            ctx.stroke();
            already_drawn[i] = 1;
            already_drawn[partner] = 1;
        }
    }

    for(let i in circles)
    {
        if(slow[i][0] != null && slow[i][1] != null)
        {
            let id_ojca = slow[i][0];
            let id_matki = slow[i][1];

            if (circles[id_ojca] && circles[id_matki]) { // Check if parent ids exist in circles
                const [x_ojca, y_ojca, w1] = circles[id_ojca];
                const [x_matki, y_matki, w2] = circles[id_matki];

                ctx.beginPath();
                ctx.moveTo((x_ojca + x_matki) / 2.0, y_ojca);
                ctx.lineTo(circles[i][0], circles[i][1]);
                ctx.stroke();
            }
        }
    }

    function drawMultilineTextWithBackground(text, x, y, lineHeight) {
        const lines = text.split('\n');
        ctx.textBaseline = 'top'; // Align text to top
        ctx.fillStyle = 'black'; // Text color

        lines.forEach((line, index) => {
            // Measure text width
            const textWidth = ctx.measureText(line).width;
            // Draw background rectangle
            ctx.fillStyle = 'white';
            ctx.fillRect(x, y + (index * lineHeight), textWidth, lineHeight);
            // Draw text
            ctx.fillStyle = 'black';
            ctx.fillText(line, x, y + (index * lineHeight));
        });
    }

    for(let i in circles)
    {
        const [x, y, partner] = circles[i];
        let scale = 0.1;
        ctx.beginPath()
        if(plec[i] === "mezczyzna")
            ctx.drawImage(imgMan, x - imgMan.width * scale / 2, y - imgMan.height * scale / 2, imgMan.width * scale, imgMan.height * scale);
        else
            ctx.drawImage(imgWoman, x - imgMan.width * scale / 2, y - imgMan.height * scale / 2, imgMan.width * scale, imgMan.height * scale);

        let str = "ID: " + i + "\n" + allDataXML[i][0] + " " + allDataXML[i][1] + "\nur. " + allDataXML[i][2];
        if(allDataXML[i][3] != null) str += '\nzm. ' + allDataXML[i][3];

        drawMultilineTextWithBackground(str, x - imgMan.width * scale / 2, y + imgMan.height * scale*3.0/4.0, 10);
    }
}

window.onload = draw;