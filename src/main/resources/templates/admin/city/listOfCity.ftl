<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Список городов</title>
</head>
<body>
<main>
    <h1>Список городов</h1>
    <div>
        <ol>
            <#list cities as city>
                <h4>
                    <li class="adminLi">
                        <a href="${'/api/admin/city/' + city.cityId}">${city.name}</a>
                    </li>
                </h4>
            </#list>
        </ol>
    </div>
    <div>
        <a href="/api/admin/city/add">
            <button type="submit">Добавить</button>
        </a>&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="/api/admin">
            <button type="submit">Вернуться назад</button>
        </a>
    </div>
</main>
</body>
</html>
