let coordinates = [[0, 0], [0, 0], [0, 0], [0, 0], [0, 0]];
//x, y and user id on map

let pid1 = 1;
let pid2 = 1;
let pid3 = 1;
let pid4 = 1;
let pid5 = 1;
let gmid;
let current_role = "gm";
let myPId = 1;
let sessionId;

function init(sessionID, pId) {
    //TODO: ajax query
    /*
    myPId = userId;
    sessionId = sessionID;
    let gameInfo = getGameInfoAjax(sessionID, myId);
    current_role = gameInfo.current_role;
    pid1 = gameInfo.cId1;
    pid2 = gameInfo.cId2;
    pid3 = gameInfo.cId3;
    pid4 = gameInfo.cId4;
    pid5 = gameInfo.cId5;
    gmid = gameInfo.gmid;
    */
    if (current_role === "gm") {
        let shop = document.getElementById("shop");
        remove_children(shop);
        shop.id = "endGame";
        let newButton = document.createElement("button");
        newButton.innerText = "Закончить игру";
        newButton.setAttribute("onclick", "endGame();");
        newButton.setAttribute("class", "end_game_button");
        shop.appendChild(newButton);
    }
}

function endGame() {
    //TODO: ajax query
    //to end game for other people
    if (confirm("Вы уверены, что хотите завершить игру?")) {

    }
}
function exitGame() {
    //TODO: ajax query
    //to end game only for me
    if (current_role === "gamer") {
        if (confirm("Вы уверены, что хотите покинуть игру?")) {

        }
    }
}

window.onload = function (ev) {
    $("#photobox2").click(function () {renderInfo(pid1);});
    $("#photobox3").click(function () {renderInfo(pid2);});
    $("#photobox4").click(function () {renderInfo(pid3);});
    $("#photobox5").click(function () {renderInfo(pid4);});
    $("#photobox6").click(function () {renderInfo(pid5);});
    //TODO: ajax query to get ids
    if (current_role === "gm") {
        $('#magic_ico').on('click', function () {
            $('#magic_gif').addClass('clicked_gif');
            $('#magic_ico').addClass('clicked_magic');
            $('#magic_number').addClass('clicked_number');
            //TODO: ajax query or another gm ajax query
            setTimeout(function () {
                let randInt = randomInteger(1, 20);
                document.getElementById('magic_number').innerText = randInt;
                if (randInt > 9) {
                    $('#magic_number').removeClass('single_number');
                    $('#magic_number').addClass('double_number');
                }
                else {
                    $('#magic_number').removeClass('double_number');
                    $('#magic_number').addClass('single_number');
                }
            }, 1500);
            setTimeout(function () {
                $('#magic_gif').removeClass('clicked_gif');
                $('#magic_ico').removeClass('clicked_magic');
                $('#magic_number').removeClass('clicked_number');
            }, 3000);
        });
        $('#magic_number').on('click', function () {
            $('#magic_gif').addClass('clicked_gif');
            $('#magic_ico').addClass('clicked_magic');
            $('#magic_number').addClass('clicked_number');
            //TODO: ajax query or another gm ajax query
            setTimeout(function () {
                document.getElementById('magic_number').innerText = randomInteger(1, 20);
            }, 1500);
            setTimeout(function () {
                $('#magic_gif').removeClass('clicked_gif');
                $('#magic_ico').removeClass('clicked_magic');
                $('#magic_number').removeClass('clicked_number');
            }, 3000);
        });
    } else {
        //TODO: maybe web socket
    }
    init(1, 1);
};

function randomInteger(min, max) {
    let rand = min - 0.5 + Math.random() * (max - min + 1);
    rand = Math.round(rand);
    return rand;
}

