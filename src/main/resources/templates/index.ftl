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
    <div class="content">
        <form method="get" action="url">
            <div class="fromBlock">
                <p><b>Откуда:</b><br>
                    <input class="inpFrom" type="text" placeholder="Название станции">
                </p>
            </div>
            <div class="toBlock">
                <p><b>Куда:</b><br>
                    <input class="inpTo" type="text" placeholder="Название станции">
                </p>
            </div>
            <div class="dateTravelBlock">
                <p><b>Дата отправления:</b><br>
                    <input class="inpDateTravel" type="date" placeholder="Дата">
                </p>
            </div>
            <div class="btnSearchTicketBlock">
                <p>
                    <button type="submit" class="btnSearchTicket">
                        Найти ж/д билеты
                    </button>
                </p>
            </div>
        </form>
    </div>
    <footer>
        <p>© 2021 Все права защищены. Верстку сайта осуществлял Попов Иван.</p>
    </footer>
</main>
</body>
</html>
