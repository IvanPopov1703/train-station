<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Бронирование Ж/Д бидетов</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<main>
    <header>
        <div class="logoImgDiv">
            <img src="img/logoRGD.png" alt="Логотип">
        </div>
        <div class="logoTextDiv">
            <img src="img/logoText.png" alt="Логотип">
        </div>
    </header>
    <div class="leftSort">
        <div class="leftHead">
            <h2>Фильтры</h2>
        </div>
        <div class="leftHeadOneFilter">
            <h2>Тип вагона</h2>
            <form method="get" action="${'/sort/'}">
<!--                <p><input checked type="radio" name="viewItem" value="${view.id}">${view.name}</p>-->
                <p><input checked type="checkbox" name="viewItem" value="${view.id}"> Плацкарт</p>
                <p><input type="checkbox" name="viewItem" value="${view.id}"> Купе</p>
                <p><input type="checkbox" name="viewItem" value="${view.id}"> СВ</p>
                <p><input type="checkbox" name="viewItem" value="${view.id}"> Люкс</p>
                <div class="leftButtonSort">
                    <button type="submit" class="btFilterFoundTicket">Применить</button>
                </div>
            </form>
        </div>
    </div>
    <div class="headersToFoundTickets">
        <p>&nbsp;&nbsp;
            Маршрут &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            Отправление &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            Прибытие &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            Места &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            Цена от</p>
    </div>
    <div class="contentFoundTicket">
        <form method="get" action="url">
            <div class="contRoute">
                <p>Москва -> Воронеж</p>
            </div>
            <div class="contDeparture">
                <p><h1><b>13:00</b></h1></p>
            </div>
            <div class="contArrival">
                <p><h1><b>20:00</b></h1></p>
            </div>
            <div class="contSeats">
                <p>
                    Купе &nbsp;&nbsp; 287 <br>
                    Плац &nbsp;&nbsp;197 <br>
                    СВ &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;197 <br>
                    Люкс &nbsp;&nbsp;197 <br>
                </p>
            </div>
            <div class="contPrice">
                <p>
                    2500<br>
                    3000<br>
                    570<br>
                    50000<br>
                </p>
            </div>
            <div class="contSelectSeats">
                <button type="submit" class="btnSelectSeats">Выбрать места</button>
            </div>
        </form>
    </div>
    <div class="contentFoundTicket">
        <form method="get" action="url">
            <div class="contRoute">
                <p>Москва -> Воронеж</p>
            </div>
            <div class="contDeparture">
                <p><h1><b>13:00</b></h1></p>
            </div>
            <div class="contArrival">
                <p><h1><b>20:00</b></h1></p>
            </div>
            <div class="contSeats">
                <p>
                    Купе &nbsp;&nbsp; 287 <br>
                    Плац &nbsp;&nbsp;197 <br>
                    СВ &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;197 <br>
                    Люкс &nbsp;&nbsp;197 <br>
                </p>
            </div>
            <div class="contPrice">
                <p>
                    2500<br>
                    3000<br>
                    570<br>
                    50000<br>
                </p>
            </div>
            <div class="contSelectSeats">
                <button type="submit" class="btnSelectSeats">Выбрать места</button>
            </div>
        </form>
    </div>
    <footer>
        <p>© 2021 Все права защищены. Верстку сайта осуществлял Попов Иван.</p>
    </footer>
</main>
</body>
</html>
