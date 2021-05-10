<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Список ролей</title>
</head>
<body>
<main>
    <h1>Список ролей</h1>
    <div>
        <ol>
            <#list roles as role>
                <h4>
                    <li class="adminLi">
                        <a href="${'/api/admin/role/' + role.roleId}">${role.name}</a>
                    </li>
                </h4>
            </#list>
        </ol>
    </div>
    <div>
        <a href="/api/admin/role/add">
            <button type="submit">Добавить</button>
        </a>&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="/api/admin">
            <button type="submit">Вернуться назад</button>
        </a>
    </div>
</main>
</body>
</html>
