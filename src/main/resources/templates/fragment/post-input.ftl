<#-- @ftlvariable name="post" type="com.junior.blog.model.Post" -->
<#-- @ftlvariable name="categories" type="java.util.Collection<com.junior.blog.model.Category>" -->

<#macro input_new_category>

    <form class="text-center needs-validation" novalidate action="/category" method="post">

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        <div class="input-group mb-3">
            <input type="text" class="form-control"
                   placeholder="Новая категория"
                   aria-label="Recipient's username"
                   aria-describedby="basic-addon2"
                   id="validationCustom04"
                   name="title"
                   required>

            <div class="input-group-append">
                <button class="btn btn-secondary" type="submit">Создать категорию</button>
            </div>

            <div class="invalid-feedback text-center">Заполните категорию</div>
        </div>
    </form>
</#macro>

<#macro select_category>
    <div class="form-group">
        <select class="custom-select ${(categoryError??)?string('is-invalid', '')}" name="category" <#--required-->>
            <option value="">Категория</option>
                    <#list categories as category>
                        <option value="${category.id}">${category.title}</option>
                    </#list>
        </select>
        <#if categoryError??>
            <div class="invalid-feedback text-center">${categoryError}</div>
        </#if>
    </div>
</#macro>

<#macro input_title>
    <div class="form-group">
        <div class="text-center"><label for="validationCustom01">Заголовок</label></div>
        <input type="text"
               class="form-control ${(titleError??)?string('is-invalid', '')}"
               id="validationCustom01"
               name="title"
               placeholder="Заголовок"
               value="<#if post??>${post.title}</#if>"/>
        <#if titleError??>
            <div class="invalid-feedback text-center">${titleError}</div>
        </#if>
    </div>
</#macro>

<#macro input_description>
    <div class="form-group">
        <div class="text-center"><label for="validationCustom02">Описание</label></div>
        <input type="text"
               class="form-control ${(descriptionError??)?string('is-invalid', '')}"
               id="validationCustom02"
               name="description"
               placeholder="Описание"
               value="<#if post??>${post.description}</#if>"/>
        <#if descriptionError??>
            <div class="invalid-feedback text-center">${descriptionError}</div>
        </#if>
    </div>
</#macro>

<#macro text_area>
    <div class="form-group">
        <div class="text-center"><label for="editor">Статья</label></div>
        <textarea class="form-control ${(textError??)?string('is-invalid', '')}"
                  id="editor"
                  name="text">
            <#if post??>${post.text}</#if>
        </textarea>
        <#if textError??>
            <div class="invalid-feedback text-center">${textError}</div>
        </#if>
    </div>
</#macro>