function renderTable(colsList, dataList, tableId, isGm, cId) {
    if (isGm) { colsList.push(""); }
    let newTable = document.createElement("table");
    newTable.setAttribute("class", "info_table");
    newTable.id = tableId;
    let newTr = document.createElement("tr");
    for (let i = 0; i < colsList.length; i++) {
        let newTh = document.createElement("th");
        newTh.innerText = colsList[i];
        newTh.setAttribute("class", "info_table_header");
        newTr.appendChild(newTh);
    }
    newTable.appendChild(newTr);
    for (let i = 0; i < dataList.length; i++) {
        newTr = document.createElement("tr");
        let newTh;
        let cols = colsList.length;
        if (isGm) {cols -= 1;}
        for (let j = 0; j < cols; j++) {
            newTh = document.createElement("th");
            newTh.innerText = dataList[i][j];
            newTh.setAttribute("class", "small_table");
            newTr.appendChild(newTh);
        }
        if (isGm) {
            newTh = document.createElement("th");
            let newButton = document.createElement("button");
            newButton.value = tableId + "; " + dataList[i][0] + "; " + cId;
            newButton.innerText = "-";
            if (tableId === "perksTable") {
                newButton.setAttribute("onclick", "deletePerk(this.value);");
            }
            else if (tableId === "inventoryTable") {
                newButton.setAttribute("onclick", "deleteItem(this.value);");
            }
            else {
                newButton.setAttribute("onclick", "deleteEffect(this.value);");
            }

            newButton.setAttribute("class", "remove_button");
            newTh.appendChild(newButton);
        }
        newTr.appendChild(newTh);
        newTable.appendChild(newTr);
    }
    return newTable;
}

//info table
//processing

