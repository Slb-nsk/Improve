<!doctype html>
<html>
    <head>
    </head>
    <body>

    <h1 align=center>Список доступных акций</h1>

        <#if searchResult?size gt 0>
            <div>
                <ul>
                    <#list searchResult as item>
                        <li>Акция <a href="/action/#{item.action_id}">${item.actionName}</a>,
                        кол-во подарков - ${item.actionGiftsNumber}</li>
                    </#list>
                </ul>
            </div>
        </#if>


    </body>
</html>
