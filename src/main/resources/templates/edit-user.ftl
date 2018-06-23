<#-- @ftlvariable name="user" type="com.junior.blog.model.User" -->

<#import "fragment/main.ftl" as main>
<#import "fragment/header.ftl" as header>

<@main.main title="Роли" css="style">
    <@header.nav true/>

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

</@main.main>
