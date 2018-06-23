<#-- @ftlvariable name="users" type="java.util.Collection<com.junior.blog.model.User>" -->

<#import "fragment/main.ftl" as main>
<#import "fragment/header.ftl" as header>

<@main.main title="Пользователи" css="style">
    <@header.nav true/>

    <div class="container text-center">
        <table class="table table-bordered">
            <thead>
            <tr style="background-color: #fbfbfb">
                <th scope="col"><i class="fa fa-user"></i> Логин</th>
                <th scope="col"><i class="fa fa-at"></i> E-Mail</th>
                <th scope="col"><i class="fa fa-users"></i> Роли</th>
            </tr>
            </thead>
            <tbody>
                <#list users as user>
                    <tr>
                        <td>${user.username}</td>
                        <td>${user.email}</td>
                        <td><#list user.roles as role>${role}<#sep>, </#list></td>
                        <td>
                            <a class="box-inline" href="/user/edit/${user.id}">
                                <i class="fa fa-pencil"></i>Edit
                            </a>,
                            <a class="box-inline" href="/user/delete/${user.id}">
                                <i class="fa fa-times"></i>Delete
                            </a>
                        </td>
                    </tr>
                <#else>
                    <tr>
                        Пользователи отсутствуют
                    </tr>
                </#list>
            </tbody>
        </table>
    </div>

</@main.main>