function renderInfo(cId){
    document.getElementById("shadowing").style.display = "block";

    let persInfoJson = getPersInfoAjax(cId);
    let persInfo = JSON.parse(persInfoJson.responseText);
    document.getElementById("pers_info_gamer").innerText += " " + persInfo.username;
    document.getElementById("pers_info_pers").innerText += " " + persInfo.name;
    document.getElementById("pers_info_cond").innerText += " " + persInfo.condition + " ";
    document.getElementById("pers_info_level").innerText += " " + persInfo.level;
    document.getElementById("pers_info_race").innerText += " " + persInfo.race;
    document.getElementById("pers_info_class").innerText += " " + persInfo.class;
    document.getElementById("pers_info_max_weight").innerText += " " + persInfo.maxWeight + " ";


    let colsList = ["название", "перк / абилка", "описание"];
    let perksJson = getMyPerksAjax(cId);
    perksJson = JSON.parse(perksJson.responseText);
    let perksList = [];
    for (let a of perksJson) {
        let newPerk = [];
        newPerk.push(a.name);
        newPerk.push(a.description);
        newPerk.push(a.p_a);
        perksList.push(newPerk);
    }
    //let perksList = [["cool jokes", "абилка", "making laugh everybody"], ["make sandwich", "абилка", "very delicious"],
    //    ["do growl while sleeping", "перк", "awful noise, everyone's praying"]];
    document.getElementById("perks_abils").appendChild(renderTable(colsList, perksList, "perksTable", (current_role === "gm"), cId));
    document.getElementById("perks_abils").appendChild(document.createElement("br"));


    let effectsJson = getPersEffectsAjax(cId);
    effectsJson = JSON.parse(effectsJson.responseText);
    let effectsList = [];
    for (let a of effectsJson) {
        let neweffect = [];
        neweffect.push(a.name);
        neweffect.push(a.description);
        neweffect.push(a.p_a);
        effectsList.push(neweffect);
    }
    colsList = ["название", "описание"];
    //let effectsList = [["cool jokes", "making laugh everybody"], ["make sandwich", "very delicious"],
    //    ["do growl while sleeping", "awful noise, everyone's praying"]];
    document.getElementById("effects").appendChild(renderTable(colsList, effectsList, "effectsTable", (current_role === "gm"), cId));
    document.getElementById("effects").appendChild(document.createElement("br"));


    let itemsJson = getPersItemsAjax(cId);
    itemsJson = JSON.parse(itemsJson.responseText);
    let itemsList = [];
    for (let a of itemsJson) {
        let curLine = [];
        curLine.push(a.name);
        curLine.push(a.description);
        curLine.push(a.price);
        curLine.push(a.weight);
        itemsList.push(curLine);
    }
    colsList = ["название", "описание", "цена", "вес"];
    //let itemsList = [["wizard's spoon", "makes poison from tea", 30, 1],
    //    ["several kittens", "distracting your attention by meow meow", 10, 5],
    //    ["new socks", "your granny made by yourself with love", 999, 1],
    //    ["no sql database", "making relations disappear, brokes hearts", 100, 9999]];
    document.getElementById("inventory").appendChild(renderTable(colsList, itemsList, "inventoryTable", (current_role === "gm"), cId));
    document.getElementById("inventory").appendChild(document.createElement("br"));


    let curMoney = getPersMoneyAjax(cId);
    document.getElementById("cur_money").innerText += curMoney;

    if (current_role === "gm") {
        //смена состояния
        let curP = document.getElementById("pers_info_cond");
        let newSelect = document.createElement("select");
        newSelect.id = "pers_info_cond_select";

        //TODO: ajax query to get all avaliable conditions with pers sex
        //!!!!!!!!!!!!!!!!!!

        let allConds = ["жива", "мертва"];
        for (let a of allConds) {
            let newOption = document.createElement("option");
            newOption.innerHTML = a;
            newSelect.appendChild(newOption);
        }
        curP.appendChild(newSelect);
        let newButton = document.createElement("button");
        newButton.setAttribute("class", "left_margin_button");
        newButton.onclick = submit_cond(cId);
        newButton.innerHTML = "Сохранить";
        curP.appendChild(newButton);

        //увеличение максимального веса
        curP = document.getElementById("pers_info_max_weight");
        let newText = document.createElement("input");
        newText.type = "text";
        newText.id = "input_max_weight";
        newText.class = "input_text";
        curP.appendChild(newText);
        newButton = document.createElement("button");
        newButton.setAttribute("class", "left_margin_button");
        newButton.onclick = submit_weight(cId);
        newButton.innerHTML = "Сохранить";
        curP.appendChild(newButton);

        //добавление перков и абилок
        let curDiv = document.getElementById("perks_abils");
        let newP = document.createElement("p");
        newP.innerHTML = "Название";
        curDiv.appendChild(newP);

        newText = document.createElement("input");
        newText.type = "text";
        newText.id = "input_perks";
        newText.class = "input_text";
        curDiv.appendChild(newText);
        let newDatalist = document.createElement("datalist");
        newDatalist.id = "perks_list";
        //!!!!!!!!!!!!!!!!!!!!!!!!!!! найти все названия перков
        let perkJson = getAllPerksAjax(cId);
        perkJson = JSON.parse(perkJson.responseText);
        let allOptions = [];
        for (let a of perkJson) {
            allOptions.push(a.name);
        }
        //let allOptions = ["jj", "xdcfghj"];
        for (let a of allOptions) {
            let newOption = document.createElement("option");
            newOption.innerText = a;
            newDatalist.appendChild(newOption);
        }
        curDiv.appendChild(newDatalist);
        newText.setAttribute("list", "perks_list");
        curDiv.appendChild(document.createElement("br"));
        curDiv.appendChild(document.createElement("br"));

        newP = document.createElement("p");
        newP.innerHTML = "Перк ";
        let newRadio = document.createElement("input");
        newRadio.type = "radio";
        newRadio.name = "p_or_a";
        newRadio.id = "p_or_a_perk";
        newRadio.value = "perk";
        newP.appendChild(newRadio);
        document.getElementById("perks_abils").appendChild(newP);
        curDiv.appendChild(document.createElement("br"));

        newP = document.createElement("p");
        newP.innerHTML = "Абилка ";
        newRadio = document.createElement("input");
        newRadio.type = "radio";
        newRadio.name = "p_or_a";
        newRadio.name = "p_or_a_ability";
        newRadio.value = "ability";
        newP.appendChild(newRadio);
        document.getElementById("perks_abils").appendChild(newP);
        curDiv.appendChild(document.createElement("br"));

        newP = document.createElement("p");
        newP.innerHTML = "Описание";
        curDiv.appendChild(newP);

        let newTextarea = document.createElement("textarea");
        newTextarea.name = "description";
        newTextarea.id = "perk_description";
        newTextarea.cols = "40";
        newTextarea.rows = "3";
        curDiv.appendChild(newTextarea);
        curDiv.appendChild(document.createElement("br"));
        curDiv.appendChild(document.createElement("br"));

        newButton = document.createElement("button");
        newButton.setAttribute("onclick", "submit_perk(" + cId + ");");
        newButton.innerHTML = "Добавить";
        curDiv.appendChild(newButton);

        //добавление эффектов
        curDiv = document.getElementById("effects");
        newP = document.createElement("p");
        newP.innerHTML = "Название";
        curDiv.appendChild(newP);

        newText = document.createElement("input");
        newText.type = "text";
        newText.id = "input_effects";
        newText.class = "input_text";
        curDiv.appendChild(newText);
        newDatalist = document.createElement("datalist");
        newDatalist.id = "effects_list";
        //!!!!!!!!!!!!!!!!!!!! названия эффектов
        let effectJson = getAllEffectsAjax(cId);
        effectJson = JSON.parse(effectJson.responseText);
        allOptions = [];
        for (let a of effectJson) {
            allOptions.push(a.name);
        }
        //allOptions = ["jj", "xdcfghj"];
        for (let a of allOptions) {
            let newOption = document.createElement("option");
            newOption.innerText = a;
            newDatalist.appendChild(newOption);
        }
        curDiv.appendChild(newDatalist);
        newText.setAttribute("list", "effects_list");
        curDiv.appendChild(document.createElement("br"));
        curDiv.appendChild(document.createElement("br"));

        newP = document.createElement("p");
        newP.innerHTML = "Описание";
        curDiv.appendChild(newP);

        newTextarea = document.createElement("textarea");
        newTextarea.name = "description";
        newTextarea.id = "effect_description";
        newTextarea.cols = "40";
        newTextarea.rows = "3";
        curDiv.appendChild(newTextarea);
        curDiv.appendChild(document.createElement("br"));
        curDiv.appendChild(document.createElement("br"));

        newButton = document.createElement("button");
        newButton.setAttribute("onclick", "submit_effect(" + cId + ");");
        newButton.innerHTML = "Добавить";
        curDiv.appendChild(newButton);

        //добавление инвентаря
        curDiv = document.getElementById("inventory");
        newP = document.createElement("p");
        newP.innerHTML = "Название";
        curDiv.appendChild(newP);

        newText = document.createElement("input");
        newText.type = "text";
        newText.id = "input_items";
        newText.class = "input_text";
        curDiv.appendChild(newText);
        newDatalist = document.createElement("datalist");
        newDatalist.id = "items_list";
        // !!!!!!!!!!!!!!!!!!!! названия предметов
        let itemJson = getAllItemsAjax(cId);
        itemJson = JSON.parse(itemJson.responseText);
        allOptions = [];
        for (let a of itemJson) {
            allOptions.push(a.name);
        }
        //allOptions = ["jj", "xdcfghj"];
        for (let a of allOptions) {
            let newOption = document.createElement("option");
            newOption.innerText = a;
            newDatalist.appendChild(newOption);
        }
        curDiv.appendChild(newDatalist);
        newText.setAttribute("list", "items_list");
        curDiv.appendChild(document.createElement("br"));
        curDiv.appendChild(document.createElement("br"));

        newP = document.createElement("p");
        newP.innerHTML = "Описание";
        curDiv.appendChild(newP);

        newTextarea = document.createElement("textarea");
        newTextarea.name = "description";
        newTextarea.id = "item_description";
        newTextarea.cols = "40";
        newTextarea.rows = "3";
        curDiv.appendChild(newTextarea);
        curDiv.appendChild(document.createElement("br"));
        curDiv.appendChild(document.createElement("br"));

        newP = document.createElement("p");
        newP.innerHTML = "Цена";
        curDiv.appendChild(newP);

        newText = document.createElement("input");
        newText.type = "text";
        newText.id = "item_weight";
        newText.class = "input_text";
        curDiv.appendChild(newText);
        curDiv.appendChild(document.createElement("br"));
        curDiv.appendChild(document.createElement("br"));

        newP = document.createElement("p");
        newP.innerHTML = "Вес";
        curDiv.appendChild(newP);

        newText = document.createElement("input");
        newText.type = "text";
        newText.id = "item_price";
        newText.class = "input_text";
        curDiv.appendChild(newText);
        curDiv.appendChild(document.createElement("br"));
        curDiv.appendChild(document.createElement("br"));

        newButton = document.createElement("button");
        newButton.setAttribute("onclick", "submit_item(" + cId + ");");
        newButton.innerHTML = "Добавить";
        curDiv.appendChild(newButton);

        //добалвение денег
        curDiv = document.getElementById("money");
        newText = document.createElement("input");
        newText.type = "text";
        newText.id = "input_money";
        newText.class = "input_text";
        curDiv.appendChild(newText);
        curDiv.appendChild(document.createElement("br"));
        curDiv.appendChild(document.createElement("br"));

        newButton = document.createElement("button");
        newButton.setAttribute("onclick", "submit_money(" + cId + ");");
        newButton.innerHTML = "Добавить";
        curDiv.appendChild(newButton);
    }
}

