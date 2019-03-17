let coordinates = [[0, 0], [0, 0], [0, 0], [0, 0], [0, 0]];
//x, y and user id on map
let uid1;
let uid2;
let uid3;
let uid4;
let uid5;
let gmid;
let current_role = "gamer";

function init(sessionId, userId) {
    //TODO: ajax query
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
            fieldClick(5, 8);
        }
    }
}

window.onload = function (ev) {
    $("#photobox2").click(function () {renderInfo("g1");});
    $("#photobox3").click(function () {renderInfo("g2");});
    $("#photobox4").click(function () {renderInfo("g3");});
    $("#photobox5").click(function () {renderInfo("g4");});
    $("#photobox6").click(function () {renderInfo("g5");});
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
    }
    init(1, 1);
};

function randomInteger(min, max) {
    let rand = min - 0.5 + Math.random() * (max - min + 1);
    rand = Math.round(rand);
    return rand;
}

function renderTable(colsList, dataList, tableId, isGm) {
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
            newButton.value = tableId + "; " + dataList[i][0];
            newButton.innerText = "-";
            newButton.setAttribute("onclick", "deleteItem(this.value);");
            newButton.setAttribute("class", "remove_button");
            newTh.appendChild(newButton);
        }
        newTr.appendChild(newTh);
        newTable.appendChild(newTr);
    }
    return newTable;
}

function renderInfo(){
    document.getElementById("shadowing").style.display = "block";
    //TODO: ajax query
    //getting main info about pers
    let newPersInfo = "Ира";
    document.getElementById("pers_info_gamer").innerText += " " + newPersInfo;
    newPersInfo = "Дана";
    document.getElementById("pers_info_pers").innerText += " " + newPersInfo;
    newPersInfo = "жива";
    document.getElementById("pers_info_cond").innerText += " " + newPersInfo + " ";
    newPersInfo = "1";
    document.getElementById("pers_info_level").innerText += " " + newPersInfo;
    newPersInfo = "эльф";
    document.getElementById("pers_info_race").innerText += " " + newPersInfo;
    newPersInfo = "втшник";
    document.getElementById("pers_info_class").innerText += " " + newPersInfo;
    newPersInfo = "20";
    document.getElementById("pers_info_max_weight").innerText += " " + newPersInfo + " ";

    //TODO: ajax query to get info
    //table: name, perk/ability, description
    let colsList = ["название", "перк / абилка", "описание"]
    let perksList = [["cool jokes", "абилка", "making laugh everybody"], ["make sandwich", "абилка", "very delicious"],
        ["do growl while sleeping", "перк", "awful noise, everyone's praying"]];
    document.getElementById("perks_abils").appendChild(renderTable(colsList, perksList, "perksTable", (current_role === "gm")));
    document.getElementById("perks_abils").appendChild(document.createElement("br"));

    //TODO: ajax query to get info
    //effects info
    //table: name, description
    colsList = ["название", "описание"]
    let effectsList = [["cool jokes", "making laugh everybody"], ["make sandwich", "very delicious"],
        ["do growl while sleeping", "awful noise, everyone's praying"]];
    document.getElementById("effects").appendChild(renderTable(colsList, effectsList, "effectsTable", (current_role === "gm")));
    document.getElementById("effects").appendChild(document.createElement("br"));

    //TODO: ajax query to get info
    //max weight and table: name, description, price, weight
    colsList = ["название", "описание", "цена", "вес"];
    let itemsList = [["wizard's spoon", "makes poison from tea", 30, 1],
        ["several kittens", "distracting your attention by meow meow", 10, 5],
        ["new socks", "your granny made by yourself with love", 999, 1],
        ["no sql database", "making relations disappear, brokes hearts", 100, 9999]];
    document.getElementById("inventory").appendChild(renderTable(colsList, itemsList, "inventoryTable", (current_role === "gm")));
    document.getElementById("inventory").appendChild(document.createElement("br"));
    //TODO: ajax query to get info
    //money info
    //just how mush money does pers have
    let curMoney = 10;
    document.getElementById("cur_money").innerText += curMoney;

    if (current_role === "gm") {
        //смена состояния
        let curP = document.getElementById("pers_info_cond");
        let newSelect = document.createElement("select");
        newSelect.id = "pers_info_cond_select"
        //TODO: ajax query to get all avaliable conditions with pers sex
        let allConds = ["жива", "мертва"]
        for (let a of allConds) {
            let newOption = document.createElement("option");
            newOption.innerHTML = a;
            newSelect.appendChild(newOption);
        }
        curP.appendChild(newSelect);
        let newButton = document.createElement("button");
        newButton.setAttribute("class", "left_margin_button");
        newButton.onclick = submit_cond();
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
        newButton.onclick = submit_weight();
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
        //TODO: ajax query to find all and relevant from them
        let allOptions = ["jj", "xdcfghj"];
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
        newButton.onclick = submit_perk();
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
        //TODO: ajax query to find all and relevant from them
        allOptions = ["jj", "xdcfghj"];
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
        newButton.onclick = submit_effect();
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
        //TODO: ajax query to find all and relevant from them
        allOptions = ["jj", "xdcfghj"];
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
        newButton.onclick = submit_item();
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
        newButton.onclick = submit_money();
        newButton.innerHTML = "Добавить";
        curDiv.appendChild(newButton);
    }
}

