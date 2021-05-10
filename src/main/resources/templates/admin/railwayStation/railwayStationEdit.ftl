<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8"/>
    <title><#if add>Добавление станции<#else>Редактирование станции</#if></title>
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
    <h1><#if add>Добавление станции<#else>Редактирование станции</#if></h1>
    <#if add>
        <#assign urlAction>${'/api/admin/railwayStation/add'}</#assign>
        <#assign submitTitle>Создать</#assign>
    <#else>
        <#assign urlAction>${'/api/admin/railwayStation/' + station.railwayStationId + '/edit'}</#assign>
        <#assign submitTitle>Изменить</#assign>
    </#if>
    <#if errorMessage?has_content>
        <div class="error">${errorMessage}</div>
    </#if>
    <form action="${urlAction}" name="station" method="POST">
        <table border="0">
            <tr>
                <td>Название:</td>
                <#if add = false || err?has_content>
                    <td>
                        <input required type="text" placeholder="Введите название" name="name" value="${station.name}"/>
                    </td>
                <#else>
                    <td><input required type="text" placeholder="Введите название" name="name"/></td>
                </#if>
                <td><span>${(Request['validation.name'])!}</span></td>
            </tr>
            <tr>
                <td>Адрес:</td>
                <#if add = false || err?has_content>
                    <td>
                        <input required type="text" placeholder="Введите название" name="address"
                               value="${station.address}"/>
                    </td>
                <#else>
                    <td><input required type="text" placeholder="Введите название" name="address"/></td>
                </#if>
                <td><span>${(Request['validation.address'])!}</span></td>
            </tr>
            <tr>
                <td>Город:</td>
                <td>
                    <select name="city">
                        <option disabled>Выберите город</option>
                        <#list cities as city>
                            <#if add = false && station.city.cityId = city.cityId>
                                <option selected value="${city.cityId}">${city.name}</option>
                            <#else>
                                <option value="${city.cityId}">${city.name}</option>
                            </#if>
                        </#list>
                    </select>
                </td>
            </tr>
        </table>
        <br>
        <button type="submit">${submitTitle}</button>&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="/api/admin/railwayStation">
            <button type="button">Назад к списку</button>
        </a>
    </form>
</main>
</body>
</html>