function submit_cond(cId) {
    //TODO: ajax query
    //change cond
}

function submit_weight(cId) {
    //TODO: ajax query
    //change weight
}

function submit_perk(cId) {
    let name = document.getElementById("input_perks").innerText;
    let desc = document.getElementById("perk_description").innerText;
    let pa;
    if (document.getElementById("p_or_a_perk").checked) {
        pa = "p";
    } else {
        pa = "a";
    }
    if (addPerkAjax(cId, name, desc, pa).responseText === "success") {
        document.getElementById("perks_abils").innerText="";
        let colsList = ["название", "перк / абилка", "описание"];
        let perksJson = getMyPerksAjax(cId);
        perksJson = JSON.parse(perksJson.responseText);
        let perksList = [];
        for (let a of perksJson) {
            let newPerk = [];
            newPerk.push(a.name);
            newPerk.push(a.description);
            newPerk.push(a.p_a);
            perksList.push(newPerk);
        }
        //let perksList = [["cool jokes", "абилка", "making laugh everybody"], ["make sandwich", "абилка", "very delicious"],
        //    ["do growl while sleeping", "перк", "awful noise, everyone's praying"]];
        document.getElementById("perks_abils").appendChild(renderTable(colsList, perksList, "perksTable", (current_role === "gm"), cId));
        document.getElementById("perks_abils").appendChild(document.createElement("br"));
    }
}

