<%@ page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<html>
<head>
    <title>Игровое поле</title>
    <link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
          rel="stylesheet"/>
    <link rel="stylesheet" href="../../resources/css/photosDiv.css"/>
    <link rel="stylesheet" href="../../resources/css/chat.css"/>
    <link rel="stylesheet" href="../../resources/css/map.css"/>
    <link rel="stylesheet" href="../../resources/css/shop_and_magic.css"/>
    <link rel="stylesheet" href="../../resources/css/pers_info.css"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <script src="../../resources/js/gamefield.js"></script>
</head>
<!--body onload="init(${param["sessionId"]}, ${param["userId"]});"-->
<body>
<jsp:include page="templates/header.jsp"/>

<button onclick="if (current_role === 'gamer') {current_role = 'gm';}
                else {current_role = 'gamer'}">смена роли УДАЛИ МЕНЯ</button>

<div class="wrapper">
    <div class="content">
        <div class="container">
            <div class="row center-content">
                <div class="col-sm-1"></div>
                <div class="col-sm-8">
                    <div id="chat" class="chat">
                        <div id="chat-header" class="chat-header">
                            <p style="padding-top: 10px;">Чат</p>
                        </div>
                        <div class="content-discussion" id="content-discussion">
                            <p class="chat-first" style="margin: 10px 0;">Обсуждайте ход игры здесь</p>
                            <div id="chat-messages" class="chat-messages">
                            </div>
                        </div>
                        <div id="chat-write" class="chat-write">
                            <input type="text" id="message-writer" >
                            <button id="send-message" onclick="sendMessage();">Отправить</button>
                        </div>
                    </div>
                    <div id="photos" class="photos">
                        <div class="photobox" id="user1">
                            <div class="photobox__previewbox" id="photobox1">
                                <img src="../../resources/default/img/race/лучник_орк.jpg" class="photobox__preview" id="user-avatar1">
                            </div>
                            <div class="description">
                                <p>${param["gmname"]}</p>
                            </div>
                        </div>
                        <div class="photobox" id="user2">
                            <div class="photobox__previewbox" id="photobox2">
                                <img src="../../resources/default/img/race/молот_шит_гном.jpg" class="photobox__preview" id="user-avatar2">
                            </div>
                            <div class="description">
                                <p>${param["persname1"]} любимая всеми Данка</p>
                                <p>${param["cond"]} в неравной борьбе с долгами</p>
                            </div>
                        </div>
                        <div class="photobox" id="user3">
                            <div class="photobox__previewbox" id="photobox3">
                                <img src="../../resources/default/img/race/женщина_воин_орк.jpg" class="photobox__preview" id="user-avatar3">
                            </div>
                            <div class="description">
                                <p>${param["persname2"]}</p>
                                <p>${param["cond"]}</p>
                            </div>
                        </div>
                        <div class="photobox" id="user4">
                            <div class="photobox__previewbox" id="photobox4">
                                <img src="../../resources/default/img/race/воин_гном.jpg" class="photobox__preview" id="user-avatar4">
                            </div>
                            <div class="description">
                                <p>${param["persname3"]}</p>
                                <p>${param["cond"]}</p>
                            </div>
                        </div>
                        <div class="photobox" id="user5">
                            <div class="photobox__previewbox" id="photobox5">
                                <img src="../../resources/default/img/race/женщина_воин_человек.jpg" class="photobox__preview" id="user-avatar5">
                            </div>
                            <div class="description">
                                <p>${param["persname4"]}</p>
                                <p>${param["cond"]}</p>
                            </div>
                        </div>
                        <div class="photobox" id="user6">
                            <div class="photobox__previewbox" id="photobox6">
                                <img src="../../resources/default/img/race/маг_хаоса_нежить.jpg" class="photobox__preview" id="user-avatar6">
                            </div>
                            <div class="description">
                                <p>${param["persname5"]}</p>
                                <p>${param["cond"]}</p>
                            </div>
                        </div>
                    </div>
                    <div id="gameField">
                        <img src="../../resources/default/img/map.jpg" id="map">
                        <div class="field">
                            <div id="11" class="part p1 pp1" onclick="fieldClick(1, 1)">
                                <div class="point spot1" id="spot1"></div>
                                <div class="point spot2" id="spot2"></div>
                                <div class="point spot3" id="spot3"></div>
                                <div class="point spot4" id="spot4"></div>
                                <div class="point spot5" id="spot5"></div>
                            </div>
                            <div id="12" class="part p1 pp2" onclick="fieldClick(1, 2)">
                            </div>
                            <div id="13" class="part p1 pp3" onclick="fieldClick(1, 3)">
                            </div>
                            <div id="14" class="part p1 pp4" onclick="fieldClick(1, 4)">
                            </div>
                            <div id="15" class="part p1 pp5" onclick="fieldClick(1, 5)">
                            </div>
                            <div id="16" class="part p1 pp6" onclick="fieldClick(1, 6)">
                            </div>
                            <div id="17" class="part p1 pp7" onclick="fieldClick(1, 7)">
                            </div>
                            <div id="18" class="part p1 pp8" onclick="fieldClick(1, 8)">
                            </div>
                            <div id="21" class="part p2 pp1" onclick="fieldClick(2, 1)">
                            </div>
                            <div id="22" class="part p2 pp2" onclick="fieldClick(2, 2)">
                            </div>
                            <div id="23" class="part p2 pp3" onclick="fieldClick(2, 3)">
                            </div>
                            <div id="24" class="part p2 pp4" onclick="fieldClick(2, 4)">
                            </div>
                            <div id="25" class="part p2 pp5" onclick="fieldClick(2, 5)">
                            </div>
                            <div id="26" class="part p2 pp6" onclick="fieldClick(2, 6)">
                            </div>
                            <div id="27" class="part p2 pp7" onclick="fieldClick(2, 7)">
                            </div>
                            <div id="28" class="part p2 pp8" onclick="fieldClick(2, 8)">
                            </div>
                            <div id="31" class="part p3 pp1" onclick="fieldClick(3, 1)">
                            </div>
                            <div id="32" class="part p3 pp2" onclick="fieldClick(3, 2)">
                            </div>
                            <div id="33" class="part p3 pp3" onclick="fieldClick(3, 3)">
                            </div>
                            <div id="34" class="part p3 pp4" onclick="fieldClick(3, 4)">
                            </div>
                            <div id="35" class="part p3 pp5" onclick="fieldClick(3, 5)">
                            </div>
                            <div id="36" class="part p3 pp6" onclick="fieldClick(3, 6)">
                            </div>
                            <div id="37" class="part p3 pp7" onclick="fieldClick(3, 7)">
                            </div>
                            <div id="38" class="part p3 pp8" onclick="fieldClick(3, 8)">
                            </div>
                            <div id="41" class="part p4 pp1" onclick="fieldClick(4, 1)">
                            </div>
                            <div id="42" class="part p4 pp2" onclick="fieldClick(4, 2)">
                            </div>
                            <div id="43" class="part p4 pp3" onclick="fieldClick(4, 3)">
                            </div>
                            <div id="44" class="part p4 pp4" onclick="fieldClick(4, 4)">
                            </div>
                            <div id="45" class="part p4 pp5" onclick="fieldClick(4, 5)">
                            </div>
                            <div id="46" class="part p4 pp6" onclick="fieldClick(4, 6)">
                            </div>
                            <div id="47" class="part p4 pp7" onclick="fieldClick(4, 7)">
                            </div>
                            <div id="48" class="part p4 pp8" onclick="fieldClick(4, 8)">
                            </div>
                            <div id="51" class="part p5 pp1" onclick="fieldClick(5, 1)">
                            </div>
                            <div id="52" class="part p5 pp2" onclick="fieldClick(5, 2)">
                            </div>
                            <div id="53" class="part p5 pp3" onclick="fieldClick(5, 3)">
                            </div>
                            <div id="54" class="part p5 pp4" onclick="fieldClick(5, 4)">
                            </div>
                            <div id="55" class="part p5 pp5" onclick="fieldClick(5, 5)">
                            </div>
                            <div id="56" class="part p5 pp6" onclick="fieldClick(5, 6)">
                            </div>
                            <div id="57" class="part p5 pp7" onclick="fieldClick(5, 7)">
                            </div>
                            <div id="58" class="part p5 pp8" onclick="exitGame()">
                                <img src="../../resources/default/img/door.png" onclick="exitGame()" id="exit_door">
                            </div>
                        </div>
                    </div>
                    <div id="shop">
                        <img src="../../resources/default/img/shop-icon.png" onclick="renderShop();" id="shop_ico">
                    </div>
                    <div id="magic_random">
                        <img src="../../resources/default/img/moving-sphere.gif" id="magic_gif">
                        <img src="../../resources/default/img/magic-ball.png" id="magic_ico">
                        <div id="magic_number"></div>
                    </div>
                </div>
                <div class="col-sm-1"></div>
            </div>
            <script src="webjars/jquery/1.9.1/jquery.min.js"></script>
            <script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        </div>
    </div>
    <div class="footer">
        <jsp:include page="templates/footer.jsp"/>
    </div>
