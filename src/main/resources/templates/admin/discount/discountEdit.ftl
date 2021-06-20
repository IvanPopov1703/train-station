<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8"/>
    <title><#if add>Добавление скидки<#else>Редактирование скидки</#if></title>
    <style>
        body {
            margin: 0;
        }

        main {
            /*border: 1px red solid;*/
            /*width: 500px;*/
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

        table, form {
            font-size: 18px;
        }

        td {
            padding-top: 7px;
        }

        button, input required {
            font-size: 15px;
        }

        .error {
            color: red;
            font-size: 18px;
        }

        select {
            font-size: 15px;
            width: 180px;
        }
    </style>
</head>
<body>
<main>
    <h1><#if add>Добавление скидки<#else>Редактирование скидки</#if></h1>
    <#if add>
        <#assign urlAction>${'/api/admin/discount/add'}</#assign>
        <#assign submitTitle>Создать</#assign>
    <#else>
        <#assign urlAction>${'/api/admin/discount/' + discount.discountId + '/edit'}</#assign>
        <#assign submitTitle>Изменить</#assign>
    </#if>
    <#if errorMessage?has_content>
        <div class="error">${errorMessage}</div>
    </#if>
    <form action="${urlAction}" name="discount" method="POST">
        <table border="0">
            <tr>
                <td>Название:</td>
                <#if add = false || err?has_content>
                    <td>
                        <input required type="text" placeholder="Введите название" name="name" value="${discount.name}"/>
                    </td>
                <#else>
                    <td><input required type="text" placeholder="Введите название" name="name"/></td>
                </#if>
                <td><span>${(Request['validation.name'])!}</span></td>
            </tr>
            <tr>
                <td>Процент:</td>
                <#if add = false || err?has_content>
                    <td>
                        <input required type="number" placeholder="Введите название" name="percent"
                               value="${discount.percent}"/>
                    </td>
                <#else>
                    <td><input required type="number" placeholder="Введите название" name="percent"/></td>
                </#if>
                <td><span>${(Request['validation.percent'])!}</span></td>
            </tr>
        </table>
        <br>
        <button type="submit">${submitTitle}</button>&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="/api/admin/discount">
            <button type="button">Назад к списку</button>
        </a>
    </form>
</main>
</body>
</html>