function submit_cond() {
    //TODO: ajax query
    //change cond
}

function submit_weight() {
    //TODO: ajax query
    //change weight
}

function submit_perk() {
    //TODO: ajax query
    //add perk
}

function submit_effect() {
    //TODO: ajax query
    //add effect
}

function submit_item() {
    //TODO: ajax query
    //add item
}

function submit_money() {
    //TODO: ajax query
    //add money
}

function deleteItem(tableId_name) {
    let tableId = tableId_name.split("; ");
    let name = tableId[1];
    tableId = tableId[0];
    //TODO: ajax query
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
    //TODO: ajax query
    document.getElementById("my_money").innerText += 100;
    document.getElementById("shadowing2").style.display = "block";
    let buyCols = [["wizard's spoon", "makes poison from tea", 30, 1],
        ["several kittens", "distracting your attention by meow meow", 10, 5],
        ["new socks", "your granny made by yourself with love", 999, 1],
        ["no sql database", "making relations disappear, brokes hearts", 100, 9999]];
    let sellCols = [["wizard's spoon", "makes poison from tea", 30, 1],
        ["several kittens", "distracting your attention by meow meow", 10, 5],
        ["new socks", "your granny made by yourself with love", 999, 1]];
    document.getElementById("shop_buy").appendChild(renderShopTable(buyCols, "buy_table", true));
    document.getElementById("shop_sell").appendChild(renderShopTable(sellCols, "sell_table", false));
}

function buyItem(name) {
    let tableId = "buy_table";
    let curTable = document.getElementById(tableId);
    for (let a of curTable.childNodes) {
        let curTh = a.firstChild;
        if (curTh.innerText === name) {
            //TODO: ajax query
            for (let b of a.childNodes) {
                if (b === a.lastChild) {
                    b.firstChild.innerText = "-";
                    b.firstChild.setAttribute('class', 'remove_button');
                    b.firstChild.setAttribute("onclick", "sellItem(this.value);");
                }
            }
            let addedItem = a;
            let table = document.getElementById("sell_table");
            table.appendChild(addedItem);
            break;
        }
    }
}

function sellItem(name) {
    let tableId = "sell_table";
    let curTable = document.getElementById(tableId);
    for (let a of curTable.childNodes) {
        let curTh = a.firstChild;
        if (curTh.innerText === name) {
            //TODO: ajax query
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
}

function exitShop() {
    document.getElementById("shadowing2").style.display = "none";
    document.getElementById("shop_buy").removeChild(document.getElementById("buy_table"));
    document.getElementById("shop_sell").removeChild(document.getElementById("sell_table"));
    document.getElementById("my_money").innerText = "В кошельке: ";
}

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