</div>

<div id="shadowing" style="display: none;">
    <div id="pers_info">
        <table width="100%">
            <tr>
                <td colspan="3" id="pers_info_main">
                    <p></p>
                    <p class="info_header">Информация о персонаже</p>
                    <p id="pers_info_gamer">Игрок: </p>
                    <p id="pers_info_pers">Персонаж: </p>
                    <p id="pers_info_cond">Состояние: </p>
                    <p id="pers_info_level">Уровень: </p>
                    <p id="pers_info_race">Раса: </p>
                    <p id="pers_info_class">Класс: </p>
                    <p id="pers_info_max_weight">Могу унести: </p>
                </td>
            </tr>
            <tr>
                <td>
                    <br>
                    <p class="info_header">Перки и абилки</p>
                    <div id="perks_abils"></div>
                </td>
                <td>
                    <br>
                    <p class="info_header">Эффекты</p>
                    <div id="effects"></div>
                </td>
                <td>
                    <br>
                    <p class="info_header">Инвентарь</p>
                    <div id="inventory"></div>
                </td>
            </tr>
            <tr>
                <td colspan="3">
                    <br>
                    <p class="info_header">Кошелёк</p>
                    <p id="cur_money">Сейчас в кошельке: </p>
                    <div id="money"></div>
                </td>
            </tr>
            <tr>
                <td colspan="3">
                    <br>
                    <button class="close" id="close_info" onclick="closeInfo()" style="float: none;">Закрыть</button>
                    <br>
                </td>
            </tr>
        </table>
    </div>
</div>

<div id="shadowing2" style="display: none;">
    <div id="shop_div">
        <table id="shop_table" width="100%">
            <tr>
                <td colspan="2">
                    <p class="info_header">Магазин</p>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <p class="info_header" id="my_money">В кошельке: </p>
                </td>
            </tr>
            <tr>
                <td>
                    <p class="info_header">Купить</p>
                </td>
                <td>
                    <p class="info_header">У меня</p>
                </td>
            </tr>
            <tr>
                <td id="shop_buy"></td>
                <td id="shop_sell"></td>
            </tr>
            <tr>
                <td colspan="2">
                    <button id="exit_shop" onclick="exitShop();">Выйти</button>
                </td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>