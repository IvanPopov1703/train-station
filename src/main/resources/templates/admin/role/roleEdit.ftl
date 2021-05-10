<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8"/>
    <title><#if add>Добавление роли<#else>Редактирование роли</#if></title>
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
    <h1><#if add>Создание роли<#else>Изменение роли</#if></h1>
    <#if add>
        <#assign urlAction>${'/api/admin/role/add'}</#assign>
        <#assign submitTitle>Создать</#assign>
    <#else>
        <#assign urlAction>${'/api/admin/role/' + role.roleId + '/edit'}</#assign>
        <#assign submitTitle>Изменить</#assign>
    </#if>
    <form action="${urlAction}" name="role" method="POST">
        <table border="0">
            <tr>
                <td>Название роли:</td>
                <#if add == false || err?has_content>
                    <td><input required type="text" placeholder="Введите название" name="name" value="${role.name}"/></td>
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
        <a href="/api/admin/role/">
            <button type="button">Назад к списку</button>
        </a>
    </form>
</main>
</body>
</html>