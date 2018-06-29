<#-- @ftlvariable name="user" type="com.junior.blog.model.User" -->

<#import "fragment/common.ftl" as common>
<#import "fragment/head.ftl" as head>
<#import "fragment/navbar.ftl" as nav>

<@common.base>
    <@head.base title="Роли"></@head.base>

    <body>
        <@nav.nav true/>

        <div class="container text-center">
            <div class="mb-3">
                Роли пользователя <span class="usr font-weight-bold">${user.username}</span>:
            </div>
            <form action="/user/edit/${user.id}" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <#list allRoles as role>
                    <div class="form-group">
                        <div class="form-check">
                            <input class="form-check-input"
                                   type="checkbox"
                                   id="gridCheck"
                                   name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>
                            <label class="form-check-label" for="gridCheck">
                                ${role}
                            </label>
                        </div>
                    </div>
                </#list>
                <button type="submit" class="btn btn-secondary">Сохранить</button>
            </form>
        </div>

        <#include "fragment/bootstrap-js.ftl">
    </body>
</@common.base>
