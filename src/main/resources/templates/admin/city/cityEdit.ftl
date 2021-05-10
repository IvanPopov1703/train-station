<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8"/>
    <title><#if add>Добавление города<#else>Редактирование города</#if></title>
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
        button, input {
            font-size: 15px;
        }
    </style>
</head>
<body>
<main>
    <h1><#if add>Создание города<#else>Изменение города</#if></h1>
    <#if add>
        <#assign urlAction>${'/api/admin/city/add'}</#assign>
        <#assign submitTitle>Создать</#assign>
    <#else>
        <#assign urlAction>${'/api/admin/city/' + city.cityId + '/edit'}</#assign>
        <#assign submitTitle>Изменить</#assign>
    </#if>
    <form action="${urlAction}" name="city" method="POST">
        <table border="0">
            <tr>
                <td>Название города:</td>
                <#if add == false || err?has_content>
                    <td><input required type="text" placeholder="Введите название" name="name" value="${city.name}"/></td>
                <#else>
                    <td><input required type="text" placeholder="Введите название" name="name" /></td>
                </#if>
                <td>
                    <span>${(Request['validation.name'])!}</span>
                    <#if errorMessage?has_content>
                        <div class="error">${errorMessage}</div>
                    </#if>
                </td>
            </tr>
        </table>
        <br>
        <button type="submit">${submitTitle}</button>&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="/api/admin/city/">
            <button type="button">Назад к списку</button>
        </a>
    </form>
</main>
</body>
</html>