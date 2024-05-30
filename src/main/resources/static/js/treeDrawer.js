const parsedData = JSON.parse(data);


class Person {
    constructor(id) {
        this.id = id;
        this.children = [];
    }

    addChild(child) {
        this.children.push(child);
    }

    getId()
    {
        return this.id;
    }
}

function buildFamilyTree(s, namesDict) {
    let personsDict = {};

    // Create Person instances for each entry in the dictionary
    for (let id in s) {
        personsDict[id] = new Person(id);
    }

    // Add children to parents
    for (let id in s) {
        let [ojca, matki, partnera] = s[id];
        let currentPerson = personsDict[id];

        if (ojca && personsDict[ojca]) {
            personsDict[ojca].addChild(currentPerson);
        }
        if (matki && personsDict[matki]) {
            personsDict[matki].addChild(currentPerson);
        }
    }

    // Find the root person (person without parents in the dictionary)
    let root = null;
    for (let id in s) {
        let [ojca, matki, partnera] = s[id];
        if (!ojca && !matki) {
            root = personsDict[id];
            break;
        }
    }

    return root;
}

function maxDepth(root) {
    if (root === null) {
        return 0;
    }
    let maxChildDepth = 0;
    for (let child of root.children) {
        maxChildDepth = Math.max(maxChildDepth, maxDepth(child));
    }
    return maxChildDepth + 1;
}

function getLevels(s, id, lev, level, root)
{
    for(let i in s)
    {
        if(s[i][2] == id)
        {
            lev[id] = [i, level];
            lev[i] = [id, level];
            break;
        }
        else
            lev[id] = [null, level]
    }

    for(let w of root.children)
    {
        getLevels(s, w.id, lev, level+1, w);
    }
}


let slow = {};
parsedData.forEach(item => {
    slow[item.id_osoby] = [item.id_ojca, item.id_matki, item.id_partnera];
});
let familyTreeRoot = buildFamilyTree(slow);
indexes = {}
getLevels(slow, familyTreeRoot.id, indexes, 0, familyTreeRoot);

let dpth = maxDepth(familyTreeRoot);

numbers_of_people_on_lvl = {}

for(let i = 0; i < dpth; i++)
{
    numbers_of_people_on_lvl[i] = 0;
}

for(let i in indexes)
{
    numbers_of_people_on_lvl[indexes[i][1]]++;
}

const canvas = document.getElementById("canvas");
const width = canvas.width;
const height = canvas.height;


for(let i in numbers_of_people_on_lvl)
{
    let num = numbers_of_people_on_lvl[i];


}