function submit_effect(cId) {
    let name = document.getElementById("input_effects").innerText;
    let desc = document.getElementById("effect_description").innerText;
    if (addEffectAjax(cId, name, desc).responseText === "success") {
        document.getElementById("effects").innerText="";
        let effectsJson = getMyPerksAjax(cId);
        effectsJson = JSON.parse(effectsJson.responseText);
        let effectsList = [];
        for (let a of effectsJson) {
            let neweffect = [];
            neweffect.push(a.name);
            neweffect.push(a.description);
            neweffect.push(a.p_a);
            effectsList.push(neweffect);
        }
        let colsList = ["название", "описание"];
        //let effectsList = [["cool jokes", "making laugh everybody"], ["make sandwich", "very delicious"],
        //    ["do growl while sleeping", "awful noise, everyone's praying"]];
        document.getElementById("effects").appendChild(renderTable(colsList, effectsList, "effectsTable", (current_role === "gm"), cId));
        document.getElementById("effects").appendChild(document.createElement("br"));
    }
}

function submit_item(cId) {
    let name = document.getElementById("input_items").innerText;
    let iDesc = document.getElementById("item_description").innerText;
    let iPrice = document.getElementById("item_price").innerText;
    let iWeight = document.getElementById("item_weight").innerText;
    if (addItemAjax(cId, name, iDesc, iPrice, iWeight).responseText === "success") {
        document.getElementById("inventory").innerText="";
        let itemsJson = getPersItemsAjax(cId);
        itemsJson = JSON.parse(itemsJson.responseText);
        let itemsList = [];
        for (let a of itemsJson) {
            let curLine = [];
            curLine.push(a.name);
            curLine.push(a.description);
            curLine.push(a.price);
            curLine.push(a.weight);
            itemsList.push(curLine);
        }
        let colsList = ["название", "описание", "цена", "вес"];
        //let itemsList = [["wizard's spoon", "makes poison from tea", 30, 1],
        //    ["several kittens", "distracting your attention by meow meow", 10, 5],
        //    ["new socks", "your granny made by yourself with love", 999, 1],
        //    ["no sql database", "making relations disappear, brokes hearts", 100, 9999]];
        document.getElementById("inventory").appendChild(renderTable(colsList, itemsList, "inventoryTable", (current_role === "gm"), cId));
        document.getElementById("inventory").appendChild(document.createElement("br"));
    }
}

function submit_money(cId) {
    let money = document.getElementById("input_money").innerText;
    setPersMoneyAjax(cId, money);
    let curMoney = getPersMoneyAjax(cId);
    document.getElementById("cur_money").innerText = "Сейчас в кошельке: " + curMoney;
}

function deleteItem(tableId_name_cId) {
    let tableId = tableId_name_cId.split("; ");
    let name = tableId[1];
    let cId = tableId[2];
    tableId = tableId[0];
    let resultAjax = sellItemAjax(name);
    if (resultAjax.responseText === "success") {
        let curTable = document.getElementById(tableId);
        for (let a of curTable.childNodes) {
            let curTh = a.firstChild;
            if (curTh.innerText === name) {
                remove_children(a);
                curTable.removeChild(a);
                break;
            }
        }
        let curMoney = getPersMoneyAjax(cId);
        document.getElementById("cur_money").innerText = "Сейчас в кошельке: " + curMoney;
    }
}

function deleteEffect(tableId_name_cId) {
    let tableId = tableId_name_cId.split("; ");
    let name = tableId[1];
    let cId = tableId[2];
    tableId = tableId[0];
    let resultAjax = removeEffectAjax(cId, name);
    if (resultAjax.responseText === "success") {
        let curTable = document.getElementById(tableId);
        for (let a of curTable.childNodes) {
            let curTh = a.firstChild;
            if (curTh.innerText === name) {
                remove_children(a);
                curTable.removeChild(a);
                break;
            }
        }
    }
}

