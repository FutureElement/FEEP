<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<feep>
    <div class="page404-body">
        <div class="page404-container">
            <img src="${contextPath}/Resource/img/large/404.png" style="margin-bottom: 10px;width: 300px;"/>
            <img src="${contextPath}/Resource/img/middle/404_msg.png" style="width: 300px;"/>

            <div style="min-height: 50px;">
            </div>
        </div>
        <div class="page404-cloud"></div>
    </div>
    <script type="text/javascript">
        function onSizeChange(w, h) {
            $(".page404-cloud").width($(".page404-body").width());
        }
    </script>
</feep>