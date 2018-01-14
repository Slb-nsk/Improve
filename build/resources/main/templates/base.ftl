<#setting url_escaping_charset='UTF-8'>

<#macro page_content>
</#macro>

<#macro display_page>
<!doctype html>
<html>
    <head>
    </head>
    <body>

    <p>Акция "${action.actionName}"
    </p>

    <p>Подарков по акции осталось: ${action.actionGiftsNumber}.
    </p>

<h2>Выберите себе подарок:</h2>



        <div class="container">
            <@page_content/>
        </div>

    </body>
</html>
</#macro>


