console.log("in script!!!");
let countriesBtn = document.querySelector(".countries-btn");
let citiesBtn = document.querySelector(".cities-btn");
let statBtn = document.querySelector(".stat-btn");
let mainpageBtn = document.querySelector(".mainpage-btn");

let message1 = document.querySelector(".message1");
let message2 = document.querySelector(".message2");
let content = document.querySelector(".content");

countriesBtn.addEventListener("click", countries);
citiesBtn.addEventListener("click", cities);
statBtn.addEventListener("click", stat);
mainpageBtn.addEventListener("click", mainpage);

async function countries() {
    console.log("in countries!!!");
    message1.innerHTML = " ";
    message2.innerHTML = " ";
    content.innerHTML = " ";
    let response = await fetch('/myApp/json', {method: 'POST', headers: {operation: 'countries'}});

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
        alert("Ошибка HTTP: " + response.status);
    }
}

async function cities() {
    console.log("in cities!!!");
    message1.innerHTML = " ";
    message2.innerHTML = " ";
    content.innerHTML = " ";
    let response = await fetch('/myApp/json', {method: 'POST', headers: {operation: 'cities'}});

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
        alert("Ошибка HTTP: " + response.status);
    }
}

async function stat() {
    console.log("in stat!!!");
    message1.innerHTML = " ";
    message2.innerHTML = " ";
    content.innerHTML = " ";

    let response = await fetch('/myApp/json', {method: 'POST', headers: {operation: 'statistics'}});

    if (response.ok) {
        let json = await response.json();

        let div = document.createElement('div');
        div.className = "city-container";

        div = document.createElement('div');
        div.className = "city-container";
        div.innerHTML = `There are ${json[0]} countries </br> There are ${json[1]} cities </br> in DataBase`;
        content.append(div);

    }else {
            alert("Ошибка HTTP: " + response.status);
        }

}

function mainpage(){
    document.location.href = "/myApp/index";
}
