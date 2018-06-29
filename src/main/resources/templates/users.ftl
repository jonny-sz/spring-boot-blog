<#-- @ftlvariable name="users" type="java.util.Collection<com.junior.blog.model.User>" -->

<#import "fragment/common.ftl" as common>
<#import "fragment/head.ftl" as head>
<#import "fragment/navbar.ftl" as nav>

<@common.base>
    <@head.base title="Пользователи"></@head.base>

    <body>
        <@nav.nav true/>

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

        <#include "fragment/bootstrap-js.ftl">
    </body>
</@common.base>
