<#include "base.ftl">

<#macro page_content>
        <div><table><tr><td>
        <form action="/search/${action_id}" method="POST">
                <input type="text" name="userText" value="${userText}">
                <input type="submit" value="Фильтровать">
                </form></td><td>
                <form action="/searchAll/${action_id}">
                <input type="hidden" name="all">
                <input type="submit" value="Показать все">
              </form></td></tr></table>
         </div>

                <#if searchResult.data?size gt 0>
                    <div>
                        <ul>
                            <#list searchResult.data as item>
                                <li><table><tr><td>${item.giftName}</td> <td><form action="/gift/${action_id}/${item.gift_id}"><input type="submit" value="Забрать"></form></td><tr></table>
                                </li>
                            </#list>
                        </ul>
                    </div>
                </#if>

</#macro>

<@display_page/>