function deletePerk(tableId_name_cId) {
    let tableId = tableId_name_cId.split("; ");
    let name = tableId[1];
    let cId = tableId[2];
    tableId = tableId[0];
    let resultAjax = removePerkAjax(cId, name);
    if (resultAjax.responseText === "success") {
        let curTable = document.getElementById(tableId);
        for (let a of curTable.childNodes) {
            let curTh = a.firstChild;
            if (curTh.innerText === name) {
                remove_children(a);
                curTable.removeChild(a);
                break;
            }
        }
    }
}

function closeInfo() {
    let info_div = document.getElementById("shadowing");
    info_div.style.display = "none";
    remove_children(document.getElementById("perks_abils"));
    remove_children(document.getElementById("effects"));
    remove_children(document.getElementById("inventory"));
    remove_children(document.getElementById("money"));
    document.getElementById("pers_info_main").innerHTML = '<p class="info_header">Информация о персонаже</p>\n' +
        '                <p id="pers_info_gamer">Игрок: </p>\n' +
        '                <p id="pers_info_pers">Персонаж: </p>\n' +
        '                <p id="pers_info_cond">Состояние: </p>\n' +
        '                <p id="pers_info_level">Уровень: </p>\n' +
        '                <p id="pers_info_race">Раса: </p>\n' +
        '                <p id="pers_info_class">Класс: </p>\n' +
        '                <p id="pers_info_max_weight">Могу унести: </p>';
    document.getElementById("cur_money").innerText = "Сейчас в кошельке: ";
}

function remove_children(curelem) {
    curelem.innerHTML = "";
}

//processing

//shop
//done

function renderShopTable(dataList, tableId, isBuy) {
    let colsList = ["название", "описание", "цена", "вес", ""];
    let newTable = document.createElement("table");
    newTable.setAttribute("class", "info_table");
    newTable.id = tableId;
    let newTr = document.createElement("tr");
    for (let i = 0; i < colsList.length; i++) {
        let newTh = document.createElement("th");
        newTh.innerText = colsList[i];
        newTh.setAttribute("class", "info_table_header");
        newTr.appendChild(newTh);
    }
    newTable.appendChild(newTr);
    for (let i = 0; i < dataList.length; i++) {
        newTr = document.createElement("tr");
        let newTh;
        let cols = colsList.length - 1;
        for (let j = 0; j < cols; j++) {
            newTh = document.createElement("th");
            newTh.innerText = dataList[i][j];
            newTh.setAttribute("class", "small_table");
            newTr.appendChild(newTh);
        }
        newTh = document.createElement("th");
        let newButton = document.createElement("button");
        newButton.value = dataList[i][0];
        if (isBuy) {
            newButton.innerText = "+";
            newButton.setAttribute("onclick", "buyItem(this.value);");
            newButton.setAttribute("class", "add_button");
        } else {
            newButton.innerText = "-";
            newButton.setAttribute("onclick", "sellItem(this.value);");
            newButton.setAttribute("class", "remove_button");
        }
        newTh.appendChild(newButton);
        newTr.appendChild(newTh);
        newTable.appendChild(newTr);
    }
    return newTable;
}

function renderShop() {
    let buyJson = getAllItemsAjax();
    buyJson = JSON.parse(buyJson.responseText);
    let buyCols = [];
    for (let a of buyJson) {
        let curLine = [];
        curLine.push(a.name);
        curLine.push(a.description);
        curLine.push(a.price);
        curLine.push(a.weight);
        buyCols.push(curLine);
    }
    let myMoney = getPersMoneyAjax(myPId);
    document.getElementById("my_money").innerText += myMoney;
    let sellJson = getPersItemsAjax(myPId);
    sellJson = JSON.parse(sellJson.responseText);
    let sellCols = [];
    for (let a of sellJson) {
        let curLine = [];
        curLine.push(a.name);
        curLine.push(a.description);
        curLine.push(a.price);
        curLine.push(a.weight);
        sellCols.push(curLine);
    }
    document.getElementById("shadowing2").style.display = "block";
    document.getElementById("shop_buy").appendChild(renderShopTable(buyCols, "buy_table", true));
    document.getElementById("shop_sell").appendChild(renderShopTable(sellCols, "sell_table", false));
}

