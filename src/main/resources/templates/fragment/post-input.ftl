<#-- @ftlvariable name="post" type="com.junior.blog.model.Post" -->
<#-- @ftlvariable name="categories" type="java.util.Collection<com.junior.blog.model.Category>" -->

<#macro input_new_category>

    <form id="newCategory" class="text-center" method="post">

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        <div id="cInput" class="input-group mb-3">
            <input type="text" class="form-control"
                   placeholder="Новая категория"
                   aria-label="Recipient's username"
                   aria-describedby="basic-addon2"
                   id="cTitle"
                   name="title">

            <div class="input-group-append">
                <button class="btn btn-secondary" type="submit">Создать категорию</button>
            </div>
        </div>
    </form>
</#macro>

<#macro select_category>
    <div class="form-group">
        <select id="categorySelect" class="custom-select ${(postCategoryError??)?string('is-invalid', '')}" name="category">
            <option value="">Категория</option>
            <#if post??>
                <#if post.category??>
                    <option value="${post.category.id}" selected>${post.category.title}</option>
                </#if>
            </#if>
            <#list categories as category>
                <option value="${category.id}">${category.title}</option>
            </#list>
        </select>
        <#if postCategoryError??>
            <#list postCategoryError as err>
                <div class="invalid-feedback text-center">${err}</div>
            </#list>
        </#if>
    </div>
</#macro>

<#macro input_title>
    <div class="form-group">
        <div class="text-center"><label for="validationCustom01">Заголовок</label></div>
        <input type="text"
               class="form-control ${(postTitleError??)?string('is-invalid', '')}"
               id="validationCustom01"
               name="title"
               placeholder="Заголовок"
               value="<#if post??>${post.title}</#if>"/>
        <#if postTitleError??>
            <#list postTitleError as err>
                <div class="invalid-feedback text-center">${err}</div>
            </#list>
        </#if>
    </div>
</#macro>

<#macro input_description>
    <div class="form-group">
        <div class="text-center"><label for="validationCustom02">Описание</label></div>
        <input type="text"
               class="form-control ${(postDescriptionError??)?string('is-invalid', '')}"
               id="validationCustom02"
               name="description"
               placeholder="Описание"
               value="<#if post??>${post.description}</#if>"/>
        <#if postDescriptionError??>
            <#list postDescriptionError as err>
                <div class="invalid-feedback text-center">${err}</div>
            </#list>
        </#if>
    </div>
</#macro>

<#macro text_area>
    <div class="form-group">
        <div class="text-center"><label for="editor">Статья</label></div>
        <textarea class="form-control ${(postTextError??)?string('is-invalid', '')}"
                  id="editor"
                  name="text">
            <#if post??>${post.text}</#if>
        </textarea>
        <#if postTextError??>
            <#list postTextError as err>
                <div class="invalid-feedback text-center">${err}</div>
            </#list>
        </#if>
    </div>
</#macro>
