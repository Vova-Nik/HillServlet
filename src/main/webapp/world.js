console.log("in script!!!");
let state = "init"; // countries - countries, cities - cities, statistics - statistics
let countriesBtn = document.querySelector(".countries-btn");
let citiesBtn = document.querySelector(".cities-btn");
let statBtn = document.querySelector(".stat-btn");
let downloadBtn = document.querySelector(".download-btn");
update();

let message1 = document.querySelector(".message1");
let message2 = document.querySelector(".message2");
let content = document.querySelector(".content");

countriesBtn.addEventListener("click", countries);
citiesBtn.addEventListener("click", cities);
statBtn.addEventListener("click", stat);
downloadBtn.addEventListener("click", download);

async function countries() {
    state = "countries";
    update();
    message1.innerHTML = " ";
    message2.innerHTML = " ";
    content.innerHTML = " ";
    let response = await fetch('/myApp/index', { method: 'POST', headers: { operation: 'show', entity: state } });

    if (response.ok) {
        let json = await response.json();
        console.log(json);
        let div = document.createElement('div');
        div.className = "city-container";
        let str = " ";
        for (let i = 0; i < json.length; i++) {
            str =
                `<div class='city-item country-code'>${json[i].code}</div>
                <div class='city-item country-name'> ${json[i].name}</div>
                <div class='city-item country-capital'> ${json[i].cap}</div>
                <div class='city-item country-region'>${json[i].region}</div>
                <div class='city-item country-population'>${json[i].population}</div>
                <div class='city-item country-surfaceArea'>${json[i].surfaceArea}</div>`;
            div = document.createElement('div');
            div.className = "city-container";
            div.innerHTML = str;
            content.append(div);
        }
    } else {
        alert("HTTP error: " + response.status);
    }
}

async function cities() {
    state = "cities";
    update();
    message1.innerHTML = " ";
    message2.innerHTML = " ";
    content.innerHTML = " ";
    let response = await fetch('/myApp/index', { method: 'POST', headers: { operation: 'show', entity: state } });

    if (response.ok) {
        let json = await response.json();
        let div = document.createElement('div');
        div.className = "city-container";
        let str = " ";
        for (let i = 0; i < json.length; i++) {
            str =
                `<div class='city-item city-id'>${json[i].id}</div> 
                <div class='city-item city-name'> ${json[i].name}</div> 
                <div class='city-item city-countryCode'> ${json[i].countryCode}</div> 
                <div class='city-item city-district'>${json[i].district}</div> 
                <div class='city-item city-population'>${json[i].population}</div>`;
            div = document.createElement('div');
            div.className = "city-container";
            div.innerHTML = str;
            content.append(div);
        }
    } else {
        alert("HTTP error: " + response.status);
    }
}

async function stat() {
    state = "statistics";
    update();
    message1.innerHTML = " ";
    message2.innerHTML = " ";
    content.innerHTML = " ";

    let response = await fetch('/myApp/index', { method: 'POST', headers: { operation: 'show', entity: state } });

    if (response.ok) {
        let json = await response.json();

        let div = document.createElement('div');
        div.className = "city-container";

        div = document.createElement('div');
        div.className = "city-container";
        div.innerHTML = `There are ${json[0]} countries </br> There are ${json[1]} cities </br> in DataBase`;
        content.append(div);

    } else {
        alert("HTTP error: " + response.status);
    }
}

async function download() {
    // update();
    message1.innerHTML = " ";
    message2.innerHTML = " ";
    content.innerHTML = " ";
    //countries - countries, cities - cities, statistics - statistics, init
    console.log("State = ", state);

   let response = await fetch('/myApp/index', {method: 'POST', headers: {operation: 'download', entity: state}});

    if (response.ok) {
        let blob = await response.blob();
        let downloadRef = document.querySelector(".download");
        downloadRef.href = URL.createObjectURL(blob);
        downloadRef.click();
    } else {
        alert("HTTP error: " + response.status);
    }

}

function mainpage() {
    document.location.href = "/myApp/index";
}

function update() {
    if (state === "init") {
        downloadBtn.style.display = "none";
    } else {
        downloadBtn.style.display = "block";
    }
}