function buyItem(name) {
    let resultAjax = buyItemAjax(name);
    if (resultAjax.responseText === "success") {
        let tableId = "buy_table";
        let curTable = document.getElementById(tableId);
        for (let a of curTable.childNodes) {
            let curTh = a.firstChild;
            if (curTh.innerText === name) {
                let newA = a.cloneNode(true);
                for (let b of newA.childNodes) {
                    if (b === newA.lastChild) {
                        b.firstChild.innerText = "-";
                        b.firstChild.setAttribute('class', 'remove_button');
                        b.firstChild.setAttribute("onclick", "sellItem(this.value);");
                    }
                }
                let table = document.getElementById("sell_table");
                table.appendChild(newA);
                break;
            }
        }
        document.getElementById("my_money").innerText = "В кошельке: " +  getPersMoneyAjax(myPId).toString();
    } else {
        alert(resultAjax.responseText);
    }
}

function sellItem(name) {
    let resultAjax = sellItemAjax(name);
    if (resultAjax.responseText === "success") {
        let tableId = "sell_table";
        let curTable = document.getElementById(tableId);
        for (let a of curTable.childNodes) {
            let curTh = a.firstChild;
            if (curTh.innerText === name) {
                for (let b of a.childNodes) {
                    if (b === a.lastChild) {
                        b.firstChild.innerText = "+";
                        b.firstChild.setAttribute('class', 'add_button');
                        b.firstChild.setAttribute("onclick", "buyItem(this.value);");
                    }
                }
                curTable.removeChild(a);
                break;
            }
        }
        document.getElementById("my_money").innerText = "В кошельке: " +  getPersMoneyAjax(myPId).toString();
    } else {
        alert(resultAjax.responseText);
    }
}

function exitShop() {
    document.getElementById("shadowing2").style.display = "none";
    document.getElementById("shop_buy").removeChild(document.getElementById("buy_table"));
    document.getElementById("shop_sell").removeChild(document.getElementById("sell_table"));
    document.getElementById("my_money").innerText = "В кошельке: ";
}

//done

function sendMessage() {
    let chat = document.getElementById("chat-messages");
    //в комментариях написан псевдокод, который будет работать в нормальной версии
    //а пока что тут тестовая заглушка
    //let messageInp = document.getElementById("chat-write");
    //let user = getUser();
    //TODO: ajaxSendMessage(user, message);
    //let newP = document.createElement("p");
    //newP.innerHTML = "[ " + user + " ] : " + message;
    //chat.appendChild(newP);
    //let objDiv = document.getElementById("content-discussion");
    //objDiv.scrollTop = objDiv.scrollHeight;
    let message = "KEKKEKKEKKEKKEKKEKKEKKEKKEKKEKKEKKEKKEKKEKKEKKEKKEKKEKKEKKEKKEKKEKKEKKEKKEKKEKKEKKEKKEKKEKKEKKEKKEKKEK";
    let user = "Зигарыг";
    let newP = document.createElement("p");
    newP.innerHTML = "[ " + user + " ] : " + message;
    chat.appendChild(newP);
    let objDiv = document.getElementById("content-discussion");
    objDiv.scrollTop = objDiv.scrollHeight;
}

function fieldClick(x, y) {
    //if(!gm)
    coordinates[0] = [x, y];
    drawSpot();
    //TODO: ajaxRequest(username, x, y);
    //чтобы другие юзеры видели перемещение
}

function drawSpot() {
    for (let i=0; i < 5; i++) {
        let curDiv = document.getElementById(coordinates[i][0].toString() + coordinates[i][1].toString());
        curDiv.appendChild(document.getElementById("spot" + (i + 1).toString()));
    }
}


function getAllItemsAjax() {
    return $.ajax({
        url: 'http://localhost:8080/ItemController/findall',
        type: 'GET',
        dataType: 'json',
        async: false,
        success: function(response){
            return  response;
        }
    });
}

function getPersItemsAjax(pId) {
    return $.ajax({
        url: 'http://localhost:8080/character/getCharacterItems',
        type: 'GET',
        dataType: 'json',
        data: {
            id: pId
        },
        async: false,
        success: function(response){
            return  response;
        }
    });
}

function buyItemAjax(name) {
    return $.ajax({
        url: 'http://localhost:8080/character/buyItem',
        type: 'GET',
        dataType: 'json',
        data: {
            character_id: myPId,
            item_name: name
        },
        async: false,
        success: function(response){
            return  response;
        }
    });
}

