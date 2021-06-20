<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Детали скидки</title>
    <style>
        body {
            margin: 0;
        }
        main {
            /*border: 1px red solid;*/
            width: 500px;
            height: 500px;
            margin: 10px;
        }
        .classTest {
            border: 1px red solid;
            width: 500px;
            height: 500px;
            margin: 10px;
        }
        .adminLi {
            padding: 3px;
        }
        table{
            font-size: 18px;
        }
        td{
            padding-top: 7px;
        }
        button {
            font-size: 15px;
        }
    </style>
</head>
<body>
<main>
    <h1>Скидка</h1>
    <#if discount??>
        <table border="0">
            <tr>
                <td>Название</td>
                <td>:</td>
                <td>${discount.name}</td>
            </tr>
            <tr>
                <td>Процент</td>
                <td>:</td>
                <td>${discount.percent}</td>
            </tr>
        </table>
        <br/>
        <#if allowDelete>
            <form action="${'/api/admin/discount/' + discount.discountId + '/delete'}" method="POST" style="font-size: 18px;">
                Удалить запись? <input style="font-size: 15px;" type="submit" value="Да"/>
                <a href="${'/api/admin/discount/' + discount.discountId}"><button type="button">Отмена</button></a>
            </form>
        <#else>
            <div>
                <a href="${'/api/admin/discount/' + discount.discountId + '/edit'}">
                    <button type="submit">Редактировать</button>
                </a>
                &nbsp;&nbsp;
                <a href="${'/api/admin/discount/' + discount.discountId + '/delete'}">
                    <button type="submit">Удалить</button>
                </a><br><br>
                <a href="/api/admin/discount/"><button type="submit" style="width: 210px">Вернуться к списку</button></a>
            </div>
        </#if>
    </#if>
    <#if errorMessage?has_content>
        <div class="error">${errorMessage}</div>
    </#if>
</main>
</body>
</html>