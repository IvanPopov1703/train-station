<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Список скидок</title>
    <link rel="stylesheet" href="adminStyle.css">
</head>
<body>
<main>
    <h1>Список скидок</h1>
    <div>
        <table border="1">
            <tr>
                <th>Название</th>
                <th>Процент</th>
            </tr>
            <#list discounts as discount>
                <tr>
                    <td><a href="${'/api/admin/discount/' + discount.discountId}">${discount.name}</a></td>
                    <td>${discount.percent}</td>
                </tr>
            </#list>
        </table>
    </div>
    <br>
    <div>
        <a href="/api/admin/discount/add">
            <button type="submit">Добавить</button>
        </a>&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="/api/admin">
            <button type="submit">Вернуться назад</button>
        </a>
    </div>
</main>
</body>
</html>