function addItemAjax(cId, name, iDesc, iPrice, iWeight) {
    return $.ajax({
        url: 'http://localhost:8080/character/addItemToCharacter',
        type: 'GET',
        dataType: 'json',
        data: {
            character_id: cId,
            name: name,
            description: iDesc,
            price: iPrice,
            weight: iWeight
        },
        async: false,
        success: function(response){
            return  response;
        }
    });
}

function sellItemAjax(name) {
    return $.ajax({
        url: 'http://localhost:8080/character/removeItemFromCharacter',
        type: 'GET',
        dataType: 'json',
        data: {
            character_id: myPId,
            item_name: name
        },
        async: false,
        success: function(response){
            return  response;
        }
    });
}

function getPersMoneyAjax(pId) {
    let curAjax = $.ajax({
        url: 'http://localhost:8080/character/getMoney',
        type: 'GET',
        async: false,
        data: {
            id: pId
        },
        success: function(response){
            return response;
        }
    });
    return curAjax.responseText;
}

function setPersMoneyAjax(pId) {
    let curAjax = $.ajax({
        url: 'http://localhost:8080/character/setMoney',
        type: 'GET',
        async: false,
        data: {
            id: pId
        },
        success: function(response){
            return response;
        }
    });
    return curAjax.responseText;
}

function getPersInfoAjax(pId) {
    return $.ajax({
        url: 'http://localhost:8080/character/getCharacterInfo',
        type: 'GET',
        dataType: 'json',
        data: {
            id: pId
        },
        async: false,
        success: function(response){
            return  response;
        }
    });
}

function getPersPerksAjax(pId, name, description, p_a) {
    return $.ajax({
        url: 'http://localhost:8080/character/addAbilityToCharacter',
        type: 'GET',
        dataType: 'json',
        data: {
            character_id: pId,
            name: name,
            description: description,
            p_a: p_a
        },
        async: false,
        success: function(response){
            return  response;
        }
    });
}

function addPerkAjax(pId, aName, aDesc, pa) {
    return $.ajax({
        url: 'http://localhost:8080/character/addAbilityToCharacter',
        type: 'GET',
        dataType: 'json',
        data: {
            character_id: pId,
            name: aName,
            description: aDesc,
            p_a: pa
        },
        async: false,
        success: function(response){
            return  response;
        }
    });
}

function removePerkAjax(pId, name) {
    return $.ajax({
        url: 'http://localhost:8080/character/removeAbilityFromCharacter',
        type: 'GET',
        dataType: 'json',
        data: {
            character_id: pId,
            ability_name: name
        },
        async: false,
        success: function(response){
            return  response;
        }
    });
}

function getMyPerksAjax(pId) {
    return $.ajax({
        url: 'http://localhost:8080/character/getCharacterAbilities',
        type: 'GET',
        dataType: 'json',
        data: {
            id: pId
        },
        async: false,
        success: function(response){
            return  response;
        }
    });
}

function getAllPerksAjax(pId) {
    return $.ajax({
        url: 'http://localhost:8080/AbilityController/findall',
        type: 'GET',
        dataType: 'json',
        async: false,
        success: function(response){
            return  response;
        }
    });
}

function getAllEffectsAjax(pId) {
    return $.ajax({
        url: 'http://localhost:8080/EffectController/findall',
        type: 'GET',
        dataType: 'json',
        async: false,
        success: function(response){
            return  response;
        }
    });
}

function getPersEffectsAjax(pId) {
    return $.ajax({
        url: 'http://localhost:8080/character/getCharacterEffects',
        type: 'GET',
        dataType: 'json',
        async: false,
        data: {
            id: pId
        },
        success: function(response){
            return  response;
        }
    });
}

function addEffectAjax(pId, name,  desc) {
    return $.ajax({
        url: 'http://localhost:8080/character/addEffectToCharacter',
        type: 'GET',
        dataType: 'json',
        data: {
            character_id: pId,
            name: name,
            description: desc
        },
        async: false,
        success: function(response){
            return  response;
        }
    });
}

function removeEffectAjax(pId, name) {
    return $.ajax({
        url: 'http://localhost:8080/character/removeEffectFromCharacter',
        type: 'GET',
        dataType: 'json',
        data: {
            character_id: pId,
            effect_name: name
        },
        async: false,
        success: function(response){
            return  response;
        }
    });
}

function getGameInfoAjax(sId, uId) {
    return $.ajax({
        url: '/',
        type: 'GET',
        dataType: 'json',
        async: false,
        data: {
            sessionId: sId,
            userId: uId
        },
        success: function(response){
            return JSON.parse(response);
        }
    });
}