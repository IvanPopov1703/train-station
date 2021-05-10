<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Список станций</title>
    <link rel="stylesheet" href="adminStyle.css">
</head>
<body>
<main>
    <h1>Список станций</h1>
    <div>
        <table border="1">
            <tr>
                <th>Название</th>
                <th>Адрес</th>
                <th>Город</th>
            </tr>
            <#list stations as station>
                <tr>
                    <td><a href="${'/api/admin/railwayStation/' + station.railwayStationId}">${station.name}</a></td>
                    <td>${station.address}</td>
                    <td>${station.city}</td>
                </tr>
            </#list>
        </table>
    </div>
    <br>
    <div>
        <a href="/api/admin/railwayStation/add">
            <button type="submit">Добавить</button>
        </a>&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="/api/admin">
            <button type="submit">Вернуться назад</button>
        </a>
    </div>
</main>
</body>
</html>